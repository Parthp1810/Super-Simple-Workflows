package com.example.configuration;

public class Configuration {
    public static MongoDbConfig mongoDbConfig;
    public static SqlConfig sqlConfig;

    public static SqlConfig getSqlConfig() {
        return sqlConfig;
    }

    public static MongoDbConfig getMongoDbConfig() {
        return mongoDbConfig;
    }


    public Configuration() {
        this.mongoDbConfig = new MongoDbConfig();
        this.sqlConfig = new SqlConfig();
    }

    public void setupConfiguration(){
        mongoDbConfig.setConnectionUrl(System.getenv("CLIENT_URL"));
        sqlConfig.setConnectionUrl(System.getenv("SQL_URL"));
        sqlConfig.setUserName(System.getenv("USERNAME"));
        sqlConfig.setPassword(System.getenv("PASSWORD"));
    }
    public void setupConfiguration(String mongoUrl, String sqlUrl, String userName, String pwd){
        mongoDbConfig.setConnectionUrl(mongoUrl);
        sqlConfig.setConnectionUrl(sqlUrl);
        sqlConfig.setUserName(userName);
        sqlConfig.setPassword(pwd);
    }
}
