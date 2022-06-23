import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Scanner

fun main(args: Array<String>) {
    p10250()
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
