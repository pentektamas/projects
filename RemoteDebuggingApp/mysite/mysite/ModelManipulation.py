import numpy as np
from keras.datasets import imdb
from keras.preprocessing.sequence import pad_sequences
from keras import Sequential, layers

# resolve allow_pickle error within load_data method
# problem in numpy module
class ModelManipulation:
    def __init__(self):
        # load and split data into train and test
        (train_data, train_labels), (test_data, test_labels) = imdb.load_data(num_words=10000)
        self.test_data = test_data
        self.test_labels = test_labels
        self.train_data = train_data
        self.train_labels = train_labels

    def prepare_data(self):
        # get the dictionary where entries are words mapped to integers
        word_index = imdb.get_word_index()
        word_index = {k:(v+3) for k, v in word_index.items()}  # reserve first 4 indices for unknown words like spaces or words not from imdb data set
        word_index["<PAD>"] = 0
        word_index["<START>"] = 1
        word_index["<UNK>"] = 2
        word_index["<UNUSED>"] = 3
        # allow maximum of 250 words in a review
        # if the number of words < 250 then add padding tags
        self.train_data = pad_sequences(self.train_data, value=word_index["<PAD>"], padding="post", maxlen=250)
        self.test_data = pad_sequences(self.test_data, value=word_index["<PAD>"], padding="post", maxlen=250)
        return word_index

    def create_model(self):
        # create the model
        model = Sequential()
        # 16 dimensions for each word vector from those 10000 words
        # group similar words
        model.add(layers.Embedding(10000, 16))
        # scale down the entry dimension of the previous layer by averaging words vectors
        model.add(layers.GlobalAveragePooling1D())
        # add complexity by activation function rectified liniar unit
        model.add(layers.Dense(16, activation="relu"))
        # map to [0,1] & use binary classification
        model.add(layers.Dense(1, activation="sigmoid"))
        # uncomment for model summary
        # model.summary()
        return model

    def compile_model(self, model):
        # binary classification 0 or 1
        model.compile(optimizer="adam", loss="binary_crossentropy", metrics=["accuracy"])

    def fit_model(self, model):
        # get validation data
        x_val = self.train_data[:10000]
        y_val = self.train_labels[:10000]

        # get train data
        x_train = self.train_data[10000:]
        y_train = self.train_labels[10000:]
        # epochs set the number of times the data is passing through the network
        fit_model = model.fit(x_train, y_train, epochs=40, batch_size=512, validation_data=(x_val, y_val), verbose=1)
        return fit_model
        # save it for future use

    def evaluate(self, model):
        results = model.evaluate(self.test_data, self.test_labels)
        return results

    def save_model(self, model, name):
        model.save(name)


'''
print(results)
# test and predict a piece of data
test_review = test_data[0]
predict = model.predict([test_review])
print(decode_review(test_review))
print("prediction = ", str(predict[0]))
print("actual = ", str(test_labels[0]))
'''