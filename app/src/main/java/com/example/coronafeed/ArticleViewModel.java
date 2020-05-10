package com.example.coronafeed;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ArticleViewModel extends AndroidViewModel {
    private ArticleRepository repository;
    private LiveData<List<ReadLaterArticle>> allArticles;
    public ArticleViewModel(@NonNull Application application) {
        super(application);
        repository = new ArticleRepository(application);
        allArticles = repository.getAllArticles();
    }

    public void insert(ReadLaterArticle article) {
        repository.insert(article);
    }

    public void update(ReadLaterArticle article) {
        repository.update(article);
    }

    public void delete(ReadLaterArticle article) {
        repository.delete(article);
    }

    public LiveData<List<ReadLaterArticle>> getAllArticles() {
        return allArticles;
    }
}
