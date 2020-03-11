from keras.preprocessing.sequence import pad_sequences


# get human readable text from all of the indices
def decode_review(word_index, text):
    reverse_object_index = dict([(value, key) for (key, value) in word_index.items()])
    return " ".join([reverse_object_index.get(i, "?") for i in text])


# encode human readable review in a vector of integers understood by the network
def encode_review(s, word_index):
    encoded = [1]
    for word in s:
        if word.lower() in word_index:
            encoded.append(word_index[word.lower()])
        else:
            encoded.append(2) #  unknown word
    return encoded


def make_statistics(word_index, model):
    npleased = 0
    nunpleased = 0
    with open("../core/templates/reviews.txt", encoding="utf-8") as f:
        for line in f.readlines():  # each line represents a review
            nline = line.replace(",", "").replace(".", "").replace(":", "").replace(", ", "").replace(")", "").strip().split(" ")
            encode = encode_review(nline, word_index)
            # entry data must have 250 indices
            encode = pad_sequences([encode], value=word_index["<PAD>"], padding="post", maxlen=250)
            predict = model.predict(encode)
            if(predict[0] > 0.5):
                npleased = npleased + 1
                print("Client found this helpful.")
            else:
                nunpleased = nunpleased + 1
                print("Client was not pleased...")
    return (npleased, nunpleased)