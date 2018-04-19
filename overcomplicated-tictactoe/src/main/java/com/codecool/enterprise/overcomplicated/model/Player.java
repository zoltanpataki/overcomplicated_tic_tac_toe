package com.codecool.enterprise.overcomplicated.model;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Player {
    String userName = "Anonymous";
    List<Integer> hits = new ArrayList<>();
    int numOfHits = hits.size();
    String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<Integer> getHits() {
        return hits;
    }

    public int getNumOfHits() {
        return numOfHits;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmptyAvatarUrl(String avatar) {
        if (this.avatar == null) {
            this.avatar = avatar;
        }
    }
}
