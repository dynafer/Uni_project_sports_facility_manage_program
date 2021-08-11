/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsfacility;

import java.sql.*;
import java.util.ArrayList;

public class BookingRepoImpl implements BookingRepo {
    private ArrayList<Customer> bookings;
    
    @Override
    public ArrayList read(Connection conn) {
        ArrayList list = new ArrayList();
        try {
            try (Statement st = conn.createStatement()) {
                ResultSet rs;
                String sql = "SELECT * FROM BOOKING ORDER BY BOOKINGID ASC";
                rs=st.executeQuery(sql);
                
                while(rs.next()) {
                    Booking book = new Booking();
                    book.setBookingid(rs.getInt("BOOKINGID"));
                    book.setCustomerid(rs.getInt("CUSTOMERID"));
                    book.setBookingtype(rs.getInt("BOOKINGTYPE"));
                    book.setFacilityid(rs.getString("FACILITYID"));
                    book.setClassid(rs.getString("CLASSID"));
                    book.setBookeddate(rs.getDate("BOOKEDDATE"));
                    book.setSchedule(rs.getDate("SCHEDULE"));
                    list.add(book);
                }
                rs.close();
            }
        } catch (SQLException ex) {
            System.out.println("SQLException failed.");
        }
        bookings = list;
        return bookings;
    }
    @Override
    public ArrayList read(Connection conn, int customerid) {
        ArrayList list = new ArrayList();
        if(customerid == 0) {
            return list;
        } else {
            try {
                try (Statement st = conn.createStatement()) {
                    ResultSet rs;
                    String sql = "SELECT * FROM BOOKING WHERE CUSTOMERID = "+customerid;
                    rs=st.executeQuery(sql);

                    while(rs.next()) {
                        Booking book = new Booking();
                        book.setBookingid(rs.getInt("BOOKINGID"));
                        book.setCustomerid(rs.getInt("CUSTOMERID"));
                        book.setBookingtype(rs.getInt("BOOKINGTYPE"));
                        book.setFacilityid(rs.getString("FACILITYID"));
                        book.setClassid(rs.getString("CLASSID"));
                        book.setBookeddate(rs.getDate("BOOKEDDATE"));
                        book.setSchedule(rs.getDate("SCHEDULE"));
                        list.add(book);
                    }
                    rs.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQLException failed.");
            }
            bookings = list;
            return bookings;
        }
    }
}
