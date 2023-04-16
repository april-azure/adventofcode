import collections

lines = open("test.txt").readlines()
matrix = [list(line.strip()) for line in lines]
# print(matrix)
row, col = len(matrix), len(matrix[0])

start, end = (0, 0), (0, 0)
for i in range(row):
    for j in range(col):
        if matrix[i][j] == 'S':
            start = (i, j)
        if matrix[i][j] == 'E':
            end = (i, j)

matrix[end[0]][end[1]] = 'z'
matrix[start[0]][start[1]] = 'a'

# bfs to find the shortest path


def adj(i, j):
    for x, y in [[i, j+1], [i, j-1], [i+1, j], [i-1, j]]:
        if 0 <= x < row and 0 <= y < col:
            yield (x, y)


def bfs():
    q = collections.deque()
    q.append((start, 0))
    visited = set(start)

    while q:
        (i, j), step = q.popleft()
        # print(i, j)
        if (i, j) == end:
            print("total steps needed ", step)
            return

        for x, y in adj(i, j):
            if ord(matrix[x][y]) - ord(matrix[i][j]) <= 1 and (x, y) not in visited:
                q.append(((x, y), step+1))
                visited.add((x, y))

bfs()
