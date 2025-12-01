import static java.lang.Integer.*;
import static util.AocUtils.*;

void main(String[] args) throws Exception {
    if (args.length == 0) {
        System.err.println("Usage: java Main <day>");
        System.exit(1);
    }

    int day = parseInt(args[0]);
    var solver = getSolver(day);

    System.out.println("Part 1: " + solver.solvePart1(getInputLines(day, 1)));
    System.out.println("Part 2: " + solver.solvePart2(getInputLines(day, 2)));
}
