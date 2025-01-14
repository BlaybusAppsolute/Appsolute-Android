package com.appsolute.tium.adpater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appsolute.tium.R;

import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.projectViewHolder> {

    private List<String> items;

    public ProjectAdapter(List<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public projectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_project, parent, false);
        return new projectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull projectViewHolder holder, int position) {
        holder.itemTitle.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateData(List<String> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

    static class projectViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;

        public projectViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.itemTitle);
        }
    }
}
