package com.example.activity;

import com.example.activity.impl.DefaultContext;
import com.example.activity.impl.DefaultInfo;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ActivityInfoListTest {

    @Test
    public void infoListStatusTest() {
        List<Info> infoList = new ArrayList<>();
        Context context = new DefaultContext();
        Info info = new DefaultInfo(Status.SUCCESS, context);
        infoList.add(info);
        ActivityInfoList activityInfoList = new ActivityInfoList(infoList);

        Assert.assertEquals(Status.SUCCESS, activityInfoList.getStatus());
    }
}
