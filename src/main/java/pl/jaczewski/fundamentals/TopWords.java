package pl.jaczewski.fundamentals;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/*
Write a function that, given a string of text (possibly with punctuation and line-breaks),
returns an array of the top-3 most occurring words, in descending order of the number of occurrences.

Assumptions:
    A word is a string of letters (A to Z) optionally containing one or more apostrophes (') in ASCII.
    Apostrophes can appear at the start, middle or end of a word ('abc, abc', 'abc', ab'c are all valid)
    Any other characters (e.g. #, \, / , . ...) are not part of a word and should be treated as whitespace.
    Matches should be case-insensitive, and the words in the result should be lowercased.
    Ties may be broken arbitrarily.
    If a text contains fewer than three unique words, then either the top-2 or top-1 words should be returned,
    or an empty array if a text contains no words.

Examples:

top_3_words("In a village of La Mancha, the name of which I have no desire to call to
mind, there lived not long since one of those gentlemen that keep a lance
in the lance-rack, an old buckler, a lean hack, and a greyhound for
coursing. An olla of rather more beef than mutton, a salad on most
nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so extra
on Sundays, made away with three-quarters of his income.")
# => ["a", "of", "on"]

top_3_words("e e e e DDD ddd DdD: ddd ddd aa aA Aa, bb cc cC e e e")
# => ["e", "ddd", "aa"]

top_3_words("  //wont won't won't")
# => ["won't", "wont"]

For java users, the calls will actually be in the form: TopWords.top3(String s), expecting you to return a List<String>.
Bonus points (not really, but just for fun):
1) Avoid creating an array whose memory footprint is roughly as big as the input text.
2) Avoid sorting the entire array of unique words.

 */

class TopWords {
    public static void main(String[] args) {
        String example1 = " Al'a4 m*a, 'k\toT_a. ' ala ala kota ala abc%de";
        System.out.println("top3(example1) = " + top3(example1));

        String example2 = "e e e e DDD ddd DdD: ddd    ddd aa aA Aa, bb cc cC e e e";
        System.out.println("top3(example2) = " + top3(example2));

//        String example3 = "In a village of La Mancha, the name of which I have no desire to call to " +
//        "mind, there lived not long since one of those gentlemen that keep a lance " +
//        "in the lance-rack, an old buckler, a lean hack, and a greyhound for " +
//        "coursing. An olla of rather more beef than mutton, a salad on most " +
//        "nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so extra " +
//        "on Sundays, made away with three-quarters of his income.";
//        System.out.println("top3(example3) = " + top3(example3));

        String example4 = "  ...  ";
        System.out.println("top3(example4) = " + top3(example4));

        String example5 = "  ''' av'   '  '' ";
        System.out.println("top3(example5) = " + top3(example5));
    }

    public static List<String> top3(String s) {

        String[] words = wordsCleaner(s);
        List<String> wordsNotPureApostrophes = apostropheRemover(words);

        if (wordsNotPureApostrophes.size() == 0 || wordsNotPureApostrophes.get(0).equals("")) {
            return new ArrayList<>();
        }

        Map<String, Integer> wordsInMap = wordCountMap(wordsNotPureApostrophes);
        return sortedMapByValue(wordsInMap);
    }

    private static String[] wordsCleaner(String s) {
        return s.toLowerCase().replaceAll("[^a-z|^'| ]", " ").trim().replaceAll("\\s+", " ").split(" ");
    }

    private static List<String> apostropheRemover(String[] words) {
        return Arrays.stream(words)
                .filter(e -> !(e.matches("'+")))
                .collect(Collectors.toList());
    }

    private static Map<String, Integer> wordCountMap(List<String> words) {
        Map<String, Integer> wordsInMap = new HashMap<>();
        for (String word: words) {
            int count = wordsInMap.containsKey(word) ? wordsInMap.get(word) + 1 : 1;
            wordsInMap.put(word, count);
        }
        return wordsInMap;
    }

    private static List<String> sortedMapByValue(Map<String, Integer> input) {
        return input.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    // for reference:
    // code by PavelKalegin, ranked as the best by Codewars users:
    public static List<String> top3BestPractice(String s) {
        return Arrays.stream(s.toLowerCase().split("[^a-z*|']"))
                .filter(o -> !o.isEmpty() && !o.replace("'","").isEmpty())
                .collect(groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .map(Map.Entry::getKey)
                .limit(3)
                .collect(Collectors.toList());
    }
}
