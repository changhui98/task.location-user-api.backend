package com.sph.location_user.user.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserCreateReq(

    @NotBlank
    @Pattern(
        regexp = "^[A-Za-z]+$",
        message = "username은 영문자만 입력 가능합니다."
    )
    String username,
    String password,
    String address,
    Double latitude,
    Double longitude
) {

}
