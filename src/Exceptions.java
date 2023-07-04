package tracker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exceptions {
    public static void correctEmail(String email) throws IllegalArgumentException {
        String regex = "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches() || !email.contains(".")) {
            throw new IllegalArgumentException("Incorrect email.");
        }
    }

    public static void uniqueEmail(String email) throws IllegalArgumentException {
        if (Data.emails.contains(email)) {
            throw new IllegalArgumentException("This email is already taken.");
        }
    }

    public static void isValid(String name, String type) {
        // Check for minimum length of name and surname
        if (name.length() < 2) {
            throw new IllegalArgumentException("Incorrect " + type);
        }

        // Check for hyphens and apostrophes as first or last characters
        if (name.startsWith("-") || name.startsWith("'") ||
                name.endsWith("-") || name.endsWith("'")) {
            throw new IllegalArgumentException("Incorrect " + type);
        }

        // Check for adjacent hyphens or apostrophes
        if (name.contains("--") || name.contains("''") ||
                name.contains("-'") || name.contains("'-")) {
            throw new IllegalArgumentException("Incorrect " + type);
        }

        // Check for valid characters
        String pattern = "^[A-Za-z'-]+$";
        if (!name.matches(pattern)) {
            throw new IllegalArgumentException("Incorrect " + type);
        }
    }

    public static boolean validId(String id) {
        try {
            Data.studentMap.get(Integer.parseInt(id));
        } catch (IllegalArgumentException e) {
            System.out.println("No student is found for id=" + id + ".");
            return false;
        }
        return true;
    }
}
