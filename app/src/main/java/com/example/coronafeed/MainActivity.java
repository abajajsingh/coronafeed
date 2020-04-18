package com.example.coronafeed;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends Activity {
    private RecyclerView mArticleRecyclerView;
    private ArticleLab mArticleLab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(this.getLocalClassName(),"Activity created.");
    }
}