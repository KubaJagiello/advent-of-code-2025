package aoc;

import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.Math.floorMod;

public class Day1 implements AdventOfCode {

    public String solvePart1(List<String> lines) {

        var dial = 50;
        var password = 0;

        for (var line : lines) {
            var rotations = parseInt(line.substring(1));

            dial += line.charAt(0) == 'L' ? -rotations : rotations;
            dial = floorMod(dial, 100);

            if (dial == 0) {
                password++;
            }
        }

        return String.valueOf(password);
    }

    public String solvePart2(List<String> lines) {

        var dial = 50;
        var password = 0;

        for (var line : lines) {
            var rotations = parseInt(line.substring(1));
            var rotate = line.charAt(0) == 'L' ? -1 : 1;

            for (var i = 0; i < rotations; i++) {
                dial += rotate;
                dial = floorMod(dial, 100);

                if (dial == 0) {
                    password++;
                }
            }
        }

        return String.valueOf(password);
    }

}
