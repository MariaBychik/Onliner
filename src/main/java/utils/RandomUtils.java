package utils;

import java.util.Random;

public class RandomUtils {
    public static String getEmailString() {
        String emailChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder email = new StringBuilder();
        java.util.Random rnd = new java.util.Random();
        while (email.length() < 8) {
            int index = (int) (rnd.nextFloat() * emailChars.length());
            email.append(emailChars.charAt(index));
        }
        String emailStr = email.toString();
        return emailStr;
    }

    public static String getPasswordString() {
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 12;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }
}
