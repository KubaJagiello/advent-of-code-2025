package aoc;

import util.Grid;
import util.Position;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Day7 implements AdventOfCode {

    public String solvePart1(List<String> lines) {
        var grid = Grid.parse(lines);
        var startPosition = new Position(lines.getFirst().indexOf("S"), 1);
        var beams = new HashSet<Position>();
        beams.add(startPosition);
        var splits = 0;

        while (!beams.isEmpty()) {
            var nextBeams = new HashSet<Position>();
            for (var beam : beams) {
                var down = beam.moveDown();
                if (!down.inBounds(grid)) {
                    continue;
                }
                if (grid.cell(down.x(), down.y()) == '^') {
                    nextBeams.add(down.moveLeft());
                    nextBeams.add(down.moveRight());
                    splits++;
                } else {
                    nextBeams.add(down);
                }
            }
            beams = nextBeams;
        }
        return splits + "";
    }

    public String solvePart2(List<String> lines) {
        var grid = Grid.parse(lines);
        var start = new Position(lines.getFirst().indexOf("S"), 1);

        return countTimelines(grid, start, new HashMap<>()) + "";
    }

    private long countTimelines(Grid grid, Position pos, Map<Position, Long> mem) {

        if (pos.y() == grid.height() - 1) {
            return 1;
        }

        if (mem.containsKey(pos)) {
            return mem.get(pos);
        }

        var down = pos.moveDown();
        var result = grid.cell(down.x(), down.y()) == '^'
                ? countTimelines(grid, down.moveLeft(), mem) + countTimelines(grid, down.moveRight(), mem)
                : countTimelines(grid, down, mem);
        mem.put(pos, result);

        return result;
    }

}

