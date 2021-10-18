package pl.jaczewski.fundamentals;

/*
Your task is to make a function that can take any non-negative integer
as an argument and return it with its digits in descending order.
Essentially, rearrange the digits to create the highest possible number.

Examples:
Input: 42145 Output: 54421
 */

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DescendingOrder {
    public static void main(String[] args) {
        System.out.println(descending(145263));
    }

    public static int descending(int num) {

        // zapisywanie kolejnych cyfr z podanej liczby do listy
        int temp = num;
        List<Integer> array = new ArrayList<>();
        do {
            array.add(temp % 10);
            temp /= 10;
        } while (temp > 0);

        List<Integer> array2 = array.stream()
                    // mnożę cyfry razy -1
                    // domyślne sortowanie działa od najmniejszej do największej (1,4,8)
                    // więc zamiana na wartości ujemne da pożądany efekt (-8,-4,-1)
                .map(a -> (a * -1))
                    // sortuję, a następnie znów zamieniam na wartości dodatnie
                .sorted()
                .map(a -> (a * -1))
                .collect(Collectors.toList());

        // z cyfr odtwarzam pierwotną liczbę
        int restoredNumber = 0;
        for (int i = 0; i < array2.size(); i++) {
            double restored = array2.get(i) * Math.pow (10, (array2.size() - 1 - i));
            restoredNumber += restored;
        }

        return restoredNumber;
    }
}
