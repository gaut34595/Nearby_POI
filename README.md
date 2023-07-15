
# Nearby_POI

This app allows you to discover nearby points of interest based on your location, providing you the additional details about the location using Google Maps API with the directions to that location from your current location.

## Demo Video

Demo Video of the application 


https://github.com/gaut34595/Nearby_POI/assets/76027634/0db6d9f4-06b4-43c3-b79d-ee4150fb8b42



## Features

Display's current location of the user on Google Maps.

Can Fetch nearby points of Interest based on user location in a predefined search radius.

Display POI as Markers.

The bottom sheet shows more information about the place like address, full name, and rating.

Dialog on the map to show the distance when clicked on marker.

Show directions to a selected point using Google Maps API.


## Installation
To use this application you need to change the API key inside the String.xml file.

Make sure you must use a new API Key and enable it for using Android Maps SDK.


## Usage/Examples

This application provides users the facility to locate and find nearby points of interest and also tells about the time and distance from the user's location.

Also, this application provides you the path to reach that point from your location in three different modes. Ex- bike, driving, walking.


## Architecture

MVVM Architecture is used to create this application.

Google Map SDK, Places & Direction APIs

Navigation Component

Data Binding

Android (Java).

User Experience is kept as a priority.

Different POJO classes/Model Classes are made to streamline data.


## Dependencies


## for Google maps

```java
# for Google Maps

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
## Screen Shots
![WhatsApp Image 2023-07-15 at 9 44 51 PM (2)](https://github.com/gaut34595/Nearby_POI/assets/76027634/fdc74456-f6fd-4cd7-a55e-2fc7ff667931)
![WhatsApp Image 2023-07-15 at 9 44 51 PM](https://github.com/gaut34595/Nearby_POI/assets/76027634/f9d080fb-f807-49dc-b7c1-b9700e43bed9)
![WhatsApp Image 2023-07-15 at 9 44 51 PM (1)](https://github.com/gaut34595/Nearby_POI/assets/76027634/e6a3013b-149e-43f9-8baf-404648847ca9)



