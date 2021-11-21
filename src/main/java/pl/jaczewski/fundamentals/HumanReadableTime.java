package pl.jaczewski.fundamentals;

/*
Write a function, which takes a non-negative integer (seconds) as input
and returns the time in a human-readable format (HH:MM:SS)

    HH = hours, padded to 2 digits, range: 00 - 99
    MM = minutes, padded to 2 digits, range: 00 - 59
    SS = seconds, padded to 2 digits, range: 00 - 59

The maximum time never exceeds 359999 (99:59:59)
 */

class HumanReadableTime {
    public static void main(String[] args) {
        System.out.println(makeReadable(3815));
    }

    public static String makeReadable(int seconds) {
        int hours;
        int minutes;

        hours = seconds / 3600;
        seconds -= hours * 3600;
        minutes = seconds / 60;
        seconds -= minutes * 60;

        String h = String.format("%02d", hours);
        String m = String.format("%02d", minutes);
        String s = String.format("%02d", seconds);

        StringBuilder sb = new StringBuilder();

        return sb.append(h).append(":").append(m).append(":").append(s).toString();
    }


}
