class Solution {
    public int solution(int n) {
        int answer = 0;

        for (int i = 1; i <= n / 2; i++) {
            int sum = 0;
            for (int j = i; j <= (n / 2) + 1 ; j++) {
                sum += j;

                if (sum == n) {
                    answer++;
                }

                if (sum > n) {
                    break;
                }
            }
        }

        return answer + 1;
    }
}
