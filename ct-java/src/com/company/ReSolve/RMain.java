package com.company.ReSolve;

import java.io.*;
import java.util.*;

public class RMain {
    private static int[] visited;
    private static List<List<Integer>> adj;
    private static int count = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());
        int R = Integer.parseInt(stringTokenizer.nextToken());

        visited = new int[N + 1];
        adj = new ArrayList<>(N + 1);

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



        for (List<Integer> a: adj) {
            Collections.sort(a);
        }

        dfs(R);



        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            stringBuilder.append(visited[i]).append("\n");
        }

        System.out.println(stringBuilder);
    }

    private static void dfs(int vertex) {
        visited[vertex] = count++;

        List<Integer> edges = adj.get(vertex);

        for (Integer edge: edges) {
            if (visited[edge] == 0) {
                dfs(edge);
            }
        }
    }
}
