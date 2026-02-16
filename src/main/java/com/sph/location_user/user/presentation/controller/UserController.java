package com.sph.location_user.user.presentation.controller;

import com.sph.location_user.user.application.service.UserService;
import com.sph.location_user.user.presentation.dto.request.UserCreateReq;
import com.sph.location_user.user.presentation.dto.response.UserCreateRes;
import com.sph.location_user.user.presentation.dto.response.UserDetailRes;
import com.sph.location_user.user.presentation.dto.response.UserSearchByAddressRes;
import com.sph.location_user.user.presentation.dto.response.UserSearchByCoordinateRes;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/test")
    public String test() {
        return "Test Success";
    }

    @PostMapping
    public ResponseEntity<UserCreateRes> createUser(
        @RequestBody @Valid UserCreateReq req
    ) {
        UserCreateRes res = userService.createUser(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDetailRes> getUserDetail(
        @PathVariable String username
    ) {
        UserDetailRes res = userService.getUserDetail(username);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/search")
    public ResponseEntity<UserSearchByAddressRes> searchUsersByAddress(
        @RequestParam String address
    ) {
        UserSearchByAddressRes res = userService.searchUsersByAddress(address);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/search-by-coordinate")
    public ResponseEntity<UserSearchByCoordinateRes> searchUsersByCoordinate(
        @RequestParam double latitude,
        @RequestParam double longitude
    ) {
        UserSearchByCoordinateRes res = userService.searchUsersByCoordinate(latitude, longitude);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
