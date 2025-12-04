package aoc;

import java.util.List;

import static java.lang.Character.getNumericValue;
import static java.lang.Long.parseLong;
import static java.lang.Math.max;


public class Day3 implements AdventOfCode {

    public String solvePart1(List<String> lines) {

        return lines
                .stream()
                .mapToLong(bank -> parseLong(findLargestJoltage(bank, 0, 2, "")))
                .sum() + "";
    }

    public String solvePart2(List<String> lines) {

        return lines
                .stream()
                .mapToLong(bank -> parseLong(findLargestJoltage(bank, 0, 12, "")))
                .sum() + "";
    }

    private String findLargestJoltage(String bank, int start, int digitsToCollect, String result) {

        if (digitsToCollect == 0) {
            return result;
        }

        var end = bank.length() - digitsToCollect;
        var maxDigit = findMaxDigit(bank.substring(start, end + 1));
        var maxPosition = bank.indexOf(String.valueOf(maxDigit), start);

        return findLargestJoltage(bank, maxPosition + 1, digitsToCollect - 1, result + maxDigit);
    }

    private int findMaxDigit(String bank) {

        var max = 0;
        for (int i = 0; i < bank.length(); i++) {
            var digit = getNumericValue(bank.charAt(i));
            if (digit == 9) {
                return 9;
            }
            max = max(max, digit);
        }
        return max;
    }

}

