import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
    public String solution(String s) {
        String answer = Arrays.stream(s.split(" "))
                .map(str -> {
                    if (str.isEmpty()) {
                        return "";
                    }

                    return String.valueOf(str.charAt(0)).toUpperCase() + str.toLowerCase().substring(1);
                })
                .collect(Collectors.joining(" "));

        if (s.charAt(s.length() - 1) == ' ') {
            answer += " ";
        }

        return answer;
    }
}
