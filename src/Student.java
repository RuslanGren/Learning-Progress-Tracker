package tracker;

public class Student {
    private String firstName;

    private String lastName;

    private String email;

    private int java = 0;

    private int dsa = 0;

    private int databases = 0;

    private int spring = 0;

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void updatePoints(int java, int dsa, int databases, int spring) {
        this.java += java;
        this.dsa += dsa;
        this.databases += databases;
        this.spring += spring;
    }

    public void printPoints() {
        System.out.printf("Java=%d; DSA=%d; Databases=%d; Spring=%d", java, dsa, databases, spring);
        System.out.println();
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

}
