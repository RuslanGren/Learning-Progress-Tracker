package tracker;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Statistics {

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the name of a course to see details or 'back' to quit:");
        printStats();
        while(true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("back")) {
                return;
            }
            try {
                printCourse(Course.valueOf(input.toUpperCase()));
            } catch (IllegalArgumentException e) {
                System.out.println("Unknown course.");
            }
        }
    }

    public static String find(Function<Course, Integer> getStats, boolean reverse) {
        List<String> result = new ArrayList<>();
        Map<Integer, List<Course>> map = new HashMap<>();

        Arrays.stream(Course.COURSES)
                .forEach(course -> {
                    int stats = getStats.apply(course);
                    map.computeIfAbsent(stats, k -> new ArrayList<>()).add(course);
                });

        Integer targetNumber = reverse ? Collections.min(map.keySet()) : Collections.max(map.keySet());

        map.get(targetNumber).forEach(course -> result.add(course.getName()));

        return result.stream().map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    public static void printStats() {
        String mostPopular = "n/a";
        String leastPopular = "n/a";
        String highestActivity = "n/a";
        String lowestActivity = "n/a";
        String easiestCourse = "n/a";
        String hardestCourse = "n/a";

        if (Course.anybodyEnrolled) {
            mostPopular = find(Course::getEnrolledStudents, false);
            leastPopular = find(Course::getEnrolledStudents, true);
            highestActivity = find(Course::getSubmissions, false);
            lowestActivity = find(Course::getSubmissions, true);
            easiestCourse = find(Course::getAverageScore, false);
            hardestCourse = find(Course::getAverageScore, true);
        }

        if (leastPopular.equals(mostPopular)) {
            leastPopular = "n/a";
        }
        if (lowestActivity.equals(highestActivity)) {
            lowestActivity = "n/a";
        }

        System.out.println("Most popular: " + mostPopular);
        System.out.println("Least popular: " + leastPopular);
        System.out.println("Highest activity: " + highestActivity);
        System.out.println("Lowest activity: " + lowestActivity);
        System.out.println("Easiest course: " + easiestCourse);
        System.out.println("Hardest course: " + hardestCourse);
    }

    public static void printCourse(Course course) {
        System.out.println(course.getName());
        System.out.println("id    points    completed");
        for (Map.Entry<Integer, Student> entry : StudentComparator.sortByCourse(course)) {
            int points = entry.getValue().getPoints(course);

            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator('.');
            DecimalFormat decimalFormat = new DecimalFormat("0.0", symbols);
            decimalFormat.setRoundingMode(RoundingMode.HALF_UP);

            String rate = decimalFormat.format((double) points / course.getMAX_POINTS() * 100);
            System.out.println(entry.getKey() + " " + points + "    " + rate + "%");
        }
    }

}