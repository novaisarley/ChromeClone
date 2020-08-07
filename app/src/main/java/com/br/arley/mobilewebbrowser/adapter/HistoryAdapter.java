package com.br.arley.mobilewebbrowser.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.br.arley.mobilewebbrowser.R;
import com.br.arley.mobilewebbrowser.model.History;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    List<History> searchHistory;

    private OnItemClickListener mListener;
    public interface OnItemClickListener{
        void onUrlClick(int position);

    };


    public HistoryAdapter(List<History> searchHistory) {
        this.searchHistory = searchHistory;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new ViewHolder(view, mListener);
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

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView formatedUrl;
        TextView date;
        ImageButton btDelete;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            formatedUrl = itemView.findViewById(R.id.item_history_url);
            date = itemView.findViewById(R.id.item_history_date);
            btDelete = itemView.findViewById(R.id.item_history_bt_delete);

            formatedUrl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onUrlClick(position);
                        }
                    }
                }
            });

            btDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }
}
