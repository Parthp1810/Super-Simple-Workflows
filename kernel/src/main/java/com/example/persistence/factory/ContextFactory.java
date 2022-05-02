package com.example.persistence.factory;

import com.example.activity.Context;
import com.example.persistence.MongoDbContext;
import com.example.persistence.MySqlContext;

import java.util.Locale;

public class ContextFactory {
    final String MONGO_DB = "MONGODB";
    final String MYSQL = "MYSQL";
    public Context createContext(String storageMedium, String workflowId){
        switch(storageMedium.toUpperCase(Locale.ROOT)){
            case MONGO_DB:
                return new MongoDbContext(workflowId);
            case MYSQL:
                return new MySqlContext(workflowId);
            default:
                return null;
        }
    }
}
