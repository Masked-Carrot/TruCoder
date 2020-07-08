package com.carrot.trucoder.ViewModel;

import android.app.Application;
import androidx.lifecycle.ViewModel;
import com.carrot.trucoder.Retrofit.CodeAPIRepository;

public class ApiRequestViewModel extends ViewModel {
    private CodeAPIRepository codeAPIRepository;


    public void getInfo(String handle , Application application){
        if(codeAPIRepository == null)
            codeAPIRepository = CodeAPIRepository.getInstance(application);
        codeAPIRepository.getUserInfo(handle);
    }

    public void getRating(String handle , Application application){
        if(codeAPIRepository == null)
            codeAPIRepository = CodeAPIRepository.getInstance(application);
        codeAPIRepository.getUserRating(handle);
    }

    public void  getNewFriend(String handle , Application application){
        if(codeAPIRepository == null)
            codeAPIRepository = CodeAPIRepository.getInstance(application);
        codeAPIRepository.getNewFriendInfo(handle);
    }

    public void getContest(Application application){
        if(codeAPIRepository == null)
            codeAPIRepository = CodeAPIRepository.getInstance(application);
        codeAPIRepository.getContestInfo();
    }

    public void getListofFriends(String handle ,Application application){
        if(codeAPIRepository == null)
            codeAPIRepository = CodeAPIRepository.getInstance(application);
        codeAPIRepository.getAllFriendInfo(handle);
    }

}