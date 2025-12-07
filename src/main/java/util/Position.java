package util;

public record Position(int x, int y) {

    public Position move(int dx, int dy) {
        return new Position(x + dx, y + dy);
    }

    public boolean inBounds(Grid grid) {
        return x >= 0 && x < grid.width() && y >= 0 && y < grid.height();
    }

    public Position moveLeft() {
        return move(-1, 0);
    }

    public Position moveRight() {
        return move(1, 0);
    }

    public Position moveDown() {
        return move(0, 1);
    }
}
