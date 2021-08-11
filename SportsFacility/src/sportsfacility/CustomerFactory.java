/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsfacility;

import java.util.*;

/**
 *
 * @author kimh22
 */
public class CustomerFactory {
    public Customer addCustomer(int customerid, String name, int membership, Date joined_date, int ispaidannual) {
        if(name == null) {
            return null;
        }
        Customer cust;
        String temp_membership = Integer.toString(membership);
        if(temp_membership.equalsIgnoreCase("0")) {
            cust = new SilverCustomer();
            cust.setCustomerid(customerid);
            cust.setName(name);
            cust.setMembership(membership);
            cust.setJoinedDate(joined_date);
            cust.setIspaidannual(ispaidannual);
            return cust;
        } else if(temp_membership.equalsIgnoreCase("1")) {
            cust = new GoldCustomer();
            cust.setCustomerid(customerid);
            cust.setName(name);
            cust.setMembership(membership);
            cust.setJoinedDate(joined_date);
            cust.setIspaidannual(ispaidannual);
            return cust;
        } else if(temp_membership.equalsIgnoreCase("2")) {
            cust = new PlatinumCustomer();
            cust.setCustomerid(customerid);
            cust.setName(name);
            cust.setMembership(membership);
            cust.setJoinedDate(joined_date);
            cust.setIspaidannual(ispaidannual);
            return cust;
        }
        return null;
    }
}
