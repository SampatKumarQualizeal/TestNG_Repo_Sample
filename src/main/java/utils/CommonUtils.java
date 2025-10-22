package utils;

import java.util.List;
import java.util.Random;

public class CommonUtils {
    private static final Random random = new Random();

    // Generates a random string of given length using letters
    public static String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    // Generates a random number of given count (count = number of digits)
    public static int generateRandomNumber(int count) {
        if (count <= 0) return 0;
        int min = (int) Math.pow(10, count - 1);
        int max = (int) Math.pow(10, count) - 1;
        return randomNumber(min, max);
    }

    // Returns a random value from a list (generic)
    public static <T> T getRandomValueFromList(List<T> list) {
        if (list == null || list.isEmpty()) return null;
        return list.get(random.nextInt(list.size()));
    }

    // Generates a random string of given length using letters and numbers
    public static String generateRandomAlphanumericString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    // Generates a random numeric string of given length
    public static String generateRandomNumeric(int length) {
        String digits = "0123456789";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(digits.charAt(random.nextInt(digits.length())));
        }
        return sb.toString();
    }

    // Returns a random element from a list (generic)
    public static <T> T getRandomElement(List<T> list) {
        return getRandomValueFromList(list);
    }

    // Returns a random number between min and max (inclusive)
    public static int randomNumber(int min, int max) {
        if (min > max) throw new IllegalArgumentException("min > max");
        return min + random.nextInt((max - min) + 1);
    }
}
