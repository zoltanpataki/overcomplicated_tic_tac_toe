package com.codecool.funfact.api;

import com.codecool.funfact.service.FunfactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class FunfactApiController {

    @Autowired
    FunfactService funfactService;

    @GetMapping(value = "/getfunfact")
    public ResponseEntity getNewFunfact() throws IOException{
        return new ResponseEntity(funfactService.getFunfact(), HttpStatus.OK);
    }
}
