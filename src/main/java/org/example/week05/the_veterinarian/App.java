package org.example.week05.the_veterinarian;

import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create()
                .start(7007);
        app.routes(getAppointments());
        app.routes(getPatients());
    }

    private static EndpointGroup getAppointments() {
        AppointmentHandler veterinarianCtl = new AppointmentHandler();
        return () -> {
            path("/api/vet", () -> {
              get("/appointments",veterinarianCtl.getAllAppointments());
                get("/appointment/{id}", veterinarianCtl.getAppointmentById());
            });
        };
    }

    private static EndpointGroup getPatients(){
        PatientHandler patientCtl = new PatientHandler();
        return () -> {
            path("/api/vet", () -> {
                get("/patient/{id}", patientCtl.getPatientById());
                get("patients",patientCtl.getAllPatients());

            });
        };

    }


}
