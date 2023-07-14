package com.example.nearby_poi;

import com.example.nearby_poi.Model.PlacesModel;

import java.util.ArrayList;
import java.util.Arrays;

public interface Constants {
    ArrayList<PlacesModel> placeName = new ArrayList<>(
            Arrays.asList(
                    new PlacesModel(1,R.drawable.baseline_restaurant_24,"Restaurant","restaurant"),
                    new PlacesModel(2,R.drawable.baseline_local_atm_24,"ATM","atm"),
                    new PlacesModel(3,R.drawable.baseline_local_gas_station_24,"Petrol","gas_station"),
                    new PlacesModel(4,R.drawable.baseline_local_grocery_store_24,"Groceries","supermarket"),
                    new PlacesModel(5,R.drawable.baseline_hotel_24,"Hotel","hotel"),
                    new PlacesModel(6,R.drawable.baseline_local_hospital_24,"Hospital","hospital"),
                    new PlacesModel(7,R.drawable.baseline_car_repair_24,"Car Services","car_wash"),
                    new PlacesModel(8,R.drawable.baseline_face_retouching_natural_24,"Grooming","beauty")
            )
    );
}
