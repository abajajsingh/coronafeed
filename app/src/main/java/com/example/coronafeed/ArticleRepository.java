package com.example.coronafeed;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.RequiresPermission;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ArticleRepository {
    private ArticleDao articleDao;
    private LiveData<List<ReadLaterArticle>> allArticles;

    public ArticleRepository(Application application) {
        ArticleDatabase database = ArticleDatabase.getInstance(application);
        articleDao = database.articleDao();
        allArticles = articleDao.getAllArticles();
    }

    public void insert(ReadLaterArticle article) {
        new InsertArticleAsyncTask(articleDao).execute(article);
    }

    public void update(ReadLaterArticle article) {
        new UpdateArticleAsyncTask(articleDao).execute(article);
    }

    public void delete(ReadLaterArticle article) {
        new DeleteArticleAsyncTask(articleDao).execute(article);
    }

    public LiveData<List<ReadLaterArticle>> getAllArticles() {
        return allArticles;
    }

    private static class InsertArticleAsyncTask extends AsyncTask<ReadLaterArticle, Void, Void> {
        private ArticleDao articleDao;

        private InsertArticleAsyncTask(ArticleDao articleDao) {
            this.articleDao = articleDao;
        }

        @Override
        protected Void doInBackground(ReadLaterArticle... articles) {
            articleDao.insert(articles[0]);
            return null;
        }
    }

    private static class UpdateArticleAsyncTask extends AsyncTask<ReadLaterArticle, Void, Void> {
        private ArticleDao articleDao;

        private UpdateArticleAsyncTask(ArticleDao articleDao) {
            this.articleDao = articleDao;
        }

        @Override
        protected Void doInBackground(ReadLaterArticle... articles) {
            articleDao.update(articles[0]);
            return null;
        }
    }

    private static class DeleteArticleAsyncTask extends AsyncTask<ReadLaterArticle, Void, Void> {
        private ArticleDao articleDao;

        private DeleteArticleAsyncTask(ArticleDao articleDao) {
            this.articleDao = articleDao;
        }

        @Override
        protected Void doInBackground(ReadLaterArticle... articles) {
            articleDao.delete(articles[0]);
            return null;
        }
    }
}
