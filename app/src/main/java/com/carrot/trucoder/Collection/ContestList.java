package com.carrot.trucoder.Collection;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "ContestList_Table")
public class ContestList {

    @SerializedName("id")
    @PrimaryKey
    private int id;
    @SerializedName("startTimeSeconds")
    private int startTime;
    @SerializedName("relativeTimeSeconds")
    private int relativetimestart;
    @SerializedName("durationSeconds")
    private int duration;
    @SerializedName("name")
    private String name;
    @SerializedName("phase")
    private String phase;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getRelativetimestart() {
        return relativetimestart;
    }

    public void setRelativetimestart(int relativetimestart) {
        this.relativetimestart = relativetimestart;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }
}
