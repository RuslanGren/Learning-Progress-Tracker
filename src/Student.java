package tracker;

public class Student {
    private String firstName;

    private String lastName;

    private String email;

    private int pointsJava = 0;

    private int pointsDSA = 0;

    private int pointsDatabases = 0;

    private int pointsSpring = 0;

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void updatePoints(int pointsJava, int pointsDSA, int pointsDatabases, int pointsSpring) {
        this.pointsJava += pointsJava;
        this.pointsDSA += pointsDSA;
        this.pointsDatabases += pointsDatabases;
        this.pointsSpring += pointsSpring;
    }

    public void printPoints() {
        System.out.printf("Java=%d; DSA=%d; Databases=%d; Spring=%d", pointsJava, pointsDSA, pointsDatabases, pointsSpring);
        System.out.println();
    }

    public int getPoints(Course course) {
        switch (course) {
            case JAVA -> {
                return getPointsJava();
            }
            case DSA -> {
                return getPointsDSA();
            }
            case DATABASES -> {
                return getPointsDatabases();
            }
            case SPRING -> {
                return getPointsSpring();
            }
        }
        return 0;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPointsJava() {
        return pointsJava;
    }

    public void setPointsJava(int pointsJava) {
        this.pointsJava = pointsJava;
    }

    public int getPointsDSA() {
        return pointsDSA;
    }

    public void setPointsDSA(int pointsDSA) {
        this.pointsDSA = pointsDSA;
    }

    public int getPointsDatabases() {
        return pointsDatabases;
    }

    public void setPointsDatabases(int pointsDatabases) {
        this.pointsDatabases = pointsDatabases;
    }

    public int getPointsSpring() {
        return pointsSpring;
    }

    public void setPointsSpring(int pointsSpring) {
        this.pointsSpring = pointsSpring;
    }
}
