import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.sqrt

fun main(args: Array<String>) {
    p1620()
}

fun p1620() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val split = bufferedReader.readLine().split(" ")

    val N = split[0].toInt()
    val M = split[1].toInt()

    val map = HashMap<String,String>()

    for (i in 1..N) {
        val s = bufferedReader.readLine();
        map.put(s, i.toString())
        map.put(i.toString(), s)
    }

    val stringBuilder = java.lang.StringBuilder()
    for (i in 0 until M) {
        stringBuilder.append(map[bufferedReader.readLine()])
        stringBuilder.append("\n")
    }

    println(stringBuilder.toString())
}

fun p14425() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))

    val split = bufferedReader.readLine().split(" ")

    val N = split[0].toInt()
    val M = split[1].toInt()
    var count = 0
    var set = HashSet<String>()

    for (i in 0 until N) {
        set.add(bufferedReader.readLine())
    }

    for (i in 0 until M) {
        if (set.contains(bufferedReader.readLine())) {
            count++
        }
    }

    println(count);

}

fun p10815() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    bufferedReader.readLine()

    val list1 = bufferedReader.readLine().split(" ").toList()
    val set = HashSet(list1)

    bufferedReader.readLine()

    val stringBuilder = java.lang.StringBuilder()
    bufferedReader.readLine().split(" ").forEach {
        if (set.contains(it)) {
            stringBuilder.append("1 ")
        } else {
            stringBuilder.append("0 ")
        }
    }

    println(stringBuilder.toString())
}

fun p1269() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    bufferedReader.readLine()

    val set1 = bufferedReader.readLine().split(" ")
    val set2 = bufferedReader.readLine().split(" ")

    val set = HashSet<String>()

    set.addAll(set1)
    set.addAll(set2)

    val gap = set1.size + set2.size - set.size

    println(set1.size + set2.size - (gap * 2))
}

fun p1764() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val split = bufferedReader.readLine().split(" ")

    val N = split[0].toInt() + split[1].toInt()

    val map = HashMap<String, Int>()
    for (i in 0 until N) {
        val string = bufferedReader.readLine()
        map[string] = map.getOrDefault(string, 0) + 1
    }

    val treeMap = TreeMap(map)

    var count = 0
    val stringBuilder = StringBuilder()
    for (entry in treeMap.entries) {
        if (entry.value == 2) {
            count++
            stringBuilder.append(entry.key)
            stringBuilder.append("\n")
        }
    }

    println(count)
    println(stringBuilder.toString())
}

fun p11478() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val s = bufferedReader.readLine()

    val set = HashSet<String>()

    for (i in 1..s.length) {
        for (j in s.indices) {
            if (i + j <= s.length) {
                set.add(s.substring(j, j + i))
            }
        }
    }

    println(set.size)

}

fun p18870() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val N = bufferedReader.readLine().toInt()


    val split = bufferedReader.readLine().split(" ")

    val set = HashSet<Int>()

    for (s in split) {
        set.add(s.toInt())
    }

    val list = ArrayList<Int>(set)

    list.sort()
    list.reverse()

    val map = HashMap<Int, Int>()

    for (i in 0 until list.size) {
        val n1 = list[i]

        var flag = false
        for (j in i until list.size) {
            val n2 = list[j]

            if (n1 > n2) {
                map[n1] = list.size - j
                flag = true
                break
            }
        }

        if (!flag) {
            map[n1] = 0
        }
    }

    val stringBuilder = StringBuilder()
    for (s in split) {
        stringBuilder.append(map[s.toInt()])
        stringBuilder.append(" ")
    }

    println(stringBuilder.toString())

}

fun p1181() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val N = bufferedReader.readLine().toInt();

    val list = ArrayList<String>()

    for (i in 0 until N) {
        list.add(bufferedReader.readLine());
    }

    Collections.sort(list) { o1: String, o2: String ->
        if (o1.length > o2.length) {
            return@sort 1
        } else if (o1.length < o2.length) {
            return@sort -1
        } else {
            return@sort o1.compareTo(o2)
        }
    }


    val stringBuilder = StringBuilder()
    val set = LinkedHashSet(list)

    for (s in set) {
        stringBuilder.append(s)
        stringBuilder.append("\n")
    }

    println(stringBuilder.toString())
}


fun p11651() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val N = bufferedReader.readLine().toInt()

    val list = ArrayList<Map<String, String>>()

    for (i in 0 until N) {
        val split = bufferedReader.readLine().split(" ")

        val map = HashMap<String, String>()
        map["x"] = split[0]
        map["y"] = split[1]


        list.add(map)
    }

    list.sortWith(compareBy<Map<String, String>> { it["y"] }.thenBy { it["x"] })

    val stringBuilder = java.lang.StringBuilder()

    for (map in list) {
        stringBuilder.append(map["x"])
        stringBuilder.append(" ")
        stringBuilder.append(map["y"])
        stringBuilder.append("\n")
    }

    println(stringBuilder.toString())
}

fun p1427() {
    val scanner = Scanner(System.`in`)
    val split = scanner.next().split("")

    Collections.sort(split) { o1, o2 -> o1.compareTo(o2) * -1 }

    System.out.println(split.joinToString(""))
}

fun p1018() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val split = bufferedReader.readLine().split(" ")
    val N = split[0].toInt()
    val M = split[1].toInt()

    val blocks = arrayOfNulls<String>(N)

    for (i in 0 until N) {
        blocks[i] = bufferedReader.readLine()
    }

    var min = 1000

    var selectedBlocks = arrayOfNulls<String>(8)

    var countWhiteStart = 0
    var countBlackStart = 0

    for (i in 0 .. N - 8) {
        for (j in 0 .. M - 8) {
            countBlackStart = 0
            countWhiteStart = 0

            for (k in 0 until  8) {
                selectedBlocks[k] = blocks[i + k]!!.substring(j, j + 8)
            }

            //  black 시작

            //  black 시작
            for (k in 0..7) {
                for (l in 0..7) {
                    if (k % 2 == 0) {
                        if (l % 2 == 0 && selectedBlocks[k]!![l] == 'W') {
                            countBlackStart++
                        } else if (l % 2 == 1 && selectedBlocks[k]!![l] == 'B') {
                            countBlackStart++
                        }
                    } else {
                        if (l % 2 == 0 && selectedBlocks[k]!![l] == 'B') {
                            countBlackStart++
                        } else if (l % 2 == 1 && selectedBlocks[k]!![l] == 'W') {
                            countBlackStart++
                        }
                    }
                }
            }


            // white 시작
            for (k in 0..7) {
                for (l in 0..7) {
                    if (k % 2 == 0) {
                        if (l % 2 == 0 && selectedBlocks[k]!![l] == 'B') {
                            countWhiteStart++
                        } else if (l % 2 == 1 && selectedBlocks[k]!![l] == 'W') {
                            countWhiteStart++
                        }
                    } else {
                        if (l % 2 == 0 && selectedBlocks[k]!![l] == 'W') {
                            countWhiteStart++
                        } else if (l % 2 == 1 && selectedBlocks[k]!![l] == 'B') {
                            countWhiteStart++
                        }
                    }
                }
            }

            if (countWhiteStart < min) {
                min = countWhiteStart
            }

            if (countBlackStart < min) {
                min = countBlackStart
            }
        }
    }

    println(min)

}

fun p2231() {
    val scanner = Scanner(System.`in`)
    val N = scanner.next().toInt()

    var i = 1

    while (true) {
        val sum = i.toString().split("").sumOf { if ("" != it) it.toInt() else 0 }

        if (i > N) {
            println(0)
            break
        }
        if (sum + i == N) {
            println(i)
            break
        }
        i++
    }
}

fun p7568() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val N = bufferedReader.readLine().toInt()
    val heightArr = IntArray(N)
    val weightArr = IntArray(N)
    val rankArr = IntArray(N)

    for (i in 0 until  N) {
        val split = bufferedReader.readLine().split(" ")
        heightArr[i] = split[0].toInt()
        weightArr[i] = split[1].toInt()
    }

    for (i in 0 until N - 1) {
        for (j in i until N) {
            if (heightArr[i] > heightArr[j] && weightArr[i] > weightArr[j]) {
                rankArr[j] = rankArr[j] + 1
            } else if (heightArr[i] < heightArr[j] && weightArr[i] < weightArr[j]) {
                rankArr[i] = rankArr[i] + 1
            }
        }

    }

    for (i in 0 until N) {
        println(rankArr[i] + 1)
    }
}

fun p1436() {
    val scanner = Scanner(System.`in`)
    val N = scanner.next().toInt()
    var count = 0
    var num = 665
    while (true) {
        num++

        if ("$num".contains("666")) {
            count++

            if (count == N) {
                println(num)
                break
            }
        }
    }
}

fun p2798() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))

    val split = bufferedReader.readLine().split(" ")
    val N = split[0].toInt()
    val M = split[1].toInt()

    val cards = bufferedReader.readLine().split(" ").map { s -> s.toInt() }


    var max = -1
    for (i in 0 until N) {
        val n1 = cards[i]
        for (j in i + 1 until N) {
            val n2 = cards[j]
            for (k in j + 1 until N) {
                val n3 = cards[k]
                val sum = n1 + n2 + n3
                if (sum <= M && sum > max) {
                    max = sum
                }
            }
        }
    }

    println(max)

}

fun p11729() {
    val scanner = Scanner(System.`in`)
    val N = scanner.next().toInt()

    val stringBuilder = StringBuilder()
    stringBuilder.append("${Math.pow(2.0, N.toDouble()).toInt() - 1}\n")
    p11729Recursive(N, 1, 2, 3, stringBuilder)
    println(stringBuilder.toString())
}

fun p11729Recursive(N: Int, from: Int, aux: Int, to: Int, stringBuilder: StringBuilder) {
    if (N == 1) {
        stringBuilder.append("$from $to\n")
        return
    }

    p11729Recursive(N - 1, from, to, aux, stringBuilder)
    stringBuilder.append("$from $to\n")
    p11729Recursive(N - 1, aux, from, to, stringBuilder)
}


fun p2447() {
    val scanner = Scanner(System.`in`)

    val N = scanner.next().toInt()

    val arr = Array(N){CharArray(N)}

    p2447Recursive(arr, N, 0, 0, false)

    val stringBuilder = StringBuilder()
    for (i in 0 until N) {
        for (j in 0 until N) {
            if (arr[i][j] == '\u0000') {
                stringBuilder.append('*')
            } else {
                stringBuilder.append(' ');
            }
        }
        stringBuilder.append('\n')
    }

    println(stringBuilder.toString())
}

fun p2447Recursive(arr: Array<CharArray>, N: Int, x: Int, y: Int, isBlank: Boolean) {

    if (isBlank) {
        for (i in x until x + N) {
            for (j in y until y + N) {
                arr[i][j] = ' '
            }
        }
        return
    }

    val size = N / 3
    if (size >= 1) {
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                if (i == 1 && j == 1) {
                    p2447Recursive(arr, size, x + i * size, y + j * size, true)
                } else {
                    p2447Recursive(arr, size, x + i * size, y + j * size, false)
                }
            }
        }
    }
}

fun p17478() {
    val scanner = Scanner(System.`in`)

    val N = scanner.next().toInt()

    println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.")

    p17478Recursive(N, 0)
}

fun p17478Recursive(N: Int, curr: Int) {
    // create prefix
    var stringBuilder = StringBuilder()
    for (i in 0 until curr) {
        stringBuilder.append("____")
    }
    var prefix = stringBuilder.toString()

    // 재귀
    if (curr < N) {
        println(prefix + "\"재귀함수가 뭔가요?\"")
        println(prefix + "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.")
        println(prefix + "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.")
        println(prefix + "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"")
        p17478Recursive(N, curr + 1)
    } else {
        println(prefix + "\"재귀함수가 뭔가요?\"")
        println(prefix + "\"재귀함수는 자기 자신을 호출하는 함수라네\"")
    }

    println(prefix + "라고 답변하였지.")
}

fun p10870() {
    val scanner = Scanner(System.`in`)

    val N = scanner.next().toInt()

    println(fibo(N))
}

fun fibo(N: Int): Int {
    if (N == 0) {
        return 0
    } else if (N == 1) {
        return 1
    } else {
        return fibo(N - 1) + fibo(N - 2)
    }
}

fun p10872() {
    val scanner = Scanner(System.`in`)

    val N = scanner.next().toInt()

    println(factorial(N, 1))
}

fun factorial(N: Int, prev: Int): Int {
    if (prev > N) {
        return 1
    }

    return prev * factorial(N, prev + 1)
}

fun p9020() {
    val scanner = Scanner(System.`in`)

    val N = scanner.next().toInt()

    for (i in 0 until N) {
        val n = scanner.next().toInt()

        val iAmNotPrime = BooleanArray(n + 1)
        iAmNotPrime[0] = true
        iAmNotPrime[1] = true

        for (j in 2..sqrt(n.toDouble()).toInt()) {
            if (iAmNotPrime[j]) {
                continue
            }

            for (k in j * j..n step j) {
                iAmNotPrime[k] = true
            }
        }

        var mid = n / 2;
        while (true) {
            if (!iAmNotPrime[mid] && !iAmNotPrime[n - mid]) {
                println("$mid ${n - mid}")
                break
            }
            mid--
        }




    }
}

fun p4948() {
    val scanner = Scanner(System.`in`)

    while (true) {
        val n = scanner.next().toInt()

        if (n == 0) {
            break
        }

        val iAmNotPrime = BooleanArray(2 * n + 1)

        iAmNotPrime[0] = true
        iAmNotPrime[1] = true

        for (i in 2..sqrt((2 * n).toDouble()).toInt()) {
            if (iAmNotPrime[i]) {
                continue
            }

            for (j in i * i..2 * n step i) {
                iAmNotPrime[j] = true
            }
        }

        var count = 0

        for (i in n + 1.. 2 * n) {
            if (!iAmNotPrime[i]) {
                count++
            }
        }

        println(count)


    }
}

fun p1929() {
    val scanner = Scanner(System.`in`)

    val M = scanner.next().toInt()
    val N = scanner.next().toInt()

    val notPrime = BooleanArray(N + 1)

    notPrime[0] = true
    notPrime[1] = true

    for (i in 2..sqrt(N.toDouble()).toInt()) {
        if (notPrime[i]) {
            continue
        }

        for (j in i * i..N step i  ) {
            notPrime[j] = true
        }
    }

    for ((index, value) in notPrime.withIndex()) {
        if (index >= M && !value) {
            println(index)
        }
    }

}

fun p11653() {
    val scanner = Scanner(System.`in`)

    val N = scanner.next().toInt()

    if (N != 1) {
        getPrimeFactors(N)
    }
}

fun getPrimeFactors(n: Int) {
    var flag = false

    for (i in 2..(n / 2)) {
        if (n % i == 0) {
            println(i)
            getPrimeFactors(n / i)
            flag = true
            break
        }
    }

    if (!flag) {
        println(n)
    }
}

fun p2581() {
    val scanner = Scanner(System.`in`)

    val M = scanner.next().toInt()
    val N = scanner.next().toInt()

    var min = 100000
    var sum = 0
    var isPrimeNumber: Boolean

    for (i in M..N) {
        if (i == 1) {
            continue
        }

        isPrimeNumber = true

        if (i > 2) {
            for (j in 2 until i) {
                if (i % j == 0) {
                    isPrimeNumber = false
                    break
                }
            }
        }

        if (isPrimeNumber) {
            sum += i

            if (min == 100000) {
                min = i
            }
        }
    }



    if (sum == 0) {
        println(-1)
    } else {
        println(sum)
        println(min)
    }


}

fun p1978() {
    val scanner = Scanner(System.`in`)

    val N: Int = scanner.next().toInt()

    var isPrimeNumber: Boolean
    var count = 0

    for (i in 0 until N) {
        val num: Int = scanner.next().toInt()

        if (num == 1) {
            continue
        }

        isPrimeNumber = true
        if (num > 2) {
            for (j in 2 until num) {
                if (num % j == 0) {
                    isPrimeNumber = false
                    break
                }
            }
        }

        if (isPrimeNumber) {
            count++
        }
    }

    print(count)

}

fun p10250() {
    val scanner = Scanner(System.`in`)

    val n = scanner.next().toInt()

    for (i in 0 until n) {
        val H = scanner.next().toInt()
        val W = scanner.next().toInt()
        val N = scanner.next().toInt()

        var x: Int
        var y: Int

        if (N > H) {
            x = if (N % H == 0) N / H else N / H + 1
            y = if (N % H == 0) H else N % H
        } else {
            x = 1
            y = N
        }

        if (x < 10) {
            println("${y}0${x}")
        } else {
            println("${y}${x}")
        }

    }
}

fun p10757() {
    val scanner = Scanner(System.`in`)

    val A = scanner.next().toBigInteger()
    val B = scanner.next().toBigInteger()

    println(A + B)
}

fun p1712() {
    val scanner = Scanner(System.`in`)
    val A = scanner.next().toInt()
    val B = scanner.next().toInt()
    val C = scanner.next().toInt()

    var result: Int

    if (B >= C) {
        result = -1
    } else {
        result = (A / (C - B)) + 1
    }

    println(result)

}

fun p2869() {
    val scanner = Scanner(System.`in`)
    val A: Double = scanner.next().toDouble()
    val B: Double = scanner.next().toDouble()
    val V: Double = scanner.next().toDouble()

    val result = Math.ceil((V - A) / (A - B)).toInt()

    println(result + 1)

}


fun p2839() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))

    val N: Int = bufferedReader.readLine().toInt()

    var quotient: Int = N / 5

    while (quotient >= 0) {
        val remainder: Int = N - quotient * 5
        if (quotient == 0 && remainder % 3 != 0) {
            println("-1")
            break
        } else {
            if (remainder % 3 == 0) {
                println(quotient + remainder / 3)
                break
            } else {
                quotient--
            }
        }
    }
}
