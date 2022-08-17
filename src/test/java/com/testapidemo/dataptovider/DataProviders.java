package com.testapidemo.dataptovider;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name="audios")
    public Object[][] getAudioURL(){


        Object [][] data = {
                {"https://github.com/gareginyesayan/Test-Data/raw/main/Thirsty.mp4", "These pretzels are making me thirsty."},
                {"https://bit.ly/3yxKEIY", "You know, demons on TV like that. And and for people to expose themselves to being rejected on TV or, you know, humili humiliated by Fear Factor or, you know."}
        };
        return data;
    }

    @DataProvider(name="possibleStatuses")
    public Object[][] getPossiblesStatuses(){


        Object [][] data = {
                {"completed"},
                {"processing"},
                {"queued"},
                {"error"}
        };
        return data;
    }

}
