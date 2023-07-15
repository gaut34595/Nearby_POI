
# Nearby_POI

This app allows you to discover nearby points of interest based on your location, providing you the additional details about the location using Google Maps API with the directions to that location from your current location.



## Features

Display's current location of user on Google Map.

Can Fetch nearby point of Interest based on user location in a predefined search radius.

Display POI as Markers.

Bottom sheet to show more information about the place like address, full name, rating.

Dialog on map to show the distance when clicked on marker.

Show directions to a selected point using Google Maps API.


## Installation
To use this application you need to change the API key inside the String.xml file .

Make sure you must use a new API Key and enable it for using Android Maps SDK.


## Usage/Examples

This application provides user the facility to locate and find the nearby points of interest and also tells about the time and distance from the user location.

Also this application provides you the path to reach at that point from your location in three different modes. Ex- bike,driving,walking.


## Architecture

MVVM Architecture is used to create this application.

Google Map SDK, Places & Direction API's

Navigation Component

Data Binding

Android (Java).

User Experience is kept at priority.

Different POJO classes/Model Classes are made to streamline data.


## Dependencies


## for Google maps

```java
# for google Maps

implementation 'com.google.android.gms:play-services-maps:18.1.0'

implementation 'com.google.maps.android:android-maps-utils:2.3.0'

implementation 'com.google.android.libraries.places:places:3.1.0'

implementation 'com.google.maps:google-maps-services:0.2.9'

implementation 'com.google.android.gms:play-services-location:21.0.1'

# for permission management

implementation 'com.karumi:dexter:6.2.3'

# for Image Upload

implementation 'com.github.bumptech.glide:glide:4.15.1'
    
# for network calls 

implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    
implementation 'com.squareup.retrofit2:converter-gson:2.7.1'
    
implementation 'com.squareup.retrofit2:converter-scalars:2.9.0'
```
