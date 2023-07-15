package com.example.nearby_poi.Model;

import com.example.nearby_poi.GoogleModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GoogleApiResponseModel {
    @SerializedName("results")
    @Expose
    private List<GoogleModel> googleModelList;

    public List<GoogleModel> getGoogleModelList() {
        return googleModelList;
    }

    public void setGoogleModelList(List<GoogleModel> googleModelList) {
        this.googleModelList = googleModelList;
    }
}
