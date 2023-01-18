class Solution {
    boolean[] visited = new boolean[5];
    String[] vowels = new String[]{"A", "E", "I", "O", "U"};

    int answer = 0;
    public int solution(String word) {


        dfs(0, new String[]{"", "", "", "", ""}, word);

        return answer;
    }

    private void dfs(int depth, String[] currWord, String target) {
        for (int i = 0; i < vowels.length; i++) {
//            if (!visited[depth]) {
                answer++;

                currWord[depth] = vowels[i];
                String join = String.join("", currWord);
                if (join.equals(target)) {
                    return;
                }
//                visited[depth] = true;
                dfs(depth + 1, currWord, target);
//                visited[depth] = false;
//            }

        }
    }
}
