import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Scanner
import kotlin.math.sqrt

fun main(args: Array<String>) {
    p17478()
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
