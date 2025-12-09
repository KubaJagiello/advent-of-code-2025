package aoc;

import util.Position;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;


public class Day9 implements AdventOfCode {

    public String solvePart1(List<String> lines) {

        var positions = parsePositions(lines);
        var areas = new ArrayList<Long>();

        for (int i = 0; i < positions.size(); i++) {
            for (int j = i + 1; j < positions.size(); j++) {
                var posA = positions.get(i);
                var posB = positions.get(j);
                var width = abs(posA.x() - posB.x()) + 1L;
                var height = abs(posA.y() - posB.y()) + 1L;

                areas.add(width * height);
            }
        }

        return areas.stream().max(Long::compareTo).get() + "";
    }

    public String solvePart2(List<String> lines) {
        var positions = parsePositions(lines);
        var polygon = buildPolygon(positions);

        return largestRectangle(positions, polygon) + "";
    }

    private List<Position> parsePositions(List<String> lines) {
        var positions = new ArrayList<Position>();
        for (var line : lines) {
            var parts = line.split(",");
            positions.add(new Position(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
        }
        return positions;
    }

    record Edge(Position a, Position b) {

        boolean isVertical() {
            return a.x() == b.x();
        }

        int minX() {
            return min(a.x(), b.x());
        }

        int maxX() {
            return max(a.x(), b.x());
        }

        int minY() {
            return min(a.y(), b.y());
        }

        int maxY() {
            return max(a.y(), b.y());
        }
    }

    record Polygon(List<Edge> edges) {

        boolean isPointOnEdge(Position p) {
            for (var edge : edges) {
                if (edge.isVertical()) {
                    if (p.x() == edge.a().x() && edge.minY() <= p.y() && p.y() <= edge.maxY()) {
                        return true;
                    }
                } else {
                    if (p.y() == edge.a().y() && edge.minX() <= p.x() && p.x() <= edge.maxX()) {
                        return true;
                    }
                }
            }
            return false;
        }

        boolean isPointInside(Position p) {
            int crossings = 0;
            for (var edge : edges) {
                if (edge.isVertical()) {
                    var edgeX = edge.a().x();
                    if (edgeX > p.x() && edge.minY() < p.y() && p.y() < edge.maxY()) {
                        crossings++;
                    }
                }
            }
            return crossings % 2 == 1;
        }

        boolean containsPoint(Position p) {
            return isPointOnEdge(p) || isPointInside(p);
        }

        boolean edgeCrossesRectangleInterior(Edge polyEdge, int rectMinX, int rectMaxX, int rectMinY, int rectMaxY) {
            if (polyEdge.isVertical()) {
                var edgeX = polyEdge.a().x();
                var edgeIsInsideRectangleHorizontally = rectMinX < edgeX && edgeX < rectMaxX;
                var edgeOverlapsRectangleVertically = polyEdge.minY() < rectMaxY && polyEdge.maxY() > rectMinY;
                return edgeIsInsideRectangleHorizontally && edgeOverlapsRectangleVertically;
            } else {
                var edgeY = polyEdge.a().y();
                var edgeIsInsideRectangleVertically = rectMinY < edgeY && edgeY < rectMaxY;
                var edgeOverlapsRectangleHorizontally = polyEdge.minX() < rectMaxX && polyEdge.maxX() > rectMinX;
                return edgeIsInsideRectangleVertically && edgeOverlapsRectangleHorizontally;
            }
        }

        boolean anyEdgeCrossesRectangleInterior(int minX, int maxX, int minY, int maxY) {
            return edges.stream().anyMatch(e -> edgeCrossesRectangleInterior(e, minX, maxX, minY, maxY));
        }
    }

    private Polygon buildPolygon(List<Position> positions) {
        var edges = new ArrayList<Edge>();
        for (int i = 0; i < positions.size(); i++) {
            var current = positions.get(i);
            var next = positions.get((i + 1) % positions.size());
            edges.add(new Edge(current, next));
        }
        return new Polygon(edges);
    }

    private long largestRectangle(List<Position> redTiles, Polygon polygon) {
        var largestArea = 0L;

        for (int i = 0; i < redTiles.size(); i++) {
            for (int j = i + 1; j < redTiles.size(); j++) {
                var cornerA = redTiles.get(i);
                var cornerB = redTiles.get(j);

                if (cornerA.x() == cornerB.x() || cornerA.y() == cornerB.y()) {
                    continue;
                }

                var area = getArea(cornerA, cornerB, polygon);
                if (area > largestArea) {
                    largestArea = area;
                }
            }
        }

        return largestArea;
    }

    private long getArea(Position cornerA, Position cornerB, Polygon polygon) {
        var minX = min(cornerA.x(), cornerB.x());
        var maxX = max(cornerA.x(), cornerB.x());
        var minY = min(cornerA.y(), cornerB.y());
        var maxY = max(cornerA.y(), cornerB.y());

        var cornerC = new Position(minX, maxY);
        var cornerD = new Position(maxX, minY);

        if (!polygon.containsPoint(cornerC) || !polygon.containsPoint(cornerD) || polygon.anyEdgeCrossesRectangleInterior(minX, maxX, minY, maxY)) {
            return 0;
        }

        return (maxX - minX + 1L) * (maxY - minY + 1L);
    }

}

