import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    p2839()
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
