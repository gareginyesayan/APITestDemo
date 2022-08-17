package com.testapidemo.base;

import com.google.gson.Gson;
import com.testapidemo.models.Transcript;
import com.testapidemo.models.TranscriptsList;
import com.testapidemo.utilities.ExtentManager;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class BaseClass {
    private  Properties prop;
    protected String key;
    protected static Gson gson = new Gson();
    protected static Header authorHeader;
    protected static Header contentTypeHeader;
    protected  static Transcript transcript;
    protected static TranscriptsList transcriptsList;



    @BeforeSuite
    public void loadConfig() {

        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(
                    System.getProperty("user.dir") + "/config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ExtentManager.setExtent();

        key = prop.getProperty("AssemblyAI_API_KEY");
        authorHeader = new Header("authorization", key);
        contentTypeHeader = new Header("content-type", "application/json");
        RestAssured.baseURI = "https://api.assemblyai.com";

    }


    @AfterSuite
    public void afterSuite() {
        ExtentManager.endReport();
    }


}
