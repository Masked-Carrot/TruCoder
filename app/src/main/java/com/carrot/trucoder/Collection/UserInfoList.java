package com.carrot.trucoder.Collection;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "USER_INFO_TABLE")
public class UserInfoList {



    @PrimaryKey(autoGenerate = true)
    private int primarykey;
    @SerializedName("country")
    private String country;
    @SerializedName("city")
    private String City;
    @SerializedName("organization")
    private String organization;
    @SerializedName("rating")
    private String rating;
    @SerializedName("handle")
    private String handle;
    @SerializedName("rank")
    private String rank;
    @SerializedName("lastOnlineTimeSeconds")
    private int lastOnlineSec;
    @SerializedName("titlePhoto")
    private String urltoImage;

    public String getUrltoImage() {
        return urltoImage;
    }

    public void setUrltoImage(String urltoImage) {
        this.urltoImage = urltoImage;
    }

    public int getPrimarykey() {
        return primarykey;
    }

    public void setPrimarykey(int primarykey) {
        this.primarykey = primarykey;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getHandle() {
        return handle;
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

    public int getLastOnlineSec() {
        return lastOnlineSec;
    }

    public void setLastOnlineSec(int lastOnlineSec) {
        this.lastOnlineSec = lastOnlineSec;
    }
}
