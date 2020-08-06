package com.br.arley.mobilewebbrowser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    ImageButton btExit;
    RecyclerView historyRecyclerView;
    RecyclerView.Adapter adapter;
    ArrayList<History> searchHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        btExit = findViewById(R.id.activity_history_bt_exit);
        historyRecyclerView = findViewById(R.id.activity_history_recycler_view);

        searchHistory = new ArrayList<>();

        searchHistory.add(new History("https://www.youtube.com/watch?v=CTBiwKlO5IU&t=1051s", "youtube.com/watch?v=CTBiwKlO5IU&t=1051s", "06/08/2020  |  18:29"));
        searchHistory.add(new History("https://meet.google.com/ejw-vnyq-muu?pli=1&authuser=1", "meet.google.com/ejw-vnyq-muu?pli=1&authuser=1", "06/08/2020  |  18:31"));
        searchHistory.add(new History("https://www.devmedia.com.br/como-pegar-a-data-do-sistema/1609", "devmedia.com.br/como-pegar-a-data-do-sistema/1609", "06/08/2020  |  18:37"));
        searchHistory.add(new History("https://meet.google.com/ejw-vnyq-muu?pli=1&authuser=1", "meet.google.com/ejw-vnyq-muu?pli=1&authuser=1", "06/08/2020  |  18:31"));
        searchHistory.add(new History("https://www.youtube.com/watch?v=CTBiwKlO5IU&t=1051s", "youtube.com/watch?v=CTBiwKlO5IU&t=1051s", "06/08/2020  |  18:29"));
        searchHistory.add(new History("https://www.devmedia.com.br/como-pegar-a-data-do-sistema/1609", "devmedia.com.br/como-pegar-a-data-do-sistema/1609", "06/08/2020  |  18:37"));
        searchHistory.add(new History("https://www.youtube.com/watch?v=CTBiwKlO5IU&t=1051s", "youtube.com/watch?v=CTBiwKlO5IU&t=1051s", "06/08/2020  |  18:29"));
        searchHistory.add(new History("https://meet.google.com/ejw-vnyq-muu?pli=1&authuser=1", "meet.google.com/ejw-vnyq-muu?pli=1&authuser=1", "06/08/2020  |  18:31"));
        searchHistory.add(new History("https://www.devmedia.com.br/como-pegar-a-data-do-sistema/1609", "devmedia.com.br/como-pegar-a-data-do-sistema/1609", "06/08/2020  |  18:37"));
        searchHistory.add(new History("https://meet.google.com/ejw-vnyq-muu?pli=1&authuser=1", "meet.google.com/ejw-vnyq-muu?pli=1&authuser=1", "06/08/2020  |  18:31"));
        searchHistory.add(new History("https://www.youtube.com/watch?v=CTBiwKlO5IU&t=1051s", "youtube.com/watch?v=CTBiwKlO5IU&t=1051s", "06/08/2020  |  18:29"));
        searchHistory.add(new History("https://www.devmedia.com.br/como-pegar-a-data-do-sistema/1609", "devmedia.com.br/como-pegar-a-data-do-sistema/1609", "06/08/2020  |  18:37"));

        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HistoryAdapter(searchHistory);

        historyRecyclerView.setAdapter(adapter);


        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}