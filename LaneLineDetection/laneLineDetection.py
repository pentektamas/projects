import cv2 as cv
import numpy as np


def get_roi(image, pixels):
    dst = np.zeros_like(image)
    cv.fillPoly(dst, pixels, 255)
    dst_image = cv.bitwise_and(image, dst)
    return dst_image


def draw_lines(img, lines):
    copy = np.copy(img)
    dst = np.zeros((img.shape[0], img.shape[1], 3), dtype=np.uint8)
    if lines is not None:
        for line in lines:
            for x1, y1, x2, y2 in line:
                cv.line(dst, (x1, y1), (x2, y2), (255, 0, 0), thickness=1)

    copy = cv.add(copy, dst)
    return copy

def drawPoligon(img, polygon):

    cv.fillPoly(img, np.array([polygon], np.int32), (105, 232, 77))
    return img

def getLeftRoadLine(img, source):
    height, width, channels = img.shape
    firstPosX = 0
    firstPosY = 0
    firstCheck = False
    centerX = int((width / 2 - 150 + width / 2 + 200) / 2)

    for i in range(height - 1, int(2 * height / 3), -1):
        if firstCheck is False:
            for j in range(int(width / 8), centerX):
                if firstCheck is False:
                    if np.any(img[i, j] == (255, 0, 0)):
                        firstPosX, firstPosY = i, j
                        firstCheck = True
                else:
                    break
        else:
            break

    secondPosX = 0
    secondPosY = 0
    cnt = 10
    for i in range(firstPosX, int(2 * height / 3), -1):
        if cnt != 0:
            cnt = 0
            for j in range(firstPosY, centerX):
                if np.any(img[i, j] == (255, 0, 0)):
                    cnt = cnt + 1
                    secondPosX, secondPosY = i, j
        else:
            break

    thirdPosX = 0
    thirdPosY = 0
    thirdCheck = False
    for i in range(secondPosX - 1, int(2 * height / 3), -1):
        if thirdCheck is False:
            for j in range(secondPosY + 1, centerX):
                if thirdCheck is False:
                    if np.any(img[i, j] == (255, 0, 0)):
                        thirdPosX, thirdPosY = i, j
                        thirdCheck = True
                else:
                    break
        else:
            break

    x01 = int(2 * height / 3)
    x02 = height - 1

    if thirdPosX != 0 and thirdPosY != 0:
        if thirdPosX != secondPosX and thirdPosY != secondPosY:
            cv.line(img, (secondPosY, secondPosX), (thirdPosY, thirdPosX), (255, 0, 0), thickness=1)
            m = (thirdPosY - secondPosY) / (thirdPosX - secondPosX)
            y01 = int(-m * (secondPosX - x01) + secondPosY)
            y02 = int(-m * (secondPosX - x02) + secondPosY)
            cv.line(source, (y02, x02), (y01, x01), (0, 0, 255), thickness=1)
    else:
        if firstPosX != secondPosX and firstPosY != secondPosY:
            cv.line(img, (secondPosY, secondPosX), (firstPosY, firstPosX), (255, 0, 0), thickness=1)
            m = (secondPosY - firstPosY) / (secondPosX - firstPosX)
            y01 = int(-m * (secondPosX - x01) + secondPosY)
            y02 = int(-m * (secondPosX - x02) + secondPosY)
            cv.line(source, (y02, x02), (y01, x01), (0, 0, 255), thickness=1)

    return source


def getRightRoadLine(img, source):
    height, width, channels = img.shape
    firstPosX = 0
    firstPosY = 0
    firstCheck = False
    centerX = int((width / 2 - 150 + width / 2 + 200) / 2)

    for i in range(height - 1, int(2 * height / 3), -1):
        if firstCheck is False:
            for j in range(width - 50, centerX, -1):
                if firstCheck is False:
                    if np.any(img[i, j] == (255, 0, 0)):
                        firstPosX, firstPosY = i, j
                        firstCheck = True
                else:
                    break
        else:
            break

    secondPosX = 0
    secondPosY = 0
    secondCheck = False

    for i in range(int(2 * height / 3), height - 1):
        if secondCheck is False:
            for j in range(width - 50, centerX, -1):
                if secondCheck is False:
                    if np.any(img[i, j] == (255, 0, 0)):
                        img[i, j] = (0, 0, 255)
                        secondPosX, secondPosY = i, j
                        secondCheck = True
                else:
                    break
        else:
            break

    if firstPosX != secondPosX and firstPosY != secondPosY:
        m = (secondPosY - firstPosY) / (secondPosX - firstPosX)
        x01 = int(2 * height / 3)
        x02 = height - 1
        y01 = int(-m * (secondPosX - x01) + secondPosY)
        y02 = int(-m * (secondPosX - x02) + secondPosY)
        cv.line(source, (y02, x02), (y01, x01), (0, 0, 255), thickness=1)

    return source


def workWithROI(image):
    height, width, channel = image.shape
    pixels = [(width / 8, height), (width / 2 - 150, 2 * height / 3), (width / 2 + 200, 2 * height / 3),
              (width - 50, height)]

    image = cv.cvtColor(image, cv.COLOR_BGR2GRAY)
    dst = np.zeros_like(image)
    cv.fillPoly(dst, np.array([pixels], np.int32), 255)
    return dst


def getRoadArea(img, dst):
    height, width, channel = img.shape
    lowerWidth = int(width / 8)
    upperWidth = width - 50
    upperStartY = 0
    upperEndY = 0
    lowerStartY = 0
    lowerEndY = 0

    lowerHeight = int(2 * height / 3)
    upperHeight = height - 1
    roi = workWithROI(dst)
    cnt1 = 0

    for j in range(lowerWidth, upperWidth):
        if roi[upperHeight, j] == 255:
            if cnt1 == 0 and np.any(img[upperHeight, j] == (0, 0, 255)):
                upperStartY = j
                cnt1 = 1
            if cnt1 == 1 and np.any(img[upperHeight, j] == (0, 0, 255)):
                upperEndY = j

    cnt2 = 0
    for j in range(lowerWidth, upperWidth):
        if roi[lowerHeight, j] == 255:
            if cnt2 == 0 and np.any(img[lowerHeight, j] == (0, 0, 255)):
                lowerStartY = j
                cnt2 = 1
            if cnt2 == 1 and np.any(img[lowerHeight, j] == (0, 0, 255)):
                lowerEndY = j

    polygonPoints = [(upperStartY, upperHeight), (lowerStartY, lowerHeight),
                     (lowerEndY, lowerHeight), (upperEndY, upperHeight)]
    cv.fillPoly(dst, np.array([polygonPoints], np.int32), (105, 232, 77))

    return dst, polygonPoints


def processingPipeline(source):
    if source is not None:
        height, width, channel = source.shape
        dst = source.copy()
        alpha = 1.4  # Contrast control (1.0-3.0)
        beta = 0  # Brightness control (0-100)
        adjustedContrast = cv.convertScaleAbs(source, alpha=alpha, beta=beta)
        #cv.imshow("Source", source)
        #cv.imshow("Adjusted contrast" , adjustedContrast)
        #cv.waitKey(0)
        hsv = cv.cvtColor(adjustedContrast, cv.COLOR_BGR2HSV)
        h, s, v = cv.split(hsv)
        roi_pixels = [(width / 8, height), (width / 2 - 150, 2 * height / 3), (width / 2 + 200, 2 * height / 3),
                      (width - 50, height)]
        canny_result = cv.Canny(v, 70, 100)
        #cv.imshow("Canny", canny_result)
        #cv.waitKey(0)
        roi_image = get_roi(canny_result, np.array([roi_pixels], np.int32))
        #cv.imshow("ROI",roi_image)
        #cv.waitKey(0)
        houghLines = cv.HoughLinesP(roi_image, rho=8, theta=np.pi / 60, threshold=150, lines=np.array([]),
                                    minLineLength=50, maxLineGap=50)
        imgHoughLines = draw_lines(source, houghLines)
        #cv.imshow("HoughLines",imgHoughLines)
        #cv.waitKey(0)
        imgLeftLines = getLeftRoadLine(imgHoughLines, source)
        #cv.imshow("Left lines", imgLeftLines)
        #cv.waitKey(0)
        imgFullLines = getRightRoadLine(imgHoughLines, imgLeftLines)
        #cv.imshow("Full lines", imgFullLines)
        #cv.waitKey(0)
        imgRoadArea, polygonPoints = getRoadArea(imgFullLines, dst)
        #cv.imshow("RoadArea", imgRoadArea)
        #cv.waitKey(0)

        return imgRoadArea, polygonPoints


def videoTest(videoName):
    capture = cv.VideoCapture(videoName)
    i = 0
    while capture.isOpened():
        ret, read_frame = capture.read()
        if ret is False or cv.waitKey(1) & 0xFF == ord('q'):
            break
        if i % 2 == 0:
            read_frame, polygonPoints = processingPipeline(read_frame)
        else:
            drawPoligon(read_frame, polygonPoints)
        cv.imshow('Resulting video', read_frame)
        i = i + 1

    capture.release()
    cv.destroyAllWindows()

def photoTest(imgName):
    img = cv.imread(imgName, 1)
    original = img.copy()
    photo, polygon = processingPipeline(img)
    cv.imshow('Original image', original)
    cv.imshow('Resulting image', photo)
    cv.waitKey(0)

if __name__ == "__main__":

    #videoTest('testvideo1.mp4')
    #videoTest('testvideo2.mp4')
    #videoTest('testvideo3.mp4')
    photoTest('testphoto1.jpg')
    #photoTest('testphoto2.jpg')
    #photoTest('testphoto3.jpg')