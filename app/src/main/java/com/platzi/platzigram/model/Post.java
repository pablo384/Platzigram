package com.platzi.platzigram.model;


import android.text.format.DateUtils;

import java.util.Date;

public class Post {

    public String uid;
    public String autor;
    public String imageURL;
    public double timeStampCreated;

    public Post() {
    }

    public Post(String autor, String imageURL, double timeStampCreated) {
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

    public double getTimeStampCreated() {
        return timeStampCreated;
    }
    public void setTimeStampCreated(double timeStampCreated) {
        this.timeStampCreated = timeStampCreated;
    }
    public String getRelativeTimeStamp(){

        return DateUtils.getRelativeTimeSpanString(
                (long)this.timeStampCreated,
                System.currentTimeMillis(),
                DateUtils.SECOND_IN_MILLIS,
                DateUtils.FORMAT_ABBREV_WEEKDAY).toString();
    }
}
