package com.example.configuration;

public class MongoDbConfig {
    public static String connectionUrl;

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public MongoDbConfig(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public MongoDbConfig() {
    }
}
