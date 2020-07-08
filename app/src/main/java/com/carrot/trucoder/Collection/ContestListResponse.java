package com.carrot.trucoder.Collection;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContestListResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("result")
    private List<ContestList> list;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ContestList> getList() {
        return list;
    }

    public void setList(List<ContestList> list) {
        this.list = list;
    }
}
