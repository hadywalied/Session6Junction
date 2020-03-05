package com.github.hadywalied.session6.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.hadywalied.session6.domain.Api;
import com.github.hadywalied.session6.model.Repos;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private Api api = new Api();

    private MutableLiveData<List<Repos>> mutableLiveData = new MutableLiveData<>();
    LiveData<? extends List<Repos>> liveData = mutableLiveData;
    private MutableLiveData<Boolean> _error = new MutableLiveData<>();
    LiveData<? extends Boolean> error = _error;


    void getRepos() {
        _error.setValue(false);
        api.getService().getUser("hadywalied").enqueue(new Callback<List<Repos>>() {
            @Override
            public void onResponse(Call<List<Repos>> call, Response<List<Repos>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    mutableLiveData.postValue(response.body());
                } else _error.setValue(true);
            }

            @Override
            public void onFailure(Call<List<Repos>> call, Throwable t) {
                _error.setValue(true);
            }
        });
    }

}
