package com.example.madarsofttask.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.madarsofttask.model.UserModel;
import com.example.madarsofttask.model.db.AppDatabase;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<UserModel>> listLiveData;

    public MainViewModel(@NonNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getsInstance(this.getApplication());
        listLiveData = appDatabase.UserDao().loadUserData();
    }

    public LiveData<List<UserModel>> getUserData() {
        return listLiveData;
    }
}
