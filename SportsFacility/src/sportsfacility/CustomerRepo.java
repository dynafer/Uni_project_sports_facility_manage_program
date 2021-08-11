/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsfacility;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author kimh22
 */
public interface CustomerRepo {
    
    public ArrayList read(Connection conn);
    public ArrayList read(Connection conn, int customerid);
    public ArrayList read(Connection conn, String query);
    public boolean update(Connection conn, ArrayList updateValues);
    public boolean delete(Connection conn, int customerid);
    
}
