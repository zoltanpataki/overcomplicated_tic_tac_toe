package com.codecool.avatar.api;

import com.codecool.avatar.service.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class AvatarApiController {

    @Autowired
    AvatarService avatarService;

    @GetMapping(value = "/newavatar")
    public ResponseEntity getNewAvatar(){
        String url = avatarService.makeUrl();
        return new ResponseEntity<>(url, HttpStatus.OK);
    }

}
