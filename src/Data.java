package tracker;

import java.util.*;

public class Data {
    private static final Scanner scanner = new Scanner(System.in);

    public static final HashMap<Integer, Student> studentMap = new HashMap<>();

    public static Set<String> emails = new HashSet<>();

    private static int id = 9999;

    public static void addStudent(Student student) {
        id++;
        studentMap.put(id, student);
        emails.add(student.getEmail());
    }

    public static void printListOfStudents() {
        if (studentMap.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("Students:");
        studentMap.keySet().forEach(System.out::println);
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
            studentMap.get(Integer.parseInt(input)).printPoints();
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

                int studentId = points[0];
                Student student = studentMap.get(studentId);

                for (int i = 0; i < Course.COURSES.length; i++) {
                    if (points[i + 1] != 0) {
                        Course.COURSES[i].addSubmissions();
                        Course.COURSES[i].addPoints(points[i + 1]);
                        if (student.getPoints(Course.COURSES[i]) == 0) {
                            Course.COURSES[i].addEnrolledStudent();
                        }
                    }
                }

                student.updatePoints(points[1], points[2], points[3], points[4]);
                System.out.println("Points updated.");

            } catch (IllegalArgumentException e) {
                System.out.println("Incorrect points format.");
            }
        }
    }

}