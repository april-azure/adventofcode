def solve():
    res = 0

    f = open('Y2022/test.txt', 'r')
    lines = f.readlines()

    cycle = 1
    val = 1

    def cal():
        nonlocal res
        if cycle in [20,60,100,140,180,220]:
            print(cycle, val)
            res += (cycle * val)

    def noop():
        nonlocal cycle
        cal()
        cycle += 1
        pass

    def addx(v): 
        # global cycle
        nonlocal cycle, val
        cal()
        cycle += 1
        cal()
        cycle += 1
        val += v
        pass

    for line in lines:
        if line.startswith("noop"):
            noop()
        elif line.startswith("addx"):    
            v = int(line[5:])
            addx(v)
    cal()
    print("part1 res: ", res)
solve()