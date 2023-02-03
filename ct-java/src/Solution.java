import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;

        List<Integer> collect = Arrays.stream(citations)
                .mapToObj(Integer::valueOf)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        for (int i = 1; i <= collect.size(); i++) {
                if (collect.get(i - 1) >= i) {
                    answer = i;
                }
        }

        return answer;
    }
}
