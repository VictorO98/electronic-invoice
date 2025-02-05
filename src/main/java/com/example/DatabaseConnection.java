package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;

public class DatabaseConnection {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnection.class);
    public Connection connection;

    public DatabaseConnection() {}

    private Properties loadEnv() throws IOException {
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new FileNotFoundException("Archivo db.properties no encontrado en src/main/resources");
            }
            props.load(input);
        }
        return props;
    }

    public void getConnection() throws ClassNotFoundException, SQLException, IOException {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        try {
            Properties env = loadEnv();
            String url = env.getProperty("DB_URL");
            String username = env.getProperty("DB_USERNAME");
            String password = env.getProperty("DB_PASSWORD");

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
