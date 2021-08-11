/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsfacility;

import java.util.*;
/**
 *
 * @author Haseong Kim
 */
abstract class Customer {
    private Integer customerid;
    private String name;
    private int membership;
    private Date joinedDate;
    private int ispaidannual;
    abstract String displayMembership();

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMembership() {
        return membership;
    }

    public void setMembership(int membership) {
        this.membership = membership;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getIspaidannual() {
        switch (ispaidannual) {
            case 0:
                return "false";
            case 1:
                return "true";
            default:
                return "false";
        }
    }

    public void setIspaidannual(int ispaidannual) {
        this.ispaidannual = ispaidannual;
    }
}
