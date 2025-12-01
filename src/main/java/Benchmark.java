import util.AocUtils;

import java.util.function.Supplier;

private static final int WARMUP_ITERATIONS = 5;
private static final int MEASURED_ITERATIONS = 10;

void main() throws Exception {
    runDay(1);
//    runAllDays();
}

void runDay(int day) throws Exception {
    benchmarkDay(day);
}

void runAllDays() throws Exception {
    for (int day = 1; day <= 12; day++) {
        benchmarkDay(day);
    }
}

void benchmarkDay(int day) throws Exception {
    var solver = AocUtils.getSolver(day);

    var inputPart1 = AocUtils.getInputLines(day, 1);
    var part1Avg = measure(() -> solver.solvePart1(inputPart1));

    var inputPart2 = AocUtils.getInputLines(day, 2);
    var part2Avg = measure(() -> solver.solvePart2(inputPart2));

    System.out.println("Day " + day + " - Part 1: " + formatTime(part1Avg) + ", Part 2: " + formatTime(part2Avg));
}

long measure(Supplier<String> task) {
    for (int i = 0; i < WARMUP_ITERATIONS; i++) {
        task.get();
    }

    long sum = 0;
    for (int i = 0; i < MEASURED_ITERATIONS; i++) {
        long start = System.nanoTime();
        task.get();
        sum += System.nanoTime() - start;
    }
    return sum / MEASURED_ITERATIONS;
}

String formatTime(long nanos) {
    if (nanos < 1_000_000_000) {
        return String.format("%.2f ms", nanos / 1_000_000.0);
    } else {
        return String.format("%.2f s", nanos / 1_000_000_000.0);
    }
}

