package com.example.proximity_notifier.models;

import android.net.Uri;

import com.google.android.gms.maps.model.LatLng;

public class placeInfo {
    private String name;
    private String address;
    private String phoneNumber;
    private String id;
    private Uri websiteUri;
    private float rating;
    private LatLng latLng;

    public placeInfo(String name, String address, String phneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phneNumber;
        this.id = id;
        this.websiteUri = websiteUri;
        this.rating = rating;
        this.latLng = latLng;
    }
    public placeInfo(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhneNumber(String phneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Uri getWebsiteUri() {
        return websiteUri;
    }

    public void setWebsiteUri(Uri websiteUri) {
        this.websiteUri = websiteUri;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    @Override
    public String toString() {
        return "placeInfo{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", id='" + id + '\'' +
                ", websiteUri=" + websiteUri +
                ", rating=" + rating +
                ", latLng=" + latLng +
                '}';
    }
}
