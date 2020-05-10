package com.example.coronafeed;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ArticleDao {
    @Insert
    void insert(ReadLaterArticle article);

    @Update
    void update(ReadLaterArticle article);

    @Delete
    void delete(ReadLaterArticle article);

    @Query("SELECT * FROM article_table")
    LiveData<List<ReadLaterArticle>> getAllArticles();

}
