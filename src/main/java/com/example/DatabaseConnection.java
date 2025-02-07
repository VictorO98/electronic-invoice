package com.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;

public class DatabaseConnection {
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
            Locale.setDefault(new Locale("es", "ES"));
            System.setProperty("user.language", "es");
            System.setProperty("user.region", "ES");
            System.setProperty("oracle.jdbc.defaultNChar", "true");

            Properties env = loadEnv();

            String url = env.getProperty("DB_URL");

            Properties props = new Properties();
            props.setProperty("user", env.getProperty("DB_USERNAME"));
            props.setProperty("password", env.getProperty("DB_PASSWORD"));
            props.setProperty("oracle.jdbc.defaultNChar","true");
            props.setProperty("NLS_LANG", "LATIN AMERICAN SPANISH_AMERICA.US7ASCII");

            connection = DriverManager.getConnection(url, props);
            System.out.println("Connection established");
        } catch (Exception e) {
            System.err.println("Error to connect database: " + e.getMessage());
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
            System.err.println("Successfully executed procedure: " + procedureSql + " with params: " + Arrays.toString(params));
        } catch (Exception e) {
            System.err.println("Error to execute procedure " + procedureSql + ": " + e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

}
