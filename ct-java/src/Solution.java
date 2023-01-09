class Solution {
    private final int DIVIDER = 1234567;

    public int solution(int n) {
        int[] memory = new int[n + 1];
        memory[0] = 0;
        memory[1] = 1;
        memory[2] = 1;

        for (int i = 3; i <= n; i++) {
            memory[i] = memory[i - 2] % DIVIDER + memory[i - 1] % DIVIDER;
        }

        int answer = memory[n];
        return answer % DIVIDER;
    }
}
