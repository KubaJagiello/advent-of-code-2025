package aoc;


import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day11 implements AdventOfCode {

    public String solvePart1(List<String> lines) {
        var graph = parseGraph(lines);
        return countPaths("you", "out", graph, new HashMap<>()) + "";
    }

    public String solvePart2(List<String> lines) {
        var graph = parseGraph(lines);
        var cache = new HashMap<String, Map<String, Long>>();
        var case1 = countPaths("svr", "dac", graph, cache) * countPaths("dac", "fft", graph, cache) * countPaths("fft", "out", graph, cache);
        var case2 = countPaths("svr", "fft", graph, cache) * countPaths("fft", "dac", graph, cache) * countPaths("dac", "out", graph, cache);
        return (case1 + case2) + "";
    }

    private Map<String, Set<String>> parseGraph(List<String> lines) {
        var graph = new HashMap<String, Set<String>>();
        for (var line : lines) {
            var parts = line.split(": ");
            Arrays.stream(parts[1].split(" ")).forEach(b -> graph.computeIfAbsent(parts[0], _ -> new HashSet<>()).add(b));
        }
        return graph;
    }

    private long countPaths(String node, String target, Map<String, Set<String>> graph, Map<String, Map<String, Long>> cache) {
        if (node.equals(target)) {
            return 1;
        }
        if (!graph.containsKey(node)) {
            return 0;
        }

        var targetCache = cache.computeIfAbsent(target, _ -> new HashMap<>());
        if (targetCache.containsKey(node)) {
            return targetCache.get(node);
        }

        var count = graph.get(node).stream().mapToLong(next -> countPaths(next, target, graph, cache)).sum();
        targetCache.put(node, count);
        return count;
    }
}

