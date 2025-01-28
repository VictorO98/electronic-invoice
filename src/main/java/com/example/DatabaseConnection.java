package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@192.168.1.7:1521:OPENFLEX"; // Reemplaza "ORCL" con tu SID o servicio
        String username = "ebilling";
        String password = "exp3rt";

        return DriverManager.getConnection(url, username, password);
    }

    public static void callProcedure(int periodo, int idFactura) {
        Connection connection = null;
        CallableStatement stmt = null;

        try {
            connection = getConnection();
            String sql = "{call EB_PROCARGA(?, ?)}";
            stmt = connection.prepareCall(sql);
            stmt.setInt(1, periodo);
            stmt.setInt(2, idFactura);

            stmt.execute();
            System.out.println("Procedimiento ejecutado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
