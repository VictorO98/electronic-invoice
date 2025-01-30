package com.example;

import com.example.controllers.ChargeInvoicesIdsController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class DatabaseConnection {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnection.class);
    public Connection connection;


    public DatabaseConnection() {}

    public void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@192.168.1.7:1521:OPENFLEX";
        String username = "ebilling";
        String password = "exp3rt";

        try {
            connection = DriverManager.getConnection(url, username, password);
            logger.info("Connection established");
        } catch (Exception e) {
            logger.error("Exception inside connect(): {}", String.valueOf(e));
            throw e;
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
            logger.info("Successfully executed procedure: {}, with params: {}", procedureSql, Arrays.toString(params));
        } catch (Exception e) {
            logger.error("Exception inside executeProcedure({}): {}", procedureSql, String.valueOf(e));
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

}
