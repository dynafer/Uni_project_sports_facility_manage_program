/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsfacility;

import java.sql.*;
import java.util.*;

public class CustomerRepoImpl implements CustomerRepo {
    private ArrayList<Customer> customers;
    
    /**
     *
     * @param conn
     * @return
     */
    @Override
    public ArrayList read(Connection conn) {
        ArrayList list = new ArrayList();
        try {
            try (Statement st = conn.createStatement()) {
                ResultSet rs;
                String sql = "SELECT * FROM CUSTOMER ORDER BY CUSTOMERID ASC";
                rs=st.executeQuery(sql);
                
                while(rs.next()) {
                    CustomerFactory custFactory = new CustomerFactory();
                    Customer cust;
                    cust = custFactory.addCustomer(rs.getInt("CUSTOMERID"), rs.getString("NAME"), rs.getInt("MEMBERSHIP"), rs.getDate("JOINED_DATE"), rs.getInt("ISPAIDANNUAL"));
                    /*cust.setCustomerid(rs.getInt("CUSTOMERID"));
                    cust.setName(rs.getString("NAME"));
                    cust.setMembership(rs.getInt("MEMBERSHIP"));
                    cust.setJoinedDate(rs.getDate("JOINED_DATE"));
                    cust.setIspaidannual(rs.getInt("ISPAIDANNUAL"));*/
                    list.add(cust);
                }
                rs.close();
            }
        } catch (SQLException ex) {
            System.out.println("SQLException failed.");
        }
        customers = list;
        return customers;
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
                    String sql = "SELECT * FROM CUSTOMER WHERE CUSTOMERID="+customerid;
                    rs=st.executeQuery(sql);

                    while(rs.next()) {
                        CustomerFactory custFactory = new CustomerFactory();
                        Customer cust = custFactory.addCustomer(rs.getInt("CUSTOMERID"), rs.getString("NAME"), rs.getInt("MEMBERSHIP"), rs.getDate("JOINED_DATE"), rs.getInt("ISPAIDANNUAL"));
                    /*    Customer cust = new Customer();
                        cust.setCustomerid(rs.getInt("CUSTOMERID"));
                        cust.setName(rs.getString("NAME"));
                        cust.setMembership(rs.getInt("MEMBERSHIP"));
                        cust.setJoinedDate(rs.getDate("JOINED_DATE"));
                        cust.setIspaidannual(rs.getInt("ISPAIDANNUAL"));*/
                        list.add(cust);
                    }
                    rs.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQLException failed.");
            }
            customers = list;
            return customers;
        }
    }
    
    @Override
    public ArrayList read(Connection conn, String query) {
        ArrayList list = new ArrayList();
        if(query.isEmpty()) {
            return list;
        } else {
            try {
                try (Statement st = conn.createStatement()) {
                    ResultSet rs;
                    rs=st.executeQuery(query);

                    while(rs.next()) {
                        CustomerFactory custFactory = new CustomerFactory();
                        Customer cust = custFactory.addCustomer(rs.getInt("CUSTOMERID"), rs.getString("NAME"), rs.getInt("MEMBERSHIP"), rs.getDate("JOINED_DATE"), rs.getInt("ISPAIDANNUAL"));
                        /*Customer cust = new Customer();
                        cust.setCustomerid(rs.getInt("CUSTOMERID"));
                        cust.setName(rs.getString("NAME"));
                        cust.setMembership(rs.getInt("MEMBERSHIP"));
                        cust.setJoinedDate(rs.getDate("JOINED_DATE"));
                        cust.setIspaidannual(rs.getInt("ISPAIDANNUAL"));*/
                        list.add(cust);
                    }
                    rs.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQLException failed.");
            }
            customers = list;
            return customers;
        }
    }
    
    @Override
    public boolean update(Connection conn, ArrayList updateValues) {
        if(updateValues.isEmpty() || (int) updateValues.get(0) == 0 || ((String) updateValues.get(1)).isEmpty()) {
            return false;
        } else {
            System.out.println("Update the customer to the database...");
            try {
                int updateResult;
                try (PreparedStatement pst = conn.prepareStatement("UPDATE CUSTOMER SET NAME=?, MEMBERSHIP=? WHERE CUSTOMERID=?")) {
                    pst.setString(1, (String) updateValues.get(1));
                    pst.setInt(2, (int) updateValues.get(2));
                    pst.setInt(3, (int) updateValues.get(0));
                    updateResult = pst.executeUpdate();
                }
                conn.close();
                return updateResult != 0;
            } catch (SQLException ex) {
                System.out.println(ex);
                return false;
            }
        }
    }
    
    @Override
    public boolean delete(Connection conn, int customerid) {
        if(customerid == 0) {
            return false;
        } else {
            System.out.println("Delete the customer to the database...");
            try {
                PreparedStatement pst = conn.prepareStatement("DELETE FROM CUSTOMER WHERE CUSTOMERID=?");
                pst.setInt(1, customerid);
                int updateResult = pst.executeUpdate();
                pst.close();
                pst = conn.prepareStatement("DELETE FROM BOOKING WHERE CUSTOMERID=?");
                pst.setInt(1, customerid);
                updateResult += pst.executeUpdate();
                pst.close();
                conn.close();
                return (2 <= updateResult);
            } catch (SQLException ex) {
                System.out.println(ex);
                return false;
            }
        }
    }

}
