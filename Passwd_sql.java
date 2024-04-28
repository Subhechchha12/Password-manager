import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Passwd_sql {
    public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:mysql://localhost:3306/psswd_stor";
        String user = "localhost";
        String password = "abc123";
        // Try-with-resources to ensure closing the connection
        try (Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM pass_info")) {
            // Process the result set
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                // ... process data ...
                System.out.println("ID: " + id + ", Name: " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}