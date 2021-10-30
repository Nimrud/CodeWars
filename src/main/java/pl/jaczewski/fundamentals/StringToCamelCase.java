package pl.jaczewski.fundamentals;

/*
Complete the method/function so that it converts dash/underscore delimited words into camel casing.
The first word within the output should be capitalized only if the original word was capitalized
(known as Upper Camel Case, also often referred to as Pascal case).

Examples:
"the-stealth-warrior" gets converted to "theStealthWarrior"
"The_Stealth_Warrior" gets converted to "TheStealthWarrior"

 */

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringToCamelCase {
    public static void main(String[] args) {
        System.out.println(toCamelCase("The-stealth-warrior"));
    }

    static String toCamelCase(String s){
        String[] words = null;
        String newString = "";

        if (s == null) {
            throw new NullPointerException();
        } else {
            if (s.contains("-")){
                words = s.split("-");
            } else if (s.contains("_")) {
                words = s.split("_");
            } else {
                System.out.println("String does not contain any dash or underscore");
            }
        }

        newString = Arrays.stream(words)
                .map(a -> a.substring(0,1).toUpperCase() + a.substring(1))
                .collect(Collectors.joining(""));

        if (s.substring(0,1).equals(s.substring(0,1).toLowerCase())) {
            return newString.substring(0,1).toLowerCase() + newString.substring(1);
        } else {
            return newString;
        }
    }
}
