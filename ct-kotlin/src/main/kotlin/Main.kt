import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Scanner
import kotlin.math.sqrt

fun main(args: Array<String>) {
    p1929()
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
