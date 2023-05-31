package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class OracleWriter {

    String url = "jdbc:oracle:thin:@localhost:1521:ORCL";
    String username = "nika";
    String password = "123456";

    private void connectDB(){
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            // Perform database operations
            connection.close(); // Close the connection when done
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void writeHighcoreToDB(long score){
        String sql = "INSERT INTO Scores (HighScore, column2) VALUES (" + score + ")";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement(sql);

            // Set values for the INSERT statement
            statement.setLong(2, score);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
