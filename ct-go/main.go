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
	p4344()
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
