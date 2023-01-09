class Solution {
    public int solution(int n) {
        int newN = n;

        String prevBin = Integer.toBinaryString(n);
        int prevBinZeroCnt = 0;
        for (char c : prevBin.toCharArray()) {
            if (c == '1') {
                prevBinZeroCnt++;
            }
        }

        while (true) {
            newN++;
            String nextBin = Integer.toBinaryString(newN);
            int nextBinZeroCnt = 0;
            for (char c : nextBin.toCharArray()) {
                if (c == '1') {
                    nextBinZeroCnt++;
                }
            }

            if (prevBinZeroCnt == nextBinZeroCnt) {
                break;
            }
        }

        return newN;
    }
}
