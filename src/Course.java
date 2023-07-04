package tracker;

public enum Course {
    JAVA("Java",600,0, 0, 0),

    DSA("DSA", 400, 0, 0, 0),

    DATABASES("Databases", 480, 0, 0, 0),

    SPRING("Spring", 550, 0, 0, 0);

    private String name;
    private final double MAX_POINTS;
    private int enrolledStudents;
    private int submissions;
    private int averageScore;
    private int totalScore = 0;
    public static boolean anybodyEnrolled = false;

    public static final Course[] COURSES = {Course.JAVA, Course.DSA, Course.DATABASES, Course.SPRING};

    Course(String name, double MAX_POINTS, int enrolledStudents, int submissions, int averageScore) {
        this.name = name;
        this.MAX_POINTS = MAX_POINTS;
        this.enrolledStudents = enrolledStudents;
        this.submissions = submissions;
        this.averageScore = averageScore;
    }

    public void addPoints(int points) {
        totalScore += points;
        averageScore = enrolledStudents / totalScore;
    }

    public void addEnrolledStudent() {
        enrolledStudents++;
        anybodyEnrolled = true;
    }

    public void addSubmissions() {
        submissions++;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public double getMAX_POINTS() {
        return MAX_POINTS;
    }

    public int getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(int averageScore) {
        this.averageScore = averageScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(int enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public int getSubmissions() {
        return submissions;
    }

    public void setSubmissions(int submissions) {
        this.submissions = submissions;
    }
}
