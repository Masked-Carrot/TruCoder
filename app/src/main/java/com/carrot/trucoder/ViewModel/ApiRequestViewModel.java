package com.carrot.trucoder.ViewModel;

import android.app.Application;
import androidx.lifecycle.ViewModel;
import com.carrot.trucoder.Retrofit.CodeAPIRepository;

public class ApiRequestViewModel extends ViewModel {
    private CodeAPIRepository codeAPIRepository;


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