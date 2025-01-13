package com.appsolute.tium.adpater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.appsolute.tium.R;

import java.util.List;

public class QuestAdapter extends RecyclerView.Adapter<QuestAdapter.questViewHolder> {

    private List<String> items;

    public QuestAdapter(List<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public questViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_quest, parent, false);
        return new questViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull questViewHolder holder, int position) {
        holder.itemTitle.setText(items.get(position));

        // Set up click listener for the More button
        holder.moreButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onMoreButtonClick(position);
            }
        });
    }

    public interface OnMoreButtonClickListener {
        void onMoreButtonClick(int position);
    }

    private OnMoreButtonClickListener listener;

    public void setOnMoreButtonClickListener(OnMoreButtonClickListener listener) {
        this.listener = listener;
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateData(List<String> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

    static class questViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        CardView moreButton;

        public questViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            moreButton = itemView.findViewById(R.id.moreButton);
        }
    }
}
