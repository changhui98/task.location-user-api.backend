package com.sph.location_user.user.application.service;

import com.sph.location_user.user.domain.entity.User;
import com.sph.location_user.user.domain.repository.UserRepository;
import com.sph.location_user.user.infrastructure.repository.GeocodingClient;
import com.sph.location_user.user.presentation.dto.request.UserCreateReq;
import com.sph.location_user.user.presentation.dto.response.UserCreateRes;
import com.sph.location_user.user.presentation.dto.response.UserDetailRes;
import com.sph.location_user.user.presentation.dto.response.UserSearchByAddressRes;
import com.sph.location_user.user.presentation.dto.response.UserSearchByCoordinateRes;
import com.sph.location_user.user.presentation.dto.response.UsersRes;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final GeocodingClient geocodingClient;
    private final GeometryFactory geometryFactory = new GeometryFactory();

    @Transactional(readOnly = true)
    public List<UsersRes> getAllUsers() {
        List<UsersRes> res = userRepository.findAll()
            .stream()
            .map(user -> new UsersRes(
                user.getId(),
                user.getUsername(),
                user.getAddress()
            )).toList();
        return res;
    }

    @Transactional
    public UserCreateRes createUser(UserCreateReq req) {

        if (userRepository.existsByUsername(req.username())) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }

        Point point;

        if (req.address() != null) {

            GeocodingClient.GeoPoint geo = geocodingClient.convert(req.address());

            point = geometryFactory.createPoint(
                new Coordinate(geo.longitude(), geo.latitude())
            );
            point.setSRID(4326);
        } else if (req.latitude() != null && req.longitude() != null) {
            point = geometryFactory.createPoint(
                new Coordinate(req.longitude(), req.latitude())
            );
            point.setSRID(4326);
        }
        else {
            throw new IllegalArgumentException("주소 또는 위/경도 중 하나는 반드시 입력해야 합니다.");
        }

        User user = User.of(
            req.username(),
            req.password(),
            req.address(),
            point
        );

        User saveUser = userRepository.save(user);

        return null;
    }

    @Transactional(readOnly = true)
    public UserDetailRes getUserDetail(String username) {

        User user = userRepository.findByUsername(username).orElseThrow(
            () -> new IllegalArgumentException("사용자를 찾을 수 없습니다.")
        );

        List<User> nearbyUsers = userRepository.findNearbyUsers(username);

        return new UserDetailRes(
            user.getId(),
            user.getUsername(),
            user.getAddress(),
            nearbyUsers.stream()
                .map(u -> new UserDetailRes.NearbyUser(
                    u.getId(),
                    u.getUsername(),
                    u.getAddress()
                )).toList()
        );
    }

    @Transactional(readOnly = true)
    public UserSearchByAddressRes searchUsersByAddress(String address) {

        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("주소는 필수입니다.");
        }

        GeocodingClient.GeoPoint geo = geocodingClient.convert(address);

        double latitude = geo.latitude();
        double longitude = geo.longitude();

        List<User> users = userRepository.findUsersWithin3km(latitude, longitude);

        return new UserSearchByAddressRes(
            address,
            latitude,
            longitude,
            users.stream()
                .map(u -> new UserSearchByAddressRes.UserSummary(
                    u.getId(),
                    u.getUsername(),
                    u.getAddress()
                )).toList()
        );
    }

    @Transactional(readOnly = true)
    public UserSearchByCoordinateRes searchUsersByCoordinate(double latitude, double longitude) {

        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("유효하지 않은 위도입니다.");
        }

        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("유효하지 않은 경도입니다.");
        }

        List<User> users = userRepository.findUsersWithin3kmByCoordinate(latitude, longitude);

        return new UserSearchByCoordinateRes(
            latitude,
            longitude,
            users.stream()
                .map(u -> new UserSearchByCoordinateRes.UserSummary(
                    u.getId(),
                    u.getUsername(),
                    u.getAddress()
                )).toList()
        );
    }
}
