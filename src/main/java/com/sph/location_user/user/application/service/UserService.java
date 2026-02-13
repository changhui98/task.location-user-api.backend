package com.sph.location_user.user.application.service;

import com.sph.location_user.user.domain.entity.User;
import com.sph.location_user.user.domain.repository.UserRepository;
import com.sph.location_user.user.infrastructure.repository.GeocodingClient;
import com.sph.location_user.user.presentation.dto.request.UserCreateReq;
import com.sph.location_user.user.presentation.dto.response.UserCreateRes;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final GeocodingClient geocodingClient;
    private final GeometryFactory geometryFactory = new GeometryFactory();

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
}
