package pl.jaczewski.fundamentals;

/*
Write a function that takes an integer as input, and returns the number of bits
that are equal to one in the binary representation of that number.
You can guarantee that input is non-negative.

Example: The binary representation of 1234 is 10011010010,
so the function should return 5 in this case

 */

public class IntegerToBinaryCount {
    public static void main(String[] args) {
        System.out.println(countBits(1234));
    }

    public static int countBits(int n){
        String binaryResult = Integer.toBinaryString(n);
        char[] chars = binaryResult.toCharArray();
        int result = 0;
        for (char c : chars) {
            if (c == '1') {
                result++;
            }
        }
        return result;
    }
}
