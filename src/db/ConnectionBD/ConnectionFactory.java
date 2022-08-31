package db.ConnectionBD;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectionFactory {

    public Connection newConnectionBD() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/quiz_Game?useTimezone=true&serverTimezone=UTC", "johnny","admin");
    }
}
