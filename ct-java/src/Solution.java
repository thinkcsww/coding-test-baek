class Solution {
    public int solution(String name) {
        int LENGTH = name.length();

        int upDownCount = 0;
        int leftRightCount = LENGTH - 1;
        for (int i = 0; i < LENGTH; i++) {
            int forward = name.charAt(i) - 'A';
            int backward = 'Z' - name.charAt(i) + 1;
            upDownCount += Math.min(forward, backward);

            int cursor = i + 1;
            while (cursor < LENGTH && name.charAt(cursor) == 'A') {
                cursor++;
            }

            leftRightCount = Math.min(leftRightCount, i * 2 + LENGTH - cursor);

        }

        return upDownCount + leftRightCount;
    }
}
