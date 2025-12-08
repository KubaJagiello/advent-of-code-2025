package util;

import static java.lang.Long.parseLong;

public record Point3D(long x, long y, long z) {

    public static Point3D parse(final String line) {
        final String[] split = line.split(",");
        return new Point3D(parseLong(split[0]), parseLong(split[1]), parseLong(split[2]));
    }

    public long distanceSquared(Point3D other) {
        long dx = x - other.x;
        long dy = y - other.y;
        long dz = z - other.z;
        return dx * dx + dy * dy + dz * dz;
    }

}

