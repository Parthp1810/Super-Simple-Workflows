package com.example.persistence.dbconnection;

import com.example.configuration.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnectionPool extends ObjectPool<Connection>{
    private String url;
    private String username;
    private String password;
    public MySqlConnectionPool() {
        url = Configuration.sqlConfig.getConnectionUrl();
        username = Configuration.sqlConfig.getUserName();
        password = Configuration.sqlConfig.getPassword();
    }

    @Override
    protected Connection create() {
        try {
            return (DriverManager.getConnection(url, username, password));
        } catch (SQLException e) {
            e.printStackTrace();
            return (null);
        }
    }

    @Override
    public boolean validate(Connection connection) {
        try {
            return (!((Connection) connection).isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    @Override
    public void expire(Connection connection) {
        try {
            ((Connection) connection).close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
