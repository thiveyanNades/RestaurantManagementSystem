/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.addreservation.business;

import java.util.ArrayList;
import ryerson.ca.addreservation.helper.ReservationSlot;
import ryerson.ca.addreservation.persistence.Reservation_CRUD;

/**
 *
 * @author student
 */
public class AddReservation{

    private ArrayList<ReservationSlot> getReservationList() {
        // Implement the logic to retrieve the reservation list from the database
        return (ArrayList<ReservationSlot>) Reservation_CRUD.getAllReservations();
    }
    
    public ReservationSlot getReservation(String date){
        ReservationSlot reservation = Reservation_CRUD.getReservation(date);
        return reservation;
    }
    
    public boolean addReservation(String date, String time, int tableOccupancy) {
        ReservationSlot newReservation = new ReservationSlot(date, time, tableOccupancy);
        // Call the method in Reservation_CRUD to add the reservation to the database
        return (Reservation_CRUD.addReservation(newReservation));
    }
}