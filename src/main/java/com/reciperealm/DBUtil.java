package com.reciperealm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

final class DBUtil {
    private DBUtil() {}

    static Connection getConnection() throws SQLException {
        String user = System.getenv("RECIPEREALM_DB_USER");
        String password = System.getenv("RECIPEREALM_DB_PASSWORD");

        if (user == null || user.isBlank() || password == null) {
            throw new SQLException("RecipeRealm database environment variables are missing");
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC driver is missing", e);
        }

        String url = System.getenv().getOrDefault(
                "RECIPEREALM_DB_URL", "jdbc:mysql://localhost:3306/reciperealm");
        return DriverManager.getConnection(url, user, password);
    }
}
