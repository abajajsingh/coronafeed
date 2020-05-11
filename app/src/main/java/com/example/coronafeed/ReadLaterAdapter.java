package com.example.coronafeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReadLaterAdapter extends RecyclerView.Adapter<ReadLaterAdapter.ReadLaterAdapterHolder> {
    private Context context;
    private ArrayList<ReadLaterArticle> list;
    private onItemClickListener mListener;

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        mListener = listener;
    }

    public ReadLaterAdapter(Context ct, ArrayList<ReadLaterArticle> a) {
        context = ct;
        list = a;
    }

    public ReadLaterAdapter(Context ct) {
        list = new ArrayList<>();
        context = ct;
    }

    @NonNull
    @Override
    public ReadLaterAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row, parent, false);
        return new ReadLaterAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadLaterAdapterHolder holder, int position) {
        holder.headline.setText(list.get(position).getTitle());
        holder.src.setText(list.get(position).getSource());
        holder.date.setText(list.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setArticles(ArrayList<ReadLaterArticle> ReadLaterArticles) {
        this.list = ReadLaterArticles;
        notifyDataSetChanged();
    }

    public ReadLaterArticle getReadLaterArticleAt(int position) {
        return list.get(position);
    }

    public Article getArticle(int position) {
        ReadLaterArticle rl_art = list.get(position);
        return new Article(rl_art.getTitle(), rl_art.getUrl(),
                rl_art.getSource(), rl_art.getDescription(), rl_art.getDate());
    }

    public class ReadLaterAdapterHolder extends RecyclerView.ViewHolder {
        TextView headline, src, date;
        public ReadLaterAdapterHolder(@NonNull View itemView) {
            super(itemView);
            headline = itemView.findViewById(R.id.headline);
            src = itemView.findViewById(R.id.source);
            date = itemView.findViewById(R.id.date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

}
