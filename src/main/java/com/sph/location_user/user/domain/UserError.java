package com.sph.location_user.user.domain;

import com.sph.location_user.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserError implements ErrorCode {

    DUPLICATE_USERNAME(HttpStatus.BAD_REQUEST, "U001", "이미 존재하는 아이디입니다."),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "U002", "조회하려는 아이디가 존재하지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
