package util;

import java.sql.*;

public class DB {
    
   

    Connection con = null;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gymmgtsystem", "root", "1234");
            System.out.println("connection done");
            return con;
        } catch (Exception e) {
            System.out.println("connection error");
            return null;
        }

    }
    

    
}
