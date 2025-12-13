package aoc;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;

public class Day10 implements AdventOfCode {

    public String solvePart1(List<String> lines) {
        var answer = 0;

        for (var line : lines) {
            var split = line.split(" ");
            var target = parseTarget(split[0]);
            var buttons = parseButtons(split);

            var queue = new ArrayDeque<QueueItem>();
            var visited = new HashSet<BitSet>();

            queue.add(new QueueItem(new BitSet(), 0));
            visited.add(new BitSet());

            while (!queue.isEmpty()) {
                var item = queue.removeFirst();

                if (item.bitset().equals(target)) {
                    answer += item.depth();
                    break;
                }

                for (var button : buttons) {
                    var next = (BitSet) item.bitset().clone();
                    next.xor(button);

                    if (visited.add(next)) {
                        queue.addLast(new QueueItem(next, item.depth() + 1));
                    }
                }
            }
        }

        return answer + "";
    }

    public String solvePart2(List<String> lines) {
        return "( ╯°□°）╯︵ ┻━┻";
    }

    private List<BitSet> parseButtons(String[] split) {
        var buttons = new ArrayList<BitSet>();
        for (int i = 1; i < split.length - 1; i++) {
            var bits = new BitSet();
            for (var idx : split[i].substring(1, split[i].length() - 1).split(",")) {
                bits.set(Integer.parseInt(idx));
            }
            buttons.add(bits);
        }
        return buttons;
    }

    private BitSet parseTarget(String diagram) {
        var bits = new BitSet();
        var chars = diagram.substring(1, diagram.length() - 1);
        for (int i = 0; i < chars.length(); i++) {
            if (chars.charAt(i) == '#') bits.set(i);
        }
        return bits;
    }

    record QueueItem(BitSet bitset, int depth) {
    }

}

