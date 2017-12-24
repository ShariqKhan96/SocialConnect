package com.example.shariqkhan.socialconnect.Model;

/**
 * Created by ShariqKhan on 12/23/2017.
 */

public class MessageModel {

    String time;
    String date;
    String message;
    String name;

    public MessageModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
