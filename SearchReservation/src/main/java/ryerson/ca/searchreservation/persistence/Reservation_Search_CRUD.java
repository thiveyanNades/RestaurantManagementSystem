/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.searchreservation.persistence;

import ryerson.ca.searchreservation.helper.ReservationSlot;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Reservation_Search_CRUD {

    private static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/search_LBS?autoReconnect=true&useSSL=false", "root", "student");
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
            String query = "SELECT * FROM Reservation_Search";
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
    /*
    public static boolean addReservation(ReservationSlot reservation) {
    try {
        try (Connection con = getConnection()) {
            String query = "INSERT INTO Reservation (date, time, tableOccupancy, id) VALUES (?, ?, ?, ?)";
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
    */
    public static ArrayList<ReservationSlot> getReservation(String date) {
        ArrayList<ReservationSlot> reservations = new ArrayList<>();
        try {
            Connection con = getConnection();
            String q = "SELECT * FROM Reservation_Search WHERE date = ?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String date1 = rs.getString("date");
                String time1 = rs.getString("time");
                int tableOccupancy1 = rs.getInt("tableOccupancy");
                int reservationID = rs.getInt("id");
                ReservationSlot reservation = new ReservationSlot(date1, time1, tableOccupancy1, reservationID);
                reservations.add(reservation);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
    }
    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>"+reservations.size());
    return reservations;
}
}
