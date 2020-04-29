package com.example.coronafeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewAdapterHolder> {
    Context context;
    ArrayList<Article> list;

    public ViewAdapter(Context ct, ArrayList<Article> a) {
        context = ct;
        list = a;
    }

    @NonNull
    @Override
    public ViewAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row, parent, false);
        return new ViewAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapterHolder holder, int position) {
        holder.headline.setText(list.get(position).getTitle());
        holder.date.setText(list.get(position).getDate());
        holder.src.setText(list.get(position).getSource());

    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public class ViewAdapterHolder extends RecyclerView.ViewHolder {
        TextView headline, src, date;
        public ViewAdapterHolder(@NonNull View itemView) {
            super(itemView);
            headline = itemView.findViewById(R.id.headline);
            src = itemView.findViewById(R.id.source);
            date = itemView.findViewById(R.id.date);
        }
    }
}
