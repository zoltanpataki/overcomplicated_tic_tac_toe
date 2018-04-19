package com.codecool.avatar.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AvatarService {
    private final String url = "https://robohash.org/";
    Random random = new Random();
    public String makeUrl(){
        System.out.println(url.concat(String.valueOf(random.nextInt(5000))).concat("?set=set2"));
        return url.concat(String.valueOf(random.nextInt(5000))).concat("?set=set2");
    }
}
