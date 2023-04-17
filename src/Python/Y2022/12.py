import collections

lines = open("test.txt").readlines()
matrix = [list(line.strip()) for line in lines]
# print(matrix)
row, col = len(matrix), len(matrix[0])

end = (0, 0)
for i in range(row):
    for j in range(col):
        if matrix[i][j] == 'S':
            matrix[i][j] = 'a'
        if matrix[i][j] == 'E':
            end = (i, j)
            matrix[i][j] = 'z'

# bfs to find the shortest path


def adj(i, j):
    for x, y in [[i, j+1], [i, j-1], [i+1, j], [i-1, j]]:
        if 0 <= x < row and 0 <= y < col:
            yield (x, y)


def bfs():
    q = collections.deque()
    q.append((end, 0))
    visited = set(end)

    while q:
        (i, j), step = q.popleft()
        # print(i, j)
        if matrix[i][j] == 'a':
            print("total steps needed ", step)
            return

        for x, y in adj(i, j):
            if ord(matrix[i][j]) - ord(matrix[x][y]) <= 1 and (x, y) not in visited:
                q.append(((x, y), step+1))
                visited.add((x, y))

bfs()
