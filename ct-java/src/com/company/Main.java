package com.company;

import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static int p2606Count = -1;
    private static int p24479Count = 1;
    private static int p24480Count = 1;

    public static void main(String[] args) throws IOException {
        p1012();
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
