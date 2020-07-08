package com.carrot.trucoder.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.carrot.trucoder.Collection.ContestList;

import java.util.List;

@Dao
public interface ContestListDao {

    @Insert
    void InsertContest(List<ContestList> contestLists);
    @Query("DELETE FROM ContestList_Table")
    void DeleteContesetTable();

    @Query("SELECT * FROM CONTESTLIST_TABLE")
    LiveData<List<ContestList>> getAllContest();
}
