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

import java.util.Locale;
import java.util.Map;

class StringToInteger {
    public static void main(String[] args) {
        /*
        tests to this class in appropriate folder
         */
    }

    public static int parseInt(String numStr) {
        String input = numStr.toLowerCase(Locale.ROOT);
        if (input.contains("million")) {
            return 1000000;
        } else if (input.contains("thousand")) {
            return thousands(input);
        } else if (input.contains("hundred")) {
            return hundreds(input);
        } else if (dozens(input) >= 20) {
            return dozens(input);
        } else {
            return numbersUnder20(input);
        }
    }

    private static String[] basicStringProcessing(String numString) {
        return numString
                .trim()
                .replaceAll("[ ]and", "")
                .split("[ -]");
    }

    private static int numbersUnder20(String input) {
        return searchMap(input.trim(), mapUnder20());
    }

    private static int dozens(String input) {
        String[] words = basicStringProcessing(input);

        if (words.length > 1) {
            int firstDigit = searchMap(words[0], mapOfDozens());
            int secondDigit = searchMap(words[1], mapUnder20());
            return firstDigit + secondDigit;
        } else {
            return searchMap(words[0], mapOfDozens());
        }
    }

    private static int hundreds(String input) {
        String[] words = basicStringProcessing(input);

        // getting index of the word "hundred" in String
        int index = gettingIndexOfGivenWord(words, "hundred");

        // counting hundreds
        int hundredsValue = numbersUnder20(words[0]) * 100;

        // getting a substring that represents a value < 100
        String remainder = remainder(words, index);

        // building up the final value (hundreds + remainder)
        if (words.length - 1 > index) {
            // if there is anything after the word "hundred"
            if (dozens(remainder) >= 20) {
                return hundredsValue + dozens(remainder);
            } else {
                return hundredsValue + numbersUnder20(remainder);
            }
        } else {
            return hundredsValue;
        }
    }

    private static int thousands(String input) {
        String[] words = basicStringProcessing(input);

        int index = gettingIndexOfGivenWord(words, "thousand");

        // getting a number that is after the word "thousand"
        int remainderValue = determineNumber(remainder(words, index));

        // getting the number of thousands
        int thousandValue = determineNumber(thousandCount(words, index));

        return thousandValue * 1000 + remainderValue;
    }

    // method to determine if the number is in hundreds, dozens or is a single-digit
    private static int determineNumber(String number) {
        int value;
        if (number.equals("")) {
            value = 0;
        } else if (number.contains("hundred")) {
            value = hundreds(number);
        } else if (dozens(number) >= 20) {
            value = dozens(number);
        } else {
            value = numbersUnder20(number);
        }
        return value;
    }

    // method to return words that are AFTER the word "hundred" or "thousand"
    private static String remainder(String[] words, int index) {
        StringBuilder remainder = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (i > index) {
                remainder.append(words[i]).append(" ");
            }
        }
        return remainder.toString();
    }

    // method to return words that are BEFORE the word "thousand"
    private static String thousandCount(String[] words, int index) {
        StringBuilder thousandCount = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (i < index) {
                thousandCount.append(words[i]).append(" ");
            }
        }
        return thousandCount.toString();
    }

    private static int gettingIndexOfGivenWord(String[] words, String keyWord) {
        int index = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(keyWord)) {
                index = i;
            }
        }
        return index;
    }

    private static Map<String, Integer> mapUnder20 () {
        return Map.ofEntries(
                Map.entry("zero", 0),
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
    }

    private static Map<String, Integer> mapOfDozens() {
        return Map.of(
                "twenty", 20,
                "thirty", 30,
                "forty", 40,
                "fifty", 50,
                "sixty", 60,
                "seventy", 70,
                "eighty", 80,
                "ninety", 90);
    }

    private static int searchMap(String numString, Map<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (numString.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return -1;
    }
}
