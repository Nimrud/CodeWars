package pl.jaczewski.fundamentals;

/*
The new "Avengers" movie has just been released!
There are a lot of people at the cinema box office standing in a huge line.
Each of them has a single 100, 50 or 25 dollar bill. An "Avengers" ticket costs 25 dollars.

Vasya is currently working as a clerk. He wants to sell a ticket to every single person in this line.
Can Vasya sell a ticket to every person and give change if he initially has no money
and sells the tickets strictly in the order people queue?

Return YES, if Vasya can sell a ticket to every person and give change with the bills he has at hand at that moment.
Otherwise, return NO.

Examples:
Line.Tickets(new int[] {25, 25, 50}) // => YES
Line.Tickets(new int[] {25, 100}) // => NO. Vasya will not have enough money to give change to 100 dollars
Line.Tickets(new int[] {25, 25, 50, 50, 100}) // => NO. Vasya will not have the right bills to give 75 dollars of change
(you can't make two bills of 25 from one of 50)

 */

public class TicketSale {
    public static void main(String[] args) {
        System.out.println(TicketSale.Tickets(new int[] {25, 25, 50, 50, 100}));
    }

    public static String Tickets(int[] peopleInLine) {
        int bill25 = 0;
        int bill50 = 0;
        int bill100 = 0;
        for (int p : peopleInLine) {
            if (p == 100) {
                bill100++;
            } else if (p == 50) {
                bill50++;
            } else {
                bill25++;
            }
//            System.out.println("25: " + bill25);
//            System.out.println("50: " + bill50);
//            System.out.println("100: " + bill100);

            if (p == 100) {
//                System.out.println("=> Stówa");
                if (bill50 >= 1 && bill25 >= 1) {
                    bill50--;
                    bill25--;
//                    System.out.println("25: " + bill25);
//                    System.out.println("50: " + bill50);
                } else if (bill25 >= 3) {
                    bill25 -= 3;
//                    System.out.println("25 (3): " + bill25);
                } else {
                    return "NO";
                }
            } else if (p == 50) {
//                System.out.println("=> Pięćdziesiątka");
                if (bill25 >= 1) {
                    bill25--;
//                    System.out.println("25: " + bill25);
                } else {
                    return "NO";
                }
            } else {
//                System.out.println("=> Dwudziestka piątka");
            }
//            System.out.println("===========");
        }
        return "YES";
    }
}
