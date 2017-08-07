package com.example.ftkj.mycook.bean;

/**
 * Created by FTKJ on 2017/5/26.
 */

public class RegEvent {
    private String username;
    private String password;
    private String emil;

    public RegEvent(String username, String password, String emil) {
        this.username = username;
        this.password = password;
        this.emil = emil;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmil() {
        return emil;
    }

    public void setEmil(String emil) {
        this.emil = emil;
    }
}
