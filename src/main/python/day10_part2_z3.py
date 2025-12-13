import re
import sys
from z3 import Int, Optimize, Sum


def solve_machine(buttons, targets):
    opt = Optimize()
    # number of times button i is pressed
    x = [Int(f'x_{i}') for i in range(len(buttons))]

    for i in range(len(buttons)):
        opt.add(x[i] >= 0)  # cant press a button negative times

    # counter j = sum of presses of all buttons that increment counter j
    for j in range(len(targets)):
        opt.add(Sum([x[i] for i, b in enumerate(buttons) if j in b]) == targets[j])

    opt.minimize(Sum(x))
    opt.check()
    return sum(opt.model()[v].as_long() for v in x)


total = 0
for line in sys.stdin:
    buttons = [[int(x) for x in m.split(',')] for m in re.findall(r'\(([^)]+)\)', line)]
    targets = [int(x) for x in re.search(r'\{([^}]+)\}', line).group(1).split(',')]
    total += solve_machine(buttons, targets)

print(total)
