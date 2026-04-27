package lion.bank.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * CREATE TABLE account (
 *     account_id BIGINT AUTO_INCREMENT PRIMARY KEY,
 *     account_number VARCHAR(30) NOT NULL UNIQUE,
 *     owner_name VARCHAR(50) NOT NULL,
 *     balance BIGINT NOT NULL DEFAULT 0,
 *     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
 * );
 */
public class DBUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/liondb?serverTimezone=Asia/Seoul";
    private static final String USER = "root";
    private static final String PASSWORD = "root1234";

    private DBUtil() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
