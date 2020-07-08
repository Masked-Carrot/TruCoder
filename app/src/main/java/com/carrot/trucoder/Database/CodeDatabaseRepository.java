package com.carrot.trucoder.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.carrot.trucoder.Collection.ContestList;
import com.carrot.trucoder.Collection.FriendList;
import com.carrot.trucoder.Collection.UserInfoList;
import com.carrot.trucoder.Collection.UserRatingList;

import java.util.List;

public class CodeDatabaseRepository {

    private FriendsDao friendsDao;
    private UserRatingDao userRatingDao;
    private UserInfoDao userInfoDao;
    private LiveData<List<FriendList>> allFriends;
    private LiveData<List<UserRatingList>> ratingListLiveData;
    private LiveData<UserInfoList> infoListLiveData;
    private ContestListDao contestListDao;
    private LiveData<List<ContestList>> contestLists;

    public CodeDatabaseRepository(Application application){
        CodeDatabase database = CodeDatabase.getInstance(application);
        friendsDao = database.friendsDao();
        userInfoDao = database.userInfoDao();
        userRatingDao = database.userRatingDao();
        ratingListLiveData = userRatingDao.getAllRating();
        infoListLiveData = userInfoDao.getAllInfo();
        allFriends = friendsDao.getAllFriends();
        contestListDao = database.contestListDao();
        contestLists  = contestListDao.getAllContest();
    }

    public LiveData<UserInfoList> getAllInfo(){
        return infoListLiveData;
    }

    public LiveData<List<UserRatingList>> getAllRating(){
        return ratingListLiveData;
    }

    public LiveData<List<FriendList>> getAllFriends(){
        return allFriends;
    }

    public LiveData<List<ContestList>> getContestLists() {
        return contestLists;
    }

    public void InsertFriend(FriendList friendList){
        new MakeFriendBack(friendsDao).execute(friendList);
    }

    public void DeleteFriends(FriendList friendList){
        new KillFriendBack(friendsDao).execute(friendList);
    }


    public void InsertRating(List<UserRatingList> userRatingList){
        new InsertRatingBack(userRatingDao).execute(userRatingList);
    }

    public void NukeInfo(){
        new DeleteInfoBack(userInfoDao).execute();
    }


    public void InsertInfo(UserInfoList userInfoList){
        new InsertInfoBack(userInfoDao).execute(userInfoList);
    }

    public void NukeRating(){
        new DeleteRatingBack(userRatingDao).execute();
    }

    public void InsertContest(List<ContestList> contestLists){
        new NukeContestList(contestListDao).execute();
        new InsertContestList(contestListDao).execute(contestLists).execute();
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


    private static class MakeFriendBack extends AsyncTask<FriendList ,Void ,Void> {

        private FriendsDao friendsDao;

        public MakeFriendBack(FriendsDao friendsDao) {
            this.friendsDao = friendsDao;
        }

        @Override
        protected Void doInBackground(FriendList...friendLists) {
            friendsDao.AddFriend(friendLists[0]);
            return null;
        }
    }

    private static class KillFriendBack extends AsyncTask<FriendList ,Void ,Void>{
        private FriendsDao friendsDao;
        public KillFriendBack(FriendsDao friendsDao){
            this.friendsDao = friendsDao;
        }

        @Override
        protected Void doInBackground(FriendList...friendLists) {
            friendsDao.KillFriend(friendLists[0]);
            return null;
        }
    }


    private static class InsertRatingBack extends  AsyncTask<List<UserRatingList> ,Void , Void>{
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

}
