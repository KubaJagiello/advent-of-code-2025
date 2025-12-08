package util;

public class UnionFind {

    private final int[] parent;
    private final int[] rank;
    private final int[] size;
    private int count;

    public UnionFind(int n) {
        this.parent = new int[n];
        this.rank = new int[n];
        this.size = new int[n];
        this.count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int a) {
        if (parent[a] != a) {
            parent[a] = find(parent[a]);
        }
        return parent[a];
    }

    public void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) {
            return;
        }
        if (rank[rootA] < rank[rootB]) {
            parent[rootA] = rootB;
            size[rootB] += size[rootA];
        } else if (rank[rootA] > rank[rootB]) {
            parent[rootB] = rootA;
            size[rootA] += size[rootB];
        } else {
            parent[rootB] = rootA;
            size[rootA] += size[rootB];
            rank[rootA]++;
        }
        count--;
    }

    public boolean connected(int a, int b) {
        return find(a) == find(b);
    }

    public int size(int x) {
        return size[find(x)];
    }
}
