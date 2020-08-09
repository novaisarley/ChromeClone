package com.br.arley.mobilewebbrowser.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.br.arley.mobilewebbrowser.db.AppDataBase;
import com.br.arley.mobilewebbrowser.model.History;
import com.br.arley.mobilewebbrowser.model.MyWebViewClient;
import com.br.arley.mobilewebbrowser.R;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WebActivity extends AppCompatActivity {

    WebView wv;
    String url;
    EditText edtUrl;
    Button btGoBack;
    ImageButton btHome, btAddGuide;
    ImageView imgError;
    TextView tvErrorMsg;
    ProgressBar progressBar;
    AppDataBase db;


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
        setWebView();
        wv.loadUrl(url);

    }

    void setObjects(){
        db = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "lilbank").allowMainThreadQueries().build();
        edtUrl = findViewById(R.id.activity_web_url_edt);
        wv = findViewById(R.id.activity_web_webview);
        btHome = findViewById(R.id.activity_web_bt_home);
        btAddGuide = findViewById(R.id.activity_web_bt_new_page);
        imgError = findViewById(R.id.activity_web_img_error);
        tvErrorMsg = findViewById(R.id.activity_web_tv_error);
        progressBar = findViewById(R.id.activity_web_progressbar);
        btGoBack = findViewById(R.id.activity_web_bt_back);
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

        btGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgError.setVisibility(View.GONE);
                tvErrorMsg.setVisibility(View.GONE);
                btGoBack.setVisibility(View.GONE);
                if (wv.canGoBack()){
                    edtUrl.setEnabled(true);
                    wv.setVisibility(View.VISIBLE);
                    wv.goBack();
                }
                else{
                    onBackPressed();
                }


            }
        });

        edtUrl.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_DONE){
                    if (!edtUrl.getText().toString().trim().isEmpty()){
                        String url = edtUrl.getText().toString();
                        String formatedUrl = formateUrl(url);
                        String date = getFormatedDate();
                        if (!url.contains("http://") && !url.contains("https://")){
                            url = "https://www.google.com/search?q=" + url;
                        }
                        setSecurityIcon(url);
                        db.historyDao().insertAll(new History(url, formatedUrl, date));
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

    private String getFormatedDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date dateO = new Date();
        String time = dateFormat.format(dateO);

        String[] dateArray = time.split(" ");
        String date = dateArray[0] + "  |  " + dateArray[1];

        return date;
    }

    private String formateUrl(String url) {
        String formatedUrl;

        if (url.contains("https://www.")) formatedUrl = url.replace("https://www.", "");
        else if (url.contains("http://www.")) formatedUrl = url.replace("http://www.", "");
        else if (url.contains("https://")) formatedUrl = url.replace("https://", "");
        else if (url.contains("http://")) formatedUrl = url.replace("http://", "");
        else formatedUrl = "www.google.com/search?q=" + url;

        return formatedUrl;
    }

    void setWebView(){
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wv.setWebViewClient(new MyWebViewClient(this, edtUrl, imgError, tvErrorMsg, progressBar, btGoBack));
    }

    void setSecurityIcon(String url){
        if (url.contains("https://"))edtUrl.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_locked, 0, 0, 0);
        else if (url.contains("http://"))edtUrl.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_unlocked, 0, 0, 0);
        else edtUrl.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
    }

    @Override
    public void onBackPressed() {
        if (btGoBack.getVisibility() == View.VISIBLE){

        }
        else if (wv.canGoBack()){
            wv.goBack();
        }else{
            super.onBackPressed();
        }
    }
}