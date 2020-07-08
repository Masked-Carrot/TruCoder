package com.carrot.trucoder.Collection;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserRatingResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("result")
    private List<UserRatingList> ratingList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<UserRatingList> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<UserRatingList> ratingList) {
        this.ratingList = ratingList;
    }
}
