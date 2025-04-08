package com.utc2.giangviendemo.repository.repositotyImplement;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnector {
    private static final Properties properties = new Properties();
    private static Connection conn = null;

    public static void connectDB() throws IOException, SQLException, ClassNotFoundException {
        if (conn == null || conn.isClosed()) {
            try (FileInputStream fis = new FileInputStream("src/main/resources/database.properties")) {
                properties.load(fis);

                String url = properties.getProperty("db.url");
                String username = properties.getProperty("db.username");
                String password = properties.getProperty("db.password");
                String driver = properties.getProperty("db.driver");

                Class.forName(driver);
                conn = DriverManager.getConnection(url, username, password);

                System.out.println("✅ Kết nối đến cơ sở dữ liệu thành công!");
            }
        }
    }

    public static Connection getConnection() throws SQLException, IOException, ClassNotFoundException {
        if (conn == null || conn.isClosed()) {
            connectDB();
        }
        return conn;
    }

    public static void disconnectDB() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                conn = null;
                System.out.println("✅ Đã ngắt kết nối cơ sở dữ liệu.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi ngắt kết nối: " + e.getMessage());
        }
    }
}