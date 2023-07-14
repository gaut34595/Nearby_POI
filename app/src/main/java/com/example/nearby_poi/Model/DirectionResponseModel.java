package com.example.nearby_poi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DirectionResponseModel {
    @SerializedName("routes")
    @Expose
    List<RouteDirectionModel> directionRouteModels;


    public List<RouteDirectionModel> getDirectionRouteModels() {
        return directionRouteModels;
    }

    public void setDirectionRouteModels(List<RouteDirectionModel> directionRouteModels) {
        this.directionRouteModels = directionRouteModels;
    }
}
