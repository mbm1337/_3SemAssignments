package org.example.week05.the_veterinarian;

import io.javalin.http.Handler;

import java.util.Date;
import java.util.List;

public class AppointmentHandler {

    List<Appointment> appointments = List.of(
            new Appointment(1, new Date(), "Checkup", 1),
            new Appointment(2, new Date(), "Vaccination", 2),
            new Appointment(3, new Date(), "Surgery", 3)

    );


    public Handler getAllAppointments() {
        return ctx -> {
            ctx.json(appointments);
        };
    }

    public Handler getAppointmentById() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Appointment appointment = appointments.get(id);
            if (appointment != null) {
                ctx.json(appointment);
            } else {
                ctx.status(404);
            }
        };
    }

class Appointment{
    int id;
    Date date;
    String reason;
    int patientId;

    public Appointment(int id, Date date, String reason, int patientId){
        this.id = id;
        this.date = date;
        this.reason = reason;
        this.patientId = patientId;
    }
}
