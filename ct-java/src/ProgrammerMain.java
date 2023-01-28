public class ProgrammerMain {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] array1 = new int[]{1, 2};
        int[] array2 = new int[]{3, 4};

        int[][] a = new int[][]{{60, 50}, {30, 70}, {60, 30}, {80, 40}};

        int solution1 = solution.solution(6, new int[][] {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}});

        System.out.println("solution1 = " + solution1);
    }
}
