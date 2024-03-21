package main.java.org.example.week04.SchoolWxercise;

public class StudentInfo {
    private String fullName;
    private int studentId;
    private String thisSemesterName;
    private String thisSemesterDescription;

    public StudentInfo(String firstname, String lastnam, int studentId, String thisSemesterName, String thisSemesterDescription) {
        this.fullName = firstname + " " + lastnam;
        this.studentId = studentId;
        this.thisSemesterName = thisSemesterName;
        this.thisSemesterDescription = thisSemesterDescription;
    }

}
