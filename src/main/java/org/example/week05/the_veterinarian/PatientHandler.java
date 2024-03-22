package org.example.week05.the_veterinarian;

import io.javalin.http.Handler;

import java.util.List;

public class PatientHandler {
    List<Patient> patients = List.of(
            new Patient(1, "Fido", "Dog", "Peanuts", "Aspirin"),
            new Patient(2, "Whiskers", "Cat", "Fish", "None"),
            new Patient(3, "Bubbles", "Fish", "None", "None")
    );

    public Handler getPatientById() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Patient patient = patients.get(id);
            if (patient != null) {
                ctx.json(patient);
                ctx.status(200);
            } else {
                ctx.status(404);
            }
        };
    }

    public Handler getAllPatients() {
        return ctx -> {
            ctx.json(patients);
            ctx.status(200);
        };
    }
}

class Patient {
    int id;
    String name;
    String species;
    String allergies;
    String medications;

    public Patient(int id, String name, String species, String allergies, String medications) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.allergies = allergies;
        this.medications = medications;
    }
}
