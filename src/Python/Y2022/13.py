lines = open("test.txt").readlines()
lines = [line.strip() for line in lines]


def compare(a, b):
    if isinstance(a, int) and isinstance(b, int):
        return a-b
    if isinstance(a, int):
        a = [a]
    if isinstance(b, int):
        b = [b]
    for i in range(min(len(a), len(b))):
        tmp = compare(a[i], b[i])
        if tmp != 0:
            return tmp

    return len(a) - len(b)

sum = 0
for i in range(0, len(lines), 3):
    first, second = eval(lines[i]), eval(lines[i+1])
    res = compare(first, second) <= 0
    if res:
        sum += (i // 3 + 1)
print(sum)
