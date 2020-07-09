package com.carrot.trucoder.Retrofit;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.carrot.trucoder.Collection.ContestList;
import com.carrot.trucoder.Collection.ContestListResponse;
import com.carrot.trucoder.Collection.FriendList;
import com.carrot.trucoder.Collection.FriendResponse;
import com.carrot.trucoder.Collection.UserInfoList;
import com.carrot.trucoder.Collection.UserInfoResposne;
import com.carrot.trucoder.Collection.UserRatingList;
import com.carrot.trucoder.Collection.UserRatingResponse;
import com.carrot.trucoder.Database.CodeDatabase;
import com.carrot.trucoder.Database.ContestListDao;
import com.carrot.trucoder.Database.FriendsDao;
import com.carrot.trucoder.Database.UserInfoDao;
import com.carrot.trucoder.Database.UserRatingDao;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CodeAPIRepository {
    private static final String TAG = "Repository";
    private static CodeAPIRepository instance;
    private static LiveData<List<FriendList>> friendlist;
    private LiveData<List<UserRatingList>> ratingListLiveData;
    private LiveData<UserInfoList> infoListLiveData;
    private  UserInfoDao userInfoDao;
    private FriendsDao friendsDao;
    private  UserRatingDao userRatingDao;
    private ContestListDao contestListDao;
    private MutableLiveData<List<ContestList>> contestList;
    private CodeApi codeApi;


    public CodeAPIRepository(Application application){
        codeApi = RetrofitService.cteateService(CodeApi.class);
        CodeDatabase database = CodeDatabase.getInstance(application);
        userInfoDao = database.userInfoDao();
        userRatingDao = database.userRatingDao();
        ratingListLiveData = userRatingDao.getAllRating();
        infoListLiveData = userInfoDao.getAllInfo();
        friendsDao = database.friendsDao();
        contestListDao = database.contestListDao();
        friendlist = friendsDao.getAllFriends();
        contestList = new MutableLiveData<>();
    }

    public static CodeAPIRepository getInstance(Application application){
        if(instance != null)
            return instance;
        instance = new CodeAPIRepository(application);
        return instance;
    }

    public void FetchUserInfo(String handle){
        codeApi.FetchUserInfo(handle).enqueue(new Callback<UserInfoResposne>() {
            @Override
            public void onResponse(Call<UserInfoResposne> call, Response<UserInfoResposne> response) {
                if (response.isSuccessful()){
                    new DeleteInfoBack(userInfoDao).execute();
                    new InsertInfoBack(userInfoDao).execute(response.body().getList().get(0));
                }
                else
                    Log.d(TAG , "response was not successful >> userInfo");
            }

            @Override
            public void onFailure(Call<UserInfoResposne> call, Throwable t) {
                Log.d(TAG , "JSON Response failed");
            }
        });

    }

    public void FetchUserRating(String handle){
        codeApi.FetchUserRating(handle).enqueue(new Callback<UserRatingResponse>() {
            @Override
            public void onResponse(Call<UserRatingResponse> call, Response<UserRatingResponse> response) {
                if(response.isSuccessful() && response.body().getRatingList() != null){
                    new InsertRatingBack(userRatingDao).execute(response.body().getRatingList());
                }
                else {
                    Log.d(TAG, "response was not successful >> userRating");
                }
            }

            @Override
            public void onFailure(Call<UserRatingResponse> call, Throwable t) {
                Log.d(TAG , "JSON Response failed");
            }
        });
    }

    public void FetchFriendInfo(String handles){
        codeApi.fetchFriendInfo(handles).enqueue(new Callback<FriendResponse>() {
            @Override
            public void onResponse(Call<FriendResponse> call, Response<FriendResponse> response) {
                if(response.isSuccessful()){
                    String pic = response.body().getList().get(0).getProfile();
                    response.body().getList().get(0).setProfile("http:".concat(pic));
                    new InsertFriendBack(friendsDao).execute(response.body().getList().get(0));
                }
            }

            @Override
            public void onFailure(Call<FriendResponse> call, Throwable t) {
            }
        });
    }

    public void UpdateFriendInfo(String handles){
        codeApi.fetchFriendInfo(handles).enqueue(new Callback<FriendResponse>() {
            @Override
            public void onResponse(Call<FriendResponse> call, Response<FriendResponse> response) {
                if(response.isSuccessful()){
                    new InsertBatchFriendBack(friendsDao).execute(response.body().getList());
                }
            }

            @Override
            public void onFailure(Call<FriendResponse> call, Throwable t) {

            }
        });
    }

    public LiveData<List<ContestList>> FetchContest(){
        codeApi.fetchContest(false).enqueue(new Callback<ContestListResponse>() {
            @Override
            public void onResponse(Call<ContestListResponse> call, Response<ContestListResponse> response) {
                if(response.isSuccessful())
                    contestList.setValue(response.body().getList());
            }

            @Override
            public void onFailure(Call<ContestListResponse> call, Throwable t) {

            }
        });
        return contestList;
    }


    public static class InsertFriendBack extends AsyncTask<FriendList , Void ,Void>{
        private FriendsDao friendsDao;
        public InsertFriendBack(FriendsDao friendsDao){
            this.friendsDao = friendsDao;
        }

        @Override
        protected Void doInBackground(FriendList...lists) {
            friendsDao.Insert1Friend(lists[0]);
            return null;
        }
    }

    private static class InsertBatchFriendBack extends AsyncTask<List<FriendList> , Void ,Void>{
        private FriendsDao friendsDao;
        public InsertBatchFriendBack(FriendsDao friendsDao){
            this.friendsDao = friendsDao;
        }

        @Override
        protected Void doInBackground(List<FriendList>... lists) {
            friendsDao.AddFriend(lists[0]);
            return null;
        }
    }

    private static class InsertRatingBack extends AsyncTask<List<UserRatingList> ,Void , Void> {
        private UserRatingDao userRatingDao;
        public InsertRatingBack(UserRatingDao userRatingDao){
            this.userRatingDao = userRatingDao;
        }

        @Override
        protected Void doInBackground(List<UserRatingList>... lists) {
            userRatingDao.InsertRating(lists[0]);
            return null;
        }
    }

    private static class DeleteRatingBack extends  AsyncTask<UserRatingList ,Void , Void>{
        private UserRatingDao userRatingDao;
        public DeleteRatingBack(UserRatingDao userRatingDao){
            this.userRatingDao = userRatingDao;
        }

        @Override
        protected Void doInBackground(UserRatingList... userRatingLists) {
            userRatingDao.NukeRating();
            return null;
        }
    }

    private static class InsertInfoBack extends AsyncTask<UserInfoList , Void , Void>{
        private UserInfoDao userInfoDao;
        public InsertInfoBack(UserInfoDao userInfoDao){
            this.userInfoDao = userInfoDao;
        }

        @Override
        protected Void doInBackground(UserInfoList... userInfoLists) {
            userInfoDao.InsertInfo(userInfoLists[0]);
            return null;
        }
    }

    private static class DeleteInfoBack extends AsyncTask<UserInfoList , Void , Void>{
        private UserInfoDao userInfoDao;
        public DeleteInfoBack(UserInfoDao userInfoDao){
            this.userInfoDao = userInfoDao;
        }

        @Override
        protected Void doInBackground(UserInfoList... userInfoLists) {
            userInfoDao.NukeInfo();
            return null;
        }
    }




    public LiveData<List<UserRatingList>> getRatingListLiveData() {
        return ratingListLiveData;
    }

    public LiveData<UserInfoList> getInfoListLiveData() {
        return infoListLiveData;
    }

    public LiveData<List<FriendList>> getFriendList(){
        return friendlist;
    }
}
