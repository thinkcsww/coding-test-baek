import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int n, int[][] costs) {
        int answer = 0;
        int[] parent = new int[n];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        // costs를 오름차순 정렬
        Arrays.sort(costs, Comparator.comparingInt(cost -> cost[2]));

        for (int[] cost : costs) {
            if (union(cost[0], cost[1], parent)) {
                answer += cost[2];
            }
        }

        return answer;
    }

    private boolean union(int x, int y, int[] parent) {
        int a = find(x, parent);
        int b = find(y, parent);

        if (a != b) {
            parent[a] = b;
            return true;
        }

        return false;
    }

    private int find(int x, int[] parent) {
        if (parent[x] == x) {
            return x;
        }

        int result = find(parent[x], parent);
        parent[x] = result;
        return result;
    }
}
