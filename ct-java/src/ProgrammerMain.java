public class ProgrammerMain {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] array1 = new int[]{1,2};
        int[] array2 = new int[]{3,4};

//        int[][] a = new int[][]{{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}};
        int[][] a = new int[][]{ {2,2},{0,1},{-10,9} };
        int solution1 = solution.solution(a);

        System.out.println("solution1 = " + solution1);
    }
}
