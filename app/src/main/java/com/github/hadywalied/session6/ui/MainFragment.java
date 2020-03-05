package com.github.hadywalied.session6.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.hadywalied.session6.R;
import com.github.hadywalied.session6.model.Repos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainFragment extends Fragment {

    private List<Repos> repos = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.pullRefresh);


        MainViewModel mViewModel = new ViewModelProvider(Objects.requireNonNull(getActivity())).get(MainViewModel.class);


        mViewModel.liveData.observe(getViewLifecycleOwner(), (Observer<List<Repos>>) it -> {
            repos.clear();
            repos.addAll(it);
            MyAdapter myAdapter = new MyAdapter(repos);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(myAdapter);
            swipeRefreshLayout.setRefreshing(false);
        });

        mViewModel.error.observe(getViewLifecycleOwner(), (Observer<Boolean>) it -> {
            if (it) {
                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).setTitle("Error")
                        .setMessage("Error Due to Unknown Cause")
                        .setNeutralButton("dismiss", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .setCancelable(false).create();
                alertDialog.show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        mViewModel.getRepos();
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(mViewModel::getRepos);

    }

}
