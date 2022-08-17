package com.testapidemo.models;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;


@Getter
@Setter
public class TranscriptsList {

    private HashMap<String, String> page_details;
    private List<HashMap<String, String>> transcripts;
}
