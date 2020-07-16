package com.example.madarsofttask.model.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.madarsofttask.model.UserModel;

import java.util.List;

@Dao
public interface RoomDao {

    @Insert
    void insertInvoice(UserModel userModel);

    @Query("SELECT * FROM UserData")
    LiveData<List<UserModel>> loadUserData();
}
