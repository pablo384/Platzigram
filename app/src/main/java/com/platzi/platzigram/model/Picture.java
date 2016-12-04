package com.platzi.platzigram.model;



public class Picture {

    private String picture;
    private String username;
    private String time;
    private String likenumbre="0";

    public Picture(String picture, String username, String time, String likenumbre) {
        this.picture = picture;
        this.username = username;
        this.time = time;
        this.likenumbre = likenumbre;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLikenumbre() {
        return likenumbre;
    }

    public void setLikenumbre(String likenumbre) {
        this.likenumbre = likenumbre;
    }
}
