package com.github.hadywalied.session6.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.github.hadywalied.session6.R;
import com.github.hadywalied.session6.model.Repos;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MYVH> {
    private List<Repos> data = new ArrayList<>();
    public View.OnClickListener onClickListener;

    MyAdapter(List<Repos> list, View.OnClickListener listener) {
        onClickListener = listener;
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
        holder.bind(data.get(position), onClickListener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class MYVH extends RecyclerView.ViewHolder {

        TextView textView;
        CardView cardView;

        MYVH(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card);
            textView = itemView.findViewById(R.id.tv_RepoName);
        }

        void bind(Repos repos, View.OnClickListener onClickListener) {
            textView.setText(repos.getName());
            cardView.setOnClickListener(onClickListener);
        }
    }

}
