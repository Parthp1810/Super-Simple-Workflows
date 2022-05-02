package com.example.activity.impl;

import com.example.activity.Context;
import com.example.activity.Info;
import com.example.activity.Status;
import org.junit.Assert;
import org.junit.Test;

public class DefaultInfoTest {

    @Test
    public void defaultInfoTest() {
        Context context = new DefaultContext();
        Info info = new DefaultInfo(Status.FAILURE, context);
        Assert.assertEquals(info.getStatus(), Status.FAILURE);
    }
}
