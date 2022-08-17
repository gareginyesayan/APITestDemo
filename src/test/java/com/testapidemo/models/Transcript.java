package com.testapidemo.models;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class Transcript {

    private String id;
    private String audio_url;
    private String status;
    private String text;
    private List<HashMap<String,String >> words;
}
