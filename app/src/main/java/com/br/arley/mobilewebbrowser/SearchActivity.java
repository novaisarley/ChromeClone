package com.br.arley.mobilewebbrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SearchActivity extends AppCompatActivity {

    ImageButton btHistory;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        btHistory = findViewById(R.id.activity_search_bt_history);
        searchView = findViewById(R.id.activity_search_searchview);

        btHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, HistoryActivity.class));
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String url = query.trim();
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

                History history = new History(url, formatedUrl, date);
                Log.d("ADOLETA", history.toString());

                Intent i = new Intent(SearchActivity.this, WebActivity.class);
                i.putExtra("url", query);
                startActivity(i);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }



}