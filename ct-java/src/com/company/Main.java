package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((System.in)));

        int[] dividendsCount = new int[42];
        int result = 0;

        for (int i = 0; i < 10; i++) {
            dividendsCount[Integer.parseInt(bufferedReader.readLine()) % 42]++;
        }

        for (int i = 0; i< 42; i++) {
            if (dividendsCount[i] > 0) {
                result++;
            }
        }

        System.out.println(result);
    }

    public static void p3052() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((System.in)));

        int[] dividendsCount = new int[42];
        int result = 0;

        for (int i = 0; i < 10; i++) {
            dividendsCount[Integer.parseInt(bufferedReader.readLine()) % 42]++;
        }

        for (int i = 0; i< 42; i++) {
            if (dividendsCount[i] > 0) {
                result++;
            }
        }

        System.out.println(result);

    }

    public static void p2577() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((System.in)));
        int result = 1;

        for (int i = 0; i < 3; i ++) {
            result *= Integer.parseInt(bufferedReader.readLine());
        }

        int[] numberCount = new int[10];

        String stringResult = String.valueOf(result);

        String[] splitResult = stringResult.split("");

        for (String s: splitResult) {
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
