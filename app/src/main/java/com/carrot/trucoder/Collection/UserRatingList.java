package com.carrot.trucoder.Collection;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "USER_RATING_TABLE")
public class UserRatingList {

    @PrimaryKey(autoGenerate = false)
    @SerializedName("contestId")
    int primarykey;
    @SerializedName("contestName")
    private String constestName;
    @SerializedName("newRating")
    private int newRating;
    @SerializedName("oldRating")
    private int oldRating;
    @SerializedName("rank")
    private String rank;

    public int getNewRating() {
        return newRating;
    }

    public int getOldRating() {
        return oldRating;
    }

    public void setOldRating(int oldRating) {
        this.oldRating = oldRating;
    }

    public void setNewRating(int newRating) {
        this.newRating = newRating;
    }

    public String getConstestName() {
        return constestName;
    }

    public void setConstestName(String constestName) {
        this.constestName = constestName;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getPrimarykey() {
        return primarykey;
    }

    public void setPrimarykey(int primarykey) {
        this.primarykey = primarykey;
    }
}
