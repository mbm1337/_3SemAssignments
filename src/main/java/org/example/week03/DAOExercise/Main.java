package main.java.org.example.week03.DAOExercise;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;


import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.buildEntityFactoryConfig();
        EntityManager em = emf.createEntityManager();

        StudentDAO studentDAO = new StudentDAO();
        Student student = new Student("John", "Doe", "john@email.com", 25);
        studentDAO.save(student);
        studentDAO.save(new Student("Jane", "Doe", "jane@email.com", 23));
        studentDAO.save(new Student("Kim", "Larsen", "kim@email.com", 20));

        List<Student> students = studentDAO.findAll();
        students.forEach(System.out::println);

        Student studentToUpdate = studentDAO.findById(1);
        studentToUpdate.setAge(26);
        studentDAO.update(studentToUpdate);

        students = studentDAO.findAll();
        students.forEach(System.out::println);


        studentDAO.delete(3);

        students = studentDAO.findAll();
        students.forEach(System.out::println);

        em.close();
        emf.close();

    }
}
