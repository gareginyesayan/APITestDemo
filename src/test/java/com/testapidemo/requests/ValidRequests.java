package com.testapidemo.requests;

import com.testapidemo.base.BaseClass;
import com.testapidemo.models.Transcript;
import com.testapidemo.models.TranscriptsList;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class ValidRequests extends BaseClass {



    public  static Response postAudio(String audioURL) {

        RestAssured.basePath = "/v2/transcript";
        transcript.setAudio_url(audioURL);
        String requestBody = gson.toJson(transcript);
        RequestSpecification postRequest = RestAssured.given()
                .header(authorHeader)
                .header(contentTypeHeader)
                .body(requestBody);
        Response postResponse = postRequest.post();
        transcript = gson.fromJson(postResponse.body().asString(), Transcript.class);
        return postResponse;

    }

    public static Response getTranscript(String id) {
        RestAssured.basePath = "/v2/transcript/" + id;
        RequestSpecification getRequest = RestAssured.given()
                .header(authorHeader);
        Response getResponse = getRequest.get();
        transcript = gson.fromJson(getResponse.body().asString(), Transcript.class);

        return getResponse;

    }

    public static Response getTranscriptsList(String status, int limit) {
        RestAssured.basePath = "/v2/transcript";
        RequestSpecification getRequest = RestAssured.given()
                .header(authorHeader)
                .header(contentTypeHeader)
                .param("status", status)
                .param("limit", limit);

        Response getResponse = getRequest.get();
        transcriptsList = gson.fromJson(getResponse.body().asString(), TranscriptsList.class);
        return getResponse;
    }

    public static Response deleteTranscript(String id) {
        RestAssured.basePath = "/v2/transcript/" + id;
        RequestSpecification getRequest = RestAssured.given()
                .header(authorHeader);
        Response deleteResponse = getRequest.delete();
        transcript = gson.fromJson(deleteResponse.body().asString(), Transcript.class);
        return deleteResponse;

    }
}
