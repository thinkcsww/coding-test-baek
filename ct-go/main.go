package main

import (
	"bufio"
	"fmt"
	"math"
	"math/big"
	"os"
	"strconv"
	"strings"
)

func main() {
	p1018()
}

func p1018() {
	var reader = bufio.NewReader(os.Stdin)
	readString, _ := reader.ReadString('\n')
	readString = strings.TrimSuffix(readString, "\n")

	split := strings.Split(readString, " ")

	var N, _ = strconv.Atoi(split[0])
	var M, _ = strconv.Atoi(split[1])

	var blocks = make([]string, N)

	for i := 0; i < N; i++ {
		s, _ := reader.ReadString('\n')
		s = strings.TrimSuffix(s, "\n")
		blocks[i] = s
	}

	var min = 1000
	var selectedBlocks = make([]string, 8)
	var countWhiteStart = 0
	var countBlackStart = 0

	for i := 0; i <= N-8; i++ {
		for j := 0; j <= M-8; j++ {

			countWhiteStart = 0
			countBlackStart = 0

			for k := 0; k < 8; k++ {
				selectedBlocks[k] = blocks[i+k][j : j+8]
			}

			//  black 시작
			for k := 0; k < 8; k++ {
				for l := 0; l < 8; l++ {
					if k%2 == 0 {
						if l%2 == 0 && selectedBlocks[k][l] == 'W' {
							countBlackStart++
						} else if l%2 == 1 && selectedBlocks[k][l] == 'B' {
							countBlackStart++
						}
					} else {
						if l%2 == 0 && selectedBlocks[k][l] == 'B' {
							countBlackStart++
						} else if l%2 == 1 && selectedBlocks[k][l] == 'W' {
							countBlackStart++
						}
					}
				}
			}

			// white 시작
			for k := 0; k < 8; k++ {
				for l := 0; l < 8; l++ {
					if k%2 == 0 {
						if l%2 == 0 && selectedBlocks[k][l] == 'B' {
							countWhiteStart++
						} else if l%2 == 1 && selectedBlocks[k][l] == 'W' {
							countWhiteStart++
						}
					} else {
						if l%2 == 0 && selectedBlocks[k][l] == 'W' {
							countWhiteStart++
						} else if l%2 == 1 && selectedBlocks[k][l] == 'B' {
							countWhiteStart++
						}
					}

				}
			}

			if countWhiteStart < min {
				min = countWhiteStart
			}

			if countBlackStart < min {
				min = countBlackStart
			}
		}
	}

	fmt.Println(min)
}

func p2231() {
	var reader = bufio.NewReader(os.Stdin)
	var N int
	fmt.Fscanln(reader, &N)

	i := 1
	for {
		if i > N {
			fmt.Println(0)
			break
		}

		var sum = 0
		nums := strings.Split(strconv.Itoa(i), "")

		for _, num := range nums {
			intNum, _ := strconv.Atoi(num)
			sum += intNum
		}

		if sum+i == N {
			fmt.Println(i)
			break
		}
		i++
	}

}

func p7568() {
	var reader = bufio.NewReader(os.Stdin)
	var N int
	fmt.Fscanln(reader, &N)

	var weightArr = make([]int, N)
	var heightArr = make([]int, N)
	var rankArr = make([]int, N)

	for i := 0; i < N; i++ {
		temp, _ := reader.ReadString('\n')
		temp = strings.TrimSuffix(temp, "\n")

		split := strings.Split(temp, " ")

		weightArr[i], _ = strconv.Atoi(split[0])
		heightArr[i], _ = strconv.Atoi(split[1])
	}

	for i := 0; i < N-1; i++ {
		for j := i; j < N; j++ {
			if weightArr[i] > weightArr[j] && heightArr[i] > heightArr[j] {
				rankArr[j]++
			} else if weightArr[i] < weightArr[j] && heightArr[i] < heightArr[j] {
				rankArr[i]++
			}
		}
	}

	for i := 0; i < N; i++ {
		fmt.Printf("%v ", rankArr[i]+1)
	}

}

func p1436() {
	var reader = bufio.NewReader(os.Stdin)
	var N int
	var num = 665
	var count = 0
	fmt.Fscan(reader, &N)

	for {
		num++

		if strings.Contains(strconv.Itoa(num), "666") {
			count++

			if count == N {
				fmt.Println(num)
				break
			}
		}
	}
}

func p2798() {
	var reader = bufio.NewReader(os.Stdin)
	var N int
	var M int
	fmt.Fscan(reader, &N)
	fmt.Fscan(reader, &M)

	reader.ReadString('\n')
	strs, _ := reader.ReadString('\n')
	strs = strings.TrimSuffix(strs, "\n")
	cards := strings.Split(strs, " ")

	max := -1

	for i := 0; i < N; i++ {
		n1, _ := strconv.Atoi(cards[i])
		for j := i + 1; j < N; j++ {
			n2, _ := strconv.Atoi(cards[j])
			for k := j + 1; k < N; k++ {
				n3, _ := strconv.Atoi(cards[k])
				sum := n1 + n2 + n3

				if sum > max && sum <= M {
					max = sum
				}
			}
		}
	}

	fmt.Println(max)

}

func p11729() {
	var reader = bufio.NewReader(os.Stdin)
	var N int

	fmt.Fscanln(reader, &N)

	fmt.Println(int(math.Pow(float64(2), float64(N))) - 1)

	var stringBuilder = strings.Builder{}

	p11729Recursive(N, 1, 2, 3, &stringBuilder)

	fmt.Println(stringBuilder.String())
}

func p11729Recursive(N int, from int, aux int, to int, stringBuilder *strings.Builder) {
	if N == 1 {
		stringBuilder.WriteString(strconv.Itoa(from) + " " + strconv.Itoa(to) + "\n")
		return
	}

	p11729Recursive(N-1, from, to, aux, stringBuilder)
	stringBuilder.WriteString(strconv.Itoa(from) + " " + strconv.Itoa(to) + "\n")
	p11729Recursive(N-1, aux, from, to, stringBuilder)
}

func p2447() {
	var reader = bufio.NewReader(os.Stdin)
	var N int

	fmt.Fscanln(reader, &N)

	var arr = make([][]bool, N)
	for i := 0; i < N; i++ {
		arr[i] = make([]bool, N)
	}

	p2447Recursive(arr, 0, 0, N, false)

	var stringBuilder strings.Builder

	for i := 0; i < N; i++ {
		for j := 0; j < N; j++ {
			if arr[i][j] {
				stringBuilder.WriteString(" ")
			} else {
				stringBuilder.WriteString("*")
			}
		}
		stringBuilder.WriteString("\n")
	}

	fmt.Println(stringBuilder.String())
}

func p2447Recursive(arr [][]bool, x int, y int, N int, isBlank bool) {
	if isBlank {
		for i := x; i < x+N; i++ {
			for j := y; j < y+N; j++ {
				arr[i][j] = true
			}
		}
		return
	}

	var size = N / 3
	if size >= 1 {
		for i := 0; i < 3; i++ {
			for j := 0; j < 3; j++ {
				if i == 1 && j == 1 {
					p2447Recursive(arr, x+i*size, y+j*size, size, true)
				} else {
					p2447Recursive(arr, x+i*size, y+j*size, size, false)
				}
			}
		}
	}
}

func p17478() {
	var reader = bufio.NewReader(os.Stdin)
	var N int

	fmt.Fscanln(reader, &N)

	fmt.Printf("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n")

	p17478Recursive(N, 0)
}

func p17478Recursive(N int, curr int) {
	// prefix
	var prefix = ""
	for i := 0; i < curr; i++ {
		prefix += "____"
	}

	// recursive
	if curr < N {
		fmt.Printf(prefix + "\"재귀함수가 뭔가요?\"\n")
		fmt.Printf(prefix + "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n")
		fmt.Printf(prefix + "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n")
		fmt.Printf(prefix + "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n")
		p17478Recursive(N, curr+1)
	} else {
		fmt.Printf(prefix + "\"재귀함수가 뭔가요?\"\n")
		fmt.Printf(prefix + "\"재귀함수는 자기 자신을 호출하는 함수라네\"\n")
	}

	fmt.Printf(prefix + "라고 답변하였지.\n")

}

func p10870() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()

	var N int

	fmt.Fscanln(reader, &N)

	fmt.Fprintln(writer, fibo(N))
}

func fibo(N int) int {
	if N == 0 {
		return 0
	} else if N == 1 {
		return 1
	} else {
		return fibo(N-1) + fibo(N-2)
	}
}

func p10872() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()

	var N int

	fmt.Fscanln(reader, &N)

	fmt.Fprintln(writer, factorial(N, 1))

}

func factorial(N int, prev int) int {
	if prev > N {
		return 1
	}

	return prev * factorial(N, prev+1)
}

func p9020() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()

	var N int

	fmt.Fscanln(reader, &N)

	for i := 0; i < N; i++ {
		var n int
		fmt.Fscanln(reader, &n)

		var primes []int

		var iAmNotPrime = make([]bool, n+1)
		iAmNotPrime[0] = true
		iAmNotPrime[1] = true

		for j := 2; j <= int(math.Sqrt(float64(n))); j++ {
			if iAmNotPrime[j] {
				continue
			}

			for k := j * j; k <= n; k += j {
				iAmNotPrime[k] = true
			}
		}

		for j := 0; j < len(iAmNotPrime); j++ {
			if !iAmNotPrime[j] {
				primes = append(primes, j)
			}
		}

		var mid = n / 2

		for {
			if !iAmNotPrime[mid] && !iAmNotPrime[n-mid] {
				fmt.Fprintf(writer, "%v %v\n", mid, n-mid)
				break
			}
			mid--
		}
	}
}

func p4948() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()
	var n int

	for {
		fmt.Fscan(reader, &n)

		if n == 0 {
			break
		}

		var iAmNotPrime = make([]bool, 2*n+1)

		iAmNotPrime[0] = true
		iAmNotPrime[1] = true

		for i := 2; i <= int(math.Sqrt(float64(2*n))); i++ {
			if iAmNotPrime[i] {
				continue
			}

			for j := i * i; j <= 2*n; j += i {
				iAmNotPrime[j] = true
			}
		}

		var count = 0

		for i := n + 1; i <= 2*n; i++ {
			if !iAmNotPrime[i] {
				count++
			}
		}

		fmt.Fprintln(writer, count)

	}

}

func p1929() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()

	var M int64
	var N int64

	fmt.Fscan(reader, &M)
	fmt.Fscan(reader, &N)

	var imNotPrime = make([]bool, M+1)

	imNotPrime[0] = true
	imNotPrime[1] = true

	for i := 2; i <= int(math.Sqrt(float64(N))); i++ {
		if imNotPrime[i] {
			continue
		}

		for j := i * i; j <= int(N); j = j + i {
			imNotPrime[j] = true
		}
	}

	for i := M; i <= N; i++ {
		if !imNotPrime[i] {
			fmt.Fprintln(writer, i)
		}
	}

}

func p11653() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()
	var N int

	fmt.Fscan(reader, &N)

	if N != 1 {
		getPrimeFactors(N, writer)
	}
}

func getPrimeFactors(n int, writer *bufio.Writer) {
	var flag = false

	for i := 2; i <= n/2; i++ {
		if n%i == 0 {
			flag = true
			fmt.Fprintln(writer, i)
			getPrimeFactors(n/i, writer)
			break
		}
	}

	if !flag {
		fmt.Fprintln(writer, n)
	}

}

func p2581() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()

	var isPrimeNum bool

	var M int
	var N int
	var sum int = 0
	var min int = 100000

	fmt.Fscan(reader, &M, &N)

	for i := M; i <= N; i++ {
		isPrimeNum = true

		if i == 1 {
			continue
		}

		if i > 2 {
			for j := 2; j < i; j++ {
				if i%j == 0 {
					isPrimeNum = false
					break
				}
			}
		}

		if isPrimeNum {
			sum += i

			if min == 100000 {
				min = i
			}
		}
	}

	if sum == 0 {
		fmt.Fprintln(writer, "-1")
	} else {
		fmt.Fprintln(writer, sum)
		fmt.Fprintln(writer, min)
	}
}

func p1978() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()

	var isPrimeNum bool
	var count = 0

	var N int
	fmt.Fscanln(reader, &N)

	for i := 0; i < N; i++ {
		var num int
		fmt.Fscan(reader, &num)

		isPrimeNum = true

		if num == 1 {
			continue
		}

		if num > 2 {
			for j := 2; j < num; j++ {
				if num%j == 0 {
					isPrimeNum = false
					break
				}
			}
		}

		if isPrimeNum {
			count++
		}
	}

	fmt.Println(count)

}

func p10250() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()

	var n int
	fmt.Fscanln(reader, &n)

	for i := 0; i < n; i++ {
		var H int
		var W int
		var N int

		fmt.Fscan(reader, &H, &W, &N)

		var x int
		var y int

		if N > H {
			if N%H == 0 {
				x = N / H
				y = H
			} else {
				x = N/H + 1
				y = N % H
			}
		} else {
			x = 1
			y = N
		}

		if x < 10 {
			fmt.Fprintf(writer, "%v0%v\n", y, x)
		} else {
			fmt.Fprintf(writer, "%v%v\n", y, x)
		}

	}

}

func p10757() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()

	var A big.Int
	var B big.Int

	fmt.Fscan(reader, &A, &B)

	add := new(big.Int)
	add = add.Add(&A, &B)

	fmt.Fprintln(writer, add)

}

func p1712() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()

	var A int
	var B int
	var C int

	fmt.Fscan(reader, &A)
	fmt.Fscan(reader, &B)
	fmt.Fscan(reader, &C)

	var result int

	if B >= C {
		result = -1
	} else {
		result = (A / (C - B)) + 1
	}

	fmt.Fprintln(writer, result)
}

func p2869() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()

	var A float64
	var B float64
	var V float64

	fmt.Fscan(reader, &A)
	fmt.Fscan(reader, &B)
	fmt.Fscan(reader, &V)

	var result = int(math.Ceil((V - A) / (A - B)))
	fmt.Fprintln(writer, result+1)
}

func p2839() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()

	var N int
	fmt.Fscanln(reader, &N)
	var quotient = N / 5

	for {
		var remainder = N - 5*quotient
		if quotient == 0 && remainder%3 != 0 {
			fmt.Fprintln(writer, "-1")
			break
		} else {
			if remainder%3 == 0 {
				fmt.Fprintln(writer, quotient+remainder/3)
				break
			} else {
				quotient--
			}
		}
	}
}

func p2775() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()

	var N int

	fmt.Fscanln(reader, &N)

	for i := 0; i < N; i++ {
		var k int
		var n int

		var floor []int = []int{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}

		fmt.Fscanln(reader, &k)
		fmt.Fscanln(reader, &n)

		for j := 0; j < k; j++ {
			for l := 1; l < n; l++ {
				floor[l+1] = floor[l] + floor[l+1]
			}
		}

		fmt.Fprintln(writer, floor[n])

	}
}

func p1193() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()

	var N int
	var limit = 4
	var i = 2
	var adder = 2

	fmt.Fscanln(reader, &N)

	if N == 1 {
		fmt.Fprintln(writer, "1/1")
		return
	}

	for {
		var prevLimit = limit - adder

		if limit > N {
			var x int
			var y int
			if i%2 == 0 {
				x = i - (N - prevLimit)
				y = 1 + (N - prevLimit)
			} else {
				x = 1 + (N - prevLimit)
				y = i - (N - prevLimit)
			}

			fmt.Fprintf(writer, "%d/%d", y, x)
			break
		} else {
			i++
			adder++
			limit += adder
		}
	}
}

func p2292() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()

	var N int
	var limit = 1
	var count = 1
	var adder = 6

	fmt.Fscanln(reader, &N)

	for {
		if N > limit {
			limit += adder
			adder += 6
			count++
		} else {
			break
		}
	}

	fmt.Fprintln(writer, count)

}

func p1316() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()

	var N int
	var count int
	fmt.Fscanln(reader, &N)

	for i := 0; i < N; i++ {
		var word string
		fmt.Fscanln(reader, &word)

		var flag = false
		var curChar = ""
		var doneCharArr = []string{}

		for _, c := range word {
			if curChar != string(c) {
				if !contains(doneCharArr, string(c)) {
					doneCharArr = append(doneCharArr, string(c))
					curChar = string(c)
					flag = true
				} else {
					flag = false
					break
				}
			}
		}

		if flag {
			count++
		}
	}

	fmt.Fprintln(writer, count)
}

func contains(s []string, e string) bool {
	for _, a := range s {
		if a == e {
			return true
		}
	}
	return false
}

func p2941() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()

	var word string
	fmt.Fscanln(reader, &word)
	var count = 0

	for i := 0; i < len(word); i++ {
		if word[i] == 'c' {
			if i < len(word)-1 {
				if word[i+1] == '=' || word[i+1] == '-' {
					i++
				}
			}
		} else if word[i] == 'd' {
			if i < len(word)-2 {
				if word[i+1] == 'z' && word[i+2] == '=' {
					i += 2
				}
			}

			if i < len(word)-1 {
				if word[i+1] == '-' {
					i++
				}
			}
		} else if word[i] == 'l' || word[i] == 'n' {
			if i < len(word)-1 {
				if word[i+1] == 'j' {
					i++
				}
			}
		} else if word[i] == 's' || word[i] == 'z' {
			if i < len(word)-1 {
				if word[i+1] == '=' {
					i++
				}
			}
		}

		count++
	}

	fmt.Fprintln(writer, count)
}

func p5622() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()
	var word string
	var time = 0
	fmt.Fscan(reader, &word)

	for _, alphabet := range word {
		var val = int(alphabet - 'A')

		if val >= 22 {
			time += 10
		} else if val >= 15 && val <= 18 {
			time += 8
		} else if val == 21 {
			time += 9
		} else {
			time += val/3 + 3
		}
	}

	fmt.Print(time)
}

func p2908() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()

	var num1 string
	var num2 string
	var rNum1 string
	var rNum2 string

	fmt.Fscan(reader, &num1)
	fmt.Fscan(reader, &num2)

	for i := 2; i >= 0; i-- {
		rNum1 += string(num1[i])
		rNum2 += string(num2[i])
	}

	r1, err := strconv.Atoi(rNum1)
	if err != nil {
		return
	}

	r2, err := strconv.Atoi(rNum2)
	if err != nil {
		return
	}

	if r1 > r2 {
		fmt.Fprintln(writer, strconv.Itoa(r1))
	} else {
		fmt.Fprintln(writer, strconv.Itoa(r2))
	}

}

func p1152() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()

	strs, _ := reader.ReadString('\n')
	strs = strings.TrimSuffix(strs, "\n")

	splitCount := len(strings.Split(strs, " "))

	if strings.HasPrefix(strs, " ") {
		splitCount--
	}

	if strings.HasSuffix(strs, " ") {
		splitCount--
	}

	if strs == " " {
		splitCount = 0
	}

	fmt.Print(splitCount)
}

func p1157() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()

	var word string
	var resultArray [26]int
	var currTopValue int = 0
	var currTopIndex int = 0
	var maxCount = 0

	fmt.Fscan(reader, &word)

	word = strings.ToUpper(word)
	for _, c := range word {
		var index = int(c - 'A')

		resultArray[index]++

		if currTopValue < resultArray[index] {
			currTopIndex = index
			currTopValue = resultArray[index]
		}

	}

	for _, value := range resultArray {
		if currTopValue == value {
			maxCount++
		}
	}

	if maxCount > 1 {
		fmt.Println("?")
	} else {
		fmt.Println(string(rune(currTopIndex + 'A')))
	}

}

func p2675() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()

	var N int

	fmt.Fscanln(reader, &N)

	for i := 0; i < N; i++ {
		var rep int
		var word string

		fmt.Fscan(reader, &rep)
		fmt.Fscan(reader, &word)

		var resultStr = ""
		for _, c := range word {
			resultStr += strings.Repeat(string(c), rep)
		}

		fmt.Fprintln(writer, resultStr)
	}
}

func p10809() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()
	var word string
	var resultArray [26]int

	fmt.Fscanln(reader, &word)

	for i := 0; i < 26; i++ {
		resultArray[i] = -1
	}

	for i := 0; i < len(word); i++ {
		var index = int(word[i] - 'a')

		if resultArray[index] == -1 {
			resultArray[index] = i
		}
	}

	for i := 0; i < 26; i++ {
		fmt.Fprintf(writer, "%d ", resultArray[i])
	}
}

func p11720() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()

	var N int
	fmt.Fscanln(reader, &N)

	var s string
	fmt.Fscanln(reader, &s)

	var sum = 0

	for i := 0; i < N; i++ {
		sum += int(s[i]) - int('0')
	}

	fmt.Fprint(writer, sum)

}

func p11654() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()

	var N string

	fmt.Fscanln(reader, &N)

	fmt.Fprintln(writer, int(N[0]))
}

func p15596(a []int) int {
	var sum int

	for i := 0; i < len(a); i++ {
		sum += a[i]
	}

	return sum
}

func p4673() {

	var arr [10001]int

	for i := 1; i <= 10000; i++ {

		result := i

		num := strconv.Itoa(i)

		for j := 0; j < len(num); j++ {
			result += int(num[j]) - '0'
		}

		if result <= 10000 {
			arr[result] = 1
		}

		if arr[i] != 1 {
			fmt.Println(i)
		}

	}

}

func p1065() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()

	var N int
	var count = 0

	fmt.Fscanln(reader, &N)

	for i := 1; i <= N; i++ {
		if i < 100 {
			count++
		} else {
			str := strconv.Itoa(i)
			num0, err := strconv.Atoi(string(str[0]))
			if err != nil {
				return
			}

			num1, err := strconv.Atoi(string(str[1]))
			if err != nil {
				return
			}

			num2, err := strconv.Atoi(string(str[2]))
			if err != nil {
				return
			}

			if (num0 - num1) == (num1 - num2) {
				count++
			}
		}
	}
	fmt.Print(count)
}

func p4344() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()

	var N int

	fmt.Fscanln(reader, &N)

	for i := 0; i < N; i++ {

		//var strs string
		//fmt.Fscanln(reader, &strs)

		strs, _ := reader.ReadString('\n')
		strs = strings.TrimSuffix(strs, "\n")

		split := strings.Split(strs, " ")

		N2, _ := strconv.Atoi(split[0])

		var totalScore float64 = 0.0
		var avg float64
		var betterThanAvgStudentCount = 0.0

		for i := 1; i <= N2; i++ {
			score, _ := strconv.Atoi(split[i])

			totalScore += float64(score)
		}

		avg = totalScore / float64(N2)

		for i := 1; i <= N2; i++ {
			score, _ := strconv.Atoi(split[i])
			if float64(score) > avg {
				betterThanAvgStudentCount++
			}
		}

		fmt.Fprintf(writer, "%.3f%%\n", betterThanAvgStudentCount/float64(N2)*100.0)

	}

}

func p8958() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()

	var N int

	fmt.Fscanln(reader, &N)

	for i := 0; i < N; i++ {
		var str string
		fmt.Fscanln(reader, &str)

		score := 0
		streak := 0

		split := strings.Split(str, "")

		for _, s := range split {
			if s == "O" {
				streak++
			} else {
				streak = 0
			}

			score += streak
		}

		fmt.Fprintln(writer, score)

	}
}

func p1546() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()

	var max float64 = 0.0
	var total float64 = 0.0
	var N int
	fmt.Fscanln(reader, &N)

	for i := 0; i < N; i++ {
		var num float64
		fmt.Fscanf(reader, "%f", &num)

		max = math.Max(max, num)
		total += num
	}

	fmt.Fprintf(writer, "%f", total/max*100.0/float64(N))

}

func p3052() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()

	var resultArray [42]int
	var count = 0

	for i := 0; i < 10; i++ {
		num, _ := reader.ReadString('\n')
		num = strings.TrimSuffix(num, "\n")
		intNum, err := strconv.Atoi(num)
		if err != nil {
			fmt.Println(err.Error())
			return
		}
		resultArray[intNum%42]++
	}

	for _, num := range resultArray {
		if num > 0 {
			count++
		}
	}

	fmt.Fprintln(writer, count)

}

func p2577() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()

	var a, b, c int
	var resultArray [10]int

	fmt.Fscan(reader, &a, &b, &c)

	var sum = a * b * c

	sumString := strconv.Itoa(sum)

	splitSum := strings.Split(sumString, "")

	for _, s := range splitSum {
		num, _ := strconv.Atoi(s)
		resultArray[num]++
	}

	for _, i := range resultArray {
		fmt.Fprintln(writer, i)
	}
}

func p10818() {
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	defer writer.Flush()

	var input string
	fmt.Fscanln(reader, &input)

	N, _ := strconv.Atoi(input)
	var min = 1000000
	var max = -1000000

	for i := 0; i < N; i++ {

		var temp int
		fmt.Fscan(reader, &temp)

		if temp > max {
			max = temp
		}

		if temp < min {
			min = temp
		}
	}

	fmt.Fprintln(writer, min, max)
}
