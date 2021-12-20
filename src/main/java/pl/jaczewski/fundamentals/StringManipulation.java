package pl.jaczewski.fundamentals;

/*
Move the first letter of each word to the end of it, then add "ay"
to the end of the word. Leave punctuation marks untouched.

Examples:
pigIt('Pig latin is cool'); // igPay atinlay siay oolcay
pigIt('Hello world !');     // elloHay orldway !
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class StringManipulation {
    public static void main(String[] args) {
        System.out.println(pigIt("O tempora , o mores !"));
        // should produce "...oresmay !" not "...oresmay !ay"

        System.out.println(pigIt("O tempora, o mores!!!"));
        // should produce "...oresmay!!!" not "...ores!!!may"
    }

    public static String pigIt(String str) {
        String[] words = str.trim().split(" ");
        StringBuilder sb = new StringBuilder();
        String newWord = "";

        Pattern pattern1 = Pattern.compile("([a-zA-Z0-9]+)[^a-zA-Z0-9]+");   // special char attached to a word
        Pattern pattern2 = Pattern.compile("[^a-zA-Z0-9]+");                 // special char as a separate 'word'

        for (String word : words) {
            Matcher matcher1 = pattern1.matcher(word);
            Matcher matcher2 = pattern2.matcher(word);
            if (matcher1.find()) {
                // mores! (i.e. special character next to last letter)
                String cleanedWord = word.replaceAll("[^a-zA-Z0-9]+", "");
                String specialCharacters = word.replaceAll("[a-zA-Z0-9]+", "");
                newWord = wordTransformation(cleanedWord);
                sb.append(newWord).append(specialCharacters).append(" ");
            } else if (matcher2.find()) {
                // mores ! (i.e. space between last character and special character - special character is a separate 'word' in String)
                sb.append(word).append(" ");
            } else {
                newWord = wordTransformation(word);
                sb.append(newWord).append(" ");
            }
        }
        return sb.delete(sb.length()-1, sb.length()).toString();
    }

    private static String wordTransformation(String s) {
        return  s.substring(1) + s.charAt(0) + "ay";
    }

    //best solution:
    public static String pigIt2(String str) {
        return str.replaceAll("(\\w)(\\w*)", "$2$1ay");
    }
}
