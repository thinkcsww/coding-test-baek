package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
	"strings"
)

func main() {
	p1546()
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
