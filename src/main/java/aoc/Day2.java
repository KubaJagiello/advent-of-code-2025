package aoc;

import java.util.List;


import static java.lang.Long.parseLong;

public class Day2 implements AdventOfCode {

    public String solvePart1(List<String> lines) {

        var idRanges = lines.getFirst().split(",");
        var answer = 0L;

        for (var idRange : idRanges) {
            var range = Range.parse(idRange);

            for (var i = range.start; i <= range.end; i++) {

                var id = String.valueOf(i);
                var middle = id.length();
                var left = id.substring(0, middle / 2);
                var right = id.substring(middle / 2);

                if (left.equals(right)) {
                    answer += i;
                }
            }
        }

        return answer + "";
    }

    public String solvePart2(List<String> lines) {

        var idRanges = lines.getFirst().split(",");
        var answer = 0L;

        for (var idRange : idRanges) {
            var range = Range.parse(idRange);

            for (var i = range.start; i <= range.end; i++) {
                var id = String.valueOf(i);

                for (var patternSize = 1; patternSize <= id.length() / 2; patternSize++) {

                    if (id.length() % patternSize != 0) {
                        continue;
                    }

                    if (isRepeating(id, patternSize)) {
                        answer += i;
                        break;
                    }
                }
            }
        }

        return answer + "";
    }

    private boolean isRepeating(String id, int patternSize) {

        var pattern = id.substring(0, patternSize);
        for (int i = patternSize; i < id.length(); i += patternSize) {
            var nextPattern = id.substring(i, i + patternSize);

            if (!pattern.equals(nextPattern)) {
                return false;
            }
        }

        return true;
    }

    record Range(long start, long end) {

        static Range parse(String range) {
            var split = range.split("-");
            return new Range(parseLong(split[0]), parseLong(split[1]));
        }

    }
}

