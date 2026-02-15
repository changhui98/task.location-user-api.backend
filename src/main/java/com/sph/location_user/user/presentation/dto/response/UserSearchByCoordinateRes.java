package com.sph.location_user.user.presentation.dto.response;

import java.util.List;

public record UserSearchByCoordinateRes(
    double latitude,
    double longitude,
    List<UserSummary> users
) {

    public record UserSummary(
        Long id,
        String username,
        String address
    ){}
}
