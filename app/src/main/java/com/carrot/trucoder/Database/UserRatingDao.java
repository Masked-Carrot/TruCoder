package com.carrot.trucoder.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.carrot.trucoder.Collection.UserRatingList;

import java.util.List;

@Dao
public interface UserRatingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertRating(List<UserRatingList> userRatingLists);

    @Query("DELETE FROM USER_RATING_TABLE")
    void NukeRating();

    @Query("SELECT * FROM USER_RATING_TABLE")
    LiveData<List<UserRatingList>> getAllRating();

}
