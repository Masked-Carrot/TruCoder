package com.carrot.trucoder.Collection;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Friend_List")
public class FriendList {

    public FriendList(String handle){
        this.handle = handle;
    }

    @PrimaryKey(autoGenerate =  true)
    private int id;

    private String handle;


    private String rank;
    private String rating;
    @SerializedName("titlePhoto")
    private String profile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getProfile() {
        return profile;
    }

    public String getHandle() {
        return handle;
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
