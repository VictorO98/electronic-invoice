package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.SQLException;

public class DatabaseConnection {
    public Connection connection;

    public DatabaseConnection() {}

    public void getConnection() throws SQLException, ClassNotFoundException   {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@192.168.1.7:1521:OPENFLEX";
        String username = "ebilling";
        String password = "exp3rt";

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established");
        } catch (Exception e) {
            System.out.println("Exception inside connect(): " + e);
            e.printStackTrace();
        }
    }

    public void executeProcedure(String procedureSql, Object... params) throws SQLException {
        CallableStatement stmt = null;

        try {
            stmt = connection.prepareCall(procedureSql);

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            stmt.execute();
            System.out.println("Successfully executed procedure: " + procedureSql);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

}
