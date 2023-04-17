package mocks;

import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;

public class RealDatabase implements Database {

    MongoClient mongo = MongoClients.create("mongodb://localhost:27017");
    MongoDatabase db = this.mongo.getDatabase("gameData");
    MongoCollection<Document> scoresCollection = db.getCollection("scores");

    public void recordScore(String player, int score) {
        if(this.getPlayerScore(player) > 0){
            Document document = new Document();
            document.put("name", player);
            document.put("score", score);
            Document query = new Document();
            query.put("name", player);
            this.scoresCollection.findOneAndReplace(query, document);
        }else{
            Document document = new Document();
            document.put("name", player);
            document.put("score", score);
            this.scoresCollection.insertOne(document);
        }
    }

    public int getPlayerScore(String player) {
        Document query = new Document();
        query.put("name", player);
        FindIterable<Document> cursor = this.scoresCollection.find(query);
        try (MongoCursor<Document> iterator = cursor.cursor()) {
            if (iterator.hasNext()) {
                Document d = iterator.next();
                return (int) d.get("score");
            }
        }
        return 0;
    }

    public ArrayList<String> getPlayerList() {
        ArrayList<String> players = new ArrayList<>();
        FindIterable<Document> cursor = this.scoresCollection.find();
        try (MongoCursor<Document> iterator = cursor.cursor()) {
            while (iterator.hasNext()) {
                Document d = iterator.next();
                players.add( (String) d.get("name"));
            }
        }
        return players;
    }

}
