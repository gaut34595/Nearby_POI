package com.example.nearby_poi.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nearby_poi.Constants;
import com.example.nearby_poi.PlacesModel;
import com.example.nearby_poi.R;
import com.example.nearby_poi.databinding.FragmentHomeBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.chip.Chip;

public class HomeFragment extends Fragment implements OnMapReadyCallback{
    FragmentHomeBinding binding;
    private GoogleMap map;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.homeMap);
        mapFragment.getMapAsync((OnMapReadyCallback) this);
        for (PlacesModel placesModel : Constants.placeName) {

            Chip chip = new Chip(requireContext());
            chip.setText(placesModel.getName());
            chip.setId(placesModel.getId());
            chip.setPadding(7, 8, 7, 8);
            chip.setTextColor(getResources().getColor(R.color.black, null));
            chip.setChipIcon(ResourcesCompat.getDrawable(getResources(), placesModel.getDrawableId(), null));
            chip.setCheckable(true);
            chip.setCheckedIconVisible(false);

            binding.chipGroup.addView(chip);
        }

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map= googleMap;
    }
}