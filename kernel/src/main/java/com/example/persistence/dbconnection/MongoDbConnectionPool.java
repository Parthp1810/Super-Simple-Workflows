package com.example.persistence.dbconnection;

import com.example.configuration.Configuration;
import com.example.configuration.MongoDbConfig;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoDbConnectionPool extends ObjectPool<MongoClient>{
    @Override
    protected MongoClient create() {
        MongoClientURI mongoUri = new MongoClientURI(Configuration.mongoDbConfig.getConnectionUrl());
        MongoClient mongoClient = new MongoClient(mongoUri);
        return mongoClient;
    }

    @Override
    public boolean validate(MongoClient o) {
        return ((MongoClient) o).isLocked();
    }

    @Override
    public void expire(MongoClient o) {
        ((MongoClient) o).close();
    }
}
