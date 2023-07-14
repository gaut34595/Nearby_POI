package com.example.nearby_poi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LegDirectionModel {
    @SerializedName("distance")
    @Expose
    private DistanceDirectionModel distance;
    @SerializedName("duration")
    @Expose
    private DistanceDirectionModel duration;
    @SerializedName("end_address")
    @Expose
    private String endAddress;
    @SerializedName("end_location")
    @Expose
    private LocationEndModel endLocation;
    @SerializedName("start_address")
    @Expose
    private String startAddress;
    @SerializedName("start_location")
    @Expose
    private LocationStartModel startLocation;
    @SerializedName("steps")
    @Expose
    private List<StepDirectionModel> steps = null;


    public DistanceDirectionModel getDistance() {
        return distance;
    }

    public void setDistance(DistanceDirectionModel distance) {
        this.distance = distance;
    }

    public DistanceDirectionModel getDuration() {
        return duration;
    }

    public void setDuration(DistanceDirectionModel duration) {
        this.duration = duration;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public LocationEndModel getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(LocationEndModel endLocation) {
        this.endLocation = endLocation;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public LocationStartModel getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(LocationStartModel startLocation) {
        this.startLocation = startLocation;
    }

    public List<StepDirectionModel> getSteps() {
        return steps;
    }

    public void setSteps(List<StepDirectionModel> steps) {
        this.steps = steps;
    }
}
