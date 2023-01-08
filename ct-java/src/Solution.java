class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        int removedZeroCount = 0;
        int conversionCount = 0;

        while (!"1".equals(s)) {
            conversionCount++;
            removedZeroCount += s.length();

            s = s.replaceAll("0", "");

            removedZeroCount -= s.length();

            s = Integer.toBinaryString(s.length());
        }

        answer[0] = conversionCount;
        answer[1] = removedZeroCount;

        return answer;
    }
}
