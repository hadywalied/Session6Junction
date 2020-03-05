package com.github.hadywalied.session6.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.hadywalied.session6.R;
import com.github.hadywalied.session6.model.Repos;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MYVH> {
    private List<Repos> data = new ArrayList<>();

    MyAdapter(List<Repos> list) {
        data.clear();
        data.addAll(list);
    }

    @NonNull
    @Override
    public MYVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MYVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MYVH holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class MYVH extends RecyclerView.ViewHolder {

        TextView textView;

        MYVH(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_RepoName);
        }

        void bind(Repos repos) {
            textView.setText(repos.getName());
        }
    }

}
