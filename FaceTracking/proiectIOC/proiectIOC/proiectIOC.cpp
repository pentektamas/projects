#include <opencv2/opencv.hpp>
#include <opencv2/tracking.hpp>
#include <opencv2/core/ocl.hpp>
#include <opencv2/objdetect/objdetect.hpp>

using namespace cv;
using namespace std;

CascadeClassifier face_cascade;

struct bBox{
    Rect2d boundingBox;
    int boxID;
};

struct myTracker {
    Ptr<Tracker> tracker;
    int trackerID;
};

std::vector<bBox> detect_face(const string& window_name, Mat frame) {

    int minFaceSize = 150;
    std::vector<Rect> faces;
    std::vector<bBox> detectedFaces;
    Mat frame_gray;
    cvtColor(frame, frame_gray, CV_BGR2GRAY);
    equalizeHist(frame_gray, frame_gray);
    face_cascade.detectMultiScale(frame_gray, faces, 1.1, 2, 0 | CV_HAAR_SCALE_IMAGE, Size(minFaceSize, minFaceSize));
    if (!faces.empty()) {
        for (int i = 0; i < faces.size(); i++)
        {
            bBox box;
            box.boundingBox = Rect2d(faces[i].x, faces[i].y, faces[i].width, faces[i].height);
            box.boxID = i + 1;
            detectedFaces.push_back(box);
            rectangle(frame, faces[i], Scalar(0, 255, 0), 2, 8, 0);
        }
    }
    else {
        bBox wrongBox;
        wrongBox.boundingBox = Rect2d();
        wrongBox.boxID = -1;
        detectedFaces.push_back(wrongBox);
    }
    return detectedFaces;
}

std::vector<bBox> detect_face_index(const string& window_name, Mat frame, bool applyIf,std::vector<bBox> currentBBoxes) {

    int minFaceSize = 150;
    std::vector<Rect> faces;
    std::vector<bBox> detectedFaces;
    Mat frame_gray;
    cvtColor(frame, frame_gray, CV_BGR2GRAY);
    equalizeHist(frame_gray, frame_gray);
    face_cascade.detectMultiScale(frame_gray, faces, 1.1, 2, 0 | CV_HAAR_SCALE_IMAGE, Size(minFaceSize, minFaceSize));
    bool condition = applyIf || (currentBBoxes.size() == faces.size());
    if (!faces.empty() && condition) {
            for (int i = 0; i < currentBBoxes.size(); i++) {
                int minimDist = 10000;
                int index = -1;
                for (int j = 0; j < faces.size(); j++) {
                    int diffX = abs(currentBBoxes[i].boundingBox.x - faces[j].x);
                    int diffY = abs(currentBBoxes[i].boundingBox.y - faces[j].y);
                    int eucDist = sqrt(diffX * diffX + diffY * diffY);
                    if (eucDist < minimDist) {
                        minimDist = eucDist;
                        index = j;
                    }
                }
                if (minimDist != 10000 && index != -1) {
                    bBox box;
                    box.boundingBox = faces.at(index);
                    box.boxID = currentBBoxes.at(i).boxID;
                    detectedFaces.push_back(box);
                }
            }
    }
    else {
        bBox wrongBox;
        wrongBox.boundingBox = Rect2d();
        wrongBox.boxID = -1;
        detectedFaces.push_back(wrongBox);
    }
    return detectedFaces;
}

int getEuclideanDistance(bBox box1, bBox box2) {
    int diffX = abs(box1.boundingBox.x - box2.boundingBox.x);
    int diffY = abs(box1.boundingBox.y - box2.boundingBox.y);
    return sqrt(diffX * diffX + diffY * diffY);
}

void process_video() {

    string trackerTypes[8] = { "BOOSTING", "MIL", "MOSSE", "CSRT" };
    string trackerType = trackerTypes[2];
    std::vector<myTracker> trackers;

    VideoCapture video("vid3.mp4");
    //VideoCapture video("vid4.mp4");
    //VideoCapture video("vid5.mp4");
    //VideoCapture video("vid6.mp4");
    //VideoCapture video("vid7.mp4");
    //VideoCapture video("vid8.mp4");

    if (!video.isOpened())
    {
        cout << "Could not read video file" << endl;
        return;
    }

    int frameNr = 0;
    std::vector<bBox> bBoxes;
    Mat frame;

    while (video.read(frame))
    {
        
        frameNr++;
        double t = (double)getTickCount();

        if (frameNr > 1) {

            for (int i = 0; i < trackers.size(); i++) {
                bool ok = trackers[i].tracker->update(frame, bBoxes[i].boundingBox);
                if (ok)
                {
                    rectangle(frame, bBoxes[i].boundingBox, Scalar(0, 255, 0), 2, 8, 0);
                    String id = "ID: " + to_string(i + 1);
                    putText(frame, id, Point(bBoxes[i].boundingBox.x + 5, bBoxes[i].boundingBox.y + 20), FONT_HERSHEY_DUPLEX, 0.75, Scalar(92, 0, 230), 2);
                }
                else
                {
                    std::vector<bBox> bBoxes2;
                    bBoxes2 = detect_face_index("Tracking", frame, true, bBoxes);
                    if (bBoxes2[0].boxID != -1 && bBoxes2[0].boundingBox != Rect2d(0, 0, 0, 0)) {
                    for (int j = 0; j < bBoxes2.size(); j++) {
                            if (bBoxes2[j].boxID == trackers[i].trackerID) {
                                trackers[i].tracker = TrackerMOSSE::create();
                                trackers[i].tracker->init(frame, bBoxes2[j].boundingBox);
                            }
                        }
                    }
                }
                
                if (frameNr % 75 == 0) {
                    std::vector<bBox> bBoxes2 = detect_face_index("Tracking", frame, false, bBoxes);           
                    if (bBoxes.size() == bBoxes2.size()) {
                        if (bBoxes2[0].boxID != -1 && bBoxes2[0].boundingBox != Rect2d(0, 0, 0, 0)) {
                        for (int j = 0; j < bBoxes.size(); j++) {
                            for (int k = 0; k < bBoxes2.size(); k++) {
                                if (bBoxes[j].boxID == bBoxes2[k].boxID) {
                                    int eucDist = getEuclideanDistance(bBoxes[k], bBoxes2[k]);
                                    if (eucDist > 40) {
                                        for (int i = 0; i < trackers.size(); i++) {
                                            if (trackers[i].trackerID == bBoxes2[k].boxID) {
                                                trackers[i].tracker = TrackerMOSSE::create();
                                                trackers[i].tracker->init(frame, bBoxes2[k].boundingBox);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    }
                }
            }
        }
        else {
            bBoxes = detect_face("Tracking", frame);
            if (bBoxes[0].boxID != -1 && bBoxes[0].boundingBox != Rect2d(0, 0, 0, 0)) {
            for (int i = 0; i < bBoxes.size(); i++) {
                    myTracker tracker;
                    tracker.tracker = TrackerMOSSE::create();
                    tracker.tracker->init(frame, bBoxes[i].boundingBox);
                    tracker.trackerID = bBoxes[i].boxID;
                    trackers.push_back(tracker);
                }
            }
        }

        t = ((double)getTickCount() - t) / getTickFrequency();
        printf("Execution time = %.3f [ms]\n", t * 1000);
        
        imshow("Tracking", frame);
        int k = waitKey(30);
        if (k == 27)
            break;
    }

    video.release();
}

void face_tracking() {
    String face_cascade_name = "haarcascade_frontalface_alt.xml";
    if (!face_cascade.load(face_cascade_name)) {
        printf("Error loading face cascades !\n");
        return;
    }
    process_video();  
}

int main(int argc, char** argv){
    face_tracking();
    return 0;
}