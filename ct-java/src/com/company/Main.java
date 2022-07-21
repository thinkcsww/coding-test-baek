package com.company;

import java.math.BigInteger;
import java.util.stream.Collectors;
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        p10814();
    }

    private static void p10814() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());

        ArrayList<Map<String, String>> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] s = bufferedReader.readLine().split(" ");

            Map<String, String> map  = new HashMap<>();
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
        for (Map<String, String> m: list) {
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
        for (String s: set) {
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

        for(Map<String, String> map: arr) {
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
