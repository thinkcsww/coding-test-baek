import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        int end = people.length - 1;

        Arrays.sort(people);

        for (int i = 0; i <= end; ) {
            if (people[end] + people[i] <= limit) {
                i++;
            }
            end--;
            answer++;
        }

        return answer;
    }
}
