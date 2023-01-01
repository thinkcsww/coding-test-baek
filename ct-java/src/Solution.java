import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution {
    public int solution(int[][] routes) {
        // 카메라 인덱스
        List<Integer> cameraIndex = new ArrayList<>();
        int currCameraIndex = Integer.MIN_VALUE;

        // 진입 지점을 기준으로 오름차순 정렬
        Arrays.sort(routes, Comparator.comparingInt(a -> a[1]));

        // 나갈때 찍혔는지 여부 판단해서 안찍혔으면 그 나간 구간을 추가한다.
        for (int[] ints : routes) {
            int in = ints[0];
            int out = ints[1];

            boolean isCameraBetweenInAndOut = false;
                // 사이에 있는지 확인
            if (in <= currCameraIndex && out >= currCameraIndex) {
                isCameraBetweenInAndOut = true;
            }

            // 사이에 없으면 out 지점에 카메라 추가
            if (!isCameraBetweenInAndOut) {
                cameraIndex.add(out);
                currCameraIndex = out;
            }
        }
        return cameraIndex.size();
    }
}
