package com.example.nearby_poi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StepDirectionModel {
    @SerializedName("distance")
    @Expose
    private DistanceDirectionModel distance;
    @SerializedName("duration")
    @Expose
    private DurationDirectionModel duration;
    @SerializedName("end_location")
    @Expose
    private LocationEndModel endLocation;
    @SerializedName("html_instructions")
    @Expose
    private String htmlInstructions;
    @SerializedName("polyline")
    @Expose
    private PolylineDirectionModel polyline;
    @SerializedName("start_location")
    @Expose
    private LocationStartModel startLocation;
    @SerializedName("travel_mode")
    @Expose
    private String travelMode;
    @SerializedName("maneuver")
    @Expose
    private String maneuver;

    public DistanceDirectionModel getDistance() {
        return distance;
    }

    public void setDistance(DistanceDirectionModel distance) {
        this.distance = distance;
    }

    public DurationDirectionModel getDuration() {
        return duration;
    }

    public void setDuration(DurationDirectionModel duration) {
        this.duration = duration;
    }

    public LocationEndModel getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(LocationEndModel endLocation) {
        this.endLocation = endLocation;
    }

    public String getHtmlInstructions() {
        return htmlInstructions;
    }

    public void setHtmlInstructions(String htmlInstructions) {
        this.htmlInstructions = htmlInstructions;
    }

    public PolylineDirectionModel getPolyline() {
        return polyline;
    }

    public void setPolyline(PolylineDirectionModel polyline) {
        this.polyline = polyline;
    }

    public LocationStartModel getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(LocationStartModel startLocation) {
        this.startLocation = startLocation;
    }

    public String getTravelMode() {
        return travelMode;
    }

    public void setTravelMode(String travelMode) {
        this.travelMode = travelMode;
    }

    public String getManeuver() {
        return maneuver;
    }

    public void setManeuver(String maneuver) {
        this.maneuver = maneuver;
    }
}
