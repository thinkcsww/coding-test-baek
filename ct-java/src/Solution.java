import java.util.*;

class Solution {
    int[] distances;
    boolean[] visited;
    List<List<Integer>> adj;
    public int solution(int n, int[][] edge) {
        distances = new int[n + 1];
        visited = new boolean[n + 1];

        adj = new ArrayList<>();

        for (int i = 0; i < n + 1; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] e: edge) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        bfs();

        int max = Arrays.stream(distances).max().getAsInt();
        long count = Arrays.stream(distances)
                .filter(distance -> distance == max)
                .count();

        return (int) count;
    }

    private void bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(1, 0));
        visited[1] = true;

        while (!queue.isEmpty()) {
            Point prev = queue.poll();

            List<Integer> edges = adj.get(prev.x);

            for (int edge: edges) {
                if (!visited[edge]) {
                    visited[edge] = true;
                    distances[edge] = prev.distance + 1;
                    queue.add(new Point(edge, prev.distance + 1));
                }
            }
        }
    }

    private static class Point {
        int x;
        int distance;

        public Point(int x, int distance) {
            this.x = x;
            this.distance = distance;
        }
    }
}
