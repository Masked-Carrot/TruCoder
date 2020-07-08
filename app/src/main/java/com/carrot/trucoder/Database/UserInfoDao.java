package com.carrot.trucoder.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.carrot.trucoder.Collection.UserInfoList;

@Dao
public interface UserInfoDao {

    @Insert()
    void InsertInfo(UserInfoList userInfoList);

    @Query("DELETE FROM USER_INFO_TABLE")
    void NukeInfo();

    @Query("SELECT * FROM USER_INFO_TABLE")
    LiveData<UserInfoList> getAllInfo();


}
