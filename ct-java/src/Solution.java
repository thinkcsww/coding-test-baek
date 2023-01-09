import java.util.Stack;

class Solution
{
    public int solution(String s)
    {
        int answer = 0;

        Stack<Character> stack = new Stack<>();

        for (char c: s.toCharArray()) {
            if (stack.isEmpty()) {
                stack.add(c);
            } else {
                if (stack.peek() == c) {
                    stack.pop();
                } else {
                    stack.add(c);
                }
            }

        }

        if (stack.isEmpty()) {
            answer = 1;
        }

        return answer;
    }
}
