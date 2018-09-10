package com.project.rent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class SQLConnector {
    public static Connection connect(String user, String pass) throws Exception {
        try {
            String url = "jdbc:postgresql://localhost/rent";
            Properties props = new Properties();
            props.setProperty("user",user);
            props.setProperty("password",pass);
            //props.setProperty("ssl","true");
            Connection conn = DriverManager.getConnection(url, props);
            return conn;

        } catch (Exception ex) {
            throw ex;
        }
    }
}
