package com.example.nearby_poi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RouteDirectionModel {
    @SerializedName("legs")
    @Expose
    private List<LegDirectionModel> legs;

    @SerializedName("overview_polyline")
    @Expose
    private PolylineDirectionModel polylineModel;

    @SerializedName("summary")
    @Expose
    private String summary;

    public List<LegDirectionModel> getLegs() {
        return legs;
    }

    public void setLegs(List<LegDirectionModel> legs) {
        this.legs = legs;
    }

    public PolylineDirectionModel getPolylineModel() {
        return polylineModel;
    }

    public void setPolylineModel(PolylineDirectionModel polylineModel) {
        this.polylineModel = polylineModel;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
