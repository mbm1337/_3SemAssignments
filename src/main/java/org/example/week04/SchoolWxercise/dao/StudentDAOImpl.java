package main.java.org.example.week04.SchoolWxercise.dao;



import main.java.org.example.week04.SchoolWxercise.Semester;
import main.java.org.example.week04.SchoolWxercise.Student;
import main.java.org.example.week04.SchoolWxercise.StudentInfo;
import main.java.org.example.week04.SchoolWxercise.Teacher;

import java.util.List;

public class StudentDAOImpl implements IStudentDAO {
    @Override
    public List<Student> findAllStudentsByFirstName(String firstName) {
        return null;
    }

    @Override
    public List<Student> findAllStudentsByLastName(String lastName) {
        return null;
    }

    @Override
    public long findTotalNumberOfStudentsBySemester(String semesterName) {
        return 0;
    }


    @Override
    public long findTotalNumberOfStudentsByTeacher(Teacher teacher) {
        return 0;
    }

    @Override
    public Teacher findTeacherWithMostSemesters() {
        return null;
    }

    @Override
    public Semester findSemesterWithFewestStudents() {
        return null;
    }

    @Override
    public StudentInfo getAllStudentInfo(int id) {
        return null;
    }
}
