import java.util.LinkedList;
import java.util.Queue;

class Solution {

    public int solution(int[][] maps) {
        return bfs(maps);
    }

    private int bfs(int[][] maps) {
        int m = maps[0].length;
        int n = maps.length;

        int[] dx = new int[]{0, 0, 1, -1};
        int[] dy = new int[]{1, -1, 0, 0};

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0, 1));
        maps[0][0] = -1;

        while (!queue.isEmpty()) {
            Point prev = queue.poll();

            for (int i = 0; i < 4; i++) {
                int x = prev.x + dx[i];
                int y = prev.y + dy[i];

                if (x == n - 1 && y == m - 1) {
                    return prev.distance + 1;
                }

                if (x >= 0 && x < n && y >= 0 && y < m && maps[x][y] == 1 ) {
                    maps[x][y] = -1;
                    queue.add(new Point(x, y, prev.distance + 1));
                }
            }
        }

        return -1;
    }

    public static class Point {
        int x;
        int y;
        int distance;

        public Point(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}
