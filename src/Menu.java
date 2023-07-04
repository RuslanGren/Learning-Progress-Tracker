package tracker;

import java.util.Arrays;
import java.util.Scanner;
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
            } else if (input.equalsIgnoreCase("list")) {
                Data.printListOfStudents();
            } else if (input.equalsIgnoreCase("add points")) {
                Data.addPoints();
            } else if (input.equalsIgnoreCase("find")) {
                Data.find();
            } else if (input.equalsIgnoreCase("statistics")) {
                Statistics.menu();
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
                Exceptions.isValid(firstName, "first name.");
                Exceptions.isValid(lastName, "last name.");
                Exceptions.correctEmail(email);
                Exceptions.uniqueEmail(email);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
            Data.addStudent(new Student(firstName, lastName, email));
            System.out.println("The student has been added.");
            countAddStudents++;
        }
    }

}
