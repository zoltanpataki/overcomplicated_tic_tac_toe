package com.codecool.enterprise.overcomplicated.api;

import com.codecool.enterprise.overcomplicated.model.Player;
import com.codecool.enterprise.overcomplicated.service.TicTacToeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@SessionAttributes({"player", "ai", "game"})
public class ControllerAPI {

    @Autowired
    TicTacToeService ticTacToeService;

    private final static String AVATARURL = "http://localhost:60077/newavatar";
    private final static String AIURL = "http://localhost:60017/getaimove/";

    @PostMapping(value = "/api/game-move")
    public ResponseEntity getGameMove(HttpServletRequest request,
                                   @ModelAttribute("player") Player player,
                                   @ModelAttribute("ai") Player ai){
        Map<String, String> responseData = ticTacToeService.move(player, ai, request, AIURL);
        return new ResponseEntity(responseData, HttpStatus.OK);
    }

    @GetMapping(value = "/api/newavatar")
    public ResponseEntity getNewAvatar(@ModelAttribute Player player){
        ticTacToeService.changeAvatar(player, AVATARURL, true);
        return new ResponseEntity(player.getAvatar(), HttpStatus.OK);
    }


}
