package com.sph.location_user.user.infrastructure.repository;

public interface GeocodingClient {

    record GeoPoint(double latitude, double longitude) {


    }

    GeoPoint convert(String address);

}
