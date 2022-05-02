package com.example.persistence;

import com.example.configuration.Configuration;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Map;
import java.util.Set;

public class MongoDbContextTest {
    @Before
    public void setUp() {
        Configuration configuration = new Configuration();
        configuration.setupConfiguration("mongodb+srv://mongoAdmin:Jols_1305@cluster0.hclh8.mongodb.net/myFirstDatabase?retryWrites\\\\\\=true&w\\\\\\=majority","jdbc:mysql://localhost:3306/SSWF","root","Mickey_1613");
    }
    @Test
    public void insertTest(){
        MongoClient mockConnection = Mockito.mock(MongoClient.class);
        MongoDatabase mockMongoDatabase = Mockito.mock(MongoDatabase.class);

        MongoDbContext mongoDb = new MongoDbContext("1101");
        Mockito.when(mockConnection.getDatabase("SSWF")).thenReturn(mockMongoDatabase);
        mongoDb.insert("test13","test13");
        Mockito.verifyNoInteractions(mockConnection);
    }
    @Test
    public void getTest(){
        MongoClient mockConnection = Mockito.mock(MongoClient.class);
        MongoDatabase mockMongoDatabase = Mockito.mock(MongoDatabase.class);
        MongoDbContext mongoDb = new MongoDbContext("1101");

        Mockito.when(mockConnection.getDatabase("SSWF")).thenReturn(mockMongoDatabase);
        System.out.println(mongoDb.get("test1"));
        Mockito.verifyNoInteractions(mockConnection);

    }
    @Test
    public void getAllTest(){
        MongoClient mockConnection = Mockito.mock(MongoClient.class);
        MongoDatabase mockMongoDatabase = Mockito.mock(MongoDatabase.class);
        MongoDbContext mongoDb = new MongoDbContext("1101");

        Mockito.when(mockConnection.getDatabase("SSWF")).thenReturn(mockMongoDatabase);
        Set<Map.Entry<String,Object>> res = mongoDb.get();
        for(Map.Entry<String,Object> val: res){
            System.out.println("Key: "+val.getKey()+"Value: "+val.getValue());
        }
        Mockito.verifyNoInteractions(mockConnection);
    }
}
