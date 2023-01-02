import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        int currCameraIndex = Integer.MIN_VALUE;

        // 진입 지점을 기준으로 오름차순 정렬
        Arrays.sort(routes, Comparator.comparingInt(a -> a[1]));

        // 나갈때 찍혔는지 여부 판단해서 안찍혔으면 그 나간 구간을 추가한다.
        for (int[] route : routes) {

            // 진입 지점이 마지막 카메라 index 보다 뒤에 있으면
            if (route[0] > currCameraIndex) {
                answer++;
                currCameraIndex = route[1];
            }
        }
        return answer;
    }
}
