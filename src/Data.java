package tracker;

import java.util.*;

public class Data {
    private static final Scanner scanner = new Scanner(System.in);

    public static final HashMap<Integer, Student> map = new HashMap<>();

    public static Set<String> emails = new HashSet<>();

    private static int id = 9999;

    public static void addStudent(Student student) {
        id++;
        map.put(id, student);
        emails.add(student.getEmail());
    }

    public static void printListOfStudents() {
        if (map.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("Students:");
        map.keySet().forEach(System.out::println);
    }

    public static void find() {
        System.out.println("Enter an id or 'back' to return");
        while(true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("back")) {
                break;
            }
            if (!Exceptions.validId(input)) {
                continue;
            }
            System.out.print(input + " points: ");
            map.get(Integer.parseInt(input)).printPoints();
        }
    }

    public static void addPoints() {
        System.out.println("Enter an id and points or 'back' to return:");
        while (true) {
            try {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    break;
                }
                String[] args = input.split(" ");
                if (args.length != 5) {
                    throw new IllegalArgumentException();
                }
                if (!Exceptions.validId(args[0])) {
                    continue;
                }
                int[] points = Arrays.stream(args).mapToInt(Integer::parseInt)
                        .peek(num -> {
                            if (num < 0) {
                                throw new IllegalArgumentException();
                            }
                        })
                        .toArray();
                map.get(points[0]).updatePoints(points[1], points[2], points[3], points[4]);
                System.out.println("Points updated.");
            } catch (IllegalArgumentException e) {
                System.out.println("Incorrect points format.");
            }
        }
    }

}
