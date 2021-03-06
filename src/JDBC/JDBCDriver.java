package JDBC;

import java.sql.*;

public class JDBCDriver {
    private static JDBCDriver jdbcDriver = new JDBCDriver();
    public static Connection con;
    private static Statement stmt;

    public static JDBCDriver getInstance() {
        return jdbcDriver;
    }

    // connect to database

    private JDBCDriver() {
        String username = "ora_o2t0b";//set to your oracle username (ora_a1b7c)
        String password = "a23586150";//set to your oracle password (a11131353)
        String url = "jdbc:oracle:thin:@localhost:52695:ug";

        try {
            // Load the Oracle JDBC driver
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        }catch (SQLException ex) {
            System.out.println("\nMessage: " + ex.getMessage());
        }

        try {
            // Connect to database
            con = DriverManager.getConnection(url,username,password);
            stmt = con.createStatement();
            con.setAutoCommit(false);
            System.out.println("\nConnected to Oracle!");
        }catch(SQLException ex){
            System.out.println("\nMessage: " + ex.getMessage());
            System.out.println("\nFailed to connect to Oracle!");
        }

    }

    // This is to disconnect from the database
    public int disconnect(){
        int outcome=-1;
        try {
            con.close();
            outcome = 1;
        } catch (SQLException ex) {
            System.out.println("\nMessage: " + ex.getMessage());
            System.out.println("\nFailed to disconnect from Oracle!");
        }
        return outcome;
    }

    // This is to execute any data manipulation or definition (e.g. update, drop, insert...)
    public String executeDataUpdate(String sqlStatement){
        String outcome="success";
        try{
            stmt.executeUpdate(sqlStatement);
            con.commit();
        } catch (SQLException ex) {
            System.out.println("\nMessage: " + ex.getMessage());
            System.out.println("\nFailed to update!");
            outcome=ex.getMessage();
        }

        return outcome;
    }

    // This is to execute any query statements
    public ResultSet executeDataQuery(String sqlStatement){
        try {
            return  stmt.executeQuery(sqlStatement);
        } catch (SQLException ex) {
            System.out.println("\nMessage: " + ex.getMessage());
            System.out.println("\nFailed to execute query: " + sqlStatement);
            return null;
        }
    }


}
