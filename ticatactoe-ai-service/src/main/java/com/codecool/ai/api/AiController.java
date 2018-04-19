package com.codecool.ai.api;

import com.codecool.ai.service.AiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AiController {

    @Autowired
    AiService aiService;

    @GetMapping(value = "/getaimove/{gameboard}")
    public ResponseEntity getAiMove(@PathVariable("gameboard") String gameboard) throws IOException{
        System.out.println(gameboard);
        return new ResponseEntity<>(aiService.getAiMove(gameboard), HttpStatus.OK);
    }
}
