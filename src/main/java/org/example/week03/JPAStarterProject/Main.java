package main.java.org.example.week03.JPAStarterProject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        var em = emf.createEntityManager();

        Person steve = new Person("Steve","steve@gmail", Person.Gender.MALE);

        //create(em, steve);
        Person person = em.find(Person.class,1);
        System.out.println(person);



    }

    private static void create(EntityManager em, Person steve) {
        em.getTransaction().begin();
        em.persist(steve);
        em.getTransaction().commit();
        em.close();
    }
}
