from functools import cmp_to_key

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

list = []
for line in lines:
    if line:
        list.append(eval(line.strip()))
target = [[[2]], [[6]]]
list.extend(target)

list.sort(key=cmp_to_key(compare))

res = 1
for idx, item in enumerate(list):
    if item in target:
        res *= (idx+1)
print(res)
