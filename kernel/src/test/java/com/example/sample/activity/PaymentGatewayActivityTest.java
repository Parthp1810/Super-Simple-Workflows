package com.example.usecase.activity;

import com.example.activity.Context;
import com.example.persistence.MongoDbContext;
import com.example.persistence.MySqlContext;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;

public class PaymentGatewayActivityTest {
    @Test
    public void paymentGatewayExecuteMongoDbTest(){
        PaymentGatewayActivity paymentGatewayActivity = new PaymentGatewayActivity();
        Context activityContext = Mockito.mock(MongoDbContext.class);

        paymentGatewayActivity.execute(activityContext);

        Mockito.verify(activityContext, times(1)).insert("e89b-42d3-a456","payment-context");
    }
    @Test
    public void paymentGatewayExecuteMySqlTest(){
        PaymentGatewayActivity paymentGatewayActivity = new PaymentGatewayActivity();
        Context activityContext = Mockito.mock(MySqlContext.class);

        paymentGatewayActivity.execute(activityContext);

        Mockito.verify(activityContext, times(1)).insert("e89b-42d3-a456","payment-context");
    }
}
