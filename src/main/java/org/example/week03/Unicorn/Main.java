package org.example.week03.Unicorn;

public class Main {

    public static void main(String[] args) {
        UnicornDAO unicornDAO = new UnicornDAO();
        //Create a Unicorn and save it using persist
        Unicorn newUnicorn = new Unicorn();
        newUnicorn.setName("Sparkle");
        newUnicorn.setAge(5);
        newUnicorn.setPowerStrength(20);
        Unicorn createdUnicorn = unicornDAO.save(newUnicorn);

        //Find the Unicorn by id
        Unicorn foundUnicorn = unicornDAO.findById(createdUnicorn.getId());
        System.out.println("Found: " + foundUnicorn);

        //Update the Unicorn
        foundUnicorn.setPowerStrength(80);
        Unicorn updatedUnicorn = unicornDAO.update(foundUnicorn);
        System.out.println("Updated: " + updatedUnicorn);

        //Delete the Unicorn
        //unicornDAO.delete(updatedUnicorn.getId());

        //Close the EntityManagerFactory
        unicornDAO.close();




    }
}
