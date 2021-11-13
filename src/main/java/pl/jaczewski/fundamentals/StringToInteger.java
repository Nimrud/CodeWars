package pl.jaczewski.fundamentals;

/*
In this kata we want to convert a string into an integer.
The strings simply represent the numbers in words.

Examples:
    "one" => 1
    "twenty" => 20
    "two hundred forty-six" => 246
    "seven hundred eighty-three thousand nine hundred and nineteen" => 783919

Additional Notes:
    The minimum number is "zero" (inclusively)
    The maximum number, which must be supported is 1 million (inclusively)
    The "and" in e.g. "one hundred and twenty-four" is optional, in some cases it's present and in others it's not
    All tested numbers are valid, you don't need to validate them

 */

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

class StringToInteger {
    public static void main(String[] args) {
        System.out.println(parseInt("two hundred forty-six"));
        System.out.println("-------------------");
        System.out.println(parseInt("Seventy"));

        System.out.println("==============");
        System.out.println(hundreds("two hundred thirty seven"));
    }

    public static int parseInt(String numStr) {
        String[] words = basicStringProcessing(numStr);
        System.out.println(Arrays.toString(words));

        if (numStr.contains("million")) {
            return 1000000;
        } else if (numStr.contains("thousand")) {
            //TODO
            return -100;
        } else if (numStr.contains("hundred")) {
            //TODO
            return -10000;
        } else if (dozens(numStr) >= 20) {
            return dozens(numStr);
        } else {
            return numbersUnder20(numStr);
        }
    }

    private static Map<String, Integer> mapUnder20 () {
        Map<String, Integer> map = Map.ofEntries(
                Map.entry("one", 1),
                Map.entry("two", 2),
                Map.entry("three", 3),
                Map.entry("four", 4),
                Map.entry("five", 5),
                Map.entry("six", 6),
                Map.entry("seven", 7),
                Map.entry("eight", 8),
                Map.entry("nine", 9),
                Map.entry("ten", 10),
                Map.entry("eleven", 11),
                Map.entry("twelve", 12),
                Map.entry("thirteen", 13),
                Map.entry("fourteen", 14),
                Map.entry("fifteen", 15),
                Map.entry("sixteen", 16),
                Map.entry("seventeen", 17),
                Map.entry("eighteen", 18),
                Map.entry("nineteen", 19));
        return map;
    }

    private static Map<String, Integer> mapOfDozens() {
        Map<String, Integer> map = Map.of(
                "twenty", 20,
                "thirty", 30,
                "forty", 40,
                "fifty", 50,
                "sixty", 60,
                "seventy", 70,
                "eighty", 80,
                "ninety", 90);
        return map;
    }

    private static int numbersUnder20(String numString) {
        return search(numString.trim().toLowerCase(Locale.ROOT), mapUnder20());
    }

    private static int dozens(String numString) {
        String[] words = basicStringProcessing(numString);

        if (words.length > 1) {
            int firstDigit = search(words[0], mapOfDozens());
            int secondDigit = search(words[1], mapUnder20());
            return firstDigit + secondDigit;
        } else {
            return search(words[0], mapOfDozens());
        }
    }

    private static int hundreds(String numString) {
        String[] words = basicStringProcessing(numString);

        // getting index of the word "hundred" in String
        int index = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("hundred")) {
                index = i;
            }
        }

        // counting hundreds
        int hundredsValue = numbersUnder20(words[0]) * 100;

        // building a String of words which are AFTER the word "hundred"
        StringBuilder remainder = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (i > index) {
                remainder.append(words[i]).append(" ");
            }
        }

        // building up the final value (hundreds + remainder)
        if (words.length - 1 > index) {
            if (dozens(remainder.toString()) >= 20) {
                return hundredsValue + dozens(remainder.toString());
            } else {
                return hundredsValue + numbersUnder20(remainder.toString());
            }
        } else {
            return hundredsValue;
        }
    }

    private static int search(String numString, Map<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (numString.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return -1;
    }

    private static String[] basicStringProcessing(String numString) {
        String lowerCased = numString.trim().toLowerCase(Locale.ROOT);
        return lowerCased.split("[ -]");
    }
}
