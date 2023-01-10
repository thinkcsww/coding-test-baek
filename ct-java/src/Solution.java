class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];

        double divider = 0;

        while (true) {
            divider++;
            double yellowCntFor1Row = Math.ceil(yellow / divider);

            //
            double left = 0;
            if (yellow % divider != 0) {
                left = divider - (yellow % divider);
            }

            double brownCnt = 0;
            // 맨 위, 아래, 모서리
            brownCnt += yellowCntFor1Row * 2 + 4;
            // 노란색이 있는 줄의 테두리
            brownCnt += divider * 2;

            if (brown == brownCnt + left) {
                break;
            }
        }

        answer[0] = (int) ((brown + yellow) / (divider + 2));
        answer[1] = (int) (divider + 2);

        return answer;
    }
}
