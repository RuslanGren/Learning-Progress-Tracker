package tracker;

import java.util.*;

public class StudentComparator implements Comparator<Map.Entry<Integer, Student>> {
    private final Course course;

    public StudentComparator(Course course) {
        this.course = course;
    }

    @Override
    public int compare(Map.Entry<Integer, Student> entry1, Map.Entry<Integer, Student> entry2) {
        // compare points
        int pointsCompare = Integer.compare(entry2.getValue().getPoints(course), entry1.getValue().getPoints(course));

        if (pointsCompare != 0) {
            // sort by points in descending order
            return pointsCompare;
        } else {
            // sort by key
            return Integer.compare(entry1.getKey(), entry2.getKey());
        }
    }

    public static List<Map.Entry<Integer, Student>> sortByCourse(Course course) {
        List<Map.Entry<Integer, Student>> studentList = new ArrayList<>();
        for (Map.Entry<Integer, Student> entry : Data.studentMap.entrySet()) {
            int points = entry.getValue().getPoints(course);
            if (points > 0) {
                studentList.add(entry);
            }
        }
        studentList.sort(new StudentComparator(course));
        return studentList;
    }

}