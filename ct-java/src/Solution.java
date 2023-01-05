import java.util.EmptyStackException;
import java.util.Stack;

class Solution {
    boolean solution(String s) {
        boolean answer = true;

        Stack<Character> stack = new Stack<>();

        for (char c: s.toCharArray()) {
            if (c == '(') {
                stack.add(c);
            } else {
                try {
                    stack.pop();
                } catch (EmptyStackException e) {
                    answer = false;
                    break;
                }
            }
        }

        if (answer && !stack.isEmpty()) {
            answer = false;
        }


        return answer;
    }
}
