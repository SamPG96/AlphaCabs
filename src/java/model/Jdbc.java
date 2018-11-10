/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author me-aydin
 */
public class Jdbc {

    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;
    //String query = null;
    public Jdbc(String query) {
        //this.query = query;
    }

    public Jdbc() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void connect(Connection con) {
        connection = con;
    }

    private ArrayList rsToList() throws SQLException {
        ArrayList aList = new ArrayList();

        int cols = rs.getMetaData().getColumnCount();
        while (rs.next()) {
            String[] s = new String[cols];
            for (int i = 1; i <= cols; i++) {
                s[i - 1] = rs.getString(i);
            }
            aList.add(s);
        }
        return aList;
    }

    private ArrayList<HashMap<String, String>> rsToMaps() throws SQLException {
        ArrayList<HashMap<String, String>> ret = new ArrayList<>();
        HashMap<String, String> rowMap;
        
        int nCols = rs.getMetaData().getColumnCount();
        String[] colNames = new String[nCols];
        for (int c = 0; c < nCols; c++) {
            colNames[c] = rs.getMetaData().getColumnName(c+1);
        }

        while (rs.next()) {
            rowMap = new HashMap<>();
            for (int c = 0; c < nCols; c++) {
                rowMap.put(colNames[c], rs.getString(c+1));
            }
            ret.add(rowMap);
        }
        return ret;
    }

    /*
    * Sets the ReturnSet to the rows in the DB that meet criteria
    * specified in the param.
    */
    private void select(String tableName) {
        String query = "SELECT * FROM " + tableName;

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    /*
    * Sets the ReturnSet to the rows in the DB that meet criteria
    * specified in the param.
    */
    private void select(String tableName, int id) {
        String query = "SELECT * FROM " + tableName + " WHERE ID = " + id + "";

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    /*
    * Sets the ReturnSet to the rows in the DB that meet criteria
    * specified in the param.
    */
    private void select(String tableName, String colName, String value) {
        String query = "SELECT * FROM " + tableName + " WHERE " + colName + " = \'" + value + "\'";

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /*
    * Returns a list of hashmaps of rows from the DB that meet criteria 
    * specified in the params.
    */
    public ArrayList<HashMap<String, String>> retrieve(String tableName) {
        
        //GET Qualifiing Rows from DB
        select(tableName);

        //Transform and Return Rows to a List of HashMaps
        try {
            if (rs == null) {
                System.out.println("rs is null");
            } else {
                return rsToMaps();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /*
    * Returns a list of hashmaps of rows from the DB that meet criteria 
    * specified in the params.
    */
    public ArrayList<HashMap<String, String>> retrieve(String tableName, int id) {
        
        //GET Qualifiing Rows from DB
        select(tableName, id);

        //Transform and Return Rows to a List of HashMaps
        try {
            if (rs == null) {
                System.out.println("rs is null");
            } else {
                return rsToMaps();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /*
    * Returns a list of hashmaps of rows from the DB that meet criteria 
    * specified in the params.
    */
    public ArrayList<HashMap<String, String>> retrieve(String tableName, String colName, String value) {
        
        //GET Qualifiing Rows from DB
        select(tableName, colName, value);

        //Transform and Return Rows to a List of HashMaps
        try {
            if (rs == null) {
                System.out.println("rs is null");
            } else {
                return rsToMaps();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void insert(String[] str) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT INTO Users VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, str[0].trim());
            ps.setString(2, str[1]);
            ps.executeUpdate();

            ps.close();
            System.out.println("1 row added.");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void update(String[] str) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("Update Users Set password=? where username=?", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, str[1].trim());
            ps.setString(2, str[0].trim());
            ps.executeUpdate();

            ps.close();
            System.out.println("1 rows updated.");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(String user) {

        String query = "DELETE FROM Users "
                + "WHERE username = '" + user.trim() + "'";

        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("way way" + e);
            //results = e.toString();
        }
    }

    public void closeAll() {
        try {
            rs.close();
            statement.close();
            //connection.close();                                         
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
