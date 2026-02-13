package com.sph.location_user.user.presentation.dto.request;

public record UserCreateReq(
    String username,
    String password,
    String address,
    Double latitude,
    Double longitude
) {

}
