package com.example.nearby_poi.Adapters;

import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nearby_poi.databinding.LayoutInformationBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.SphericalUtil;

public class LayoutAdapter implements GoogleMap.InfoWindowAdapter {

    private LayoutInformationBinding binding;
    private Location location;
    private Context context;

    public LayoutAdapter(Location location, Context context) {
        this.location = location;
        this.context = context;
        binding= LayoutInformationBinding.inflate(LayoutInflater.from(context),null,false);
    }

    @Nullable
    @Override
    public View getInfoContents(@NonNull Marker marker) {
        return null;
    }

    @Nullable
    @Override
    public View getInfoWindow(@NonNull Marker marker) {
        binding.locationName.setText(marker.getTitle());

        float distance  = (float) SphericalUtil.computeDistanceBetween(new LatLng(location.getLatitude(),location.getLongitude()),marker.getPosition());
        if(distance>1000){
            float kilometers = distance/1000;
            binding.locDistance.setText(kilometers + " Km");
        }else{
            binding.locDistance.setText(distance + "m");
        }
        float speed = location.getSpeed();
        if(speed>0){
            float time = distance/speed;
            binding.locTime.setText(time + "sec");
        }else{
            binding.locTime.setText("NA");
        }
        return binding.getRoot();
    }
}
