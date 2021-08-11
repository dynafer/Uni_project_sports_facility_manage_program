/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsfacility;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
/**
 *
 * @author kimh22
 */
public class AdvisorController {
    ArrayList custList = new ArrayList();
    ArrayList bookList = new ArrayList();
    CustomerRepoImpl custRepo = new CustomerRepoImpl();
    BookingRepoImpl bookRepo = new BookingRepoImpl();
    Connection conn;
    String data_url = "jdbc:derby://localhost:1527/sportsfacility";
    String data_name = "kimh22";
    String data_pass = "hiqpfyehaaj8874";
    public AdvisorController() {
        try {
            conn = DriverManager.getConnection(data_url, data_name, data_pass);
            System.out.println("Connect to database...");
            if(conn != null) {
                ArrayList list = custRepo.read(conn);
                custList = list;
                list = bookRepo.read(conn);
                bookList = list;
            } else {
                System.out.println("null");
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Connection error...");
        }
    }
    public AdvisorController(int customerid, boolean isBooking) {
        if(customerid == 0) {
            custList = new ArrayList();
        } else {
            if(isBooking == false) {
                try {
                    conn = DriverManager.getConnection(data_url, data_name, data_pass);
                    if(conn != null) {
                        ArrayList list = custRepo.read(conn, customerid);
                        custList = list;
                    } else {
                        System.out.println("null");
                    }
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            } else {
                try {
                    conn = DriverManager.getConnection(data_url, data_name, data_pass);
                    if(conn != null) {
                        ArrayList list = bookRepo.read(conn, customerid);
                        bookList = list;
                    } else {
                        System.out.println("null");
                    }
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        }
    }
    public void refreshCustomerList(String query) {
        custList.clear();
        if(!query.isEmpty()) {
            try {
                conn = DriverManager.getConnection(data_url, data_name, data_pass);
                if(conn != null) {
                    ArrayList list = custRepo.read(conn, query);
                    custList = list;
                } else {
                    System.out.println("null");
                }
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }
    public String isUpdatable(int custid, boolean isOptionItem) {
        if(custid == 0) {
            return "";
        } else {
            bookList.clear();
            AdvisorController getcustlist = new AdvisorController(custid, false);
            AdvisorController getbooklist = new AdvisorController(custid, true);
            Customer customer = (Customer) getcustlist.custList.get(0);
            
            if(isOptionItem == false) {
                if(!"Platinum".equals(customer.displayMembership()) && "true".equals(customer.getIspaidannual())) {
                    return "Updatable";
                } else if("Silver".equals(customer.displayMembership()) == true && getbooklist.getCurrentYearBookings() >= 10) {
                    return "Updatable";
                } else if("Platinum".equals(customer.displayMembership()) && "false".equals(customer.getIspaidannual())) {
                    return "Updatable";
                } else {
                    return "";
                }
            } else {
                if("true".equals(customer.getIspaidannual()) && getbooklist.getCurrentYearBookings() >= 10) {
                    return "Platinum,Gold";
                } else if("true".equals(customer.getIspaidannual()) && getbooklist.getCurrentYearBookings() < 10) {
                    return "Platinum,NotGold";
                } else if("Silver".equals(customer.displayMembership()) == true && getbooklist.getCurrentYearBookings() >= 10) {
                    return "Gold";
                } else {
                    if(getbooklist.getCurrentYearBookings() >= 10) {
                        return "Gold";
                    } else if(getbooklist.getCurrentYearBookings() < 10) {
                        return "Silver";
                    }
                    return customer.displayMembership();
                }
            }
        }
    }
    public boolean updateCustomerInfo(int custid, String name, int mbship) {
        if(custid == 0 || name.isEmpty()) {
            return false;
        } else {
            String isUpdatable = this.isUpdatable(custid, false);
            Customer customer = (Customer) this.custList.get(0);
            String tempMembership;
            ArrayList updateValues = new ArrayList();
            boolean updateResult = false;
            switch(mbship) {
                case 0:
                    tempMembership = "Silver";
                    break;
                case 1:
                    tempMembership = "Gold";
                    break;
                case 2:
                    tempMembership = "Platinum";
                    break;
                default:
                    tempMembership = "Silver";
                    break;
            }
            if("Updatable".equals(isUpdatable) || "Platinum".equals(customer.displayMembership())) {
                try {
                    conn = DriverManager.getConnection(data_url, data_name, data_pass);
                    if(conn != null) {
                        updateValues.add(0, custid);
                        updateValues.add(1, name);
                        updateValues.add(2, mbship);
                        updateResult = custRepo.update(conn, updateValues);
                    }
                    conn.close();
                    return updateResult;
                } catch (SQLException ex) {
                    System.out.println(ex);
                    return false;
                }
            } else if(!name.equals(customer.getName()) && tempMembership.equals(customer.displayMembership())) {
                try {
                    conn = DriverManager.getConnection(data_url, data_name, data_pass);
                    if(conn != null) {
                        updateValues.add(0, custid);
                        updateValues.add(1, name);
                        updateValues.add(2, mbship);
                        updateResult = custRepo.update(conn, updateValues);
                    }
                    conn.close();
                    return updateResult;
                } catch (SQLException ex) {
                    System.out.println(ex);
                    return false;
                }
            } else if(mbship == 0 && "Gold".equals(customer.displayMembership())) {
                try {
                    conn = DriverManager.getConnection(data_url, data_name, data_pass);
                    if(conn != null) {
                        updateValues.add(0, custid);
                        updateValues.add(1, name);
                        updateValues.add(2, 0);
                        updateResult = custRepo.update(conn, updateValues);
                    }
                    conn.close();
                    return updateResult;
                } catch (SQLException ex) {
                    System.out.println(ex);
                    return false;
                }
            } else if(name.equals(customer.getName()) && tempMembership.equals(customer.displayMembership())) {
                JOptionPane.showMessageDialog(null, "Nothing is changed!");
                return false;
            } else {
                JOptionPane.showMessageDialog(null, "The customer is not updatable!");
                return false;
            }
        }
    }
    public boolean updateAllCustomers() {
        if(custList.isEmpty()) {
            return false;
        } else {
            for(int i = 0; i < custList.size(); i++) {
                Customer tempCusto = (Customer)custList.get(i);
                String updatable = isUpdatable(tempCusto.getCustomerid(), false);
                String uMembership = isUpdatable(tempCusto.getCustomerid(), true);
                ArrayList updateValues = new ArrayList();
                int nextMembership;
                if(null == uMembership) {
                    nextMembership = 0;
                } else switch (uMembership) {
                    case "Gold":
                        nextMembership = 1;
                        break;
                    case "Platinum,NotGold":
                        nextMembership = 2;
                        break;
                    case "Platinum,Gold":
                        nextMembership = 2;
                        break;
                    default:
                        nextMembership = 0;
                        break;
                }
                if("Updatable".equals(updatable)) {
                    try {
                        conn = DriverManager.getConnection(data_url, data_name, data_pass);
                        if(conn != null) {
                            updateValues.add(0, tempCusto.getCustomerid());
                            updateValues.add(1, tempCusto.getName());
                            updateValues.add(2, nextMembership);
                            if(custRepo.update(conn, updateValues) == true) {
                                updateValues.clear();
                            } else {
                                return false;
                            }
                        }
                        conn.close();
                    } catch (SQLException ex) {
                        System.out.println(ex);
                        return false;
                    }
                }
            }
            return true;
        }
    }
    public boolean deleteCustomer(int custid) {
        if(custid == 0 || custList.isEmpty()) {
            return false;
        } else {
            boolean deleteResult = false;
            try {
                conn = DriverManager.getConnection(data_url, data_name, data_pass);
                if(conn != null) {
                    deleteResult = custRepo.delete(conn, custid);
                }
                conn.close();
                return deleteResult;
            } catch (SQLException ex) {
                System.out.println(ex);
                return false;
            }
        }
    }
    public int getCurrentYearBookings() {
        int currentYear = (Calendar.getInstance().getTime()).getYear();
        int actualNumberofBookings = 0;
        for(int i=0; i < bookList.size(); i++) {
            Booking bookings = (Booking) bookList.get(i);
            if(bookings.getBookeddate().getYear() == currentYear) {
                actualNumberofBookings++;
            }
        }
        return actualNumberofBookings;
    }
}
