package com.sph.location_user.global.exception;

public record ApiResponse(
    String errorCode,
    String message
) {

}
