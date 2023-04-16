def solve():
    vals = []
    res1 = 0
    lines = open('test.txt', 'r').readlines()
    cycle = 1
    val = 1

    def tick():
        nonlocal res1, vals
        if cycle in [20, 60, 100, 140, 180, 220]:
            print(cycle, val)
            res1 += (cycle * val)
        vals.append(val)

    def noop():
        nonlocal cycle
        tick()
        cycle += 1
        pass

    def addx(v):
        # global cycle
        nonlocal cycle, val
        tick()
        cycle += 1
        tick()
        cycle += 1
        val += v
        pass

    for line in lines:
        if line.startswith("noop"):
            noop()
        elif line.startswith("addx"):
            v = int(line[5:])
            addx(v)

    # part 1
    print("part1 res: ", res1)

    # part 2
    for i in range(len(vals)):
        pos = i % 40
        if vals[i] in [pos-1, pos, pos+1]:
            print("#", end="")
        else:
            print(".", end="")
        if pos == 39:
            print()

solve()
