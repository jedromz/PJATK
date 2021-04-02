import math
import matplotlib.pyplot as plt


def isfloat(value):
    try:
        float(value)
        return True
    except ValueError:
        return False


def get_data(path_to_file):
    file = open(path_to_file, 'r')
    result_data = []
    for line in file.readlines():
        entry = line.split(",")
        result_data.append([[x for x in entry if isfloat(x)], entry[-1].replace("\n", "")])
    file.close()
    return result_data


def calculate_distance(size, v1, v2):
    result = 0
    for cord in range(0, size):
        result += math.pow(float(v1[cord]) - float(v2[cord]), 2)
    return math.sqrt(result)


def get_labels(k, training_data, testing_data, vector_size):
    distances = []
    results = {}
    labels = []
    for test_entry in testing_data:
        for train_entry in training_data:
            distances.append([[calculate_distance(vector_size, test_entry[0], train_entry[0])], train_entry[1]])
        distances.sort(key=lambda x: x[0], reverse=True)
        for i in range(0, k):
            if len(distances) > 0:
                result = distances.pop()[1]
                if result in results:
                    results[result] += 1
                else:
                    results[result] = 1

        max_occur = max(results.values())
        label_list = [l for l in results.keys() if results.get(l) == max_occur]
        label = ""
        if len(label_list) == 1:
            label = label_list[0]
        else:

            for label_class in label_list:
                label += label_class + "/"

        labels.append(label)
        distances = []
        results = {}
    return labels


def export_results(training_file, testing_file, results_file, k):
    if k <= 0:
        raise Exception("Wrong k")
    # returns average
    training_data = get_data(training_file)
    testing_data = get_data(testing_file)
    counter = 0
    f = open(results_file, 'w')

    vector_size = len(training_data[0][0])
    labels = get_labels(k, training_data, testing_data, vector_size)
    results = []
    for i in range(0, len(testing_data)):
        if labels[i] == testing_data[i][1]:
            counter += 1
        results.append([testing_data[i][0], labels[i]])
        f.writelines([str(testing_data[i][0]), labels[i]])
        f.write("\n")
    f.write(f"\nAccuracy: {(counter / len(testing_data))} for k:{k}")
    f.close()
    return counter / len(testing_data)


def classify(vector, train_file, k):
    label = get_labels(k, get_data(train_file), [[vector]], 4)
    print(label)


# VARIABLES
train_file = 'train.txt'
test_file = 'test.txt'
results_file = 'results.txt'
k = 10

export_results(train_file, test_file, results_file, k)
classify([5.2, 4.1, 1.5, 0.1], train_file, 10)

"""

Plotting accuracy vsrsus K
s
x_axis = [x for x in range(1, 101, 10)]
y_axis = [export_results(train_file, test_file, results_file, k) for k in x_axis]
plt.xlabel('K')
plt.ylabel('accuracy')
plt.plot(x_axis, y_axis)
plt.show()

"""
