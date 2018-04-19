package com.codecool.enterprise.overcomplicated.controller;

import com.codecool.enterprise.overcomplicated.model.Player;
import com.codecool.enterprise.overcomplicated.model.TictactoeGame;
import com.codecool.enterprise.overcomplicated.service.TicTacToeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@SessionAttributes({"player", "ai", "game"})
public class GameController {

    @Autowired
    TicTacToeService ticTacToeService;

    private static final String AVATARURL = "http://localhost:60077/newavatar";
    private static final String COMICURL = "http://localhost:60007/getcomic";
    private static final String FUNFACTURL = "http://localhost:60777/getfunfact";

    @ModelAttribute("player")
    public Player getPlayer() {
        return new Player();
    }

    @ModelAttribute("game")
    public TictactoeGame getGame() {
        return new TictactoeGame();
    }

    @ModelAttribute("avatar_uri")
    public String getAvatarUri(@ModelAttribute Player player) {
        ticTacToeService.changeAvatar(player, AVATARURL, false);
        return player.getAvatar();
    }

    @ModelAttribute("ai")
    public Player getAi(){
        return new Player();
    }

    @GetMapping(value = "/")
    public String welcomeView(@ModelAttribute Player player) {
        return "welcome";
    }

    @PostMapping(value="/changeplayerusername")
    public String changPlayerUserName(@ModelAttribute Player player) {
        return "redirect:/game";
    }

    @GetMapping(value = "/game")
    public String gameView(@ModelAttribute("player") Player player, Model model) {
        Map<String, String> funfactData= ticTacToeService.getFunfact(FUNFACTURL);
        model.addAttribute("funfact", funfactData.get("value"));
        model.addAttribute("comic_uri", ticTacToeService.getComic(COMICURL));
        return "game";
    }
}
