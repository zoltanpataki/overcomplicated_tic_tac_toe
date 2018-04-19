package com.codecool.funfact.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class FunfactService {
    private final String url = "https://api.chucknorris.io/jokes/random";
    public Map<String, String> getFunfact() throws IOException {
        System.setProperty("http.agent", "Chrome");
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> myMap = mapper.readValue(new URL(url), HashMap.class);
        return mapper.readValue(new URL(url), HashMap.class);
    }
}
