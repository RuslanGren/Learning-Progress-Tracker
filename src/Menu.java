package tracker;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    public void start() {
        System.out.println("Learning Progress Tracker");
        while(true) {
            String input = scanner.nextLine().strip();
            if (input.isEmpty()) {
                System.out.println("No input");
            } else if (input.equalsIgnoreCase("exit")) {
                System.out.println("Bye!");
                break;
            } else if (input.equalsIgnoreCase("add students")) {
                addStudents();
            } else if (input.equalsIgnoreCase("back")) {
                System.out.println("Enter 'exit' to exit the program");
            } else {
                System.out.println("Error: unknown command!");
            }
        }
    }

    public void addStudents() {
        System.out.println("Enter student credentials or 'back' to return");
        int countAddStudents = 0;
        while(true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("back")) {
                System.out.println("Total " + countAddStudents + " students have been added.");
                return;
            }
            String[] words = input.split(" ");
            if (words.length < 3) {
                System.out.println("Incorrect credentials");
                continue;
            }
            String firstName = words[0];
            String email = words[words.length - 1];
            String lastName = Arrays.stream(words, 1, words.length - 1)
                    .collect(Collectors.joining(""));
            try {
                isValid(firstName, "first name.");
                isValid(lastName, "last name.");
                correctEmail(email);
            } catch (Exception e) {
                System.out.println("Incorrect " + e.getMessage());
                continue;
            }
            System.out.println("The student has been added.");
            countAddStudents++;
        }
    }

    public void correctEmail(String email) throws IllegalArgumentException {
        String regex = "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches() || !email.contains(".")) {
            throw new IllegalArgumentException("email.");
        }
    }

    public void isValid(String name, String type) {
        // Check for minimum length of name and surname
        if (name.length() < 2) {
            throw new IllegalArgumentException(type);
        }

        // Check for hyphens and apostrophes as first or last characters
        if (name.startsWith("-") || name.startsWith("'") ||
                name.endsWith("-") || name.endsWith("'")) {
            throw new IllegalArgumentException(type);
        }

        // Check for adjacent hyphens or apostrophes
        if (name.contains("--") || name.contains("''") ||
                name.contains("-'") || name.contains("'-")) {
            throw new IllegalArgumentException(type);
        }

        // Check for valid characters
        String pattern = "^[A-Za-z'-]+$";
        if (!name.matches(pattern)) {
            throw new IllegalArgumentException(type);
        }
    }

}
