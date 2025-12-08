package aoc;

import util.Point3D;
import util.UnionFind;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day8 implements AdventOfCode {

    record Edge(int a, int b, long distance) {
    }

    public String solvePart1(List<String> lines) {
        var points = lines.stream().map(Point3D::parse).toList();
        var unionFind = new UnionFind(points.size());
        var edges = getAllEdgesSorted(points);

        var connectionsMade = 0;
        for (var edge : edges) {
            if (connectionsMade >= 1000) {
                break;
            }
            unionFind.union(edge.a, edge.b);
            connectionsMade++;
        }

        var sizes = new ArrayList<Long>();
        for (int i = 0; i < points.size(); i++) {
            if (unionFind.find(i) == i) {
                sizes.add((long) unionFind.size(i));
            }
        }

        return sizes.stream().sorted(Comparator.reverseOrder()).limit(3).reduce(1L, (a, b) -> a * b) + "";
    }


    public String solvePart2(List<String> lines) {
        var points = lines.stream().map(Point3D::parse).toList();
        var unionFind = new UnionFind(points.size());
        var edges = getAllEdgesSorted(points);

        for (var edge : edges) {
            if (unionFind.find(edge.a) != unionFind.find(edge.b)) {
                unionFind.union(edge.a, edge.b);

                if (unionFind.size(edge.a) == points.size()) {
                    var pointA = points.get(edge.a);
                    var pointB = points.get(edge.b);
                    return pointA.x() * pointB.x() + "";
                }
            }
        }

        return "( ╯°□°）╯︵ ┻━┻";
    }

    private static List<Edge> getAllEdgesSorted(List<Point3D> points) {
        var edges = new ArrayList<Edge>();
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                var distance = points.get(i).distanceSquared(points.get(j));
                edges.add(new Edge(i, j, distance));
            }
        }
        edges.sort(Comparator.comparingLong(Edge::distance));

        return edges;
    }
}

