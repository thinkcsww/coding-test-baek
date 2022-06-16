package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        p5622();
    }

    public static void p5622() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String word = bufferedReader.readLine();
        int time = 0;
        for (int i = 0; i < word.length(); i++) {
            int val = word.charAt(i) - 'A';
            if (val >= 23) {
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

        System.out.println((int)bufferedReader.readLine().charAt(0));
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
