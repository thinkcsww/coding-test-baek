package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
)

func main() {
}

func p2577() {
	
}

func p10818() {
	var reader *bufio.Reader = bufio.NewReader(os.Stdin)
	var writer *bufio.Writer = bufio.NewWriter(os.Stdout)

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
