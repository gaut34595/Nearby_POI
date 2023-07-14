package com.example.nearby_poi.Fragments;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nearby_poi.Activities.DirectionActivity;
import com.example.nearby_poi.Constants;
import com.example.nearby_poi.Dialog;
import com.example.nearby_poi.Model.GoogleApiResponseModel;
import com.example.nearby_poi.Model.GoogleModel;
import com.example.nearby_poi.NetworkRequest.RetrofitAPI;
import com.example.nearby_poi.NetworkRequest.RetrofitClient;
import com.example.nearby_poi.Model.PlacesModel;
import com.example.nearby_poi.R;
import com.example.nearby_poi.databinding.FragmentHomeBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements OnMapReadyCallback {
    FragmentHomeBinding binding;
    private GoogleMap map;
    int radius = 1500;
    private FusedLocationProviderClient client;
    SupportMapFragment mapFragment;
    LatLng latLng;
    Dialog dialog;
    private ProgressDialog progressDialog;
    private RetrofitAPI retrofitAPI;

    private PlacesModel selectedPmodel;
    private List<GoogleModel> googleModelList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        client= LocationServices.getFusedLocationProviderClient(getContext());
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.homeMap);
        mapFragment.getMapAsync((OnMapReadyCallback) this);
        dialog = new Dialog(requireActivity());
        retrofitAPI= RetrofitClient.getRetrofit().create(RetrofitAPI.class);

        binding.chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                if(checkedId!=-1){
                    PlacesModel placesModel = Constants.placeName.get(checkedId-1);
                    binding.edPlaceName.setText(placesModel.getName());
                    selectedPmodel= placesModel;
                    getPlaces(placesModel.getPlaceType());

                }
            }
        });

        googleModelList = new ArrayList<>();
        for (PlacesModel placesModel : Constants.placeName) {

            Chip chip = new Chip(requireContext());
            chip.setText(placesModel.getName());
            chip.setId(placesModel.getId());
            chip.setPadding(7, 8, 7, 8);
            chip.setTextColor(getResources().getColor(R.color.black, null));
            chip.setChipIcon(ResourcesCompat.getDrawable(getResources(), placesModel.getDrawableId(), null));
            chip.setCheckable(true);
           // chip.setBackgroundColor(getResources().getColor(R.color.black, null));
            chip.setCheckedIconVisible(false);

            binding.chipGroup.addView(chip);
        }

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        Dexter.withContext(getContext().getApplicationContext())
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        setUpGMaps();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    private void setUpGMaps() {

        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        map.setMyLocationEnabled(true);
        map.getUiSettings().setTiltGesturesEnabled(true);
        Task<Location> task = client.getLastLocation();

        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                mapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(@NonNull GoogleMap googleMap) {
                        latLng = new LatLng(location.getLatitude(),location.getLongitude());
                        Log.d(">>>>>>>>",latLng.latitude + "<<<" + latLng.longitude);
                        MarkerOptions markerOptions = new MarkerOptions().position(latLng)
                                .title("Your Location")
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                        map.addMarker(markerOptions);
                        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,17));
                    }
                });
            }
        });

    }



    private void getPlaces(String placeName){
        Log.d(">>>>>>>>insidegetplaces","1234");
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+ latLng.latitude
                +","+ latLng.longitude + "&radius=" + radius + "&type=" + placeName + "&key=" +
                getResources().getString(R.string.API_KEY);

        Log.d(">>>>",url);
        retrofitAPI.getNearByPlace(url).enqueue(new Callback<GoogleApiResponseModel>() {
            @Override
            public void onResponse(Call<GoogleApiResponseModel> call, Response<GoogleApiResponseModel> response) {
                if(response.errorBody()==null){

                    if(response.body()!=null){
                       if(response.body().getGoogleModelList()!=null && response.body().getGoogleModelList().size()>0){
                           googleModelList.clear();
                           map.clear();
                           Log.d(">>>>>size", String.valueOf(response.body().getGoogleModelList().size()));
                           for(int i=0;i<response.body().getGoogleModelList().size();i++){
                               googleModelList.add(response.body().getGoogleModelList().get(i));
                               addMarkers(response.body().getGoogleModelList().get(i),i);
                           }
                       }else{
                           map.clear();
                           googleModelList.clear();
                           radius+=1000;
                           getPlaces(placeName);
                       }
                    }
                }else{
                    Log.d(">>>>",response.errorBody().toString());
                    Toast.makeText(requireContext(), "Error Occured", Toast.LENGTH_SHORT).show();
                    dialog.stopLoading();
                }
            }

            @Override
            public void onFailure(Call<GoogleApiResponseModel> call, Throwable t) {
                    Log.d(">>>>>>",t.getMessage().toString());
                    dialog.stopLoading();
            }
        });

    }

    private void addMarkers(GoogleModel googleModel,int position) {
        MarkerOptions markerOptions = new MarkerOptions()
                .position(new LatLng(googleModel.getGeometry().getLocation().getLat(),googleModel.getGeometry().getLocation().getLng()))
                .title(googleModel.getName())
                .snippet(googleModel.getVicinity());
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        map.addMarker(markerOptions).setTag(position);
        MarkerOptions markerOptions1 = new MarkerOptions().position(latLng)
                .title("Your Location")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        map.addMarker(markerOptions1);

    }

    public void onDirectionClick(GoogleModel googleModel) {
        String placeId = googleModel.getPlaceId();
        Double lat = googleModel.getGeometry().getLocation().getLat();
        Double lng = googleModel.getGeometry().getLocation().getLng();

        Intent i = new Intent(requireContext(), DirectionActivity.class);
        i.putExtra("placeID",placeId);
        i.putExtra("latitude",lat);
        i.putExtra("longitude",lng);

        startActivity(i);
    }



}