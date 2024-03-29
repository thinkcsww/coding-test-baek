import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    private static int p2606Count = -1;
    private static int p24479Count = 1;
    private static int p24480Count = 1;

    public static void main(String[] args) throws IOException {
        p14442();
    }

    private static void p14442() throws IOException {
        // x, count, break
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] array = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int N = array[0];
        int M = array[1];
        int K = array[2];
        int[] dx = new int[]{0, 0, -1, 1};
        int[] dy = new int[]{1, -1, 0, 0};
        int[][] visited = new int[N][M];
        int[][] broke = new int[N][M];

        for (int[] b: broke) {
            Arrays.fill(b, Integer.MAX_VALUE);
        }

        int count = -1;

        // adj
        int[][] adj = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = bufferedReader.readLine();

            for (int j = 0; j < s.length(); j++) {
                adj[i][j] = s.charAt(j) - 48;
            }
        }

        // queue
        Queue<p14442Val> queue = new LinkedList<>();
        queue.add(new p14442Val(0, 0, 1));
        visited[0][0] = 1;
        broke[0][0] = 0;

        while (!queue.isEmpty()) {
            p14442Val curr = queue.poll();

            if (curr.x == N - 1 && curr.y == M - 1) {
                count = curr.count;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = curr.x + dx[i];
                int nextY = curr.y + dy[i];
                if (nextX >= N || nextY >= M || nextX < 0 || nextY < 0) {
                    continue;
                }

                int nextBroke = broke[curr.x][curr.y] + adj[nextX][nextY];
                if (nextBroke > K || broke[nextX][nextY] <= nextBroke) {
                    continue;
                }

                visited[nextX][nextY] = 1;
                broke[nextX][nextY] = nextBroke;
                queue.add(new p14442Val(nextX, nextY, curr.count + 1));
            }
        }

        System.out.println(count);
    }

    private static class p14442Val {
        int x;
        int y;
        int count;

        public p14442Val(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    private static void p12865() throws IOException {
        Integer[][] memo;
        int[] weight;
        int[] value;

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int K = Integer.parseInt(stringTokenizer.nextToken());

        weight = new int[N];
        value = new int[N];

        memo = new Integer[N][K + 1];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            weight[i] =  Integer.parseInt(stringTokenizer.nextToken());
            value[i] =  Integer.parseInt(stringTokenizer.nextToken());
        }

        System.out.println(p12865Recur(N - 1, K, memo, weight, value));
    }

    private static int p12865Recur(int i, int k, Integer[][] memo, int[] weight, int[] value) {
        if (i < 0) {
            return 0;
        }

        if (memo[i][k] == null) {
            if (weight[i] > k) {
                memo[i][k] = p12865Recur(i - 1, k, memo, weight, value);
            } else {
                memo[i][k] = Math.max(p12865Recur(i - 1, k, memo, weight, value), p12865Recur(i - 1, k - weight[i], memo, weight, value) + value[i]);
            }
        }

        return memo[i][k];
    }

    private static void p9251() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        char[] str1 = bufferedReader.readLine().toCharArray();
        char[] str2 = bufferedReader.readLine().toCharArray();

        int length1 = str1.length;
        int length2 = str2.length;

        int[][] memo = new int[length1 + 1][length2 + 1];

        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {

                if (str1[i - 1] == str2[j - 1]) {
                    memo[i][j] = memo[i - 1][j - 1] + 1;
                } else {
                    memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
                }
            }
        }

        System.out.println(memo[length1][length2]);
    }

    private static void p2565() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());

        int[][] wires = new int[N][2];
        int[] memo = new int[N];

        StringTokenizer stringTokenizer;
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            wires[i][0] = Integer.parseInt(stringTokenizer.nextToken());
            wires[i][1] = Integer.parseInt(stringTokenizer.nextToken());
        }

        Arrays.sort(wires, Comparator.comparingInt((value -> value[0])));

        for (int i = 0; i < N; i++) {
            memo[i] = 1;

            for (int j = 0; j < i; j++) {
                if (wires[i][1] > wires[j][1] && memo[i] == memo[j]) {
                    memo[i] = memo[j] + 1;
                }
            }
        }

        int max = Arrays.stream(memo).max().getAsInt();

        System.out.println(N - max);
    }

    private static void p11054() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());

        int[] arr = new int[N];
        int[] memoUp = new int[N];
        int[] memoDown = new int[N];

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        for (int i = 0; i < N; i++) {
            memoUp[i] = 1;
            memoDown[i] = 1;

            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && memoUp[j] == memoUp[i]) {
                    memoUp[i] = memoUp[j] + 1;
                }
            }

            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j]) {
                    if (memoDown[i] < memoUp[j] + 1) {
                        memoDown[i] = memoUp[j] + 1;
                    } else if (memoDown[i] < memoDown[j] + 1) {
                        memoDown[i] = memoDown[j] + 1;
                    }
                }
            }
        }

        Arrays.sort(memoDown);
        Arrays.sort(memoUp);

        System.out.println(Math.max(memoDown[N - 1], memoUp[N - 1]));


    }

    private static void p11053() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());

        int[] arr = new int[N];
        int[] memo = new int[N];

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        for (int i = 0; i < N; i++) {
            memo[i] = 1;

            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && memo[j] + 1 > memo[i]) {
                    memo[i] = memo[j] + 1;
                }
            }
        }

        Arrays.sort(memo);

        System.out.println(memo[N - 1]);
    }


    private static void p2156() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        int[] memo = new int[N + 1];
        int[] wines = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            wines[i] = Integer.parseInt(bufferedReader.readLine());
        }

        memo[1] = wines[1];
        if (N > 1) {
            memo[2] = wines[1] + wines[2];
        }

        for (int i = 3; i <= N; i++) {
            memo[i] = Math.max(memo[i - 1], Math.max(memo[i - 2] + wines[i], memo[i - 3] + wines[i - 1] + wines[i]));
        }


        System.out.println(memo[N]);
    }

    private static void p10844() throws IOException {
        long mod = 1000000000L;

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());
        long[][] memo = new long[N + 1][10];

        memo[1][1] = 1;
        memo[1][2] = 1;
        memo[1][3] = 1;
        memo[1][4] = 1;
        memo[1][5] = 1;
        memo[1][6] = 1;
        memo[1][7] = 1;
        memo[1][8] = 1;
        memo[1][9] = 1;

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    memo[i][j] = memo[i - 1][1] % mod;
                } else if (j == 9) {
                    memo[i][j] = memo[i - 1][8] % mod;
                } else {
                    memo[i][j] = (memo[i - 1][j - 1] + memo[i - 1][j + 1]) % mod;
                }

            }
        }

        long result = 0;

        for (int i = 0; i < 10; i++) {
            result += memo[N][i];
        }
        System.out.println(result % mod);
    }

    private static void p1463() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int[] memo;
        int N = Integer.parseInt(bufferedReader.readLine());

        if (N == 1) {
            System.out.println(0);
            return;
        } else if (N <= 3) {
            System.out.println(1);
            return;
        }

        memo = new int[N + 1];

        memo[1] = 0;
        memo[2] = 1;
        memo[3] = 1;

        for (int i = 4; i <= N; i++) {
            int div3 = (int) Math.pow(10, 6);
            int div2 = (int) Math.pow(10, 6);

            if (i % 3 == 0) {
                div3 = memo[i / 3] + 1;
            }

            if (i % 2 == 0) {
                div2 = memo[i / 2] + 1;
            }

            int temp = Math.min(div3, div2);

            int minusOne = memo[i - 1] + 1;
            memo[i] = Math.min(minusOne, temp);

        }


        System.out.println(memo[N]);
    }

    private static void p1912() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        // 입력값 담을 1차원 배열
        int[] inputs = new int[N];
        // 2차원 배열 [N][N]
        int[] results = new int[N];

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            inputs[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        results[0] = inputs[0];
        int max = inputs[0];

        for (int i = 1; i < N; i++) {
            results[i] = Math.max(results[i - 1] + inputs[i], inputs[i]);
            max = Math.max(results[i], max);
        }

        System.out.println(max);
    }

    private static void p9461() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());

        long[] memo = new long[101];
        memo[1] = 1;
        memo[2] = 1;
        memo[3] = 1;
        memo[4] = 2;
        memo[5] = 2;

        StringBuilder stringBuilder = new StringBuilder();
        while (T --> 0) {
            int N = Integer.parseInt(bufferedReader.readLine());

            stringBuilder.append(p9461Padovan(N, memo)).append("\n");
        }

        System.out.println(stringBuilder);
    }

    private static long p9461Padovan(int N, long[] memo) {
        if (memo[N] > 0) {
            return memo[N];
        }

        return memo[N] = p9461Padovan(N - 1, memo) + p9461Padovan(N - 5, memo);
    }

    private static void p5014() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int F = Integer.parseInt(stringTokenizer.nextToken());
        int S = Integer.parseInt(stringTokenizer.nextToken());
        int G = Integer.parseInt(stringTokenizer.nextToken());
        int U = Integer.parseInt(stringTokenizer.nextToken());
        int D = Integer.parseInt(stringTokenizer.nextToken());
        boolean[] visited = new boolean[F + 1];

        int result = p5014BFS(S, G, F, U, D, visited);

        if (result == -1) {
            System.out.println("use the stairs");
        } else {
            System.out.println(result);
        }
    }

    private static int p5014BFS(int S, int G, int F, int U, int D, boolean[] visited) {
        if (S == G) {
            return 0;
        }

        Queue<p5014Point> queue = new LinkedList<>();
        queue.add(new p5014Point(S, 0));
        visited[S] = true;
        int[] dx = new int[]{U, D * -1};

        while (!queue.isEmpty()) {
            p5014Point prev = queue.poll();

            for (int i = 0; i < 2; i++) {
                int x = prev.x + dx[i];

                if (x == G) {
                    return prev.count + 1;
                }

                if (x >= 1 && x <= F && !visited[x]) {
                    queue.add(new p5014Point(x, prev.count + 1));
                    visited[x] = true;
                }
            }
        }

        return -1;
    }

    private static class p5014Point {
        int x;
        int count;

        public p5014Point(int x, int count) {
            this.x = x;
            this.count = count;
        }
    }


    private static void p2468() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        int N = Integer.parseInt(bufferedReader.readLine());


        int[][] adj = new int[N][N];
        int minHeight = 101;
        int maxHeight = -1;
        int[] dx = new int[]{0, 0, -1, 1};
        int[] dy = new int[]{1, -1, 0, 0};
        int result = 1;

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            for (int j = 0; j < N; j++) {
                adj[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                maxHeight = Math.max(maxHeight, adj[i][j]);
                minHeight = Math.min(minHeight, adj[i][j]);
            }
        }

        for (int level = minHeight; level < maxHeight; level++) {
            boolean[][] visited = new boolean[N][N];
            Queue<p2468Point> queue = new LinkedList<>();
            int count = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && adj[i][j] > level) {
                        count++;
                        visited[i][j] = true;
                        queue.add(new p2468Point(i, j));

                        while (!queue.isEmpty()) {
                            p2468Point prev = queue.poll();

                            for (int k = 0; k < 4; k++) {
                                int x = prev.x + dx[k];
                                int y = prev.y + dy[k];

                                if (x >= 0 && x < N && y >= 0 && y < N && !visited[x][y] && adj[x][y] > level) {
                                    queue.add(new p2468Point(x, y));
                                    visited[x][y] = true;
                                }
                            }
                        }
                    }
                }
            }

            result = Math.max(result, count);
        }

        System.out.println(result);
    }

    private static class p2468Point {
        int x;
        int y;

        public p2468Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void p9205() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer stringTokenizer;
        StringBuilder stringBuilder = new StringBuilder();

        while (T-- > 0) {
            int N = Integer.parseInt(bufferedReader.readLine());
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int hx = Integer.parseInt(stringTokenizer.nextToken());
            int hy = Integer.parseInt(stringTokenizer.nextToken());

            Queue<p9205Point> queue = new LinkedList<>();
            queue.add(new p9205Point(hx, hy, true));

            List<p9205Point> cList = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());

                int cx = Integer.parseInt(stringTokenizer.nextToken());
                int cy = Integer.parseInt(stringTokenizer.nextToken());

                cList.add(new p9205Point(cx, cy, false));
            }

            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int dx = Integer.parseInt(stringTokenizer.nextToken());
            int dy = Integer.parseInt(stringTokenizer.nextToken());

            boolean happy = false;
            while (!queue.isEmpty()) {
                p9205Point prevPoint = queue.poll();
                int dis = Math.abs(dx - prevPoint.x) + Math.abs(dy - prevPoint.y);
                if (dis <= 1000) {
                    happy = true;
                    break;
                }
                for (int i = 0; i < N; i++) {
                    p9205Point nextPoint = cList.get(i);
                    int dis2 = Math.abs(nextPoint.x - prevPoint.x) + Math.abs(nextPoint.y - prevPoint.y) ;
                    if (!nextPoint.visited && dis2 <= 1000) {
                        nextPoint.visited = true;
                        queue.add(new p9205Point(nextPoint.x, nextPoint.y, false));
                    }
                }
            }

            if (happy) {
                stringBuilder.append("happy").append("\n");
            } else {
                stringBuilder.append("sad").append("\n");
            }
        }

        System.out.println(stringBuilder);

    }

    private static class p9205Point {
        int x;
        int y;
        boolean visited;

        public p9205Point(int x, int y, boolean visited) {
            this.x = x;
            this.y = y;
            this.visited = visited;
        }
    }

    private static void p4963() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        StringBuilder stringBuilder = new StringBuilder();

        int dx[] = new int[]{0, 0, 1, -1, 1, 1, -1, -1};
        int dy[] = new int[]{-1, 1, 0, 0, -1, 1, -1, 1};

        while (true) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int h = Integer.parseInt(stringTokenizer.nextToken());
            int w = Integer.parseInt(stringTokenizer.nextToken());

            if (w == 0 && h == 0) {
                break;
            }

            int adj[][] = new int[w][h];

            for (int i = 0; i < w; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                for (int j = 0; j < h; j++) {
                    adj[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            int count = 0;

            Queue<p4963Point> queue = new LinkedList<>();

            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    if (adj[i][j] == 1) {
                        queue.add(new p4963Point(i, j));
                        adj[i][j] = -1;
                        count++;

                        while (!queue.isEmpty()) {
                            p4963Point prevPoint = queue.poll();

                            for (int k = 0; k < 8; k++) {
                                int currX = prevPoint.x + dx[k];
                                int currY = prevPoint.y + dy[k];

                                if (currX >= 0 && currX < w && currY >= 0 && currY < h && adj[currX][currY] == 1) {
                                    queue.add(new p4963Point(currX, currY));
                                    adj[currX][currY] = -1;
                                }
                            }
                        }
                    }
                }
            }

            stringBuilder.append(count).append("\n");
        }

        System.out.println(stringBuilder);
    }

    private static class p4963Point {
        int x;
        int y;

        public p4963Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void p14502() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());
        List<p14502Point> virusPoints = new ArrayList<>();
        int[][] adj = new int[N][M];
        int[] dx = new int[]{0, 0, 1, -1};
        int[] dy = new int[]{1, -1, 0, 0};
        int minCount = 100;
        int zeroCount = 0;

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                int val = Integer.parseInt(stringTokenizer.nextToken());
                adj[i][j] = val;
                if (val == 0) {
                    zeroCount++;
                } else if (val == 2) {
                    virusPoints.add(new p14502Point(i, j));
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (adj[i][j] == 0) {
                    boolean[][] visited = new boolean[N][M];
                    visited[i][j] = true;
                    for (int k = 0; k < N; k++) {
                        for (int l = 0; l < M; l++) {
                            if (!visited[k][l] && adj[k][l] == 0) {
                                boolean[][] visited2 = new boolean[N][M];
                                visited2[k][l] = true;

                                for (int m = 0; m < N; m++) {
                                    for (int n = 0; n < M; n++) {
                                        if (!visited[m][n] && !visited2[m][n] && adj[m][n] == 0) {
                                            int count = 0;
                                            boolean[][] visited3 = new boolean[N][M];
                                            visited3[m][n] = true;

                                            Queue<p14502Point> queue = new LinkedList<>();
                                            for (p14502Point v : virusPoints) {
                                                visited3[v.x][v.y] = true;
                                                queue.add(new p14502Point(v.x, v.y));
                                            }

                                            while (!queue.isEmpty()) {
                                                p14502Point prevPoint = queue.poll();

                                                for (int o = 0; o < 4; o++) {
                                                    int currX = prevPoint.x + dx[o];
                                                    int currY = prevPoint.y + dy[o];

                                                    if (currX >= 0 && currX < N && currY >= 0 && currY < M &&
                                                            !visited[currX][currY] && !visited2[currX][currY]
                                                            && !visited3[currX][currY] && adj[currX][currY] == 0) {
                                                        visited3[currX][currY] = true;
                                                        count++;
                                                        if (count > minCount) break;
                                                        queue.add(new p14502Point(currX, currY));
                                                    }
                                                }
                                            }

                                            minCount = Math.min(minCount, count);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println(zeroCount - minCount - 3);
    }

    private static class p14502Point {
        int x;
        int y;

        public p14502Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void p11724() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());

        boolean[] visited = new boolean[N + 1];

        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int u = Integer.parseInt(stringTokenizer.nextToken());
            int v = Integer.parseInt(stringTokenizer.nextToken());

            adj.get(u).add(v);
            adj.get(v).add(u);

        }



        int count = 0;

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {

            if (!visited[i]) {
                queue.add(i);
                visited[i] = true;
                count++;
            }

            while (!queue.isEmpty()) {
                Integer poll = queue.poll();

                List<Integer> edges = adj.get(poll);

                for (Integer edge: edges) {
                    if (!visited[edge]) {
                        visited[edge] = true;
                        queue.add(edge);
                    }
                }
            }
        }

        System.out.println(count);
    }

    private static void p14503() throws IOException {
        int count = 1;
        int[] dx = new int[]{-1, 0, 1, 0};
        int[] dy = new int[]{0, 1, 0, -1};

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int X = Integer.parseInt(stringTokenizer.nextToken());
        int Y = Integer.parseInt(stringTokenizer.nextToken());

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int r = Integer.parseInt(stringTokenizer.nextToken());
        int c = Integer.parseInt(stringTokenizer.nextToken());
        int d = Integer.parseInt(stringTokenizer.nextToken());

        int[][] adj = new int[X][Y];

        for (int i = 0; i < X; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            for (int j = 0; j < Y; j++) {
                int num = Integer.parseInt(stringTokenizer.nextToken());
                adj[i][j] = num;
            }
        }

        boolean done;
        while (true) {
            done = false;
            adj[r][c] = 2;

            for (int i = 0; i < 4; i++) {
                d = (d + 3) % 4;

                int currX = r + dx[d];
                int currY = c + dy[d];

                if (currY >= 0 && currY < Y && currX >= 0 && currX < X && adj[currX][currY] == 0) {
                    count++;
                    done = true;
                    r = r + dx[d];
                    c = c + dy[d];
                    break;
                }
            }

            if (!done) {
                int back = (d + 2) % 4;
                int backX = r + dx[back];
                int backY = c + dy[back];

                if (backY < 0 || backX < 0 || backX >= X || backY >= Y || adj[backX][backY] == 1) {
                    break;
                }

                if (adj[backX][backY] != 1) {
                    r = backX;
                    c = backY;
                }
            }
        }

        System.out.println(count);
    }

    private static void p1026() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());
        List<Integer> aList = new ArrayList<>();
        List<p1026Value> bList = new ArrayList<>();

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        for (int i = 0; i < N; i++) {
            aList.add(Integer.parseInt(stringTokenizer.nextToken()));
        }

        Collections.sort(aList);

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        for (int i = 0; i < N; i++) {
            bList.add(new p1026Value(Integer.parseInt(stringTokenizer.nextToken()), i));
        }

        bList.sort(Comparator.comparingInt(p1026Value::getNum).reversed());


        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += aList.get(i) * bList.get(i).num;
        }
        System.out.println(sum);
    }

    private static class p1026Value {
        int num;
        int index;

        public p1026Value(int num, int index) {
            this.num = num;
            this.index = index;
        }

        public int getNum() {
            return num;
        }

        public int getIndex() {
            return index;
        }
    }

    private static void p11399() throws IOException {
        BufferedReader bufferedReader =  new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int[] array = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(array);

        int index = 0;

        for (int i = 1; i < array.length; i++) {
            array[i] += array[i - 1];
        }

        int sum = IntStream.of(array).sum();

        System.out.println(sum);
    }

    private static void p2164() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int[] array = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = array[0];
        int K = array[1];

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            queue.add(i);
        }


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<");

        int count = 0;

        while (!queue.isEmpty()) {
            int num = queue.poll();
            count++;

            if (count == K) {
                count = 0;
                stringBuilder.append(num).append(", ");
            } else {
                queue.add(num);
            }
        }

        String s = stringBuilder.append(">").toString();
        s = s.replace(", >", ">");

        System.out.println(s);
    }

    private static void p4195() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bufferedReader.readLine());

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < T; i++) {

            Map<String, Integer> stringToInteger = new HashMap<>();
            int F = Integer.parseInt(bufferedReader.readLine());
            int[] parent = new int[F * 2];
            int[] nodeSum = new int[F * 2];

            for (int j = 0; j < F * 2; j++) {
                parent[j] = j;
                nodeSum[j] = 1;
            }

            for (int j = 0; j < F; j++) {
                String[] names = bufferedReader.readLine().split(" ");

                stringToInteger.computeIfAbsent(names[0], (name) -> stringToInteger.size());
                stringToInteger.computeIfAbsent(names[1], (name) -> stringToInteger.size());

                stringBuilder.append(p4195union(stringToInteger.get(names[0]), stringToInteger.get(names[1]), parent, nodeSum)).append("\n");
            }
        }

        System.out.println(stringBuilder);
    }

    private static Integer p4195union(Integer n1, Integer n2, int[] parent, int[] nodeSum) {
        Integer a = p4195find(n1, parent);
        Integer b = p4195find(n2, parent);

        if (!a.equals(b)) {
            parent[a] = b;
            nodeSum[b] = nodeSum[b] + nodeSum[a];
        }

        return nodeSum[b];
    }

    private static Integer p4195find(Integer name, int[] parent) {
        if (name == parent[name]) {
            return name;
        }

        Integer result = p4195find(parent[name], parent);
        parent[name] = result;
        return result;
    }

    private static void p1707() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < K; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int V = Integer.parseInt(stringTokenizer.nextToken());
            int E = Integer.parseInt(stringTokenizer.nextToken());
            int[] status = new int[V + 1]; // 0 = not visited, 1 = odd, 2 = even

            List<List<Integer>> adj = new ArrayList<>();

            for (int j = 0; j <= V; j++) {
                adj.add(new ArrayList<>());
            }

            for (int j = 0; j < E; j++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int u = Integer.parseInt(stringTokenizer.nextToken());
                int v = Integer.parseInt(stringTokenizer.nextToken());

                adj.get(u).add(v);
                adj.get(v).add(u);

            }

            Queue<Integer> queue = new LinkedList<>();
            boolean loopFlag = true;
            for (int j = 1; j < V + 1; j++) {
                if (!loopFlag) break;
                if (status[j] == 0) {
                    queue.add(j);
                    status[j] = 1;
                }

                while (!queue.isEmpty() && loopFlag) {
                    Integer prevPoint = queue.poll();

                    List<Integer> edges = adj.get(prevPoint);

                    for (Integer edge: edges) {
                        if (status[edge] == 0) {
                            status[edge] = status[prevPoint] == 1 ? 2 : 1;
                            queue.add(edge);
                        } else if (status[edge] != 0 && status[edge] == status[prevPoint]) {
                            stringBuilder.append("NO").append("\n");
                            loopFlag = false;
                            break;
                        }
                    }
                }
            }

            if (loopFlag) {
                stringBuilder.append("YES").append("\n");
            }

        }

        System.out.println(stringBuilder);
    }

    private static void p1697() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] array = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int MAX = 100001;
        int N = array[0];
        int K = array[1];
        int[] dx = new int[]{1, -1};
        int count = 0;
        boolean[] visited = new boolean[MAX];

        Queue<p1697Point> queue = new LinkedList<>();
        queue.add(new p1697Point(N, 0));
        visited[N] = true;
        boolean loopFlag = true;

        while (!queue.isEmpty() && loopFlag) {
            p1697Point prevPoint = queue.poll();

            for (int i = 0; i < 3; i++) {
                int currX;

                if (i == 2) {
                    currX = prevPoint.x * 2;
                } else {
                    currX = prevPoint.x + dx[i];
                }

                if (currX < 0 || currX >= MAX) {
                    continue;
                }

                if (!visited[currX]) {
                    if (currX == K) {
                        count = prevPoint.count + 1;
                        loopFlag = false;
                        break;
                    }

                    visited[currX] = true;
                    queue.add(new p1697Point(currX, prevPoint.count + 1));
                }
            }

        }

        System.out.println(count);
    }

    private static class p1697Point {
        int x;
        int count;

        public p1697Point(int x, int count) {
            this.x = x;
            this.count = count;
        }
    }

    private static void p1021() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());

        int count = 0;

        LinkedList<Integer> linkedList = new LinkedList<>();
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        for (int i = 1; i <= N; i++) {
            linkedList.add(i);
        }

        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(stringTokenizer.nextToken());

            Integer currentNum = linkedList.peek();
            if (currentNum == target) {
                linkedList.pollFirst();
            } else {
                int targetIndex = linkedList.indexOf(target);

                int mid = linkedList.size() / 2;
                if (mid < targetIndex) {
                    while (linkedList.peek() != target) {
                        count++;
                        Integer pop = linkedList.pollLast();
                        linkedList.addFirst(pop);
                    }
                } else {
                    while (linkedList.peek() != target) {
                        count++;
                        linkedList.addLast(linkedList.pollFirst());
                    }
                }

                linkedList.pollFirst();
            }
        }

        System.out.println(count);
    }

    private static void p1966() throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            // T 입력
            int T = Integer.parseInt(bufferedReader.readLine());
            StringBuilder stringBuilder = new StringBuilder();
            for (int t = 0; t < T; t++) {
                // 개수 / 몇번째에 있는지
                int[] array = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int N = array[0];
                int M = array[1];

                // Queue에 순서대로 넣기 -> 중요도를 그대로 넣자
                Queue<p1966Task> queue = new LinkedList<>();
                StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

                for (int i = 0; i < N; i++) {
                    queue.add(new p1966Task(Integer.parseInt(stringTokenizer.nextToken()), i == M));
                }

                int count = 0;

                // max를 사용해서 본인이면 출력 아니면 맨 뒤로 넣기
                while (!queue.isEmpty()) {
                    p1966Task max = queue.stream()
                            .max(Comparator.comparingInt(o -> o.weight))
                            .get();
                    p1966Task poll = queue.poll();

                    if (max.weight != poll.weight) {
                        queue.add(poll);
                    } else {
                        count++;
                        if (poll.isTarget) {
                            stringBuilder.append(count).append("\n");
                            break;
                        }
                    }
                }
            }

            System.out.println(stringBuilder);
    }

    private static class p1966Task {
        int weight;
        boolean isTarget;

        public p1966Task(int weight, boolean isTarget) {
            this.weight = weight;
            this.isTarget = isTarget;
        }
    }

    private static void p18258() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());

        Deque<String> queue = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < N; i++) {
            String s = bufferedReader.readLine();

            if (s.startsWith("push")) {
                queue.add(s.split(" ")[1]);
            } else if (s.equals("pop")) {
                String poll = queue.poll();
                if (poll == null) {
                    stringBuilder.append("-1").append("\n");
                } else {
                    stringBuilder.append(poll).append("\n");
                }
            } else if (s.equals("size")) {
                stringBuilder.append(queue.size()).append("\n");
            } else if (s.equals("empty")) {
                stringBuilder.append(queue.isEmpty() ? 1 : 0).append("\n");
            } else if (s.equals("front")) {
                stringBuilder.append(queue.peek() != null ? queue.peek() : -1).append("\n");
            } else if (s.equals("back")) {
                try {
                    stringBuilder.append(queue.getLast()).append("\n");
                } catch (NoSuchElementException e) {
                    stringBuilder.append(-1).append("\n");
                }

            }
        }

        System.out.println(stringBuilder);
    }

    private static void p28528() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String s = bufferedReader.readLine();
        String[] operators = s.split("[0-9]{1,5}");
        String[] numbers = s.split("[//+//-]");

        int index = Arrays.asList(operators).indexOf("-");

        int result;

        if (index > -1) {
            int plusSum = Arrays.stream(numbers).limit(index).mapToInt(Integer::parseInt).sum();
            int minusSum = Arrays.stream(numbers).skip(index).mapToInt(Integer::parseInt).sum();

            result = plusSum - minusSum;
        } else {
            result = Arrays.stream(numbers).mapToInt(Integer::parseInt).sum();
        }


        System.out.println(result);
    }


    private static void p9012() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());

        StringBuilder stringBuilder = new StringBuilder();
        Stack<Character> stack;
        for (int i = 0; i < N; i++) {
            boolean vps = true;
            stack = new Stack<>();
            String s = bufferedReader.readLine();
            for (char c: s.toCharArray()) {
                if (c == '(') {
                    stack.add(c);
                } else {
                    try {
                        stack.pop();
                    } catch (Exception e) {
                        vps = false;
                        break;
                    }
                }
            }

            if (vps && stack.isEmpty()) {
                stringBuilder.append("YES").append("\n");
            } else {
                stringBuilder.append("NO").append("\n");
            }


        }

        System.out.println(stringBuilder);
    }


    private static class p2206Val {
        int x;
        int y;
        int count;

        public p2206Val(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    private static void p2206() throws IOException {
        int[] dx = new int[]{0, 0, -1, 1};
        int[] dy = new int[]{1, -1, 0, 0};
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = 1;

        int[][] world = new int[n][m];
        for (int x = 0; x < n; x++) {
            String line = br.readLine();
            for (int y = 0; y < m; y++) {
                world[x][y] = line.charAt(y) - '0';
            }
        }

        int[][] broke = new int[n][m];

        for (int[] b : broke){
            Arrays.fill(b, Integer.MAX_VALUE);
        }

        Queue<p2206Val> queue = new LinkedList<>();
        broke[0][0] = 0;
        queue.add(new p2206Val(0, 0, 1));
        int answer = -1;
        while (!queue.isEmpty()) {
            p2206Val curr = queue.poll();

            int cx = curr.x;
            int cy = curr.y;
            int count = curr.count + 1;

            if (cx == n - 1 && cy == m - 1) {
                answer = curr.count;
                break;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nx = cx + dx[dir];
                int ny = cy + dy[dir];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                int nextBrake = broke[cx][cy] + world[nx][ny];
                if (nextBrake > k || broke[nx][ny] <= nextBrake) continue;
                broke[nx][ny] = nextBrake;
                queue.add(new p2206Val(nx, ny, count));
            }
        }

        System.out.print(answer);
    }

    private static class p16928Val {
        public int x;
        public int count;

        public p16928Val(int x, int count) {
            this.x = x;
            this.count = count;
        }
    }

    private static void p16928() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());

        int[] ladders = new int[101];
        int[] crocs = new int[101];
        boolean[] visited = new boolean[101];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());

            ladders[x] = y;
        }

        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());

            crocs[x] = y;
        }

        Queue<p16928Val> queue = new LinkedList<>();
        queue.add(new p16928Val(1, 0));
        int count = 0;
        while (!queue.isEmpty() && count == 0) {
            p16928Val poll = queue.poll();

            for (int i = 1; i <= 6 ; i++) {
                if (poll.x >= 94) {
                    count = poll.count + 1;
                    break;
                }

                int currX = poll.x + i;

                if (ladders[currX] != 0) {
                    currX = ladders[currX];
                }

                if (crocs[currX] != 0) {
                    currX = crocs[currX];
                }

                if (!visited[currX]) {
                    visited[currX] = true;
                    queue.add(new p16928Val(currX, poll.count + 1));
                }
            }
        }

        System.out.println(count);

    }

    private static void p4949() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Stack<Character> stack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();
        boolean checked;
        while (true) {
            String s = bufferedReader.readLine();
            checked = false;

            if (s.equals(".")) {
                break;
            }

            for (char c: s.toCharArray()) {
                if (c == '(' || c == '[') {
                    stack.add(c);
                } else if (c == ')') {
                    if (stack.isEmpty() || stack.pop() != '(') {
                        stringBuilder.append("no").append("\n");
                        checked = true;
                        break;
                    }
                } else if (c == ']') {
                    if (stack.isEmpty() || stack.pop() != '[') {
                        stringBuilder.append("no").append("\n");
                        checked = true;
                        break;
                    }
                }
            }

            if (!checked && !stack.isEmpty()) {
                stringBuilder.append("no").append("\n");
                checked = true;
            }

            if (!checked) {
                stringBuilder.append("yes").append("\n");
            }
            stack.clear();
        }

        System.out.println(stringBuilder);
    }

    private static void p1874() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int number = 1;

        StringBuilder stringBuilder = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(bufferedReader.readLine());

            if (stack.isEmpty()) {
                stack.add(number++);
                stringBuilder.append("+").append("\n");
            }

            if (stack.peek() > n) {
                stringBuilder.delete(0, stringBuilder.length());
                stringBuilder.append("NO").append("\n");;
                break;
            }

            while (stack.peek() < n) {
                stringBuilder.append("+").append("\n");;
                stack.add(number++);
            }

            if (stack.peek() == n) {
                stringBuilder.append("-").append("\n");;
                stack.pop();
            }
        }

        System.out.println(stringBuilder);
    }

    private static class p7569Value {
        int x;
        int y;
        int z;
        int count;

        public p7569Value(int x, int y, int z, int count) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.count = count;
        }
    }

    private static void p7569() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int M = Integer.parseInt(stringTokenizer.nextToken());
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int H = Integer.parseInt(stringTokenizer.nextToken());
        int[] dx = new int[]{0, 0, 1 , -1, 0, 0};
        int[] dy = new int[]{-1, 1, 0 , 0, 0, 0};
        int[] dz = new int[]{0, 0, 0, 0, -1, 1};
        int count = 0;

        int[][][] adj = new int[H][N][M];
        boolean[][][] visited = new boolean[H][N][M];

        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                for (int j = 0; j < M; j++) {
                    adj[k][i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }
        }

        Queue<p7569Value> queue = new LinkedList<>();
        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (adj[k][i][j] == 1) {
                        queue.add(new p7569Value(i, j, k, 0));
                        visited[k][i][j] = true;
                    }
                }
            }
        }

        while (!queue.isEmpty()) {
            p7569Value point = queue.poll();

            for (int i = 0; i < 6; i++) {
                int currX = point.x + dx[i];
                int currY = point.y + dy[i];
                int currZ = point.z + dz[i];

                if (currX >= N || currY >= M || currZ >= H || currX < 0 || currY < 0 || currZ < 0) {
                    continue;
                }

                if (!visited[currZ][currX][currY] && adj[currZ][currX][currY] == 0) {
                    visited[currZ][currX][currY] = true;
                    adj[currZ][currX][currY] = 1;
                    queue.add(new p7569Value(currX, currY, currZ, point.count + 1));
                    count = point.count + 1;
                }
            }
        }

        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (adj[k][i][j] == 0) {
                        count = -1;
                        break;
                    }
                }
            }
        }



        System.out.println(count);
    }

    private static void p10828() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        Stack<Integer> stack = new Stack<>();

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String s = bufferedReader.readLine();

            if (s.startsWith("push")) {
                stack.add(Integer.parseInt(s.split(" ")[1]));
            } else if (s.equals("pop")) {
                try {
                    stringBuilder.append(stack.pop()).append("\n");
                } catch (EmptyStackException e) {
                    stringBuilder.append("-1").append("\n");
                }
            } else if (s.equals("size")) {
                stringBuilder.append(stack.size()).append("\n");
            } else if (s.equals("empty")) {
                stringBuilder.append(stack.isEmpty() ? "1" : "0").append("\n");
            } else if (s.equals("top")) {
                try {
                    stringBuilder.append(stack.peek()).append("\n");
                } catch (EmptyStackException e) {
                    stringBuilder.append("-1").append("\n");
                }

            }
        }

        System.out.println(stringBuilder);
    }

    private static void p10773() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            int k = Integer.parseInt(bufferedReader.readLine());
            if (k == 0) {
                stack.pop();
            } else {
                stack.add(k);
            }
        }

        System.out.println(stack.stream().mapToInt(Integer::intValue).sum());
    }

    private static void p20040() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());

        int[] parent = IntStream.iterate(0, i -> i + 1).limit(N).toArray();
        int count = 0;
        for (int i = 1; i <= M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int u = Integer.parseInt(stringTokenizer.nextToken());
            int v = Integer.parseInt(stringTokenizer.nextToken());

            int result = p20040Union(u, v, parent);

            if (result == -1) {
                count = i;
                break;
            }

        }

        System.out.println(count);

    }

    private static int p20040Find(int x, int[] parent) {
        if (x == parent[x]) {
            return x;
        }

        int result = p20040Find(parent[x], parent);
        parent[x] = result;
        return result;
    }

    private static int p20040Union(int a, int b, int[] parent) {
        int x = p20040Find(a, parent);
        int y = p20040Find(b, parent);

        if (x != y) {
            return parent[x] = y;
        } else {
            return -1;
        }
    }

    private static class p7576Value {
        int x;
        int y;
        int count;

        public p7576Value(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    private static void p7576() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int[] dx = new int[]{0, 0, -1, 1};
        int[] dy = new int[]{1, -1, 0, 0};

        int M = Integer.parseInt(stringTokenizer.nextToken());
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int[][] adj = new int[N][M];
        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                adj[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        int count = 0;

        Queue<p7576Value> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (adj[i][j] == 1) {
                    queue.add(new p7576Value(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        boolean validCount = false;
        boolean c = true;

        while (!queue.isEmpty()) {
            p7576Value point = queue.poll();

            for (int k = 0; k < 4; k++) {
                int currX = point.x + dx[k];
                int currY = point.y + dy[k];

                if (currX >= N || currX < 0 || currY >= M || currY < 0 || adj[currX][currY] != 0) {
                    continue;
                }

                if (!visited[currX][currY]) {
                    validCount = true;
                    visited[currX][currY] = true;
                    adj[currX][currY] = 1;
                    queue.add(new p7576Value(currX, currY, point.count + 1));
                    count = point.count + 1;
                }
            }
        }

        if (!validCount) {
            count = 0;
        }

        boolean allChecked = true;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (adj[i][j] == 0) {
                    allChecked = false;
                }
            }
        }

        System.out.println(allChecked ? count : -1);

    }

    private static void p1012() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        int T = Integer.parseInt(bufferedReader.readLine());
        int[] dx = new int[]{0, 0, -1, 1};
        int[] dy = new int[]{1, -1, 0, 0};

        for (int i = 0; i < T; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int N = Integer.parseInt(stringTokenizer.nextToken());
            int M = Integer.parseInt(stringTokenizer.nextToken());
            int K = Integer.parseInt(stringTokenizer.nextToken());

            int[][] adj = new int[N][M];
            boolean[][] visited = new boolean[N][M];

            for (int j = 0; j < K; j++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());

                int x = Integer.parseInt(stringTokenizer.nextToken());
                int y = Integer.parseInt(stringTokenizer.nextToken());

                adj[x][y] = 1;
            }

            int count = 0;
            Queue<Point> queue = new LinkedList<>();

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (adj[j][k] == 1 && !visited[j][k]) {
                        queue.add(new Point(j, k));

                        while (!queue.isEmpty()) {
                            Point poll = queue.poll();

                            for (int l = 0; l < 4; l++) {
                                int currX = poll.x + dx[l];
                                int currY = poll.y + dy[l];

                                if (currX < 0 || currY < 0 || currX >= N || currY >= M) {
                                    continue;
                                }

                                if (adj[currX][currY] == 1 && !visited[currX][currY]) {
                                    visited[currX][currY] = true;
                                    queue.add(new Point(currX, currY));
                                }

                            }
                        }
                        count++;
                    }
                }
            }

            System.out.println(count);
        }
    }

    private static void p7562() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        int[] dx = new int[]{2, 2, -2, -2, 1, -1, 1, -1};
        int[] dy = new int[]{1, -1, 1, -1, 2, 2, -2, -2};

        for (int i = 0; i < N; i++) {
            int I = Integer.parseInt(bufferedReader.readLine());
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int[][] visited = new int[I][I];

            Point start = new Point(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));

            visited[start.x][start.y] = 1;
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            Point end = new Point(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));

            Queue<Point> queue = new LinkedList<>();
            queue.add(start);

            while (!queue.isEmpty()) {
                Point startPoint = queue.poll();

                for (int j = 0; j < 8; j++) {
                    int currX = startPoint.x + dx[j];
                    int currY = startPoint.y + dy[j];

                    if (currX >= I || currX < 0 || currY >= I || currY < 0) {
                        continue;
                    }

                    if (visited[currX][currY] == 0) {
                        visited[currX][currY] = visited[startPoint.x][startPoint.y] + 1;
                        queue.add(new Point(currX, currY));
                    }
                }
            }

            System.out.println(visited[end.x][end.y] - 1);
        }
    }

    private static class p2178Value {
        public int x;
        public int y;
        public int count;

        public p2178Value(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    private static void p2178() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);

        int[][] adj = new int[N][M];
        int[][] count = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;

        for (int i = 0; i < N; i++) {
            int[] array = Arrays.stream(bufferedReader.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < M; j++) {
                adj[i][j] = array[j];
            }
        }

        Queue<p2178Value> queue = new LinkedList<>();
        queue.add(new p2178Value(0, 0, 1));

        while (!queue.isEmpty()) {
            p2178Value vertex = queue.poll();

            int i = vertex.x;
            int j = vertex.y;

            if (i - 1 >= 0
                    && adj[i - 1][j] == 1
                    && !visited[i - 1][j]) {
                queue.add(new p2178Value(i - 1, j, vertex.count + 1));
                count[i - 1][j] = vertex.count + 1;
                visited[i - 1][j] = true;
            }

            if (i + 1 < N
                    && adj[i + 1][j] == 1
                    && (!visited[i + 1][j])) {
                queue.add(new p2178Value(i + 1, j, vertex.count + 1));
                count[i + 1][j] = vertex.count + 1;
                visited[i + 1][j] = true;
            }

            if (j + 1 < M
                    && adj[i][j + 1] == 1
                    && (!visited[i][j + 1])) {
                queue.add(new p2178Value(i, j + 1, vertex.count + 1));
                count[i][j + 1] = vertex.count + 1;
                visited[i][j + 1] = true;
            }

            if (j - 1 >= 0
                    && adj[i][j - 1] == 1
                    && (!visited[i][j - 1])) {
                queue.add(new p2178Value(i, j - 1, vertex.count + 1));
                count[i][j - 1] = vertex.count + 1;
                visited[i][j - 1] = true;
            }
        }

        System.out.println(count[N - 1][M - 1]);
    }

    private static void p2667() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        int groupCount = 0;
        List<Integer> countList = new ArrayList<>();

        int[][] adj = new int[N][N];
        boolean[][] visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            int[] array = Arrays.stream(bufferedReader.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < N; j++) {
                adj[i][j] = array[j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (adj[i][j] == 1 && visited[i][j] == false) {
                    groupCount++;
                    Point p = new Point(i, j);
                    countList.add(p2667BFS(p, adj, visited, N));
                }
            }
        }

        System.out.println(groupCount);

        countList.stream()
                .sorted()
                .forEach(System.out::println);

    }

    private static int p2667BFS(Point p, int[][] adj, boolean[][] visited, int N) {
        int count = 1;
        Queue<Point> queue = new LinkedList<>();
        queue.add(p);
        visited[p.x][p.y] = true;


        while (!queue.isEmpty()) {
            Point currPoint = queue.poll();
            // 좌
            if (currPoint.x - 1 >= 0 && !visited[currPoint.x - 1][currPoint.y] && adj[currPoint.x - 1][currPoint.y] == 1) {
                count++;
                queue.add(new Point(currPoint.x - 1, currPoint.y));
                visited[currPoint.x - 1][currPoint.y] = true;
            }
            // 우
            if (currPoint.x + 1 < N && !visited[currPoint.x + 1][currPoint.y] && adj[currPoint.x + 1][currPoint.y] == 1) {
                count++;
                queue.add(new Point(currPoint.x + 1, currPoint.y));
                visited[currPoint.x + 1][currPoint.y] = true;
            }
            // 상
            if (currPoint.y - 1 >= 0 && !visited[currPoint.x][currPoint.y - 1] && adj[currPoint.x][currPoint.y - 1] == 1) {
                count++;
                queue.add(new Point(currPoint.x, currPoint.y - 1));
                visited[currPoint.x][currPoint.y - 1] = true;
            }
            // 하
            if (currPoint.y + 1 < N && !visited[currPoint.x][currPoint.y + 1] && adj[currPoint.x][currPoint.y + 1] == 1) {
                count++;
                queue.add(new Point(currPoint.x, currPoint.y + 1));
                visited[currPoint.x][currPoint.y + 1] = true;
            }
        }

        return count;
    }

    private static void p1260() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());
        int R = Integer.parseInt(stringTokenizer.nextToken());

        List<List<Integer>> adj = new ArrayList<>(N + 1);
        boolean[] visitedDFS = new boolean[N + 1];
        boolean[] visitedBFS = new boolean[N + 1];

        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int u = Integer.parseInt(stringTokenizer.nextToken());
            int v = Integer.parseInt(stringTokenizer.nextToken());

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        for (List<Integer> l : adj) {
            Collections.sort(l);
        }

        StringBuilder stringBuilderDFS = new StringBuilder();
        StringBuilder stringBuilderBFS = new StringBuilder();

        p1260DFSImpl(R, visitedDFS, adj, stringBuilderDFS);
        System.out.println(stringBuilderDFS);

        stringBuilderBFS.append(R).append(" ");
        visitedBFS[R] = true;
        p1260BFSImpl(R, visitedBFS, adj, stringBuilderBFS);
        System.out.println(stringBuilderBFS);
    }

    private static void p1260DFSImpl(int vertex, boolean[] visited, List<List<Integer>> adj, StringBuilder stringBuilder) {
        visited[vertex] = true;
        stringBuilder.append(vertex).append(" ");

        List<Integer> edges = adj.get(vertex);
        for (int edge : edges) {
            if (!visited[edge]) {
                p1260DFSImpl(edge, visited, adj, stringBuilder);
            }
        }
    }

    private static void p1260BFSImpl(int vertex, boolean[] visited, List<List<Integer>> adj, StringBuilder stringBuilder) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(vertex);

        while (!queue.isEmpty()) {
            Integer v = queue.poll();

            List<Integer> edges = adj.get(v);
            for (Integer edge : edges) {
                if (!visited[edge]) {
                    visited[edge] = true;
                    stringBuilder.append(edge).append(" ");
                    queue.add(edge);
                }
            }
        }
    }

    private static void p24480() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());
        int R = Integer.parseInt(stringTokenizer.nextToken());

        List<List<Integer>> adj = new ArrayList<>();
        boolean[] visited = new boolean[N + 1];
        int[] sequence = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int u = Integer.parseInt(stringTokenizer.nextToken());
            int v = Integer.parseInt(stringTokenizer.nextToken());

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        for (List<Integer> l : adj) {
            l.sort(Comparator.reverseOrder());
        }

        p24480DFSImpl(R, visited, sequence, adj);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            stringBuilder.append(sequence[i]).append("\n");
        }

        System.out.println(stringBuilder);

    }

    private static void p24480DFSImpl(int vertex, boolean[] visited, int[] sequence, List<List<Integer>> adj) {
        visited[vertex] = true;
        sequence[vertex] = p24480Count++;

        List<Integer> edges = adj.get(vertex);

        for (int edge : edges) {
            if (!visited[edge]) {
                p24480DFSImpl(edge, visited, sequence, adj);
            }
        }
    }

    private static void p24479() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());
        int R = Integer.parseInt(stringTokenizer.nextToken());
        boolean[] visited = new boolean[N + 1];
        int[] sequence = new int[N + 1];

        List<List<Integer>> adj = new ArrayList<>(N + 1);

        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
        }

        // adj 생성
        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int u = Integer.parseInt(stringTokenizer.nextToken());
            int v = Integer.parseInt(stringTokenizer.nextToken());

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // adj 오름차순 정렬
        for (List<Integer> l : adj) {
            Collections.sort(l);
        }

        p24479DFSImpl(R, visited, sequence, adj);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            stringBuilder.append(sequence[i]).append("\n");
        }

        System.out.println(stringBuilder);

    }

    private static void p24479DFSImpl(int vertex, boolean[] visited, int[] sequence, List<List<Integer>> adj) {
        visited[vertex] = true;
        sequence[vertex] = p24479Count++;

        List<Integer> edges = adj.get(vertex);

        for (int edge : edges) {
            if (!visited[edge]) {
                p24479DFSImpl(edge, visited, sequence, adj);
            }
        }
    }

    private static void p24444() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());
        int R = Integer.parseInt(stringTokenizer.nextToken());
        boolean[] visited = new boolean[N + 1];
        int[] sequence = new int[N + 1];
        int count = 1;

        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int u = Integer.parseInt(stringTokenizer.nextToken());
            int v = Integer.parseInt(stringTokenizer.nextToken());
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        for (List<Integer> l : adj) {
            l.sort(Comparator.comparingInt(Integer::intValue));
        }


        Queue<Integer> queue = new LinkedList<>();
        queue.add(R);
        visited[R] = true;
        sequence[R] = count;

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();

            List<Integer> edges = adj.get(poll);

            for (int edge : edges) {
                if (!visited[edge]) {
                    visited[edge] = true;
                    queue.add(edge);
                    sequence[edge] = ++count;
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i < N + 1; i++) {
            stringBuilder.append(sequence[i]).append("\n");
        }

        System.out.println(stringBuilder);
        bufferedReader.close();

    }

    private static void p24445() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());
        int R = Integer.parseInt(stringTokenizer.nextToken());

        boolean[] visited = new boolean[N + 1];
        visited[R] = true;
        int[] sequence = new int[N + 1];
        int count = 1;
        sequence[R] = 1;


        List<Integer>[] adj = new ArrayList[N + 1];

        for (int i = 0; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
        }


        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        for (List<Integer> l : adj) {
            l.sort(Comparator.comparingInt(Integer::intValue).reversed());
        }

        Queue<Integer> queue = new LinkedList<>();

        queue.add(R);

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();

            for (int i : adj[poll]) {
                if (!visited[i]) {
                    visited[i] = true;
                    sequence[i] = ++count;
                    queue.add(i);
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            stringBuilder.append(sequence[i]).append("\n");
        }

        System.out.println(stringBuilder.toString());
        bufferedReader.close();
    }


    private static void p2606DFS() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());
        int M = Integer.parseInt(bufferedReader.readLine());

        boolean[] visited = new boolean[N + 1];

        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int[] array = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            adj.get(array[0]).add(array[1]);
            adj.get(array[1]).add(array[0]);
        }

        p2606DfsImpl(1, visited, adj);

        System.out.println(p2606Count);
    }

    private static void p2606DfsImpl(int nodeNum, boolean[] visited, List<List<Integer>> adj) {
        visited[nodeNum] = true;
        p2606Count++;

        List<Integer> integers = adj.get(nodeNum);

        for (int i : integers) {
            if (!visited[i]) {
                p2606DfsImpl(i, visited, adj);
            }
        }
    }

    private static void p2606BFS() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());
        int M = Integer.parseInt(bufferedReader.readLine());

        boolean[] visited = new boolean[N + 1];
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int[] array = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            adj.get(array[0]).add(array[1]);
        }

        int count = 0;

        Queue<Integer> stack = new LinkedList<>();

        stack.add(1);

        while (!stack.isEmpty()) {
            Integer num = stack.poll();
            visited[num] = true;

            for (Integer n : adj.get(num)) {
                if (!visited[n]) {
                    visited[n] = true;
                    stack.add(n);
                    count++;
                }
            }
        }

        System.out.println(count);


    }

    private static int findp2606(int x, int[] arr) {
        if (x == arr[x]) {
            return x;
        }

        int result = findp2606(arr[x], arr);
        arr[x] = result;
        return result;
    }

    private static void unionp2606(int x, int y, int[] arr) {
        int a = findp2606(x, arr);
        int b = findp2606(y, arr);

        if (a != b) {
            arr[a] = b;
        }
    }

    private static void p13305() throws IOException {


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());
        int[] distances = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] prices = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        long cost = 0;
        long currentPrice = prices[0];

        for (int i = 0; i < N - 1; i++) {
            if (currentPrice > prices[i + 1]) {
                cost += currentPrice * distances[i];
                currentPrice = prices[i + 1];
            } else {
                cost += currentPrice * distances[i];
            }
        }

        System.out.println(cost);

    }

    private static void p11047() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] s = bufferedReader.readLine().split(" ");

        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);

        int change = M;
        int count = 0;

        List<Integer> coins = Stream.iterate(0, i -> i + 1)
                .limit(N)
                .map(i -> {
                    try {
                        return Integer.parseInt(bufferedReader.readLine());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList());

        for (int i = N - 1; i >= 0; i--) {
            if (change == 0) {
                break;
            }
            int coin = coins.get(i);
            int divider = change / coin;

            if (divider >= 1) {
                change -= coin * divider;
                count += divider;
            }

        }

        System.out.println(count);
    }


    private static void p1931() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());

        List<Meeting1931> meetings = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int[] array = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            meetings.add(new Meeting1931(array[0], array[1]));
        }

        meetings.sort(Comparator.comparing(Meeting1931::getEnd)
                .thenComparing(Meeting1931::getStart));

        int count = 0;
        int last = 0;

        for (Meeting1931 meeting : meetings) {
            if (meeting.getStart() >= last) {
                last = meeting.getEnd();
                count++;
            }
        }

        System.out.println(count);

    }

    static class Meeting1931 {
        int start;
        int end;

        public Meeting1931(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }
    }

    private static void p1976() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int M = Integer.parseInt(bufferedReader.readLine());

        int[] parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            int[] array = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int j = 1; j <= array.length; j++) {
                if (array[j - 1] == 1) {
                    if (parent[i] == parent[j]) {
                        continue;
                    }

                    p1976Union(i, j, parent);

                }
            }
        }

        String result = null;
        int value = -1;
        int[] destination = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < M; i++) {
            int root = p1976Find(destination[i], parent);
            if (value == -1) {
                value = root;
            } else if (value != root) {
                result = "NO";
                break;
            }
        }
        if (result == null) {
            result = "YES";
        }
        System.out.println(result);

    }

    private static void p1976Union(int x, int y, int[] parent) {
        int a = p1976Find(x, parent);
        int b = p1976Find(y, parent);

        if (a != b) {
            parent[a] = b;
        }
    }

    private static int p1976Find(int x, int[] parent) {
        if (parent[x] == x) {
            return x;
        }

        int result = p1976Find(parent[x], parent);
        parent[x] = result;
        return result;
    }

    private static void p10775() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(bufferedReader.readLine());

        int[] arr = new int[G + 1];

        for (int i = 1; i <= G; i++) {
            arr[i] = i;
        }

        int P = Integer.parseInt(bufferedReader.readLine());

        int count = 0;
        for (int i = 0; i < P; i++) {
            int N = Integer.parseInt(bufferedReader.readLine());

            int availableGate = find(N, arr);

            if (availableGate == 0) {
                break;
            }

            count++;

            union(availableGate, availableGate - 1, arr);

        }

        System.out.println(count);
    }

    private static int find(int x, int[] arr) {
        if (x == arr[x]) {
            return x;
        }

        return arr[x] = find(arr[x], arr);
    }

    private static void union(int x, int xMinus1, int[] arr) {
        int a = find(x, arr);
        int b = find(xMinus1, arr);

        if (a != b) {
            arr[a] = b;
        }
    }


    private static void p1002() throws IOException {
        // read
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < N; i++) {
            int[] array = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int x1 = array[0];
            int y1 = array[1];
            int r1 = array[2];
            int x2 = array[3];
            int y2 = array[4];
            int r2 = array[5];

            if (x1 == x2 && y1 == y2 && r1 == r2) {
                System.out.println(-1);
                continue;
            }

            double originDistance = Math.sqrt(Math.pow(Math.abs(x2 - x1), 2) + Math.pow(Math.abs(y2 - y1), 2));
            int radiusSum = r1 + r2;

            int bigR = Math.max(r1, r2);
            int smallR = Math.min(r1, r2);

            if (originDistance <= bigR) {
                if (originDistance + smallR > bigR) {
                    System.out.println(2);
                } else if (originDistance + smallR == bigR) {
                    System.out.println(1);
                } else {
                    System.out.println(0);
                }
            } else {
                if (radiusSum > originDistance) {
                    System.out.println(2);
                } else if (radiusSum == originDistance) {
                    System.out.println(1);
                } else {
                    System.out.println(0);
                }
            }
        }
    }

    private static void p1004() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < N; i++) {
            int[] inputArray = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int x1 = inputArray[0];
            int y1 = inputArray[1];
            int x2 = inputArray[2];
            int y2 = inputArray[3];

            int M = Integer.parseInt(bufferedReader.readLine());

            int startCount = 0;
            int destCount = 0;
            int overlapCount = 0;

            for (int j = 0; j < M; j++) {
                int[] circleArray = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int xc = circleArray[0];
                int yc = circleArray[1];
                int rc = circleArray[2];

                boolean isOverlapped = false;
                if (Math.sqrt(Math.pow(Math.abs(xc - x1), 2) + Math.pow(Math.abs(yc - y1), 2)) <= rc) {
                    isOverlapped = true;
                    startCount++;
                }

                if (Math.sqrt(Math.pow(Math.abs(xc - x2), 2) + Math.pow(Math.abs(yc - y2), 2)) <= rc) {
                    if (isOverlapped) overlapCount++;
                    destCount++;
                }
            }

            System.out.println(startCount + destCount - (overlapCount * 2));
        }
    }

    private static void p1358() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int[] inputs = Arrays
                .stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int W = inputs[0];
        int H = inputs[1];
        int X = inputs[2];
        int Y = inputs[3];
        int P = inputs[4];
        int R = H / 2;
        int X2 = X + W;
        int Y2 = Y + H;

        int count = 0;

        for (int i = 0; i < P; i++) {
            int[] point = Arrays.stream(bufferedReader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int x = point[0];
            int y = point[1];

            if ((x >= X && x <= X2) && (y >= Y && y <= Y2)) {
                count++;
            } else if (x < X) {
                if (Math.sqrt(Math.pow(Math.abs(X - x), 2) + Math.pow(Math.abs(Y + R - y), 2)) <= R) {
                    count++;
                }
            } else if (x > X2) {
                if (Math.sqrt(Math.pow(Math.abs(X2 - x), 2) + Math.pow(Math.abs(Y + R - y), 2)) <= R) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    private static void p3053() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        double R = Double.parseDouble(bufferedReader.readLine());

        DecimalFormat decimalFormat = new DecimalFormat("#.######");
        System.out.println(decimalFormat.format(Math.PI * R * R));
        System.out.println(decimalFormat.format(2 * R * R));


    }

    private static void p2477() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());

        int[] countArr = new int[5];
        List<String> inputList = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            String s = bufferedReader.readLine();
            inputList.add(s);

            int[] array = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray();
            countArr[array[0]]++;
        }

        int x = 1, y = 1;
        int smallX = 1, smallY = 1;

        if (countArr[1] == 2 && countArr[3] == 2) {

            for (int i = 0; i < 6; i++) {
                String s = inputList.get(i);

                if (s.startsWith("2")) {
                    x = Integer.parseInt(s.split(" ")[1]);

                    smallX = Integer.parseInt(inputList.get((i + 2) % 6).split(" ")[1]);
                    smallY = Integer.parseInt(inputList.get((i + 3) % 6).split(" ")[1]);

                } else if (s.startsWith("4")) {
                    y = Integer.parseInt(s.split(" ")[1]);
                }
            }

        } else if (countArr[1] == 2 && countArr[4] == 2) {
            for (int i = 0; i < 6; i++) {
                String s = inputList.get(i);

                if (s.startsWith("2")) {
                    x = Integer.parseInt(s.split(" ")[1]);

                    smallX = Integer.parseInt(inputList.get((i + 4) % 6).split(" ")[1]);
                    smallY = Integer.parseInt(inputList.get((i + 3) % 6).split(" ")[1]);

                } else if (s.startsWith("3")) {
                    y = Integer.parseInt(s.split(" ")[1]);
                }
            }
        } else if (countArr[2] == 2 && countArr[3] == 2) {
            for (int i = 0; i < 6; i++) {
                String s = inputList.get(i);

                if (s.startsWith("1")) {
                    x = Integer.parseInt(s.split(" ")[1]);

                    smallY = Integer.parseInt(inputList.get((i + 3) % 6).split(" ")[1]);
                    smallX = Integer.parseInt(inputList.get((i + 4) % 6).split(" ")[1]);

                } else if (s.startsWith("4")) {
                    y = Integer.parseInt(s.split(" ")[1]);
                }
            }
        } else if (countArr[2] == 2 && countArr[4] == 2) {
            for (int i = 0; i < 6; i++) {
                String s = inputList.get(i);

                if (s.startsWith("1")) {
                    x = Integer.parseInt(s.split(" ")[1]);

                    smallX = Integer.parseInt(inputList.get((i + 2) % 6).split(" ")[1]);
                    smallY = Integer.parseInt(inputList.get((i + 3) % 6).split(" ")[1]);

                } else if (s.startsWith("3")) {
                    y = Integer.parseInt(s.split(" ")[1]);
                }
            }
        }

        int width = (x * y) - (smallX * smallY);

        System.out.println(N * width);

    }

    private static void p4153() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            int[] array = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            Arrays.sort(array);

            if (array[0] == 0) break;

            if (Math.pow(array[0], 2) + Math.pow(array[1], 2) == Math.pow(array[2], 2)) {
                System.out.println("right");
            } else {
                System.out.println("wrong");
            }
        }
    }

    private static void p3009() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();

        for (int i = 0; i < 3; i++) {
            String[] s = bufferedReader.readLine().split(" ");

            map1.put(s[0], map1.getOrDefault(s[0], 0) + 1);
            map2.put(s[1], map2.getOrDefault(s[1], 0) + 1);
        }

        for (Map.Entry entry : map1.entrySet()) {
            if (entry.getValue().equals(1)) {
                System.out.print(entry.getKey() + " ");
                break;
            }
        }

        for (Map.Entry entry : map2.entrySet()) {
            if (entry.getValue().equals(1)) {
                System.out.print(entry.getKey() + " ");
                break;
            }
        }

    }


    private static void p1085() {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int w = scanner.nextInt();
        int h = scanner.nextInt();

        int min = Math.min(x, w - x);
        min = Math.min(min, y);
        min = Math.min(min, h - y);

        System.out.println(min);
    }

    private static void p25305() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int cutline = Integer.parseInt(bufferedReader.readLine().split(" ")[1]);

        ArrayList<Integer> list = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        list.sort(Collections.reverseOrder());

        System.out.println(list.get(cutline - 1));
    }

    private static void p25304() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int sum = Integer.parseInt(bufferedReader.readLine());
        int N = Integer.parseInt(bufferedReader.readLine());

        int mySum = 0;
        for (int i = 0; i < N; i++) {
            int[] split = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            mySum += split[0] * split[1];

        }

        if (sum == mySum) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

    }

    private static void p3003() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] array = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.print(String.format("%d ", 1 - array[0]));
        System.out.print(String.format("%d ", 1 - array[1]));
        System.out.print(String.format("%d ", 2 - array[2]));
        System.out.print(String.format("%d ", 2 - array[3]));
        System.out.print(String.format("%d ", 2 - array[4]));
        System.out.print(String.format("%d ", 8 - array[5]));
    }

    private static void p10816() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        bufferedReader.readLine();

        Map<Integer, Integer> map = new HashMap<>();

        int[] split = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int n : split) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        bufferedReader.readLine();

        int[] split2 = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        StringBuilder stringBuilder = new StringBuilder();
        for (int n : split2) {
            stringBuilder.append(map.getOrDefault(n, 0)).append(" ");
        }

        System.out.println(stringBuilder);


    }

    private static void p1620() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] split = bufferedReader.readLine().split(" ");

        int N = Integer.parseInt(split[0]);
        int M = Integer.parseInt(split[1]);

        Map<String, String> map = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            String s = bufferedReader.readLine();
            map.put(s, String.valueOf(i));
            map.put(String.valueOf(i), s);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < M; i++) {
            stringBuilder.append(map.get(bufferedReader.readLine()));
            stringBuilder.append("\n");
        }

        System.out.println(stringBuilder.toString());


    }

    private static void p14425() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] split = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int M = Integer.parseInt(split[1]);
        int count = 0;

        Set<String> set = new HashSet<>();

        for (int i = 0; i < N; i++) {
            set.add(bufferedReader.readLine());
        }

        for (int i = 0; i < M; i++) {
            String s = bufferedReader.readLine();

            if (set.contains(s)) {
                count++;
            }
        }

        System.out.println(count);

    }

    private static void p10815() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        bufferedReader.readLine();

        ArrayList<Integer> list1 = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        Set<Integer> map1 = new HashSet<>(list1);

        bufferedReader.readLine();

        ArrayList<Integer> list2 = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        StringBuilder stringBuilder = new StringBuilder();

        list2.forEach(integer -> {
            if (map1.contains(integer)) {
                stringBuilder.append(1);
                stringBuilder.append(" ");
            } else {
                stringBuilder.append(0);
                stringBuilder.append(" ");
            }
        });

        System.out.println(stringBuilder.toString());

    }

    private static void p1269() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedReader.readLine();
        String[] set1 = bufferedReader.readLine().split(" ");
        String[] set2 = bufferedReader.readLine().split(" ");

        Set<String> set = new HashSet<>();

        set.addAll(Arrays.asList(set1));
        set.addAll(Arrays.asList(set2));

        int gap = set1.length + set2.length - set.size();

        System.out.println(set1.length + set2.length - (gap * 2));


    }

    private static void p1764() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bufferedReader.readLine().split(" ");

        int N = Integer.parseInt(s[0]) + Integer.parseInt(s[1]);

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String name = bufferedReader.readLine();
            map.put(name, map.getOrDefault(name, 0) + 1);
        }


        int count = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Integer> entry : new TreeMap<>(map).entrySet()) {
            if (entry.getValue() == 2) {
                count++;
                stringBuilder.append(entry.getKey());
                stringBuilder.append("\n");
            }
        }

        System.out.println(count);
        System.out.println(stringBuilder.toString());
    }

    private static void p11478() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();

        Set<String> set = new HashSet<>();

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                if (j + i <= s.length()) {
                    set.add(s.substring(j, j + i));
                }
            }
        }

        System.out.println(set.size());

    }

    private static void p10989() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        int[] list = new int[N];
        int[] countList = new int[10000001];
        int[] resultList = new int[N];

        for (int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(bufferedReader.readLine());
        }

        for (int i = 0; i < list.length; i++) {
            countList[list[i]]++;
        }

        for (int i = 1; i < countList.length; i++) {
            countList[i] = countList[i - 1] + countList[i];
        }

        for (int i = list.length - 1; i >= 0; i--) {
            int value = list[i];
            countList[value]--;
            resultList[countList[value]] = value;
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < resultList.length; i++) {
            stringBuilder.append(resultList[i]);
            stringBuilder.append("\n");
        }

        System.out.println(stringBuilder.toString());
    }

    private static void p2108() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(bufferedReader.readLine()));
        }

        Collections.sort(list);

        int n1 = 0;

        int sum = 0;
        for (int num : list) {
            sum += num;
        }

        n1 = (int) Math.round(sum / (N * 1.0));

        int n2 = list.get(list.size() / 2);

        int n3 = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            map.put(list.get(i), map.getOrDefault(list.get(i), 0) + 1);
        }

        List<Map<String, Integer>> mapList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Map<String, Integer> map1 = new HashMap<>();
            map1.put("key", entry.getKey());
            map1.put("value", entry.getValue());
            mapList.add(map1);
        }

        Collections.sort(mapList, ((o1, o2) -> {
            if (o1.get("value") > o2.get("value")) {
                return -1;
            } else if (o1.get("value") < o2.get("value")) {
                return 1;
            } else {
                if (o1.get("key") > o2.get("key")) {
                    return 1;
                } else if (o1.get("key") < o2.get("key")) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }));

        if (list.size() == 1) {
            n3 = mapList.get(0).get("key");
        } else {
            if (mapList.get(0).get("value").equals(mapList.get(1).get("value"))) {
                n3 = mapList.get(1).get("key");
            } else {
                n3 = mapList.get(0).get("key");
            }
        }

        int n4;
        if (list.size() == 1) {
            n4 = 0;
        } else {
            n4 = list.get(list.size() - 1) - list.get(0);
        }


        System.out.println(n1);
        System.out.println(n2);
        System.out.println(n3);
        System.out.println(n4);
    }


    private static void p18870() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());

        String[] s = bufferedReader.readLine().split(" ");

        Set<Integer> set = new HashSet<>();

        for (String s1 : s) {
            set.add(Integer.parseInt(s1));
        }

        List<Integer> list = new ArrayList(set);

        Collections.sort(list);
        Collections.reverse(list);

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < list.size(); i++) {
            int n1 = list.get(i);
            boolean flag = false;
            for (int j = i; j < list.size(); j++) {
                int n2 = list.get(j);

                if (n1 > n2) {
                    map.put(n1, list.size() - j);
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                map.put(n1, 0);
            }
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (String s1 : s) {
            stringBuilder.append(map.get(Integer.parseInt(s1)));
            stringBuilder.append(" ");
        }

        System.out.println(stringBuilder);

    }

    private static void p10814() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());

        ArrayList<Map<String, String>> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] s = bufferedReader.readLine().split(" ");

            Map<String, String> map = new HashMap<>();
            map.put("age", s[0]);
            map.put("name", s[1]);

            list.add(map);
        }

        Collections.sort(list, (o1, o2) -> {
            int o1Age = Integer.parseInt(o1.get("age"));
            int o2Age = Integer.parseInt(o2.get("age"));


            if (o1Age > o2Age) {
                return 1;
            } else if (o1Age < o2Age) {
                return -1;
            } else {
                return 0;
            }
        });

        StringBuilder stringBuilder = new StringBuilder();
        for (Map<String, String> m : list) {
            stringBuilder.append(m.get("age"));
            stringBuilder.append(" ");
            stringBuilder.append(m.get("name"));
            stringBuilder.append("\n");
        }

        System.out.println(stringBuilder.toString());

    }

    private static void p1181() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());

        ArrayList<String> arr = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            arr.add(bufferedReader.readLine());
        }

        Collections.sort(arr, (o1, o2) -> {
            if (o1.length() > o2.length()) {
                return 1;
            } else if (o1.length() == o2.length()) {
                return o1.compareTo(o2);
            } else {
                return -1;
            }
        });

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        Set<String> set = new LinkedHashSet<>(arr);
        for (String s : set) {
            bufferedWriter.write(s);
            bufferedWriter.write("\n");
        }

        bufferedWriter.flush();

    }

    private static void p11651() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        List<Map<String, String>> arr = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] s = bufferedReader.readLine().split(" ");

            Map<String, String> map = new HashMap<>();
            map.put("x", s[0]);
            map.put("y", s[1]);

            arr.add(map);
        }

        Collections.sort(arr, (o1, o2) -> {
            int x1 = Integer.parseInt(o1.get("x"));
            int x2 = Integer.parseInt(o2.get("x"));
            int y1 = Integer.parseInt(o1.get("y"));
            int y2 = Integer.parseInt(o2.get("y"));

            if (y1 > y2) {
                return 1;
            } else if (y1 == y2) {
                if (x1 > x2) {
                    return 1;
                } else if (x1 == x2) {
                    return 0;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        });

        StringBuilder stringBuilder = new StringBuilder();

        for (Map<String, String> map : arr) {
            stringBuilder.append(map.get("x"));
            stringBuilder.append(" ");
            stringBuilder.append(map.get("y"));
            stringBuilder.append("\n");
        }

        System.out.println(stringBuilder.toString());
    }

    private static void p1427() {
        Scanner scanner = new Scanner(System.in);
        String[] split = scanner.next().split("");
        Arrays.sort(split, (o1, o2) -> o1.compareTo(o2) * -1);

        System.out.println(String.join("", split));

    }

    private static void p11650() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        List<Map<String, String>> arr = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] s = bufferedReader.readLine().split(" ");

            Map<String, String> map = new HashMap<>();
            map.put("x", s[0]);
            map.put("y", s[1]);

            arr.add(map);
        }

        Collections.sort(arr, (o1, o2) -> {
            int x1 = Integer.parseInt(o1.get("x"));
            int x2 = Integer.parseInt(o2.get("x"));
            int y1 = Integer.parseInt(o1.get("y"));
            int y2 = Integer.parseInt(o2.get("y"));

            if (x1 > x2) {
                return 1;
            } else if (x1 < x2) {
                return -1;
            } else {
                if (y1 > y2) {
                    return 1;
                } else if (y1 < y2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < N; i++) {
            stringBuilder.append(arr.get(i).get("x") + " ");
            stringBuilder.append(arr.get(i).get("y") + "\n");
        }

        System.out.println(stringBuilder.toString());
    }

    private static void p2751() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            arr.add(scanner.nextInt());
        }

        Collections.sort(arr);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < N; i++) {
            stringBuilder.append(arr.get(i) + "\n");
        }

        System.out.println(stringBuilder.toString());
    }

    private static void p2750() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
        }

        Arrays.sort(arr);

        for (int i = 0; i < N; i++) {
            System.out.println(arr[i]);
        }
    }

    private static void p1018() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int M = Integer.parseInt(split[1]);

        String[] blocks = new String[N];

        for (int i = 0; i < N; i++) {
            blocks[i] = bufferedReader.readLine();
        }

        int min = 1000;

        String[] selectedBlocks = new String[8];
        int countWhiteStart;
        int countBlackStart;

        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {

                countWhiteStart = 0;
                countBlackStart = 0;

                // 8줄 뽑아오기
                for (int k = 0; k < 8; k++) {
                    selectedBlocks[k] = blocks[i + k].substring(j, j + 8);
                }

                //  black 시작
                for (int k = 0; k < 8; k++) {
                    for (int l = 0; l < 8; l++) {
                        if (k % 2 == 0) {
                            if (l % 2 == 0 && selectedBlocks[k].charAt(l) == 'W') {
                                countBlackStart++;
                            } else if (l % 2 == 1 && selectedBlocks[k].charAt(l) == 'B') {
                                countBlackStart++;
                            }
                        } else {
                            if (l % 2 == 0 && selectedBlocks[k].charAt(l) == 'B') {
                                countBlackStart++;
                            } else if (l % 2 == 1 && selectedBlocks[k].charAt(l) == 'W') {
                                countBlackStart++;
                            }
                        }
                    }
                }

                // white 시작
                for (int k = 0; k < 8; k++) {
                    for (int l = 0; l < 8; l++) {
                        if (k % 2 == 0) {
                            if (l % 2 == 0 && selectedBlocks[k].charAt(l) == 'B') {
                                countWhiteStart++;
                            } else if (l % 2 == 1 && selectedBlocks[k].charAt(l) == 'W') {
                                countWhiteStart++;
                            }
                        } else {
                            if (l % 2 == 0 && selectedBlocks[k].charAt(l) == 'W') {
                                countWhiteStart++;
                            } else if (l % 2 == 1 && selectedBlocks[k].charAt(l) == 'B') {
                                countWhiteStart++;
                            }
                        }

                    }
                }

                if (countWhiteStart < min) {
                    min = countWhiteStart;
                }

                if (countBlackStart < min) {
                    min = countBlackStart;
                }
            }
        }

        System.out.println(min);
    }

    private static void p2231() {
        Scanner scanner = new Scanner(System.in);
        int N = Integer.parseInt(scanner.next());

        int i = 1;
        while (true) {

            if (i > N) {
                System.out.println(0);
                break;
            }

            int sum = Arrays.stream(String.valueOf(i).split("")).mapToInt(Integer::parseInt).sum();

            if (sum + i == N) {
                System.out.println(i);
                break;
            }

            i++;
        }
    }

    private static void p7568() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int[] weight = new int[N];
        int[] height = new int[N];
        int[] rank = new int[N];


        for (int i = 0; i < N; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            weight[i] = Integer.parseInt(split[0]);
            height[i] = Integer.parseInt(split[1]);
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = i; j < N; j++) {
                if (weight[i] > weight[j] && height[i] > height[j]) {
                    rank[j]++;
                } else if (weight[i] < weight[j] && height[i] < height[j]) {
                    rank[i]++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.println(rank[i] + 1);
        }

    }

    private static void p1436() {
        Scanner scanner = new Scanner(System.in);

        int N = Integer.parseInt(scanner.next());
        int count = 0;
        int num = 665;
        while (true) {
            num++;
            if ((num + "").contains("666")) {
                count++;

                if (count == N) {
                    break;
                }
            }
        }

        System.out.println(num);

    }

    private static void p2798() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);

        List<Integer> cards = Arrays.stream(bufferedReader.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());

        int max = -1;
        for (int i = 0; i < N; i++) {
            int n1 = cards.get(i);
            for (int j = i + 1; j < N; j++) {
                int n2 = cards.get(j);
                for (int k = j + 1; k < N; k++) {
                    int n3 = cards.get(k);
                    int sum = n1 + n2 + n3;

                    if (sum <= M && sum > max) {
                        max = sum;
                    }
                }
            }
        }

        System.out.println(max);
    }

    private static void p11729() {
        Scanner scanner = new Scanner(System.in);
        int N = Integer.parseInt(scanner.next());

        System.out.println((int) Math.pow(2, N) - 1);
        StringBuilder stringBuilder = new StringBuilder();
        p11729Recursive(N, 1, 2, 3, stringBuilder);
        System.out.println(stringBuilder.toString());
    }

    private static void p11729Recursive(int N, int from, int aux, int to, StringBuilder stringBuilder) {
        if (N == 1) {
            stringBuilder.append(from + " " + to + "\n");
            return;
        }

        p11729Recursive(N - 1, from, to, aux, stringBuilder);
        stringBuilder.append(from + " " + to + "\n");
        p11729Recursive(N - 1, aux, from, to, stringBuilder);


    }

    private static void p2447() {
        Scanner scanner = new Scanner(System.in);
        int N = Integer.parseInt(scanner.next());

        char[][] arr = new char[N][N];

        p2447Recursive(arr, N, false, 0, 0);

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == '\u0000') {
                    stringBuilder.append('*');
                } else {
                    stringBuilder.append(' ');
                }


            }
            stringBuilder.append('\n');
        }

        System.out.println(stringBuilder.toString());
    }

    private static void p2447Recursive(char[][] arr, int N, boolean isBlank, int x, int y) {


        if (isBlank) {
            for (int i = x; i < x + N; i++) {
                for (int j = y; j < y + N; j++) {
                    arr[i][j] = ' ';
                }
            }
            return;
        }


        int size = N / 3;
        if (size >= 1) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == 1 && j == 1) {
                        p2447Recursive(arr, size, true, x + i * size, y + j * size);
                    } else {
                        p2447Recursive(arr, size, false, x + i * size, y + j * size);
                    }
                }
            }
        }
    }

    private static void p17478() {
        Scanner scanner = new Scanner(System.in);
        int N = Integer.parseInt(scanner.next());

        System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
        p17484Recursive(0, N);
    }

    private static void p17484Recursive(int curr, int N) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < curr; i++) {
            stringBuilder.append("____");
        }
        String prefix = stringBuilder.toString();

        if (curr < N) {
            System.out.println(prefix + "\"재귀함수가 뭔가요?\"");
            System.out.println(prefix + "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
            System.out.println(prefix + "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
            System.out.println(prefix + "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");

            p17484Recursive(curr + 1, N);
        } else {
            System.out.println(prefix + "\"재귀함수가 뭔가요?\"");
            System.out.println(prefix + "\"재귀함수는 자기 자신을 호출하는 함수라네\"");
        }

        System.out.println(prefix + "라고 답변하였지.");
    }

    private static void p10870() {
        Scanner scanner = new Scanner(System.in);

        int N = Integer.parseInt(scanner.next());

        System.out.println(fibo(N));
    }

    private static int fibo(int N) {
        if (N == 0) {
            return 0;
        } else if (N == 1) {
            return 1;
        } else {
            return fibo(N - 1) + fibo(N - 2);
        }
    }

    private static void p10872() {
        Scanner scanner = new Scanner(System.in);

        int N = Integer.parseInt(scanner.next());

        System.out.println(factorial(N, 1));
    }

    private static int factorial(int N, int prev) {
        if (prev > N) {
            return 1;
        }
        return prev * factorial(N, prev + 1);
    }

    public static void p9020() throws IOException {
        Scanner scanner = new Scanner(System.in);

        int N = Integer.parseInt(scanner.next());

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(scanner.next());

            boolean[] iAmNotPrime = new boolean[n + 1];

            iAmNotPrime[0] = true;
            iAmNotPrime[1] = true;

            for (int j = 2; j <= Math.sqrt(n); j++) {
                if (iAmNotPrime[j]) {
                    continue;
                }

                for (int k = j * j; k <= n; k += j) {
                    iAmNotPrime[k] = true;
                }
            }

            int mid = n / 2;

            while (true) {
                if (!iAmNotPrime[mid] && !iAmNotPrime[n - mid]) {
                    System.out.println(mid + " " + (n - mid));
                    break;
                }
                mid--;
            }

        }


    }


    public static void p4948() throws IOException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int n = Integer.parseInt(scanner.next());

            if (n == 0) {
                break;
            }

            boolean[] iAmNotPrime = new boolean[2 * n + 1];

            iAmNotPrime[0] = true;
            iAmNotPrime[1] = true;

            for (int i = 2; i <= Math.sqrt(2 * n); i++) {
                if (iAmNotPrime[i]) {
                    continue;
                }

                for (int j = i * i; j <= 2 * n; j += i) {
                    iAmNotPrime[j] = true;
                }
            }

            int count = 0;
            for (int i = n + 1; i <= 2 * n; i++) {
                if (!iAmNotPrime[i]) {
                    count++;
                }
            }

            System.out.println(count);
        }

    }


    public static void p1929() throws IOException {
        Scanner scanner = new Scanner(System.in);

        int M = Integer.parseInt(scanner.next());
        int N = Integer.parseInt(scanner.next());

        boolean[] prime = new boolean[N + 1];

        prime[0] = true;
        prime[1] = true;

        for (int i = 2; i <= Math.sqrt(N); i++) {
            if (prime[i]) {
                continue;
            }

            for (int j = i * i; j < prime.length; j = j + i) {
                prime[j] = true;
            }
        }

        for (int i = M; i <= N; i++) {
            if (!prime[i]) {
                System.out.println(i);
            }
        }

    }

    public static void p11653() throws IOException {
        Scanner scanner = new Scanner(System.in);

        int N = Integer.parseInt(scanner.next());

        if (N != 1) {
            getMember(N);
        }
    }

    public static void getMember(int n) {
        boolean flag = false;

        for (int i = 2; i <= (n / 2); i++) {
            if (n % i == 0) {
                System.out.println(i);
                flag = true;
                getMember(n / i);
                break;
            }
        }

        if (!flag) {
            System.out.println(n);
        }

    }

    public static void p2581() throws IOException {
        Scanner scanner = new Scanner(System.in);

        int M = Integer.parseInt(scanner.next());
        int N = Integer.parseInt(scanner.next());

        boolean isPrimeNumber;
        int min = 100000;
        int sum = 0;

        for (int i = M; i <= N; i++) {

            if (i == 1) {
                continue;
            }

            isPrimeNumber = true;

            if (i > 2) {
                for (int j = 2; j < i; j++) {
                    if (i % j == 0) {
                        isPrimeNumber = false;
                        break;
                    }
                }
            }

            if (isPrimeNumber) {
                sum += i;

                if (min == 100000) {
                    min = i;
                }
            }
        }

        if (sum == 0) {
            System.out.println(-1);
        } else {
            System.out.println(sum);
            System.out.println(min);
        }

    }


    public static void p1978() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int N = Integer.parseInt(scanner.next());

        int count = 0;
        boolean isPrimeNum;
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(scanner.next());

            if (num == 1) {
                continue;
            }

            isPrimeNum = true;

            if (num > 2) {
                for (int j = 2; j < num; j++) {
                    if (num % j == 0) {
                        isPrimeNum = false;
                        break;
                    }
                }
            }


            if (isPrimeNum) {
                count++;
            }

        }

        System.out.println(count);
    }

    public static void p10250() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.next());

        for (int i = 0; i < n; i++) {
            int H = Integer.parseInt(scanner.next());
            int W = Integer.parseInt(scanner.next());
            int N = Integer.parseInt(scanner.next());

            int y;
            int x;
            if (N > H) {
                y = N % H == 0 ? H : N % H;
                x = N % H == 0 ? N / H : N / H + 1;
            } else {
                y = N;
                x = 1;
            }

            if (x < 10) {
                System.out.println(y + "0" + x);
            } else {
                System.out.println(y + "" + x);
            }

        }
    }


    public static void p10757() throws IOException {
        Scanner scanner = new Scanner(System.in);
        BigInteger A = new BigInteger(scanner.next());
        BigInteger B = new BigInteger(scanner.next());
        System.out.println(A.add(B));
    }

    public static void p1712() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int A = Integer.parseInt(scanner.next());
        int B = Integer.parseInt(scanner.next());
        int C = Integer.parseInt(scanner.next());

        int result;

        if (B >= C) {
            result = -1;
        } else {
            result = (A / (C - B)) + 1;
        }


        System.out.println(result);


    }

    public static void p2869() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int A = Integer.parseInt(scanner.next());
        int B = Integer.parseInt(scanner.next());
        int V = Integer.parseInt(scanner.next());

        int r = (int) Math.ceil((V - A) / (A - B * 1.0));

        System.out.println(r + 1);

    }

    public static void p2839() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());

        int quotient = N / 5;

        while (quotient >= 0) {
            int remainder = N - (quotient * 5);
            if (quotient == 0 && remainder % 3 != 0) {
                System.out.println("-1");
                break;
            } else {
                if (remainder % 3 == 0) {
                    System.out.println(quotient + remainder / 3);
                    break;
                } else {
                    quotient--;
                }
            }
        }
    }

    public static void p2775() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < N; i++) {
            int k = Integer.parseInt(bufferedReader.readLine());
            int n = Integer.parseInt(bufferedReader.readLine());

            int[] floor = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

            for (int j = 0; j < k; j++) {
                for (int l = 1; l < n; l++) {
                    floor[l + 1] = floor[l] + floor[l + 1];
                }
            }
            System.out.println(floor[n]);
        }
    }

    public static void p1193() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());

        int limit = 4;
        int adder = 2;
        int i = 2;

        if (N == 1) {
            System.out.println("1/1");
            return;
        }

        while (true) {
            if (limit > N) {
                int prevLimit = limit - adder;
                int x;
                int y;

                if (i % 2 == 0) {
                    y = i - (N - prevLimit);
                    x = 1 + (N - prevLimit);
                } else {
                    y = 1 + (N - prevLimit);
                    x = i - (N - prevLimit);
                }

                System.out.println(x + "/" + y);
                break;
            } else {
                i++;
                adder++;
                limit += adder;
            }
        }

    }

    public static void p2292() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());

        int limit = 1;
        int adder = 6;
        int count = 1;
        while (true) {
            if (limit < N) {
                limit += adder;
                adder += 6;
                count++;
            } else {
                break;
            }
        }

        System.out.println(count);

    }

    public static void p문자열압축(String s) {
        int min = s.length();
        for (int i = 1; i <= s.length() / 2; i++) {

            String prevString = "";
            StringBuilder stringBuilder = new StringBuilder();
            int matchCount = 1;

            for (int j = 0; j < s.length(); j += i) {
                String substring = s.substring(j, Math.min(j + i, s.length()));

                if (prevString.equals(substring)) {
                    matchCount++;
                } else {
                    if (matchCount <= 1) {
                        stringBuilder.append(prevString);
                    } else {
                        stringBuilder.append(matchCount).append(prevString);
                        matchCount = 1;
                    }
                }

                prevString = substring;
            }

            if (matchCount <= 1) {
                stringBuilder.append(prevString);
            } else {
                stringBuilder.append(matchCount).append(prevString);
                matchCount = 1;
            }

            int r = stringBuilder.toString().length();
            if (min > r) {
                min = r;
            }


        }

        System.out.println(min);
    }

    public static void p1316() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());
        int count = 0;
        ArrayList<String> done = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            done.clear();
            boolean incFlag = true;
            String currChar = "";

            String word = bufferedReader.readLine();

            for (int j = 0; j < word.length(); j++) {
                String c = String.valueOf(word.charAt(j));

                if (!currChar.equals(c)) {
                    if (!done.contains(c)) {
                        done.add(c);
                        currChar = c;
                    } else {
                        incFlag = false;
                        break;
                    }
                }

            }

            if (incFlag) {
                count++;
            }
        }

        System.out.println(count);
    }

    public static void p2941() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String word = bufferedReader.readLine();
        int count = 0;

        for (int i = 0; i < word.length(); i++) {
            char character = word.charAt(i);

            if (character == 'c') {
                if (i < word.length() - 1) {
                    if (word.charAt(i + 1) == '=' || word.charAt(i + 1) == '-') {
                        i++;
                    }
                }
            } else if (character == 'd') {
                if (i < word.length() - 2) {
                    if (word.charAt(i + 1) == 'z' && word.charAt(i + 2) == '=') {
                        i += 2;
                    }
                }

                if (i < word.length() - 1) {
                    if (word.charAt(i + 1) == '-') {
                        i++;
                    }
                }
            } else if (character == 'l' || character == 'n') {
                if (i < word.length() - 1) {
                    if (word.charAt(i + 1) == 'j') {
                        i++;
                    }
                }
            } else if (character == 's' || character == 'z') {
                if (i < word.length() - 1) {
                    if (word.charAt(i + 1) == '=') {
                        i++;
                    }
                }
            }

            count++;

        }


        System.out.println(count);
    }

    public static void p5622() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String word = bufferedReader.readLine();
        int time = 0;
        for (int i = 0; i < word.length(); i++) {
            int val = word.charAt(i) - 'A';
            if (val >= 22) {
                time += 10;
            } else if (val >= 15 && val <= 18) {
                time += 8;
            } else if (val == 21) {
                time += 9;
            } else {
                time += val / 3 + 3;
            }
        }

        System.out.println(time);
    }

    public static void p2908() throws IOException {
        Scanner scanner = new Scanner(System.in);

        int result = 0;
        String num1 = scanner.next();
        String num2 = scanner.next();

        String reverseNum1 = "";
        String reverseNum2 = "";
        for (int i = 2; i >= 0; i--) {
            reverseNum1 += num1.charAt(i);
            reverseNum2 += num2.charAt(i);
        }

        if (Integer.parseInt(reverseNum1) > Integer.parseInt(reverseNum2)) {
            System.out.println(reverseNum1);
        } else {
            System.out.println(reverseNum2);
        }

    }

    public static void p1152() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String sentence = bufferedReader.readLine().toUpperCase();


        if (sentence.length() > 0) {
            int wordCount = sentence.split(" ").length;
            if (sentence.startsWith(" ") && sentence.trim().length() > 0) {
                wordCount--;
            }
            System.out.println(wordCount);
        } else {
            System.out.println("0");
        }
    }

    public static void p1157() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String word = bufferedReader.readLine().toUpperCase();
        int[] resultArray = new int[26];
        int currTopValue = 0;
        int currTopIndex = 0;
        int maxCount = 0;

        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'A';

            resultArray[index] += 1;

            if (currTopValue < resultArray[index]) {
                currTopValue = resultArray[index];
                currTopIndex = index;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (currTopValue == resultArray[i]) {
                maxCount++;
            }
        }

        if (maxCount == 1) {
            System.out.println(Character.toChars(currTopIndex + 'A'));
        } else {
            System.out.println("?");
        }
    }

    public static void p2675() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int rep = Integer.parseInt(stringTokenizer.nextToken());

            String word = stringTokenizer.nextToken();

            StringBuilder stringBuilder = new StringBuilder();

            for (int j = 0; j < word.length(); j++) {
                for (int k = 0; k < rep; k++) {
                    stringBuilder.append(word.charAt(j));
                }
            }

            System.out.println(stringBuilder.toString());

        }
    }

    public static void p10809() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String word = bufferedReader.readLine();

        int[] arr = new int[26];

        for (int i = 0; i < 26; i++) {
            arr[i] = -1;
        }

        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (arr[index] == -1) {
                arr[index] = i;
            }
        }

        for (int i = 0; i < 26; i++) {
            System.out.print(arr[i] + " ");
        }

    }

    public static void p11720() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());

        int sum = 0;

        String s = bufferedReader.readLine();

        for (int i = 0; i < N; i++) {
            sum += s.charAt(i) - '0';
        }

        System.out.println(sum);
    }

    public static void p11654() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println((int) bufferedReader.readLine().charAt(0));
    }

    public static void p15596() {

    }

    public static void p4673() {

        int[] arr = new int[10001];

        for (int i = 1; i <= 10000; i++) {
            String num = String.valueOf(i);
            int result = i;
            for (int j = 0; j < num.length(); j++) {
                result += num.charAt(j) - '0';
            }

            if (result <= 10000) {
                arr[result] = 1;
            }


            if (arr[i] == 0) {
                System.out.println(i);
            }
        }
    }

    public static void p1065() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());

        int count = 0;

        for (int i = 1; i <= N; i++) {
            if (i < 100) {
                count++;
            } else {
                String s = String.valueOf(i);
                if ((((int) s.charAt(0) - (int) s.charAt(1)) == (((int) s.charAt(1) - (int) s.charAt(2))))) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }


    public static void p4344() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < N; i++) {
            String[] strings = bufferedReader.readLine().split(" ");

            int N2 = Integer.parseInt(strings[0]);

            double totalScore = 0;
            double betterThanAvgStudentCount = 0;
            double avg;

            for (int j = 1; j <= N2; j++) {
                totalScore += Double.parseDouble(strings[j]);
            }

            avg = totalScore / N2;

            for (int j = 1; j <= N2; j++) {
                int score = Integer.parseInt(strings[j]);
                if (score > avg) {
                    betterThanAvgStudentCount++;
                }
            }

            System.out.println(String.format("%.3f", betterThanAvgStudentCount / N2 * 100) + "%");

        }

    }

    public static void p8958() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < N; i++) {
            int score = 0;
            int streak = 0;

            String[] split = bufferedReader.readLine().split("");

            for (String s : split) {
                if ("O".equals(s)) {
                    streak++;
                } else {
                    streak = 0;
                }

                score += streak;
            }

            System.out.println(score);
        }

    }

    public static void p1546() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());
        int max = 0;
        int totalScore = 0;

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(stringTokenizer.nextToken());
            totalScore += n;
            max = Math.max(max, n);
        }

        System.out.println(100.0 * totalScore / max / N);


    }

    public static void p3052() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((System.in)));

        int[] dividendsCount = new int[42];
        int result = 0;

        for (int i = 0; i < 10; i++) {
            dividendsCount[Integer.parseInt(bufferedReader.readLine()) % 42]++;
        }

        for (int i = 0; i < 42; i++) {
            if (dividendsCount[i] > 0) {
                result++;
            }
        }

        System.out.println(result);

    }

    public static void p2577() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((System.in)));
        int result = 1;

        for (int i = 0; i < 3; i++) {
            result *= Integer.parseInt(bufferedReader.readLine());
        }

        int[] numberCount = new int[10];

        String stringResult = String.valueOf(result);

        String[] splitResult = stringResult.split("");

        for (String s : splitResult) {
            numberCount[Integer.parseInt(s)]++;
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(numberCount[i]);
        }

    }


    public static void p2562() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((System.in)));
        int testCaseCount = 9;

        int max = 1;
        int maxIndex = 1;

        for (int i = 0; i < testCaseCount; i++) {
            int currentInt = Integer.parseInt(bufferedReader.readLine());
            if (currentInt > max) {
                max = currentInt;
                maxIndex = i + 1;
            }
        }

        System.out.println(max);
        System.out.println(maxIndex);

    }

    public static void p10818() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((System.in)));
        int testCaseCount = Integer.parseInt(bufferedReader.readLine());

        int[] arr = new int[testCaseCount];

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        for (int i = 0; i < testCaseCount; i++) {
            arr[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        int min = 1000000000;
        int max = -1000000000;

        for (int i = 0; i < testCaseCount; i++) {
            if (min > arr[i]) {
                min = arr[i];
            }
            if (max < arr[i]) {
                max = arr[i];
            }
        }


        System.out.println(String.format("%d %d", min, max));
    }
}
