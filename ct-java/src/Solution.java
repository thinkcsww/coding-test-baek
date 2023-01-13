import java.util.HashSet;
import java.util.Set;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[]{0, 0};

        Set<String> historySet = new HashSet<>();

        char lastChar = ' ';

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            boolean flag = true;

            if (lastChar != ' ' && word.charAt(0) != lastChar) {
                flag = false;
            }

            if (word.length() == 1) {
                flag = false;
            }

            if (historySet.contains(word)) {
                flag = false;
            }

            if (!flag) {
                int personNum = (i + 1) % n;
                int turnNum = (int)Math.ceil((i + 1) / (double)n);
                answer[0] = personNum == 0 ? n : personNum;
                answer[1] = turnNum;
                break;
            }


            lastChar = word.charAt(word.length() - 1);
            historySet.add(word);
        }

        return answer;
    }
}
