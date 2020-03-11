from ModelManipulation import *
from TextManipulation import *
import matplotlib.pyplot as plt
import keras
import time


def create_model():
    model_man = ModelManipulation()
    word_index = model_man.prepare_data()
    model = model_man.create_model()
    model_man.compile_model(model)
    model_man.fit_model(model)
    model_man.save_model(model, "SAModel.h5")
    return (word_index, model)


def load_model():
    model_man = ModelManipulation()
    word_index = model_man.prepare_data()
    model = keras.models.load_model("SAModel.h5")
    return (word_index, model)


def main():
    model_is_created = False
    word_index = None
    line = ""
    try:
        with open('flag.txt', 'r') as file:
           line = file.readline(10)
    except FileNotFoundError:
        file = open('flag.txt', 'w+')
        file.close()
    if line == "True":
        model_is_created = True
    if not model_is_created:
        file = open('flag.txt', 'w')
        (word_index, model) = create_model()
        file.write("True")
        file.close()
    else:
        (word_index, model) = load_model()
    (x, y) = make_statistics(word_index, model)
    # create plot
    objects = ('pleased', 'unpleased')
    # 2 columns
    y_pos = np.arange(len(objects))
    # y values
    performance = [x, y]
    plt.bar(y_pos, performance, align='center', alpha=0.5)
    plt.xticks(y_pos, objects)
    plt.ylabel('#customers')
    plt.title('Customers\' reviews')
    plt.savefig('..\core\static\images\plot.png')
    # plt.show()



if __name__ == "__main__":
    while True:
        main()
        time.sleep(30)