package com.example.coronafeed;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReadLaterFragment extends Fragment {
    private ArticleViewModel articleViewModel;
    private Context mContext;
    private RecyclerView mArticleRecyclerView;
    private ReadLaterAdapter mViewAdapter;
    private TextView noArticleMessage;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
        articleViewModel.getAllArticles().observe(this, new Observer<List<ReadLaterArticle>>() {
            @Override
            public void onChanged(List<ReadLaterArticle> readLaterArticles) {
                ArrayList rl_list = (ArrayList)readLaterArticles;
                if(rl_list.size() == 0) {
                    noArticleMessage.setVisibility(View.VISIBLE);
                } else {
                    noArticleMessage.setVisibility(View.INVISIBLE);
                }
                mViewAdapter.setArticles((ArrayList)readLaterArticles);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_readlater, container, false);
        noArticleMessage = view.findViewById(R.id.no_articles);
        buildRecyclerView(view);
        return view;
    }

    private void buildRecyclerView(View view) {
        mArticleRecyclerView = view.findViewById(R.id.readlater_view);
        mArticleRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mViewAdapter = new ReadLaterAdapter(mContext);
        mArticleRecyclerView.setAdapter(mViewAdapter);
        mArticleRecyclerView.setHasFixedSize(true);

        mViewAdapter.setOnItemClickListener(new ReadLaterAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Article art = mViewAdapter.getArticle(position);
                ReadMoreDialog dialog = new ReadMoreDialog(art.getDescription(),art.getUrl(), art, articleViewModel, false);
                dialog.show(getFragmentManager(),"read more");
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                articleViewModel.delete(mViewAdapter.getReadLaterArticleAt(viewHolder.getAdapterPosition()));
                Toast.makeText(mContext,"Article Removed From Read Later", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(mArticleRecyclerView);

    }

}
