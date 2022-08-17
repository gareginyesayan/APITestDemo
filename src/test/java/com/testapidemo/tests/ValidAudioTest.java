package com.testapidemo.tests;

import com.aventstack.extentreports.Status;
import com.testapidemo.base.BaseClass;
import com.testapidemo.dataptovider.DataProviders;
import com.testapidemo.models.Transcript;
import com.testapidemo.requests.ValidRequests;
import com.testapidemo.utilities.Log;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


import static com.testapidemo.utilities.ExtentManager.test;

public class ValidAudioTest extends BaseClass {


// This test is to verify correct audios as per AssenblyAI requirements
    // Steps:
    // 1. Create new transcript and get transcript ID.
    //.2. Query for transcript to be completed.
    // 3. Verify that transcript text is as expected
    // NOTE: all methods returning Response type also map response onto the model class



    @Test(dataProvider = "audios", dataProviderClass = DataProviders.class, timeOut = 15000)
    public void test001_VerifyValidAudio(String testAudioURL, String audioText) throws InterruptedException {
        Log.startTestCase("test001_VerifyGoodAudio for '" + audioText.substring(0,10) +"'...");
        transcript = new Transcript();
        test.log(Status.INFO, "Sending " + testAudioURL + " for transcript");

        Response postResponse = ValidRequests.postAudio(testAudioURL);

        test.log(Status.INFO, "Verifying if post request is successful");
        Assert.assertEquals(postResponse.getStatusCode(), 200, "Post Request status is not 200");
        Log.info("Audio has been sent for new transcript. Status code: " + postResponse.getStatusCode());

        String transcriptId = transcript.getId();
        Log.info("Transcript ID: " + transcriptId);
        Response getResponse;

        test.log(Status.INFO, "Starting querying server for transcription to be completed");
        do {
            getResponse = ValidRequests.getTranscript(transcriptId);
            System.out.println(transcript.getStatus());
            Thread.sleep(1000);
        } while (!transcript.getStatus().equals("completed") && !transcript.getStatus().equals("error"));
        Log.info("Querying for transcript ready. Status code: " + getResponse.getStatusCode());

        test.log(Status.INFO, "Querying is done. Verifying Status is Completed");
        Assert.assertEquals(transcript.getStatus(), "completed", "Request status is not completed");

        Log.info("Transcript is completed. Verifying text is as expected");
        test.log(Status.INFO, "Transcript is completed. Verifying text is as expected");
        Assert.assertTrue(transcript.getText().equals(audioText));
        Log.endTestCase("test001_VerifyGoodAudio for " + audioText);

    }

}
