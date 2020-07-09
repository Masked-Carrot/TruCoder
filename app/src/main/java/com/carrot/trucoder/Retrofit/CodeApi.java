package com.carrot.trucoder.Retrofit;

import com.carrot.trucoder.Collection.FriendResponse;
import com.carrot.trucoder.Collection.UserInfoResposne;
import com.carrot.trucoder.Collection.UserRatingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CodeApi {
    @GET("user.info")
    Call<UserInfoResposne> FetchUserInfo(@Query("handles") String handle);

    @GET("user.rating")
    Call<UserRatingResponse> FetchUserRating(@Query("handle") String handle);

    @GET("user.info")
    Call<FriendResponse> fetchFriendInfo(@Query("handles") String handles);

}