package com.example.nearby_poi.NetworkRequest;

import com.example.nearby_poi.Model.DirectionResponseModel;
import com.example.nearby_poi.Model.GoogleApiResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitAPI {

    @GET
    Call<GoogleApiResponseModel> getNearByPlace(@Url String url);

    @GET
    Call<DirectionResponseModel> getDirection(@Url String url);
}
