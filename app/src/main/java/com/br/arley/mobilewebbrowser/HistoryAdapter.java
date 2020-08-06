package com.br.arley.mobilewebbrowser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    ArrayList<History> searchHistory;

    public HistoryAdapter(ArrayList<History> searchHistory) {
        this.searchHistory = searchHistory;
    }


    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        holder.formatedUrl.setText(searchHistory.get(position).getFormatedUrl());
        holder.date.setText(searchHistory.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return searchHistory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView formatedUrl;
        TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            formatedUrl = itemView.findViewById(R.id.item_history_url);
            date = itemView.findViewById(R.id.item_history_date);
        }
    }
}
