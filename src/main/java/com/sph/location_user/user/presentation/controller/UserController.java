package com.sph.location_user.user.presentation.controller;

import com.sph.location_user.user.application.service.UserService;
import com.sph.location_user.user.presentation.dto.request.UserCreateReq;
import com.sph.location_user.user.presentation.dto.response.UserCreateRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/test")
    public String test() {
        return "Test Success";
    }

    @PostMapping
    public ResponseEntity<UserCreateRes> createUser(
        @RequestBody UserCreateReq req
    ) {
        UserCreateRes res = userService.createUser(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }


}
