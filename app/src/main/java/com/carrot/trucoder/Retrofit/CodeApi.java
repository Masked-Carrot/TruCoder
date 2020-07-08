package com.carrot.trucoder.Retrofit;

import com.carrot.trucoder.Collection.ContestListResponse;
import com.carrot.trucoder.Collection.UserInfoResposne;
import com.carrot.trucoder.Collection.UserRatingResponse;
import com.carrot.trucoder.FriendResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CodeApi {
    @GET("user.info")
    Call<UserInfoResposne> getUserInfo(@Query("handles") String handle);

    @GET("user.rating")
    Call<UserRatingResponse> getUserRating(@Query("handle") String handle);

    @GET("contest.list")
    Call<ContestListResponse> getAllConstest(@Query("gym") boolean q);

    @GET("user.info")
    Call<FriendResponse> getFriendInfo(@Query("handle") String handle);
}