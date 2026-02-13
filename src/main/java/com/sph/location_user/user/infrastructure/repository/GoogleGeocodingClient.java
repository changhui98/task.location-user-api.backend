package com.sph.location_user.user.infrastructure.repository;

import org.springframework.stereotype.Component;

@Component
public class GoogleGeocodingClient implements GeocodingClient {

    @Override
    public GeoPoint convert(String address) {

        return new GeoPoint(37.0, 127.0);
    }
}
