package com.sph.location_user.user.presentation.dto.response;

public record UserCreateRes(
    Long id,
    String username,
    String address,
    Double latitude,
    Double longitude
) {

}
