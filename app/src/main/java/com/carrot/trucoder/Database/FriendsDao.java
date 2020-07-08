package com.carrot.trucoder.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.carrot.trucoder.Collection.FriendList;

import java.util.List;

@Dao
public interface FriendsDao {

    @Insert
    void AddFriend(FriendList friendList);

    @Delete
    void KillFriend(FriendList friendList);

    @Insert()
    void InsertBatchFriend(List<FriendList> friendLists);

    @Query("DELETE FROM FRIEND_LIST")
    void NukeFriend();

    @Query("SELECT * FROM FRIEND_LIST")
    LiveData<List<FriendList>> getAllFriends();
}
