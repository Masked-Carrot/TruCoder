package com.carrot.trucoder.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.carrot.trucoder.Collection.ContestList;
import com.carrot.trucoder.Collection.FriendList;
import com.carrot.trucoder.Collection.UserInfoList;
import com.carrot.trucoder.Collection.UserRatingList;
import com.carrot.trucoder.Database.CodeDatabaseRepository;

import java.util.List;

public class DatabaseViewModel extends AndroidViewModel {

    LiveData<List<ContestList>> contestListLiveData;
    private LiveData<List<FriendList>> listMutableLiveData;
    private LiveData<UserInfoList> userInfoListLiveData;
    private LiveData<List<UserRatingList>> userRatingListLiveData;
    private CodeDatabaseRepository repository;

    public DatabaseViewModel(@NonNull Application application) {
        super(application);
        repository = new CodeDatabaseRepository(application);
        listMutableLiveData = repository.getAllFriends();
        userInfoListLiveData = repository.getAllInfo();
        userRatingListLiveData = repository.getAllRating();
        contestListLiveData = repository.getContestLists();
    }

    public void InsertRating(List<UserRatingList> lists){
        repository.InsertRating(lists);
    }

    public void NukeRating(){
        repository.NukeRating();
    }

    public void InsertInfo(UserInfoList userInfoList){
        repository.InsertInfo(userInfoList);
    }

    public void NukeInfo(){
        repository.NukeInfo();
    }

    public void InsertFriends(FriendList friendList){
        repository.InsertFriend(friendList);
    }

    public void DeleteFriends(FriendList friendList){
        repository.DeleteFriends(friendList);
    }

    public LiveData<List<FriendList>> getAllFriends(){
        return listMutableLiveData;
    }

    public LiveData<UserInfoList> getUserInfoListLiveData() {
        return userInfoListLiveData;
    }

    public LiveData<List<UserRatingList>> getUserRatingListLiveData() {
        return userRatingListLiveData;
    }

    public LiveData<List<ContestList>> getContestListLiveData() {
        return contestListLiveData;
    }
}