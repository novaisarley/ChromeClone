package com.br.arley.mobilewebbrowser.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.br.arley.mobilewebbrowser.adapter.HistoryAdapter;
import com.br.arley.mobilewebbrowser.R;
import com.br.arley.mobilewebbrowser.db.AppDataBase;
import com.br.arley.mobilewebbrowser.model.History;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    ImageButton btExit, btClearAll;
    RecyclerView historyRecyclerView;
    HistoryAdapter adapter;
    List<History> searchHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        btExit = findViewById(R.id.activity_history_bt_exit);
        btClearAll = findViewById(R.id.activity_history_bt_clean_history);
        historyRecyclerView = findViewById(R.id.activity_history_recycler_view);

        buildRecyclerView();

        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDataBase db = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "lilbank").allowMainThreadQueries().build();
                db.historyDao().cleanSearchHistory();
                buildRecyclerView();
            }
        });

    }

    public void buildRecyclerView(){

        final AppDataBase db = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "lilbank").allowMainThreadQueries().build();
        searchHistory = db.historyDao().getAllHistory();

        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Collections.reverse(searchHistory);
        adapter = new HistoryAdapter(searchHistory);
        historyRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new HistoryAdapter.OnItemClickListener() {
            @Override
            public void onUrlClick(int position) {
                Intent intent = new Intent(HistoryActivity.this, WebActivity.class);
                intent.putExtra("url", searchHistory.get(position).getUrl());
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(int position) {
                db.historyDao().delete(searchHistory.get(position));
                buildRecyclerView();
            }
        });
    }

    @Override
    protected void onResume() {
        buildRecyclerView();
        super.onResume();
    }
}