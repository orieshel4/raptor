package run;

import controller.MongoWriter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static Map<String, Integer> playerScores = new HashMap<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345); // Create a server socket on port 12345
            System.out.println("Server started. Waiting for clients...");

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Wait for a client to connect
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostName());

                // Create input/output streams for client communication
                ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());

                String playerName = (String) inputStream.readObject();
                int playerScore = (int) inputStream.readObject();
                System.out.println("Received from client: " + playerName + " - Score: " + playerScore);

                playerScores.put(playerName, playerScore);

                outputStream.writeObject(playerScores);

                MongoWriter mongoWriter = new MongoWriter();
                mongoWriter.writeData(playerName, playerScore);

                clientSocket.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

