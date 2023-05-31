package run;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345); // Connect to the server on localhost:12345
            System.out.println("Connected to server.");

            // Create input/output streams for server communication
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            // Send player name and score to the server
            Random r = new Random();
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Enter playerName:");

            String playerName = myObj.nextLine();  // Read user input
            System.out.println("playerName is: " + playerName);

            int playerScore = r.nextInt(200-1) + 1;;
            outputStream.writeObject(playerName);
            outputStream.writeObject(playerScore);
            System.out.println("Sent to server: " + playerName + " - Score: " + playerScore);

            // Receive updated player scores from the server
            Map<String, Integer> playerScores = (Map<String, Integer>) inputStream.readObject();
            System.out.println("Received from server:");
            for (String name : playerScores.keySet()) {
                int score = playerScores.get(name);
                System.out.println(name + " - Score: " + score);
            }

            // Close the socket
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

