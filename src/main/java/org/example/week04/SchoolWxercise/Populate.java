package main.java.org.example.week04.SchoolWxercise;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Populate {

    public static void main(String[] args) {
            EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
            EntityManager em = emf.createEntityManager();

            try {
                em.getTransaction().begin();
                populate(em);
                em.getTransaction().commit();
            } finally {
                em.close();
            }


    }

    private static void populate(EntityManager em) {
        Semester semester = new Semester("Spring 2021", "Spring semester 2021");
        Teacher teacher = new Teacher("John", "Doe");
        Teacher teacher2 = new Teacher("Jane", "Doe");
        Student student = new Student("John", "Smith");
        Student student2 = new Student("Jane", "Smith");

        semester.addTeacher(teacher);
        semester.addTeacher(teacher2);
        semester.addStudent(student);
        semester.addStudent(student2);

        em.persist(semester);
        em.persist(teacher);
        em.persist(teacher2);
        em.persist(student);
        em.persist(student2);

    }
}
