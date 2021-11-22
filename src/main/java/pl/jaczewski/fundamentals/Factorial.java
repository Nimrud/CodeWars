package pl.jaczewski.fundamentals;

/*
In mathematics, the factorial of integer n is written as n!.
It is equal to the product of n and every integer preceding it.
For example: 5! = 1 x 2 x 3 x 4 x 5 = 120

Your mission is simple: write a function that takes an integer n
and returns the value of n!.

You are guaranteed an integer argument.
For any values outside the non-negative range, return null.
For non-negative numbers a full length number is expected,
for example: return 25! = "15511210043330985984000000" as a string.
 */

import java.math.BigInteger;

class Factorial {
    public static void main(String[] args) {
        System.out.println(factorial(129));
    }

    public static String factorial(int n) {
        if (n < 0) {
            return "null";
        } else if (n == 0 || n == 1) {
            return "1";
        }

        int number = 2;   // consecutive numbers (from 2 to given n)
        BigInteger result = new BigInteger("1");
        while(number <= n) {
            result = result.multiply(BigInteger.valueOf(number));
            number++;
        }
        return String.valueOf(result);
    }
}
