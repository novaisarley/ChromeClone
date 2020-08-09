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
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.br.arley.mobilewebbrowser.model.MyWebViewClient;
import com.br.arley.mobilewebbrowser.R;

import org.w3c.dom.Text;

public class WebActivity extends AppCompatActivity {

    WebView wv;
    String url;
    EditText edtUrl;
    ImageButton btHome, btAddGuide;
    ImageView imgError;
    TextView tvErrorMsg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        url = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            url = extras.getString("url", "https://www.google.com/");
        }

        setObjects();
        setObjectsListeners();

        setSecurityIcon(url);
        edtUrl.setText(url);
        setWebView();
        wv.loadUrl(url);

    }

    void setObjects(){
        edtUrl = findViewById(R.id.activity_web_url_edt);
        wv = findViewById(R.id.activity_web_webview);
        btHome = findViewById(R.id.activity_web_bt_home);
        btAddGuide = findViewById(R.id.activity_web_bt_new_page);
        imgError = findViewById(R.id.activity_web_img_error);
        tvErrorMsg = findViewById(R.id.activity_web_tv_error);
    }

    void setObjectsListeners(){
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
                    if (!edtUrl.getText().toString().trim().isEmpty()){
                        String url = edtUrl.getText().toString();
                        setSecurityIcon(url);
                        wv.loadUrl(url);
                    }else{
                        Toast.makeText(WebActivity.this, "Campo vazio", Toast.LENGTH_SHORT).show();
                    }

                }
                return false;
            }
        });

        btAddGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WebActivity.this, WebActivity.class));
            }
        });
    }

    void setWebView(){
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wv.setWebViewClient(new MyWebViewClient(this, edtUrl, imgError, tvErrorMsg));
    }

    void setSecurityIcon(String url){
        if (url.contains("https://"))edtUrl.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_locked, 0, 0, 0);
        else if (url.contains("http://"))edtUrl.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_unlocked, 0, 0, 0);
        else edtUrl.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
    }

    @Override
    public void onBackPressed() {
        if (wv.canGoBack()){
            wv.goBack();
            edtUrl.setText(wv.getUrl());
        }else{
            super.onBackPressed();
        }
    }
}