package com.codecool.enterprise.overcomplicated.service;

import com.codecool.enterprise.overcomplicated.model.Player;
import com.codecool.enterprise.overcomplicated.model.TictactoeGame;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TicTacToeService {

    Random random = new Random();

    public Map<String, String> move(Player player, Player ai, HttpServletRequest request, String url){
        String myMove = null;
        String aiMove = null;
        boolean IWon = false;
        boolean aiWon = false;
        int totalHits;
        String urlWithGameboard = null;

        try {
            myMove = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e){
            e.printStackTrace();
        }

        player.getHits().add(Integer.parseInt(myMove));
        totalHits = player.getHits().size() + ai.getHits().size();

        /* Smart AI */

        urlWithGameboard = url.concat(getGameboard(player.getHits(), ai.getHits()));
        aiMove = getRecommendationForAi(urlWithGameboard);

        /* Random AI */

//        if (totalHits < 9){
//            aiMove = random.nextInt(9);
//        }
//        while (ai.getHits().contains(aiMove) && totalHits < 9 || player.getHits().contains(aiMove) && totalHits < 9){
//            aiMove = random.nextInt(9);
//        }


        if (!aiMove.equals("null")){
            ai.getHits().add(Integer.parseInt(aiMove));
        }

        String hit1 = null;
        String hit2 = null;
        String hit3 = null;

        System.out.println(player.getHits());
        System.out.println(ai.getHits());
        System.out.println(totalHits);
        if (totalHits >= 3){
            for (int i = 0; i < TictactoeGame.getWINCASES().size(); i++){
                if (player.getHits().containsAll(TictactoeGame.getWINCASES().get(i))){
                    IWon = true;
                    hit1 = String.valueOf(TictactoeGame.getWINCASES().get(i).get(0));
                    hit2 = String.valueOf(TictactoeGame.getWINCASES().get(i).get(1));
                    hit3 = String.valueOf(TictactoeGame.getWINCASES().get(i).get(2));
                    player.getHits().clear();
                    ai.getHits().clear();
                    totalHits = 0;
                }
                if (ai.getHits().containsAll(TictactoeGame.getWINCASES().get(i))){
                    aiWon = true;
                    hit1 = String.valueOf(TictactoeGame.getWINCASES().get(i).get(0));
                    hit2 = String.valueOf(TictactoeGame.getWINCASES().get(i).get(1));
                    hit3 = String.valueOf(TictactoeGame.getWINCASES().get(i).get(2));
                    ai.getHits().clear();
                    player.getHits().clear();
                    totalHits = 0;
                }
            }
        }
        if (totalHits >= 9){
            ai.getHits().clear();
            player.getHits().clear();
            totalHits = 0;
        }



        Map<String, String> responseData = new HashMap<>();
        responseData.put("myMove", myMove);
        responseData.put("aiMove", aiMove);
        responseData.put("IWon", String.valueOf(IWon));
        responseData.put("aiWon", String.valueOf(aiWon));
        responseData.put("hit1", hit1);
        responseData.put("hit2", hit2);
        responseData.put("hit3", hit3);

        return responseData;
    }

    private String getGameboard(List<Integer> playerHits, List<Integer> aiHits){
        List<String> gameboardList = new ArrayList<>();
        for (int i = 0; i < 9; i++){
            if (playerHits.contains(i)){
                gameboardList.add("O");
            } else if (aiHits.contains(i)){
                gameboardList.add("X");
            } else {
                gameboardList.add("-");
            }
        }

        String gameboard = String.join("", gameboardList);
        return gameboard;
    }

    private String getRecommendationForAi(String url){
        RestTemplate restTemplate = new RestTemplate();
        try {
            System.out.println(url);
            return restTemplate.getForEntity(url, String.class).getBody();
        } catch (RestClientException e){
            System.out.println("There is no ai service!");
            return "-1";
        }
    }

    public void changeAvatar(Player player, String url, boolean overWrite){
        RestTemplate restTemplate = new RestTemplate();
        try{
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            if (overWrite) {
                player.setAvatar(response.getBody());
            } else {
                player.setEmptyAvatarUrl(response.getBody());
            }
        } catch (RestClientException e){
            System.out.println("There is no avatar service!");
        }
    }

    public String getComic(String url){
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForEntity(url, String.class).getBody();
        } catch (RestClientException e){
            System.out.println("There is no comic service!");
            return "Not good uhh!";
        }
    }

    public Map<String, String> getFunfact(String url){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new URL(url), HashMap.class);
        } catch (IOException e){
            System.out.println("There is no funfact service!");
            return new HashMap<>();
        }
    }
}
