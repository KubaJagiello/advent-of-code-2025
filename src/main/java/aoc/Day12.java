package aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Day12 implements AdventOfCode {

    record Region(int width, int height, int[] shapeCounts) {
    }

    public String solvePart1(List<String> lines) {
        var regions = parseRegions(lines);

        int count = 0;
        for (var region : regions) {
            int totalShapes = Arrays.stream(region.shapeCounts).sum();
            int blocks = (region.width / 3) * (region.height / 3);
            if (totalShapes <= blocks) {
                count++;
            }
        }
        return count + "";
    }

    public String solvePart2(List<String> lines) {
        return "(╯°□°）╯︵ ┻━┻";
    }

    private List<Region> parseRegions(List<String> lines) {
        var regions = new ArrayList<Region>();
        for (var line : lines) {
            if (line.contains("x") && line.contains(":")) {
                var parts = line.split(": ");
                var dims = parts[0].split("x");
                var width = parseInt(dims[0]);
                var height = parseInt(dims[1]);
                var counts = Arrays.stream(parts[1].split(" "))
                        .mapToInt(Integer::parseInt).toArray();
                regions.add(new Region(width, height, counts));
            }
        }
        return regions;
    }
}

