package com.example.usecase.activity;

import com.example.activity.Context;
import com.example.persistence.MongoDbContext;
import com.example.persistence.MySqlContext;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;

public class NotificationActivityTest {

    @Test
    public void notificationExecuteMongoDbTest(){
        NotificationActivity notificationActivity = new NotificationActivity();
        Context activityContext = Mockito.mock(MongoDbContext.class);

        notificationActivity.execute(activityContext);

        Mockito.verify(activityContext, times(1)).insert("e89b-42d3-a135","notification-context");
    }
    @Test
    public void notificationExecuteMySqlTest(){
        NotificationActivity notificationActivity = new NotificationActivity();
        Context activityContext = Mockito.mock(MySqlContext.class);

        notificationActivity.execute(activityContext);

        Mockito.verify(activityContext, times(1)).insert("e89b-42d3-a135","notification-context");
    }
}
