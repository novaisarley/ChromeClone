package com.br.arley.mobilewebbrowser.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.br.arley.mobilewebbrowser.model.History;

import java.util.List;

@Dao
public interface HistoryDao {

    @Query("SELECT * FROM history")
    List<History> getAllHistory();

    @Query("DELETE FROM history")
    void cleanSearchHistory();

    @Insert
    void insertAll(History... history);

    @Delete
    void delete(History history);

}

