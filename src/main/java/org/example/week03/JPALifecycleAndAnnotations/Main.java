package org.example.week03.JPALifecycleAndAnnotations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        var em = emf.createEntityManager();
        // entity is in transient state

        Student student1 = new Student("Steve", "Jobs", "steve@gmail", 56);
        Student student2 = new Student("Bill", "Gates", "bill@gmail", 65);
        Student student3 = new Student("Elon", "Musk", "elon@gmail", 49);

        createStudent(em, student1);
        createStudent(em, student2);
        createStudent(em, student3);
        readStudent(em, 2);
        updateStudent(em, student1, new Student("Steve", "Jobs", "stevejobs@gmail", 56));
        deleteStudent(em, 1);
        readAllStudents(em);


    }

    private static List<Student> readAllStudents(EntityManager em) {
        List<Student> students = em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
        em.close();
        return students;
    }

    private static void deleteStudent(EntityManager em, int i) {
        em.getTransaction().begin();
        // entity is in managed state (after persist)
        Student student = em.find(Student.class, i);
        em.remove(student);
        // entity is in detached state after the transaction is committed
        em.getTransaction().commit();
        em.close();
    }

    private static Student updateStudent(EntityManager em, Student oldStudent, Student newStudent) {
        em.getTransaction().begin();
        // entity is in managed state (after persist)
        Student student = em.find(Student.class, oldStudent.getId());
        student.setFirstName(newStudent.getFirstName());
        student.setLastName(newStudent.getLastName());
        student.setEmail(newStudent.getEmail());
        student.setAge(newStudent.getAge());
        // entity is in detached state after the transaction is committed
        em.getTransaction().commit();
        em.close();
        return student;
    }

    private static Student readStudent(EntityManager em, int i) {

        Student student = em.find(Student.class, i);
        em.close();
        return student;
    }

    private static void createStudent(EntityManager em, Student student) {
        em.getTransaction().begin();
        // entity is in managed state (after persist)
        em.persist(student);
        // entity is in detached state after the transaction is committed
        em.getTransaction().commit();
        em.close();
    }
}
