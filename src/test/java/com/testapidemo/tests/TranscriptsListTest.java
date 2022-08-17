package com.testapidemo.tests;

import com.aventstack.extentreports.Status;
import com.testapidemo.base.BaseClass;
import com.testapidemo.dataptovider.DataProviders;
import com.testapidemo.models.TranscriptsList;
import com.testapidemo.requests.ValidRequests;
import com.testapidemo.utilities.Log;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.testapidemo.utilities.ExtentManager.test;

public class TranscriptsListTest extends BaseClass {


    // This test is to verify correct audios as per AssenblyAI requirements
    // Steps:
    // 1. Do GET request to get the list od previously created transcripts for each possible status.
    //.2. Verify status code.
    // NOTE: all methods returning Response type also map response onto the model class

    @Test( dataProvider = "possibleStatuses", dataProviderClass = DataProviders.class, timeOut = 4000)
    public void test001_verifyTranscriptsList(String status){
        Log.startTestCase("test001_verifyTranscriptsInfo for status " + status);
        test.log(Status.INFO, "Verifying status \"" + status + "\"");

        transcriptsList = new TranscriptsList();
        Response getResponse = ValidRequests.getTranscriptsList(status, 200);
        int stCode = getResponse.statusCode();
        Log.logger.info("Status code: " + stCode);
        Assert.assertEquals(stCode, 200);

    }

}
