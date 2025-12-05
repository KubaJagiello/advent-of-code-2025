package util;

import static java.lang.Long.parseLong;

public record Range(long start, long end) {

    public static Range parse(String range) {
        var split = range.split("-");
        return new Range(parseLong(split[0]), parseLong(split[1]));
    }

    public Range merge(Range range) {
        return new Range(Math.min(start, range.start), Math.max(end, range.end));
    }

    public boolean inRange(long value) {
        return value >= start && value <= end;
    }

    public boolean rangesOverlap(Range range) {
        return end >= range.start && start <= range.end;
    }

    public long lengthInclusive() {
        return end - start + 1;
    }
}

