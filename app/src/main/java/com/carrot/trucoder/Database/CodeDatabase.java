package com.carrot.trucoder.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.carrot.trucoder.Collection.ContestList;
import com.carrot.trucoder.Collection.FriendList;
import com.carrot.trucoder.Collection.UserInfoList;
import com.carrot.trucoder.Collection.UserRatingList;

@Database(entities = {FriendList.class , UserInfoList.class , UserRatingList.class , ContestList.class} , version = 1)
public abstract class CodeDatabase extends RoomDatabase {

    private static CodeDatabase instance;
    public abstract FriendsDao friendsDao();
    public abstract UserInfoDao userInfoDao();
    public abstract UserRatingDao userRatingDao();
    public abstract ContestListDao contestListDao();

    public static synchronized CodeDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CodeDatabase.class, "Friend_Database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }


}
