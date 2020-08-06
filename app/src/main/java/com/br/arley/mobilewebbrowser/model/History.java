package com.br.arley.mobilewebbrowser.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class History {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "url")
    private String url;

    @ColumnInfo(name = "formated_url")
    private String formatedUrl;

    @ColumnInfo(name = "date")
    private String date;

    public History(String url, String formatedUrl, String date) {
        this.url = url;
        this.formatedUrl = formatedUrl;
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

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
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
