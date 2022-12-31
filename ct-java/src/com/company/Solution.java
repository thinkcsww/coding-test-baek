package com.company;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private final int MAX = 102;
    // 가장자리 1, or 2
    private int[][] map = new int[MAX][MAX];
    private boolean[][] visited = new boolean[MAX][MAX];
    private int[] dx = new int[]{0, 0, 1, -1};
    private int[] dy = new int[]{1, -1, 0, 0};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;

        checkEdge(rectangle);

        answer = bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2);
        return answer;
    }

    private int bfs(int startX, int startY, int itemX, int itemY) {

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(startX, startY, 0));
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            Point prev = queue.poll();
            if (prev.x == itemX && prev.y == itemY) {
                return prev.distance / 2;
            }

            for (int i = 0; i < 4; i++) {
                int x = prev.x + dx[i];
                int y = prev.y + dy[i];

                if (!visited[x][y] && map[x][y] == 1) {
                    queue.add(new Point(x, y, prev.distance + 1));
                    visited[x][y] = true;
                }

            }
        }

        return 0;
    }

    private void checkEdge(int[][] rectangles) {
        for (int[] rectangle : rectangles) {
            int startX = rectangle[0] * 2;
            int startY = rectangle[1] * 2;
            int endX = rectangle[2] * 2;
            int endY = rectangle[3] * 2;

            for (int i = startX; i <= endX; i++) {
                for (int j = startY; j <= endY ; j++) {
                    if (map[i][j] == 2) continue;

                    map[i][j] = 2;
                    if (i == startX || i == endX || j == startY || j == endY) {
                        map[i][j] = 1;
                    }
                }
            }
        }
    }

    private static class Point {
        int x;
        int y;
        int distance;

        public Point(int x, int y, int distnace) {
            this.x = x;
            this.y = y;
            this.distance = distnace;
        }
    }
}
