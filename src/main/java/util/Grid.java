package util;

import java.util.List;

public record Grid(char[][] cells, int height, int width) {

    private static final int[][] ADJACENT_MOVES = {
            {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}
    };

    public static Grid parse(List<String> lines) {

        char[][] cells = new char[lines.size()][lines.getFirst().length()];

        for (int y = 0; y < lines.size(); y++) {
            cells[y] = lines.get(y).toCharArray();
        }
        return new Grid(cells, lines.size(), lines.getFirst().length());
    }

    public int countAdjacentNeighbours(int x, int y, char neighbour) {
        var neighboursCount = 0;

        for (var move : ADJACENT_MOVES) {
            var nx = x + move[0];
            var ny = y + move[1];
            if (nx >= 0 && nx < width && ny >= 0 && ny < height && cells[ny][nx] == neighbour) {
                neighboursCount++;
            }
        }

        return neighboursCount;
    }

    public char cell(int x, int y) {
        return cells[y][x];
    }

    public void setCell(int x, int y, char c) {
        cells[y][x] = c;
    }

}
