package com.carrot.trucoder.ViewModel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.carrot.trucoder.Collection.ContestList;
import com.carrot.trucoder.Retrofit.CodeAPIRepository;

import java.util.List;

public class ApiRequestViewModel extends ViewModel {
    private CodeAPIRepository codeAPIRepository;

    public LiveData<List<ContestList>> getContestList(Application application){
        if(codeAPIRepository == null)
            codeAPIRepository = CodeAPIRepository.getInstance(application);
        return  codeAPIRepository.FetchContest();
    }


    public void getInfo(String handle , Application application){
        if(codeAPIRepository == null)
            codeAPIRepository = CodeAPIRepository.getInstance(application);
        codeAPIRepository.FetchUserInfo(handle);
    }

    public void getRating(String handle , Application application){
        if(codeAPIRepository == null)
            codeAPIRepository = CodeAPIRepository.getInstance(application);
        codeAPIRepository.FetchUserRating(handle);
    }

    public void  getNewFriend(String handle , Application application){
        if(codeAPIRepository == null)
            codeAPIRepository = CodeAPIRepository.getInstance(application);
        codeAPIRepository.FetchFriendInfo(handle);
    }


}