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
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;
import model.tableclasses.*;

/**
 *
 * @author me-aydin
 */
public class Jdbc {

    Connection connection = null;
//    Statement statement = null;
//    ResultSet rs = null;

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

    //START_RETRIEVE
    /*
    * Sets the ReturnSet to the rows in the DB that meet criteria
    * specified in the param.
     */
    private ResultSet select(String tableName) {
        String query = "SELECT * FROM " + tableName;

        return this.executeSelect(query);
    }

    /*
    * Sets the ReturnSet to the rows in the DB that meet criteria
    * specified in the param.
     */
    private ResultSet select(String tableName, String colName, String value,
            boolean usePar) {
        String query;

        if (usePar == true) {
            // Parenthesis will surrond the value making it a string
            query = "SELECT * FROM " + tableName + " WHERE " + colName + " = \'" + value + "\'";
        } else {
            // Parenthesis will not surround the value
            query = "SELECT * FROM " + tableName + " WHERE " + colName + " = " + value;
        }

        return this.executeSelect(query);
    }

    /*
    * Executes the select query.
     */
    private ResultSet executeSelect(String query) {
        Statement statement;
        ResultSet rs;
        
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return rs;
    }

    /*
    * Returns a list of hashmaps of rows from the DB that meet criteria 
    * specified in the params.
     */
    public ArrayList<HashMap<String, String>> retrieve(String tableName) {
        ResultSet rs;
        
        //GET Qualifiing Rows from DB
        rs = select(tableName);

        return this.processRetrieve(rs);
    }

    /*
    * Returns a list of hashmaps of rows from the DB that meet criteria 
    * specified in the params.
     */
    public ArrayList<HashMap<String, String>> retrieve(String tableName, long id) {
        ResultSet rs;
        
        //GET Qualifiing Rows from DB
        rs = select(tableName, "Id", String.valueOf(id), false);

        return this.processRetrieve(rs);
    }

    /*
    * Returns a list of hashmaps of rows from the DB that meet criteria 
    * specified in the params. The field value must be a long.
     */
    public ArrayList<HashMap<String, String>> retrieve(String tableName, String colName, long value) {
        ResultSet rs;

        //GET Qualifiing Rows from DB
        rs = select(tableName, colName, String.valueOf(value), false);

        return this.processRetrieve(rs);
    }

    /*
    * Returns a list of hashmaps of rows from the DB that meet criteria 
    * specified in the params. The field value must be a string.
     */
    public ArrayList<HashMap<String, String>> retrieve(String tableName, String colName, String value) {
        ResultSet rs;
        
        //GET Qualifiing Rows from DB
        rs = select(tableName, colName, value, true);

        return this.processRetrieve(rs);
    }

    /*
    * Processes the response of a retrieve request. Results are returned as
    * a list of hashmaps.
     */
    private ArrayList<HashMap<String, String>> processRetrieve(ResultSet rs) {
        //Transform and Return Rows to a List of HashMaps
        try {
            if (rs == null) {
                System.out.println("rs is null");
            } else {
                return rsToMaps(rs);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }
    //END_RETRIEVE

    //START_INSERT
    /*
    * Inserts given user object to the database table users
    * Return the generated ID of new row if successful else returns 0
     */
    public long insert(User user) {
        PreparedStatement ps;
        int nAffectedRows;
        ResultSet rs;

        try {
            ps = connection.prepareStatement(
                    "INSERT INTO Users (Username, Password, UserTypeId, CustomerId, DriverId, UserStatusId) VALUES (?,?,?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);

            //Write user values to statement
            if (user.getUsername() != null) {
                ps.setString(1, user.getUsername().trim());
            } else {
                throw new RuntimeException("Username in User cannot be null");
            }
            if (user.getPassword() != null) {
                ps.setString(2, user.getPassword().trim());
            } else {
                throw new RuntimeException("Password in User cannot be null");
            }
            if (user.getUserType() != null) {
                ps.setLong(3, user.getUserType().getId());
            } else {
                throw new RuntimeException("UserType in User cannot be null");
            }
            if (user.getCustomer() != null) {
                ps.setLong(4, user.getCustomer().getId());
            } else {
                ps.setNull(4, Types.INTEGER);
            }
            if (user.getDriver() != null) {
                ps.setLong(5, user.getDriver().getId());
            } else {
                ps.setNull(5, Types.INTEGER);
            }
            if (user.getUserStatus() != null) {
                ps.setLong(6, user.getUserStatus().getId());
            } else {
                throw new RuntimeException("UserStatus in User cannot be null");
            }

            //Execute and check for change
            nAffectedRows = ps.executeUpdate();
            if (nAffectedRows == 0) {
                throw new SQLException("Inserting user failed, no rows affected.");
            }

            //GET the generated ID
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                long id = rs.getLong(1);
                ps.close();
                return id;
            } else {
                throw new SQLException("Inserting user failed, no ID obtained");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /*
    * Inserts given customer object to the database table customers
    * Return the generated ID of new row if successful else returns 0
     */
    public long insert(Customer customer) {
        PreparedStatement ps;
        int nAffectedRows;
        ResultSet rs;

        try {
            ps = connection.prepareStatement(
                    "INSERT INTO Customers (FirstName, LastName, Address) VALUES (?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);

            //Write customer values to statement
            if (customer.getFirstName() != null) {
                ps.setString(1, customer.getFirstName().trim());
            }
            if (customer.getLastName() != null) {
                ps.setString(2, customer.getLastName().trim());
            }
            if (customer.getAddress() != null) {
                ps.setString(3, customer.getAddress().trim());
            }

            //Execute and check for change
            nAffectedRows = ps.executeUpdate();
            if (nAffectedRows == 0) {
                throw new SQLException("Inserting customer failed, no rows affected.");
            }

            //GET the generated ID
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                long id = rs.getLong(1);
                ps.close();
                return id;
            } else {
                throw new SQLException("Inserting customer failed, no ID obtained");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /*
    * Inserts given driver object to the database table drivers
    * Return the generated ID of new row if successful else returns 0
     */
    public long insert(Driver driver) {
        PreparedStatement ps;
        int nAffectedRows;
        ResultSet rs;

        try {
            ps = connection.prepareStatement(
                    "INSERT INTO Drivers (FirstName, LastName, Registration) VALUES (?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);

            //Write driver values to statement
            if (driver.getFirstName() != null) {
                ps.setString(1, driver.getFirstName().trim());
            }
            if (driver.getLastName() != null) {
                ps.setString(2, driver.getLastName().trim());
            }
            if (driver.getRegistration() != null) {
                ps.setString(3, driver.getRegistration().trim());
            } else {
                throw new RuntimeException("Registration in Driver"
                        + " cannot be null");
            }

            //Execute and check for change
            nAffectedRows = ps.executeUpdate();
            if (nAffectedRows == 0) {
                throw new SQLException("Inserting driver failed, no rows affected.");
            }

            //GET the generated ID
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                long id = rs.getLong(1);
                ps.close();
                return id;
            } else {
                throw new SQLException("Inserting driver failed, no ID obtained");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /*
    * Inserts given booking object to the database table bookings
    * Return the generated ID of new row if successful else returns 0
     */
    public long insert(Booking booking) {
        PreparedStatement ps;
        int nAffectedRows;
        ResultSet rs;

        try {
            ps = connection.prepareStatement(
                    "INSERT INTO Bookings (CustomerId"
                    + ", SourceAddress, DestinationAddress"
                    + ", NumOfPassengers, Distance, FareExcVAT, FareIncVAT, TimeBooked"
                    + ", DepartureTime,  BookingStatusId)"
                    + " VALUES (?,?,?,?,?,?,?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);

            //Write booking values to statement
            //CustomerID
            if (booking.getCustomer() != null) {
                ps.setLong(1, booking.getCustomer().getId());
            } else {
                throw new RuntimeException("Customer in Booking cannot be null");
            }
            //DriverID
//            if (booking.getDriver() != null) {
//                ps.setLong(2, booking.getDriver().getId());
//            }
            //SourceAddress
            if (booking.getSourceAddress() != null) {
                ps.setString(2, booking.getSourceAddress().trim());
            } else {
                throw new RuntimeException("SourceAddress in Booking"
                        + " cannot be null");
            }
            //DestinationAddress
            if (booking.getDestinationAddress() != null) {
                ps.setString(3, booking.getDestinationAddress().trim());
            } else {
                throw new RuntimeException("DestinationAddress in Booking"
                        + " cannot be null");
            }
            //NumOfPassengers
            if (booking.getNumOfPassengers() != 0) {
                ps.setInt(4, booking.getNumOfPassengers());
            } else {
                throw new RuntimeException("NumOfPassengers in Booking"
                        + " cannot be 0");
            }
            //Distance
            if (booking.getDistance() != 0.0) {
                ps.setDouble(5, booking.getDistance());
            } else {
                throw new RuntimeException("Distance in Booking"
                        + " cannot be 0.0");
            }
            //FareExcVAT
            if (booking.getFareExcVAT() != 0.0) {
                ps.setDouble(6, booking.getFareExcVAT());
            } else {
                throw new RuntimeException("FareExcVAT in Booking"
                        + " cannot be 0.0");
            }
            //FareIncVAT
            if (booking.getFareIncVAT() != 0.0) {
                ps.setDouble(7, booking.getFareIncVAT());
            } else {
                throw new RuntimeException("FareIncVAT in Booking"
                        + " cannot be 0.0");
            }
            //TimeBooked
            if (booking.getTimeBooked() != null) {
                ps.setTimestamp(8, booking.getTimeBooked());
            } else {
                throw new RuntimeException("TimeBooked in Booking"
                        + " cannot be null");
            }
            //DepartureTime
            if (booking.getDepartureTime() != null) {
                ps.setTimestamp(9, booking.getDepartureTime());
            }
            //ArrivalTime
//            if (booking.getTimeArrived() != null) {
//                ps.setTimestamp(9, booking.getTimeArrived());
//            }
            //BookingStatus
            if (booking.getBookingStatus() != null) {
                ps.setLong(10, booking.getBookingStatus().getId());
            } else {
                throw new RuntimeException("Booking Status in Booking"
                        + " cannot be null");
            }

            //Execute and check for change
            nAffectedRows = ps.executeUpdate();
            if (nAffectedRows == 0) {
                throw new SQLException("Inserting booking failed, no rows affected.");
            }

            //GET the generated ID
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                long id = rs.getLong(1);
                ps.close();
                return id;
            } else {
                throw new SQLException("Inserting booking failed, no ID obtained");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    //END_INSERT

    //START_UPDATE
    /*
    * Updates given user on the database table users
    * Return the generated ID of new row if successful else returns 0
     */
    public long update(User user) {
        PreparedStatement ps;
        int nAffectedRows;

        try {
            ps = connection.prepareStatement("UPDATE Users SET "
                    + "username = ?,"
                    + "password = ?,"
                    + "usertypeid = ?,"
                    + "userstatusid = ? "
                    + "WHERE id=?", PreparedStatement.RETURN_GENERATED_KEYS);

            //Write user values to statement
            if (user.getUsername() != null) {
                ps.setString(1, user.getUsername().trim());
            } else {
                throw new RuntimeException("Username in User cannot be null");
            }
            if (user.getPassword() != null) {
                ps.setString(2, user.getPassword().trim());
            } else {
                throw new RuntimeException("Password in User cannot be null");
            }
            if (user.getUserType() != null) {
                ps.setLong(3, user.getUserType().getId());
            } else {
                throw new RuntimeException("UserType in User cannot be null");
            }
            if (user.getCustomer() != null) {
                ps.setLong(4, user.getCustomer().getId());
            }
            else{
                ps.setNull(4, Types.INTEGER);
            }
            if (user.getDriver() != null) {
                ps.setLong(5, user.getDriver().getId());
            }
            else{
                ps.setNull(5, Types.INTEGER);
            }
            if (user.getUserStatus() != null) {
                ps.setLong(4, user.getUserStatus().getId());
            } else {
                throw new RuntimeException("UserStatus in User cannot be null");
            }
            if (user.getId() != 0) {
                ps.setLong(5, user.getId());
            } else {
                throw new RuntimeException("Id in User cannot be 0");
            }

            //Execute and check for change
            nAffectedRows = ps.executeUpdate();
            if (nAffectedRows == 0) {
                throw new SQLException("Updating user failed, no rows affected.");
            }

            ps.close();
            return user.getId();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /*
    * Updates given customer on the database table customers
    * Return the generated ID of new row if successful else returns 0
     */
    public long update(Customer customer) {
        PreparedStatement ps;
        int nAffectedRows;

        try {
            ps = connection.prepareStatement("UPDATE Customers SET "
                    + "firstname = ?,"
                    + "lastname = ?,"
                    + "address = ? "
                    + "WHERE id=?", PreparedStatement.RETURN_GENERATED_KEYS);

            //Write customer values to statement
            if (customer.getFirstName() != null) {
                ps.setString(1, customer.getFirstName().trim());
            }
            if (customer.getLastName() != null) {
                ps.setString(2, customer.getLastName().trim());
            }
            if (customer.getAddress() != null) {
                ps.setString(3, customer.getAddress().trim());
            }
            if (customer.getId() != 0) {
                ps.setLong(4, customer.getId());
            } else {
                throw new RuntimeException("Id in Customer"
                        + " cannot be 0");
            }

            //Execute and check for change
            nAffectedRows = ps.executeUpdate();
            if (nAffectedRows == 0) {
                throw new SQLException("Updating customer failed, no rows affected.");
            }

            ps.close();
            return customer.getId();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /*
    * Updates given driver on the database table drivers
    * Return the generated ID of new row if successful else returns 0
     */
    public long update(Driver driver) {
        PreparedStatement ps;
        int nAffectedRows;

        try {
            ps = connection.prepareStatement("UPDATE Drivers SET "
                    + "firstname = ?,"
                    + "lastname = ?,"
                    + "registration = ? "
                    + "WHERE id=?", PreparedStatement.RETURN_GENERATED_KEYS);

            //Write driver values to statement
            if (driver.getFirstName() != null) {
                ps.setString(1, driver.getFirstName().trim());
            }
            if (driver.getLastName() != null) {
                ps.setString(2, driver.getLastName().trim());
            }
            if (driver.getRegistration() != null) {
                ps.setString(3, driver.getRegistration().trim());
            } else {
                throw new RuntimeException("Registration in Driver"
                        + " cannot be null");
            }
            if (driver.getId() != 0) {
                ps.setLong(4, driver.getId());
            } else {
                throw new RuntimeException("Id in Driver"
                        + " cannot be 0");
            }

            //Execute and check for change
            nAffectedRows = ps.executeUpdate();
            if (nAffectedRows == 0) {
                throw new SQLException("Updating driver failed, no rows affected.");
            }

            ps.close();
            return driver.getId();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /*
    * Updates given booking on the database table bookings
    * Return the generated ID of new row if successful else returns 0
     */
    public long update(Booking booking) {
        PreparedStatement ps;
        int nAffectedRows;

        try {
            ps = connection.prepareStatement("UPDATE Bookings SET "
                    + "customerid = ?,"
                    + "driverid = ?,"
                    + "sourceaddress = ?,"
                    + "destinationaddress = ?,"
                    + "distance = ?,"
                    + "fareexcvat = ?,"
                    + "fareincvat = ?,"
                    + "timebooked = ?,"
                    + "departuretime = ?,"
                    + "arrivaltime = ?,"
                    + "bookingstatusid = ? "
                    + "WHERE id=?", PreparedStatement.RETURN_GENERATED_KEYS);

            //Write booking values to statement
            if (booking.getCustomer() != null) {
                ps.setLong(1, booking.getCustomer().getId());
            } else {
                throw new RuntimeException("Customer in Booking cannot be null");
            }
            if (booking.getDriver() != null) {
                ps.setLong(2, booking.getDriver().getId());
            }
            if (booking.getSourceAddress() != null) {
                ps.setString(3, booking.getSourceAddress().trim());
            } else {
                throw new RuntimeException("SourceAddress in Booking"
                        + " cannot be null");
            }
            if (booking.getDestinationAddress() != null) {
                ps.setString(4, booking.getDestinationAddress().trim());
            } else {
                throw new RuntimeException("DestinationAddress in Booking"
                        + " cannot be null");
            }
            if (booking.getDistance() != 0.0) {
                ps.setDouble(5, booking.getDistance());
            } else {
                throw new RuntimeException("Distance in Booking"
                        + " cannot be null");
            }
            if (booking.getFareExcVAT() != 0.0) {
                ps.setDouble(6, booking.getFareExcVAT());
            } else {
                throw new RuntimeException("FareExcVAT in Booking"
                        + " cannot be null");
            }
            if (booking.getFareIncVAT() != 0.0) {
                ps.setDouble(7, booking.getFareIncVAT());
            } else {
                throw new RuntimeException("FareIncVAT in Booking"
                        + " cannot be null");
            }
            if (booking.getTimeBooked() != null) {
                ps.setTimestamp(8, booking.getTimeBooked());
            } else {
                throw new RuntimeException("TimeBooked in Booking"
                        + " cannot be null");
            }
            if (booking.getDepartureTime() != null) {
                ps.setTimestamp(9, booking.getDepartureTime());
            } else {
                throw new RuntimeException("DepartureTime in Booking"
                        + " cannot be null");
            }
            if (booking.getTimeArrived() != null) {
                ps.setTimestamp(10, booking.getTimeArrived());
            }else{
                ps.setNull(10, java.sql.Types.TIMESTAMP);
            }
            if (booking.getBookingStatus() != null) {
                ps.setLong(11, booking.getBookingStatus().getId());
            } else {
                throw new RuntimeException("BookingStatus in Booking"
                        + " cannot be null");
            }
            if (booking.getId() != 0) {
                ps.setLong(12, booking.getId());
            } else {
                throw new RuntimeException("Id in Booking"
                        + " cannot be 0");
            }

            //Execute and check for change
            nAffectedRows = ps.executeUpdate();
            if (nAffectedRows == 0) {
                throw new SQLException("Updating booking failed, no rows affected.");
            }

            ps.close();
            return booking.getId();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /*
    * Updates given configuration on the database table customers
    * Return the generated ID of new row if successful else returns 0
     */
    public long update(Configuration configuration) {
        PreparedStatement ps;
        int nAffectedRows;

        try {
            ps = connection.prepareStatement("UPDATE Configurations SET "
                    + "configname = ?,"
                    + "configvalue = ?"
                    + "WHERE id=?", PreparedStatement.RETURN_GENERATED_KEYS);

            //Write configuration values to statement
            if (configuration.getConfigName() != null) {
                ps.setString(1, configuration.getConfigName().trim());
            } else {
                throw new RuntimeException("ConfigName in Configuration"
                        + " cannot be null");
            }
            if (configuration.getConfigValue() != null) {
                ps.setString(2, configuration.getConfigValue().trim());
            } else {
                throw new RuntimeException("ConfigValue in Configuration"
                        + " cannot be null");
            }
            if (configuration.getId() != 0) {
                ps.setLong(3, configuration.getId());
            } else {
                throw new RuntimeException("Id in Configuration"
                        + " cannot be 0");
            }

            //Execute and check for change
            nAffectedRows = ps.executeUpdate();
            if (nAffectedRows == 0) {
                throw new SQLException("Updating Configuration failed, no rows affected.");
            }

            ps.close();
            return configuration.getId();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    //END_UPDATE

    //START_DELETE
    /*
    * Deletes the record found at the given tablename and id
     */
    public void delete(String tablename, int id) {
        Statement statement;
        String query = "DELETE FROM " + tablename
                + " WHERE id = " + String.valueOf(id);

        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //END_DELETE

    public void closeAll() {
        try {
           // rs.close();
           // statement.close();
            connection.close();                                         
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //START_MAPPING
    private ArrayList rsToList(ResultSet rs) throws SQLException {
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

    private ArrayList<HashMap<String, String>> rsToMaps(ResultSet rs) throws SQLException {
        ArrayList<HashMap<String, String>> ret = new ArrayList<>();
        HashMap<String, String> rowMap;

        int nCols = rs.getMetaData().getColumnCount();
        String[] colNames = new String[nCols];
        for (int c = 0; c < nCols; c++) {
            colNames[c] = rs.getMetaData().getColumnName(c + 1);
        }

        while (rs.next()) {
            rowMap = new HashMap<>();
            for (int c = 0; c < nCols; c++) {
                rowMap.put(colNames[c], rs.getString(c + 1));
            }
            ret.add(rowMap);
        }
        return ret;
    }
    //END_MAPPING
}
