/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsfacility;

import java.util.Date;

/**
 *
 * @author Haseong Kim
 */
public class Booking {
    private Integer bookingid;
    private int customerid;
    private int bookingtype;
    private String facilityid;
    private String classid;
    private Date bookeddate;
    private Date schedule;
    public Booking() {
    }

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public String getBookingtype() {
        switch (bookingtype) {
            case 0:
                return "Facility";
            case 1:
                return "Class";
            default:
                return "Error";
        }
    }

    public void setBookingtype(int bookingtype) {
        this.bookingtype = bookingtype;
    }

    public String getFacilityid() {
        return facilityid;
    }

    public void setFacilityid(String facilityid) {
        this.facilityid = facilityid;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public Date getBookeddate() {
        return bookeddate;
    }

    public void setBookeddate(Date bookeddate) {
        this.bookeddate = bookeddate;
    }

    public Date getSchedule() {
        return schedule;
    }

    public void setSchedule(Date schedule) {
        this.schedule = schedule;
    }
}
