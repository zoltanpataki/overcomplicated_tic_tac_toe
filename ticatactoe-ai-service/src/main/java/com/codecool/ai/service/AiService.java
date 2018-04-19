package com.codecool.ai.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class AiService {

    private final String url = "http://tttapi.herokuapp.com/api/v1/";

    public String getAiMove(String gameboard) throws IOException{
        System.setProperty("http.agent", "Chrome");
        String fullUrl = url.concat(gameboard).concat("/X");
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> myMap = mapper.readValue(new URL(fullUrl), HashMap.class);
        return String.valueOf(myMap.get("recommendation"));
    }
}
