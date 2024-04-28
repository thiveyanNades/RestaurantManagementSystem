/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.addreservation.persistence;

import ryerson.ca.addreservation.helper.ReservationSlot;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author student
 */
public class Reservation_CRUD {

    private static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/add_LBS?autoReconnect=true&useSSL=false", "root", "student");
            System.out.println("Connection established.");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
    
        public static List<ReservationSlot> getAllReservations() {
        List<ReservationSlot> reservations = new ArrayList<>();
        try {
            Connection con = getConnection();
            String query = "SELECT * FROM Reservation_Add";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String date = rs.getString("date");
                    String time = rs.getString("time");
                    int tableOccupancy = rs.getInt("tableOccupancy");
                    int id = rs.getInt("id");

                    ReservationSlot reservation = new ReservationSlot(date, time, tableOccupancy, id);
                    reservations.add(reservation);
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return reservations;
    }
    public static boolean addReservation(ReservationSlot reservation) {
    try {
        try (Connection con = getConnection()) {
            String query = "INSERT INTO Reservation_Add(date, time, tableOccupancy, id) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, reservation.getDate());
                ps.setString(2, reservation.getTime());
                ps.setInt(3, reservation.getCapacity());
                ps.setInt(4, reservation.getReservationID());
                ps.executeUpdate();
                System.out.println("Reservation added successfully.");
                return true;
                
            }
        }
    } catch (SQLException e) {
        System.err.println("Error adding reservation to the database: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
    }
    public static ReservationSlot getReservation(String date) {
        ReservationSlot reservation = null;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM Reservation_Add WHERE date = ?")) {
            ps.setString(1, date);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String time = rs.getString("time");
                    int tableOccupancy = rs.getInt("tableOccupancy");

                    reservation = new ReservationSlot(date, time, tableOccupancy);
                }
            }

		}catch(Exception e){System.out.println(e);}
            
    
        return reservation;
        
    }
}
