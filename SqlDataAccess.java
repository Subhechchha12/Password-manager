import java.sql.*;

public class SqlDataAccess {
    //JDBC URL, username, and password for MySQL database
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/psswd_stor";
    static final String USERNAME = "localhost";
    static final String PASSWORD = "abc123";


    public static void main(String[] args) {

        try {
            // Connection to the MySQL database is established
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // Create a new record in the 'users' table
            insertUser(connection, "John Doe", "john.doe@example.com");

            // Infor retrieved
            retrieveUsers(connection);

            // Connection closed
            connection.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to insert a new user record into the 'pass_info' table
    private static void insertUser(Connection connection, String name, String email) throws SQLException {
        String sql = "INSERT INTO pass_info (name, email) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, email);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new user password record was inserted successfully!");
        }
    }

    // Method to retrieve all users from the 'users' table
    private static void retrieveUsers(Connection connection) throws SQLException {
        String sql = "SELECT * FROM pass_info";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            System.out.println("User ID: " + id + ", Name: " + name + ", Email: " + email);
        }
        resultSet.close();
    }
}


