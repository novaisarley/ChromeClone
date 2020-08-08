package com.br.arley.mobilewebbrowser.model;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;

import androidx.room.Room;

import com.br.arley.mobilewebbrowser.db.AppDataBase;
import com.br.arley.mobilewebbrowser.ui.WebActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.br.arley.mobilewebbrowser.ui.WebActivity.edtUrl;

public class MyWebViewClient extends android.webkit.WebViewClient {

    private Activity activity = null;

    public MyWebViewClient(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        AppDataBase db = Room.databaseBuilder(view.getContext(), AppDataBase.class, "lilbank").allowMainThreadQueries().build();
        url = url.trim();
        String formatedUrl = "";

        if (url.contains("https://www."))formatedUrl = url.replace("https://www.", "");
        else if (url.contains("http://www."))formatedUrl = url.replace("http://www.", "");
        else if (url.contains("https://"))formatedUrl = url.replace("https://", "");
        else if (url.contains("http://"))formatedUrl = url.replace("http://", "");
        else formatedUrl = url;


        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date dateO = new Date();
        String time = dateFormat.format(dateO);

        String[] dateArray = time.split(" ");
        String date = dateArray[0] + "  |  " + dateArray[1];
        db.historyDao().insertAll(new History(url, formatedUrl, date));

        edtUrl.setText(url);

        return false;
    }
}
