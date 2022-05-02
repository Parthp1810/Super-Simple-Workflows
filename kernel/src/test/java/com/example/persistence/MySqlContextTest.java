package com.example.persistence;

import com.example.configuration.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.*;

public class MySqlContextTest {

    @Before
    public void setUp() {
        Configuration configuration = new Configuration();
        configuration.setupConfiguration("mongodb+srv://mongoAdmin:Jols_1305@cluster0.hclh8.mongodb.net/myFirstDatabase?retryWrites\\\\\\=true&w\\\\\\=majority","jdbc:mysql://localhost:3306/SSWF","root","Mickey_1613");
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void mySqlTest() throws Exception{
        Connection mockConnection = Mockito.mock(Connection.class);
        Statement mockStatement = Mockito.mock(Statement.class);
        MySqlContext mySql = Mockito.mock(MySqlContext.class);

        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
        Mockito.when(mockConnection.createStatement().execute(Mockito.any())).thenReturn(true);
        mySql.insert("test1","test13");
        Mockito.verify(mockConnection).createStatement();
    }
    @Test
    public void mySqlGetTest() throws SQLException {
        Connection mockConnection = Mockito.mock(Connection.class);
        Statement mockStatement = Mockito.mock(Statement.class);
        ResultSet mockResult = Mockito.mock(ResultSet.class);
        MySqlContext mySql = Mockito.mock(MySqlContext.class);

        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
        Mockito.when(mockConnection.createStatement().executeQuery(Mockito.any())).thenReturn(mockResult);
        Object res = mySql.get("test1");
        Mockito.verify(mockConnection).createStatement();

    }
    @Test
    public void mySqlGetAllTest() throws SQLException {
        Connection mockConnection = Mockito.mock(Connection.class);
        Statement mockStatement = Mockito.mock(Statement.class);
        ResultSet mockResult = Mockito.mock(ResultSet.class);

        MySqlContext mySql = Mockito.mock(MySqlContext.class);

        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
        Mockito.when(mockConnection.createStatement().executeQuery(Mockito.any())).thenReturn(mockResult);
        mySql.get();
        Mockito.verify(mockConnection).createStatement();

    }
}
