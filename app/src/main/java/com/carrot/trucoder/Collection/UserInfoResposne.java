package com.carrot.trucoder.Collection;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserInfoResposne {

    @SerializedName("status")
    private String status;

    @SerializedName("result")
    private List<UserInfoList> list;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<UserInfoList> getList() {
        return list;
    }

    public void setList(List<UserInfoList> list) {
        this.list = list;
    }
}
