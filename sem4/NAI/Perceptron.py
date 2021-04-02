from random import random

"""
Record class which hold data in format [[list of inputs],label]
"""


class Record:
    labels = {}
    d = -1
    last_label = ""

    def __init__(self, values, label):
        """
        maps labels to 1 and -1
        :param values: inputs
        :param label:
        """

        self.values = [float(x) for x in values]
        label = label.replace("\n", "")
        if label != Record.last_label:
            Record.d = - Record.d
            Record.last_label = label

        if label not in Record.labels and label:
            Record.labels[label] = Record.d
            Record.last_label = label

        self.label = Record.d

    def __str__(self):
        return f"Values: {self.values}, Label: {self.label}"


def map_to_record(line):
    """
    :param line: line read from file
    :return: object of type Record
    """
    record = line.split(",")
    n = len(record)

    return Record(record[0:n - 1], record[-1])


def read_data(filename):
    """
    :param filename: name of file with desired data
    :return: list of Record objects
    """
    with open(filename) as f:
        entries = f.readlines()
    data = [map_to_record(x) for x in entries]
    return data


def calculate_output(weights, input_x, threshold):
    """
    :param weights: list of weights
    :param input_x: list of x inputs
    :param threshold:
    :return: output 1 or -1
    """
    dot_product = 0
    for j in range(len(weights)):
        dot_product += weights[j] * input_x[j]

    return 1 if dot_product >= threshold else -1


def train(weights, data, learning_param, threshold):
    """
    :param weights: list of weigths
    :param data: list of Record objects
    :param learning_param:
    :param threshold: 
    :return: 
    """
    for record in data:
        d = record.label
        y = calculate_output(weights, record.values, threshold)

        while d != y:
            delta = d - y
            for i in range(len(weights)):
                weights[i] += delta * learning_param * record.values[i]
            threshold -= delta * learning_param

            y = calculate_output(weights, record.values, threshold)

    return threshold


def test(weights, data, threshold):
    """
    :param weights: list of weights
    :param data: list of Record objects
    :param threshold:
    :return: accuracy of the model
    """
    counter = 0
    for record in data:
        y = calculate_output(weights, record.values, threshold)
        d = record.label
        if y == d:
            counter += 1
    return counter / len(data)


ex_test = "data/example1_test.txt"
ex_train = "data/example2_train.txt"
iris_test = "data/iris_test.txt"
iris_training = "data/iris_training.txt"

train_data = read_data(iris_training)
test_data = read_data(iris_test)
weights = [random() for _ in range(2)]

learning_param = 0.2
threshold = random()
print(f"Accuracy before training: {test(weights, test_data, threshold)}")
threshold = train(weights, train_data, learning_param, threshold)
print(f"Accuracy after training: {test(weights, test_data, threshold)}")
