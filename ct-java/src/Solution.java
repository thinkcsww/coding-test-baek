import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num: nums) {
            set.add(num);
            if (nums.length / 2 == set.size()) {
                break;
            }
        }
        return set.size();
    }
}
