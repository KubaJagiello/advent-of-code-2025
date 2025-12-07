package aoc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.isDigit;
import static java.lang.Long.parseLong;


public class Day6 implements AdventOfCode {

    public String solvePart1(List<String> lines) {

        var operators = lines.getLast().replaceAll("[^+*]", "").toCharArray();
        var columns = new ArrayList<ArrayList<Long>>();

        for (int i = 0; i < operators.length; i++) {
            columns.add(new ArrayList<>());
        }

        for (int row = 0; row < lines.size() - 1; row++) {
            var numbers = lines.get(row).trim().replaceAll("\\s+", " ").split(" ");

            for (int i = 0; i < numbers.length; i++) {
                columns.get(i).add(parseLong(numbers[i]));
            }
        }

        return Long.toString(calculateTotal(columns, operators));
    }

    public String solvePart2(List<String> lines) {

        var operators = new StringBuilder(lines.getLast().replaceAll("[^+*]", "")).reverse().toString().toCharArray();
        var columns = new ArrayList<ArrayList<Long>>();
        var current = new ArrayList<Long>();

        for (int col = lines.getFirst().length() - 1; col >= 0; col--) {
            var isSeparator = true;
            for (int row = 0; row < lines.size() - 1; row++) {
                if (lines.get(row).charAt(col) != ' ') {
                    isSeparator = false;
                    break;
                }
            }

            if (isSeparator) {
                if (!current.isEmpty()) {
                    columns.add(current);
                    current = new ArrayList<>();
                }
            } else {
                var digits = new StringBuilder();
                for (int row = 0; row < lines.size() - 1; row++) {
                    var c = lines.get(row).charAt(col);
                    if (isDigit(c)) {
                        digits.append(c);
                    }
                }
                if (!digits.isEmpty()) {
                    current.add(parseLong(digits.toString()));
                }
            }
        }
        if (!current.isEmpty()) {
            columns.add(current);
        }

        return Long.toString(calculateTotal(columns, operators));
    }

    private static long calculateTotal(ArrayList<ArrayList<Long>> numbers, char[] operators) {
        var total = 0L;
        for (int i = 0; i < numbers.size(); i++) {
            var op = operators[i];
            var result = op == '+'
                    ? numbers.get(i).stream().mapToLong(Long::longValue).sum()
                    : numbers.get(i).stream().mapToLong(Long::longValue).reduce(1L, (a, b) -> a * b);
            total += result;
        }
        return total;
    }

}

