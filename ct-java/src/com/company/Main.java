package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        p4673();
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
                if ((((int)s.charAt(0) - (int)s.charAt(1)) == (((int)s.charAt(1) - (int)s.charAt(2))))) {
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

            for (String s: split) {
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
