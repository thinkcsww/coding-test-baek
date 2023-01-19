class Solution {
    String[] vowels = new String[]{"A", "E", "I", "O", "U"};

    int count = 0;
    int answer = 0;
    public int solution(String word) {
        dfs("", word);
        return answer;
    }

    private void dfs(String currWord, String target) {
        count++;
        if (currWord.equals(target)) {
            answer = count;
            return;
        }

        if (currWord.length() == 5) {
            return;
        }

        for (int i = 0; i < vowels.length; i++) {
            dfs(currWord + vowels[i], target);
        }
    }
}
