package com.platzi.platzigram.model;


import java.util.HashMap;

public class Post {

    public String uid;
    public String autor;
    public String imageURL;
    public HashMap<String, Object> timeStampCreated;

    public Post(String autor, String imageURL, HashMap<String, Object> timeStampCreated) {
        this.autor = autor;
        this.imageURL = imageURL;
        this.timeStampCreated = timeStampCreated;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public HashMap<String, Object> getTimeStampCreated() {
        return timeStampCreated;
    }

    public void setTimeStampCreated(HashMap<String, Object> timeStampCreated) {
        this.timeStampCreated = timeStampCreated;
    }
}
