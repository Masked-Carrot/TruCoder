package com.carrot.trucoder;

import com.carrot.trucoder.Collection.FriendList;
import com.carrot.trucoder.Collection.UserInfoList;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FriendResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("result")
    private List<FriendList> list;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<FriendList> getList() {
        return list;
    }

    public void setList(List<FriendList> list) {
        this.list = list;
    }
}

