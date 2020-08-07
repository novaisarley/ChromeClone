package com.br.arley.mobilewebbrowser.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import com.br.arley.mobilewebbrowser.model.MyWebViewClient;
import com.br.arley.mobilewebbrowser.R;

public class WebActivity extends AppCompatActivity {

    WebView wv;
    String url;
    EditText edtUrl;
    ImageButton btHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            url = extras.getString("url", "https://www.google.com/");
        }

        edtUrl = findViewById(R.id.activity_web_url_edt);
        wv = findViewById(R.id.activity_web_webview);
        btHome = findViewById(R.id.activity_web_bt_home);


        if (url.contains("https://"))edtUrl.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_locked, 0, 0, 0);
        else if (url.contains("http://"))edtUrl.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_unlocked, 0, 0, 0);
        else edtUrl.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

        edtUrl.setText(url);
        MyWebViewClient webViewClient = new MyWebViewClient(this);
        wv.setWebViewClient(webViewClient);

        wv.loadUrl(url);

        WebSettings webSettings = wv.getSettings();
        //Habilitando o JavaScript
        webSettings.setJavaScriptEnabled(true);

        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WebActivity.this, SearchActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        edtUrl.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_DONE){
                    Intent i = new Intent(WebActivity.this, WebActivity.class);
                    i.putExtra("url", edtUrl.getText().toString());
                    startActivity(i);
                }
                return false;
            }
        });
    }
}