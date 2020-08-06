package com.br.arley.mobilewebbrowser;

public class History {
    String url;
    String formatedUrl;
    String date;

    public History(String url, String formatedUrl, String date) {
        this.url = url;
        this.formatedUrl =  formatedUrl;
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFormatedUrl() {
        return formatedUrl;
    }

    public void setFormatedUrl(String formatedUrl) {
        this.formatedUrl = formatedUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "History{" +
                "url='" + url + '\'' +
                ", formatedUrl='" + formatedUrl + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
