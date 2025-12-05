package aoc;

import util.Range;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Day5 implements AdventOfCode {

    public String solvePart1(List<String> lines) {

        var ranges = lines.stream()
                .takeWhile(l -> !l.isBlank())
                .map(Range::parse)
                .toList();

        var ingredientIds = lines.stream()
                .dropWhile(line -> !line.isBlank())
                .skip(1)
                .map(Long::valueOf)
                .collect(Collectors.toSet());

        return ingredientIds.stream()
                .filter(id -> ranges.stream().anyMatch(range -> range.inRange(id)))
                .count() + "";
    }

    public String solvePart2(List<String> lines) {

        var ranges = lines.stream()
                .takeWhile(line -> !line.isBlank())
                .map(Range::parse)
                .sorted(Comparator.comparingLong(Range::start))
                .toList();

        var mergedRanges = new ArrayList<Range>();
        mergedRanges.add(ranges.getFirst());

        for (var rangeA : ranges) {
            var rangeB = mergedRanges.getLast();

            if (rangeA.rangesOverlap(rangeB)) {
                var mergedRange = rangeA.merge(rangeB);
                mergedRanges.set(mergedRanges.size() - 1, mergedRange);
            } else {
                mergedRanges.add(rangeA);
            }
        }

        return mergedRanges
                .stream()
                .mapToLong(Range::lengthInclusive)
                .sum() + "";
    }

}

