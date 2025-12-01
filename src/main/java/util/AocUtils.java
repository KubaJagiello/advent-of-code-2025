package util;

import aoc.AdventOfCode;

import java.io.FileNotFoundException;
import java.util.List;

public class AocUtils {

    public static AdventOfCode getSolver(int day) throws Exception {
        return (AdventOfCode) Class.forName("aoc.Day" + day).getDeclaredConstructor().newInstance();
    }

    public static List<String> getInputLines(int day, int part) throws Exception {
        var fileName = "day%dpart%d.txt".formatted(day, part);
        try (var inputStream = ClassLoader.getSystemResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new FileNotFoundException("File not found: " + fileName);
            }
            return new String(inputStream.readAllBytes()).lines().toList();
        }
    }
}

