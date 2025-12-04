package aoc;

import util.Grid;

import java.util.ArrayList;
import java.util.List;

public class Day4 implements AdventOfCode {

    public String solvePart1(List<String> lines) {

        var grid = Grid.parse(lines);
        var accessible = 0;

        for (int y = 0; y < grid.height(); y++) {
            for (int x = 0; x < grid.width(); x++) {

                if (grid.cell(x, y) != '.' && grid.countAdjacentNeighbours(x, y, '@') < 4) {
                    accessible++;
                }
            }
        }

        return String.valueOf(accessible);
    }

    public String solvePart2(List<String> lines) {

        var grid = Grid.parse(lines);
        var paperRollsToRemove = new ArrayList<int[]>();
        var accessible = 0;

        do {
            paperRollsToRemove.forEach(cell -> grid.setCell(cell[0], cell[1], '.'));
            paperRollsToRemove.clear();

            for (int y = 0; y < grid.height(); y++) {
                for (int x = 0; x < grid.width(); x++) {

                    if (grid.cell(x, y) != '.' && grid.countAdjacentNeighbours(x, y, '@') < 4) {
                        paperRollsToRemove.add(new int[]{x, y});
                        accessible++;
                    }
                }
            }
        } while (!paperRollsToRemove.isEmpty());

        return String.valueOf(accessible);
    }
}

