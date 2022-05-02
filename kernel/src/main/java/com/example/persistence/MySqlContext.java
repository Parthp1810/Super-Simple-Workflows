package com.example.persistence;

import com.example.activity.Context;
import com.example.persistence.dbconnection.MySqlConnectionPool;
import org.eclipse.jetty.util.ConcurrentHashSet;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MySqlContext implements Context {
    private final Connection connection;
    private final String INSERT = "INSERT INTO `WorkflowContext`(`key`,`value`,`workflowId`) VALUES ('%s', '%s','%s')";
    private final String SELECT = "SELECT `value` FROM `WorkflowContext` WHERE `key` = '%s' LIMIT 1;";
    private final String SELECT_ALL = "SELECT `key`,`value` FROM `WorkflowContext` WHERE workflowId = '%s';";

    private String workflowId;

    public MySqlContext(String workflowId) {
        this.workflowId = workflowId;
        MySqlConnectionPool mySqlConnectionPool = new MySqlConnectionPool();
        connection = mySqlConnectionPool.checkOut();
    }

    @Override
    public void insert(String key, Object value) {
        try {
            Statement statement  = connection.createStatement();
            String insertQuery = String.format(INSERT,key,value,workflowId).toString();
            statement.execute(insertQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object get(String key) {
        try {
            Statement statement  = connection.createStatement();
            ResultSet res = statement.executeQuery(String.format(SELECT,key).toString());
            return res.getString("value");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<Map.Entry<String, Object>> get() {
       HashMap<String,Object> collection = new HashMap<>();
        try {

            Statement statement  = connection.createStatement();
            ResultSet res = statement.executeQuery(String.format(SELECT_ALL,workflowId).toString());
            while(res.next()){
                collection.put(res.getString("key"),res.getString("value"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return collection.entrySet();
    }
}
