package com.carrot.trucoder.Retrofit;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.carrot.trucoder.Collection.ContestList;
import com.carrot.trucoder.Collection.ContestListResponse;
import com.carrot.trucoder.Collection.FriendList;
import com.carrot.trucoder.Collection.UserInfoList;
import com.carrot.trucoder.Collection.UserInfoResposne;

import com.carrot.trucoder.Collection.UserRatingList;
import com.carrot.trucoder.Collection.UserRatingResponse;
import com.carrot.trucoder.Database.CodeDatabase;
import com.carrot.trucoder.Database.ContestListDao;
import com.carrot.trucoder.Database.FriendsDao;
import com.carrot.trucoder.Database.UserInfoDao;
import com.carrot.trucoder.Database.UserRatingDao;
import com.carrot.trucoder.FriendResponse;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CodeAPIRepository {
    private static final String TAG = "Repository";
    private static CodeAPIRepository instance;
    private LiveData<List<UserRatingList>> ratingListLiveData;
    private LiveData<List<FriendList>> allFriends;
    private LiveData<UserInfoList> infoListLiveData;
    private FriendsDao friendsDao;
    private  UserInfoDao userInfoDao;
    private  UserRatingDao userRatingDao;
    private CodeApi codeApi;
    private ContestListDao contestListDao;


    public CodeAPIRepository(Application application){
        codeApi = RetrofitService.cteateService(CodeApi.class);
        CodeDatabase database = CodeDatabase.getInstance(application);
        friendsDao = database.friendsDao();
        userInfoDao = database.userInfoDao();
        userRatingDao = database.userRatingDao();
        ratingListLiveData = userRatingDao.getAllRating();
        infoListLiveData = userInfoDao.getAllInfo();
        allFriends = friendsDao.getAllFriends();
        contestListDao = database.contestListDao();
    }


    public static CodeAPIRepository getInstance(Application application){
        if(instance != null)
            return instance;
        instance = new CodeAPIRepository(application);
        return instance;
    }

    public void getContestInfo(){
        codeApi.getAllConstest(false).enqueue(new Callback<ContestListResponse>() {
            @Override
            public void onResponse(Call<ContestListResponse> call, Response<ContestListResponse> response) {
                if(response.isSuccessful()){
                    new NukeContestList(contestListDao).execute();
                    new InsertContestList(contestListDao).execute(response.body().getList());
                }
            }

            @Override
            public void onFailure(Call<ContestListResponse> call, Throwable t) {

            }
        });
    }

    public void getUserInfo(String handle){
        codeApi.getUserInfo(handle).enqueue(new Callback<UserInfoResposne>() {
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

    public void getUserRating(String handle){
        codeApi.getUserRating(handle).enqueue(new Callback<UserRatingResponse>() {
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

    public void getNewFriendInfo(String handle){
        codeApi.getUserInfo(handle).enqueue(new Callback<UserInfoResposne>() {
            @Override
            public void onResponse(Call<UserInfoResposne> call, Response<UserInfoResposne> response) {
                FriendList friendList = new FriendList(response.body().getList().get(0).getHandle());
                friendList.setRank(response.body().getList().get(0).getRank());
                friendList.setRating(response.body().getList().get(0).getRating());
                friendList.setProfile("http://".concat(response.body().getList().get(0).getUrltoImage().substring(2)));
                new AddFriendBack(friendsDao).execute(friendList);
            }

            @Override
            public void onFailure(Call<UserInfoResposne> call, Throwable t) {
                Log.d(TAG , "response failed userfriendinfo");
            }
        });
    }

    public void getAllFriendInfo(String handle){
        codeApi.getFriendInfo(handle).enqueue(new Callback<FriendResponse>() {
            @Override
            public void onResponse(Call<FriendResponse> call, Response<FriendResponse> response) {
                if(response.isSuccessful()){
                    new updateFriendsList(friendsDao).execute(response.body().getList());
                }
            }

            @Override
            public void onFailure(Call<FriendResponse> call, Throwable t) {

            }
        });
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

    private static class AddFriendBack extends  AsyncTask<FriendList , Void ,Void >{
        private FriendsDao friendsDao;
        public AddFriendBack(FriendsDao friendsDao){
            this.friendsDao = friendsDao;
        }

        @Override
        protected Void doInBackground(FriendList... friendLists) {
            friendsDao.AddFriend(friendLists[0]);
            return null;
        }
    }

    private static class InsertContestList extends AsyncTask<List<ContestList> , Void , Void>{
        private ContestListDao contestListDao;
        public InsertContestList(ContestListDao contestListDao){
            this.contestListDao = contestListDao;
        }

        @Override
        protected Void doInBackground(List<ContestList>... lists) {
            contestListDao.InsertContest(lists[0]);
            return null;
        }
    }

    private static class NukeContestList extends AsyncTask<Void , Void , Void>{
        private ContestListDao contestListDao;
        public NukeContestList(ContestListDao contestListDao){
            this.contestListDao = contestListDao;
        }

        @Override
        protected Void doInBackground(Void...voids) {
            contestListDao.DeleteContesetTable();
            return null;
        }
    }

    private static class updateFriendsList extends AsyncTask<List<FriendList> , Void ,Void>{
        private FriendsDao friendsDao;
        public updateFriendsList(FriendsDao friendsDao){
            this.friendsDao = friendsDao;
        }

        @Override
        protected Void doInBackground(List<FriendList>... lists) {
            friendsDao.NukeFriend();
            friendsDao.InsertBatchFriend(lists[0]);
            return null;
        }
    }


}
