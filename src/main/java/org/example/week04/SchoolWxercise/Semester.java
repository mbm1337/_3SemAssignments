package org.example.week04.SchoolWxercise;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "semester")
@Entity
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany
    private Set<Teacher> teaches = new HashSet<>();

    @OneToMany(mappedBy = "currentSemester")
    private Set<Student> students = new HashSet<>();


    public Semester(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addTeacher(Teacher teacher) {
        this.teaches.add(teacher);
        if (teacher != null) {
            teacher.getCourses().add(this);
        }
    }

    public void addStudent(Student student) {
        this.students.add(student);
        if (student != null) {
            student.setCurrentSemester(this);
        }
    }





}
