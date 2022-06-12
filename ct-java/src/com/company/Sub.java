package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Sub {
    public static void main(String[] args) {
        int[] a = squareOrSquareRoot(new int[]{4, 3, 9, 7, 2, 1});

        System.out.println(Math.sqrt(1));
    }

    public static int[] squareOrSquareRoot(int[] array)
    {
        int[] newArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 1) {
                newArray[i] = 1;
            } else if (Math.sqrt(array[i]) % 1 == 0) {
                newArray[i] = (int)Math.sqrt(array[i]);
            } else {
                newArray[i] = (int)Math.pow(array[i], 2);
            }
        }
        return newArray;
    }

    @Test
    public void test1() throws Exception {
        String[] name = {"John", "Smith"};
        assertEquals("Hello, John Smith! Welcome to Phoenix, Arizona!", sayHello(name, "Phoenix", "Arizona"));
    }

    public String sayHello(String [] name, String city, String state){
        return String.format("Hello, %s! Welcome to %s, %s!", String.join(" ", name), city, state);
    }
}
