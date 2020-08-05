package com.br.arley.mobilewebbrowser;

public class History {
    String url;
    String date;
    String time;

    public History(String url, String date, String time) {
        this.url = url;
        this.date = date;
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
