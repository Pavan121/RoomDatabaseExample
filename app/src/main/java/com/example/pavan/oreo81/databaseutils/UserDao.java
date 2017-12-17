package com.example.pavan.oreo81.databaseutils;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by pavan on 16/11/17.
 */

@Dao
public interface UserDao {

    @Query("Select * from user ORDER by first_name COLLATE NOCASE desc")
    List<User> getAll();

    @Query("Select * from user where uid IN (:userIds)")
    List<User> loadAllByIds(String [] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND "
            + "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM user WHERE first_name LIKE :search "
            + "OR last_name LIKE :search")
    public List<User> findUserWithName(String search);

    @Query("Select * from user ORDER by first_name COLLATE NOCASE desc")
    LiveData<List<User>> getAllLive();
}
