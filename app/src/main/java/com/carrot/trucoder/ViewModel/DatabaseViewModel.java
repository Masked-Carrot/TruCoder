package com.carrot.trucoder.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.carrot.trucoder.Collection.ContestList;
import com.carrot.trucoder.Collection.FriendList;
import com.carrot.trucoder.Collection.UserInfoList;
import com.carrot.trucoder.Collection.UserRatingList;
import com.carrot.trucoder.Retrofit.CodeAPIRepository;

import java.util.List;

public class DatabaseViewModel extends AndroidViewModel {

    LiveData<List<ContestList>> contestListLiveData;
    private LiveData<UserInfoList> userInfoListLiveData;
    private LiveData<List<UserRatingList>> userRatingListLiveData;
    private LiveData<List<FriendList>> friendListLive;

    public DatabaseViewModel(@NonNull Application application) {
        super(application);
        CodeAPIRepository repository = new CodeAPIRepository(application);
        userInfoListLiveData = repository.getInfoListLiveData();
        userRatingListLiveData = repository.getRatingListLiveData();
        friendListLive = repository.getFriendList();
    }

    public LiveData<UserInfoList> getUserInfoListLiveData() {
        return userInfoListLiveData;
    }

    public LiveData<List<UserRatingList>> getUserRatingListLiveData() {
        return userRatingListLiveData;
    }

    public LiveData<List<FriendList>> getFriendListLive() {
        return friendListLive;
    }

    public LiveData<List<ContestList>> getContestListLiveData() {
        return contestListLiveData;
    }
}