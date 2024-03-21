package main.java.org.example.week04.DolphinExercise;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Dolphin!");

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        try(EntityManager em = emf.createEntityManager())
        {
            Person p1 = new Person("Hanzi");
            Person p2 = new Person("Finn");
            PersonDetail pd1 = new PersonDetail("Algade 3", 4300, "Holbæk", 45);
            PersonDetail pd2 = new PersonDetail("Bredgade 3", 4300, "Holbæk", 45);
            p1.addPersonDetail(pd1);
            p2.addPersonDetail(pd2);
            Fee f1 = new Fee(125, LocalDate.of(2023, 8, 25));
            Fee f2 = new Fee(150, LocalDate.of(2023, 7, 19));
            p1.addFee(f1);
            p1.addFee(f2);

            Note n1 = new Note("Husk at betale", LocalDate.of(2023, 8, 25), "Hanz");
            Note n2 = new Note("Husk at betale", LocalDate.of(2023, 8, 25), "Hanz");
            p1.addNote(n1);
            p1.addNote(n2);


            em.getTransaction().begin();
            em.persist(p1);
            em.getTransaction().commit();


            DolphinDAO<Person> dao = new DolphinDAO<>();
            Person foundPerson = dao.findById(1, Person.class);
            System.out.println(foundPerson);
            dao.getAllPayments(foundPerson);
            dao.getAllNotesFromPerson(foundPerson);
            dao.getAllNotesPlusNameAndAgeFromPerson(foundPerson);


        }
    }
}