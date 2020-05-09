package com.example.coronafeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewAdapterHolder> {
    private Context context;
    private ArrayList<Article> list;
    private onItemClickListener mListener;

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        mListener = listener;
    }

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
        holder.src.setText(list.get(position).getSource());
        holder.date.setText(list.get(position).getDate());
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
