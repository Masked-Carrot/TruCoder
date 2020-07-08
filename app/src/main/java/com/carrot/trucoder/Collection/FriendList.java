package com.carrot.trucoder.Collection;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Friend_List")
public class FriendList {

    public FriendList(String handle){
        this.handle = handle;
    }

    @PrimaryKey(autoGenerate =  true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String handle;
    public String getHandle() {
        return handle;
    }

    private String rank;
    private String rating;
    private String profile;

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
