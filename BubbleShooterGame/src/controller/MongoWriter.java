package controller;
import com.mongodb.client.*;
import org.bson.Document;


public class MongoWriter {
    MongoClient client = MongoClients.create("mongodb+srv://am:am@cluster0.95wul21.mongodb.net/?retryWrites=true&w=majority");
    MongoDatabase db = client.getDatabase("Bubble");
    MongoCollection col = db.getCollection("sampleBu");



    public void writeData(String playerName, int playerScore){
        Document sampleDoc = new Document("playerName", playerName).append("playerScore", playerScore);
        col.insertOne(sampleDoc);
        System.out.printf("The score was successfully added to DB.");
    }
}
