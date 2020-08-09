package com.br.arley.mobilewebbrowser.model;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.room.Room;

import com.br.arley.mobilewebbrowser.db.AppDataBase;
import com.br.arley.mobilewebbrowser.ui.WebActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyWebViewClient extends android.webkit.WebViewClient {

    private Activity activity = null;
    private EditText edtUrl;
    private ImageView imgError;
    private TextView tvErrorMsg;

    public MyWebViewClient(Activity activity, EditText edtUrl, ImageView imgError, TextView tvErrorMsg) {
        this.activity = activity;
        this.edtUrl = edtUrl;
        this.imgError = imgError;
        this.tvErrorMsg = tvErrorMsg;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view,  WebResourceRequest request) {
        AppDataBase db = Room.databaseBuilder(view.getContext(), AppDataBase.class, "lilbank").allowMainThreadQueries().build();

        String url = request.getUrl().toString();
        String formatedUrl = formateUrl(url.trim());
        String date = getFormatedDate();

        db.historyDao().insertAll(new History(url, formatedUrl, date));

        edtUrl.setText(url);

        return false;
    }


    private String formateUrl(String url){
        String formatedUrl;

        if (url.contains("https://www."))formatedUrl = url.replace("https://www.", "");
        else if (url.contains("http://www."))formatedUrl = url.replace("http://www.", "");
        else if (url.contains("https://"))formatedUrl = url.replace("https://", "");
        else if (url.contains("http://"))formatedUrl = url.replace("http://", "");
        else formatedUrl = url;

        return formatedUrl;
    }

    private String getFormatedDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date dateO = new Date();
        String time = dateFormat.format(dateO);

        String[] dateArray = time.split(" ");
        String date = dateArray[0] + "  |  " + dateArray[1];

        return date;
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
    }
}
