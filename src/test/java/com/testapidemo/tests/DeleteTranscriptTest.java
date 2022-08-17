package com.testapidemo.tests;

import com.aventstack.extentreports.Status;
import com.testapidemo.base.BaseClass;
import com.testapidemo.models.Transcript;
import com.testapidemo.requests.ValidRequests;
import com.testapidemo.utilities.Log;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import static com.testapidemo.utilities.ExtentManager.test;

public class DeleteTranscriptTest extends BaseClass {



    private SoftAssert softAssert = new SoftAssert();

    // This test is to verify delete transcript functionality.
    // Steps:
    // 1. Create new transcript and get transcript ID.
    //.2. Query for transcript to be completed.
    // 3. Delete created transcript and verify that audio_url is changed to "http://deleted_by_user"
    // NOTE: all methods returning Response type also map response onto the model class

    @Test(timeOut = 15000)
    public void test001_DeleteTest() throws InterruptedException {
        Log.startTestCase("test001_DeleteTest");
        transcript = new Transcript();
        String testAudioURL = "https://github.com/gareginyesayan/Test-Data/raw/main/Thirsty.mp4";

        test.log(Status.INFO, "Sending " + testAudioURL + " for transcript");
        Response postResponse = ValidRequests.postAudio(testAudioURL);

        test.log(Status.INFO, "Verifying post request is successful");
        softAssert.assertEquals(postResponse.getStatusCode(), 200, "Post Request status is not 200");
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
        test.log(Status.INFO, "Querying is done. Verifying status is Completed");
        Assert.assertEquals(transcript.getStatus(), "completed", "Request status is not completed");

        Log.info("Transcript is completed. Deleting now");
        test.log(Status.INFO, "Deleting transcript");
        Response deleteResponse = ValidRequests.deleteTranscript(transcriptId);
        test.log(Status.INFO, "Verifying delete request is successful");
        softAssert.assertEquals(deleteResponse.getStatusCode(), 200, "Delete Request status code is not 200");
        Log.info("Delete request status code: " + deleteResponse.getStatusCode());
        Log.info("Now audio url is: " + transcript.getAudio_url());
        test.log(Status.INFO, "Verifying transcript is deleted");
        softAssert.assertEquals(transcript.getAudio_url(), "http://deleted_by_user", "Delete Request status code is not 200");
        Log.endTestCase("test001_DeleteTest");

        softAssert.assertAll();

    }

}
