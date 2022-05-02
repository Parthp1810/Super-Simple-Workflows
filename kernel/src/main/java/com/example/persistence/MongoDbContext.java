package com.example.persistence;

import com.example.activity.Context;
import com.example.persistence.dbconnection.MongoDbConnectionPool;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MongoDbContext implements Context {
    final MongoClient mongoClient;
    final String workflowId;
    public MongoDbContext(String workflowId) {
        MongoDbConnectionPool mongoDbConnectionPool = new MongoDbConnectionPool();
        mongoClient = mongoDbConnectionPool.checkOut();
        this.workflowId = workflowId;
    }

    @Override
    public void insert(String key, Object value) {
        MongoDatabase mongoDatabase = mongoClient.getDatabase("SSWF");
        MongoCollection<Document> contextCollection = mongoDatabase.getCollection("WorkflowContext");
        Document document = new Document();
        document.append("key", key);
        document.append("value", value);
        document.append("workflowId",workflowId);

        contextCollection.insertOne(document);
    }

    @Override
    public Object get(String key) {
        MongoDatabase mongoDatabase = mongoClient.getDatabase("SSWF");
        MongoCollection<Document> contextCollection = mongoDatabase.getCollection("WorkflowContext");
        Document result = contextCollection.find(new Document("key",key)).first();
        try {
            if(Objects.nonNull(result)){
                JSONObject jsonObject = new JSONObject(result.toJson());
                return jsonObject.getString("value").toString();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<Map.Entry<String, Object>> get() {
        Map<String, Object> contextResult = new HashMap<>();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("SSWF");
        MongoCollection<Document> contextCollection = mongoDatabase.getCollection("WorkflowContext");
        MongoCursor<Document> cur = contextCollection.find(new Document("workflowId",workflowId)).iterator();
        while(cur.hasNext()){
            Document doc = cur.next();
            try {
                JSONObject jsonObject = new JSONObject(doc.toJson());
                contextResult.put(jsonObject.getString("key"),jsonObject.getString("value"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return contextResult.entrySet();
    }
}
