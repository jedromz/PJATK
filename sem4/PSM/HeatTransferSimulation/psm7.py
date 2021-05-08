import matplotlib.pyplot as plt
import numpy as np
import seaborn as sns

tempTop = 200
tempBottom = 150
tempLeft = 100
tempRight = 50
plate_width = 40
plate_height = 40


def get_b(size):
    """

    :param size:
    :return: returns b_vector
    """
    b = np.zeros(size)
    for i in range(len(b)):
        result = 0
        x = i % plate_width + 1
        y = i // plate_width + 1
        if x == 1:
            result += -tempLeft
        if x == plate_width:
            result += -tempRight
        if y == 1:
            result += -tempTop
        if y == plate_height:
            result += -tempBottom
        b[i] = result

    return b


def get_x():
    return inverse_matrix.dot(b).reshape((plate_height, plate_width))


def fill_matrix(matrix):
    """
    :param matrix:
    :return: fills matrix with calculated data
    """
    for i in range(len(matrix)):
        for j in range(len(matrix[i])):
            cx = i % plate_width + 1
            cy = i // plate_width + 1
            dx = j % plate_width + 1
            dy = j // plate_width + 1

            delX = abs(dx - cx)
            delY = abs(dy - cy)
            if delX == 1 and cy == dy or delY == 1 and cx == dx:
                matrix[i][j] = 1

    np.fill_diagonal(matrix, -4)


initial_matrix = np.zeros((plate_height * plate_width, plate_height * plate_width))
fill_matrix(initial_matrix)

inverse_matrix = np.linalg.inv(initial_matrix)

b = get_b(plate_height * plate_width)

x = get_x()

ax = sns.heatmap(x, linewidth=0.5)
plt.show()
