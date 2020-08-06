package com.br.arley.mobilewebbrowser.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.br.arley.mobilewebbrowser.dao.HistoryDao;
import com.br.arley.mobilewebbrowser.model.History;

@Database(entities = {History.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract HistoryDao historyDao();


}