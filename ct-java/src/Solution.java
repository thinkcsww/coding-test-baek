import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] copy = nums.clone();
        Map<Integer, Integer> map = new HashMap<>();

        Arrays.sort(copy);

        for (int i = 0; i < copy.length; i++) {
            map.putIfAbsent(copy[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            copy[i] = map.get(nums[i]);
        }

        return copy;
    }
}
