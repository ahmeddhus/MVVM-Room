package com.example.madarsofttask.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.madarsofttask.R;
import com.example.madarsofttask.model.UserModel;
import com.example.madarsofttask.model.db.AppDatabase;
import com.example.madarsofttask.view.adapters.UsersAdapter;
import com.example.madarsofttask.viewModel.MainViewModel;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UsersListActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_users)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        init();
    }

    private void init() {
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //Room DB
        AppDatabase appDatabase = AppDatabase.getsInstance(UsersListActivity.this);
        setupViewModel();
    }

    private void setupViewModel() {
        MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mainViewModel.getUserData().observe(this, userModels -> {
            if (!userModels.isEmpty())
                setRecyclerView(userModels);
            else
                Toast.makeText(UsersListActivity.this, "No Users!", Toast.LENGTH_LONG).show();
        });
    }

    private void setRecyclerView(List<UserModel> models){
        UsersAdapter usersAdapter = new UsersAdapter(models);
        recyclerView.setAdapter(usersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}