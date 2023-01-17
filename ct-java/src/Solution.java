import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    boolean[] visited;
    List<List<Integer>> adj;
    int[] counts;
    public int solution(int n, int[][] wires) {
        int answer = 1000;
        int exceptIndex = 0;
        int groupCount;

        // 1개를 빼고 DFS 하면서 2개로 쪼개지고 송전탑 개수의 차이가 가장 작은 경우를 구한다.

        for (int i = 0; i < wires.length; i++) {
            // counts 초기화
            counts = new int[n + 1];
            // groupCount 초기화
            groupCount = 0;

            // visited 초기화
            visited = new boolean[n + 1];

            // adj 초기화
            adj = new ArrayList<>();
            for (int j = 0; j <= n; j++) {
                adj.add(new ArrayList<>());
            }

            // adj 설정
            for (int j = 0; j < wires.length; j++) {
                if (exceptIndex != j) {
                    adj.get(wires[j][0]).add(wires[j][1]);
                    adj.get(wires[j][1]).add(wires[j][0]);
                }
            }


            // dfs
            for (int j = 1; j <= n; j++) {
                if (!visited[j]) {
                    groupCount++;
                    dfs(j, 1, j);
                }
            }

            if (groupCount == 2) {
                int[] array = Arrays.stream(counts)
                        .filter(v -> v != 0)
                        .toArray();
                answer = Math.min(answer, Math.abs(array[0] - array[1]));
            }

            exceptIndex++;
        }

        return answer;
    }

    private void dfs(int vertex, int depth, int root) {
        visited[vertex] = true;
        counts[root]++;
        List<Integer> edges = adj.get(vertex);

        for (int edge: edges) {
            if (!visited[edge]) {
                dfs(edge, depth + 1, root);
            }
        }
    }
}
