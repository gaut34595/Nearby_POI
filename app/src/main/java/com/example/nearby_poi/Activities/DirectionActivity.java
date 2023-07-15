package com.example.nearby_poi.Activities;

import static com.google.maps.internal.PolylineEncoding.decode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.nearby_poi.Adapters.AdapterV;
import com.example.nearby_poi.Dialog;
import com.example.nearby_poi.Model.DirectionResponseModel;
import com.example.nearby_poi.Model.LegDirectionModel;
import com.example.nearby_poi.Model.RouteDirectionModel;
import com.example.nearby_poi.Model.StepDirectionModel;
import com.example.nearby_poi.NetworkRequest.RetrofitAPI;
import com.example.nearby_poi.NetworkRequest.RetrofitClient;
import com.example.nearby_poi.R;
import com.example.nearby_poi.databinding.ActivityDirectionBinding;
import com.example.nearby_poi.databinding.BottomBannerLayoutBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.chip.ChipGroup;
import com.google.gson.Gson;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DirectionActivity extends AppCompatActivity implements OnMapReadyCallback {

    private ActivityDirectionBinding binding;
    private BottomSheetBehavior<View> bottomSheetBehavior;
    private GoogleMap map;
    private BottomBannerLayoutBinding bottomBannerLayoutBinding;

    private boolean isTrafficEnabled;
    private Dialog dialog;
    private Location currLoc;
    private Double endLat, endLng;
    private String placeID;
    private RetrofitAPI retrofitAPI;
    private FusedLocationProviderClient client;

    private AdapterV adapter;
    LatLng ll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDirectionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        endLat = getIntent().getDoubleExtra("latitude", 0.0);
        endLng = getIntent().getDoubleExtra("longitude", 0.0);
        Log.d(">>>>>endlatlng",endLat.toString()+ ","+endLng.toString());

        ll = new LatLng(endLat,endLng);
        placeID = getIntent().getStringExtra("placeID");

       getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dialog = new Dialog(this);
        retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        bottomBannerLayoutBinding = binding.bottomSheet;
        bottomSheetBehavior = BottomSheetBehavior.from(bottomBannerLayoutBinding.getRoot());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        adapter = new AdapterV();

        bottomBannerLayoutBinding.stepRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bottomBannerLayoutBinding.stepRecyclerView.setAdapter(adapter);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_direction);

        mapFragment.getMapAsync((OnMapReadyCallback) this);

        binding.trafficBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isTrafficEnabled) {
                    if (map != null) {
                        map.setTrafficEnabled(false);
                        isTrafficEnabled = false;
                    }
                } else {
                    if (map != null) {
                        map.setTrafficEnabled(true);
                        isTrafficEnabled = true;
                    }
                }
            }
        });
        binding.travelModes.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                if (checkedId != -1) {

                    if (checkedId == R.id.btnDriving) {
                        getDirection("driving");
                    }
                    if (checkedId == R.id.btnbike) {
                        getDirection("bike");
                    }
                    if (checkedId == R.id.btnWalking) {
                        getDirection("walking");
                    }

                }
            }
        });


    }

    private void getDirection(String mode) {

        String url = "https://maps.googleapis.com/maps/api/directions/json?" +
                "origin=" + currLoc.getLatitude() + "," + currLoc.getLongitude() +
                "&destination=" + endLat + "," + endLng +
                "&mode=" + mode + "&key=" + getResources().getString(R.string.API_KEY);
        Log.d(">>>>>>",url);

        retrofitAPI.getDirection(url).enqueue(new Callback<DirectionResponseModel>() {
            @Override
            public void onResponse(Call<DirectionResponseModel> call, Response<DirectionResponseModel> response) {

                Gson gson = new Gson();
                String gresponse = gson.toJson(response.body());
                Log.d(">>>>>>", "RES" + gresponse);

                if (response.errorBody() == null) {
                    if (response.body() != null) {
                        clearUI();

                        if (response.body().getDirectionRouteModels().size() > 0) {
                            RouteDirectionModel routeDirectionModel = response.body().getDirectionRouteModels().get(0);

                            getSupportActionBar().setTitle(routeDirectionModel.getSummary());
                            Log.d(">>>>>>>insideinside",routeDirectionModel.getSummary());
                            LegDirectionModel legDirectionModel = routeDirectionModel.getLegs().get(0);
                            binding.originloc.setText(legDirectionModel.getStartAddress());
                            binding.destloc.setText(legDirectionModel.getEndAddress());

                            bottomBannerLayoutBinding.timesheet.setText(legDirectionModel.getDuration().getText());
                            bottomBannerLayoutBinding.distancesheet.setText(legDirectionModel.getDistance().getText());

                            map.addMarker(new MarkerOptions()
                                    .position(new LatLng(legDirectionModel.getStartLocation().getLat(), legDirectionModel.getStartLocation().getLng()))
                                    .title("Start Location"));
                            Log.d(">>>>>startloc",legDirectionModel.getStartLocation().getLat()+","+ legDirectionModel.getStartLocation().getLng());
                            map.addMarker(new MarkerOptions()
                                    .position(new LatLng(legDirectionModel.getEndLocation().getLat(), legDirectionModel.getEndLocation().getLng()))
                                    .title("End Location"));
                            Log.d(">>>>>end",legDirectionModel.getEndLocation().getLat()+","+ legDirectionModel.getEndLocation().getLng());
                            adapter.setDirectionStepModels(legDirectionModel.getSteps());
                            List<LatLng> step = new ArrayList<>();

                            PolylineOptions options = new PolylineOptions()
                                    .width(30)
                                    .color(Color.BLUE)
                                    .geodesic(true)
                                    .clickable(true)
                                    .visible(true);

                            List<PatternItem> patternItems;
                            if (mode.equals("walking")) {
                                patternItems = Arrays.asList(
                                        new Dot(), new Gap(12));
                                options.jointType(JointType.ROUND);
                            } else {
                                patternItems = Arrays.asList(
                                        new Dash(30));
                            }
                            options.pattern(patternItems);


                            for (StepDirectionModel stepDirectionModel : legDirectionModel.getSteps()) {
                                List<com.google.maps.model.LatLng> decodedLatLng = decode(stepDirectionModel.getPolyline().getPoints());
                                for (com.google.maps.model.LatLng latLng : decodedLatLng) {
                                    step.add(new LatLng(latLng.lat, latLng.lng));
                                }
                            }
                            options.addAll(step);
                            Polyline polyline = map.addPolyline(options);
                            LatLng end_loc = new LatLng(legDirectionModel.getEndLocation().getLat(), legDirectionModel.getEndLocation().getLng());
                            LatLng start_loc = new LatLng(legDirectionModel.getStartLocation().getLat(), legDirectionModel.getStartLocation().getLng());
                            Log.d(">>>startlocm",start_loc.toString());
                            Log.d(">>>endlocm",end_loc.toString());
                           map.animateCamera(CameraUpdateFactory.newLatLngBounds(new LatLngBounds(start_loc,start_loc),17));


                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<DirectionResponseModel> call, Throwable t) {

            }
        });
    }

    private void clearUI() {

        map.clear();
        binding.originloc.setText("");
        binding.destloc.setText("");
        getSupportActionBar().setTitle("");
        bottomBannerLayoutBinding.distancesheet.setText("");
        bottomBannerLayoutBinding.timesheet.setText("");


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;

        Dexter.withContext(getApplicationContext())
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
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.getUiSettings().setCompassEnabled(false);

        client = LocationServices.getFusedLocationProviderClient(this);
        client.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                currLoc=location;
                getDirection("driving");
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if(bottomSheetBehavior.getState()== BottomSheetBehavior.STATE_EXPANDED){
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }else{
            super.onBackPressed();
        }
        super.onBackPressed();
    }

}