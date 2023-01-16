class Solution {
    boolean[] visited;
    int answer = -1;
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        dfs(1, k, dungeons);
        return answer;
    }

    private void dfs(int depth, int k, int[][] dungeons) {
        for (int i = 0; i < dungeons.length; i++) {
            int[] dungeon = dungeons[i];

            if (!visited[i] && k >= dungeon[0]) {
                visited[i] = true;
                dfs(depth + 1, k - dungeon[1], dungeons);
                visited[i] = false;
                answer = Math.max(answer, depth);
            }

        }
    }
}
