package com.sph.location_user.user.presentation.dto.response;

import java.util.List;

public record UserDetailRes(
    Long id,
    String username,
    String address,
    List<NearbyUser> nearbyUsers
) {

    public record NearbyUser(
        Long id,
        String username,
        String address
    ){}
}
