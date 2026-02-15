package com.sph.location_user.user.domain.repository;

import com.sph.location_user.user.domain.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

    boolean existsByUsername(String username);

    User save(User user);

    Optional<User> findByUsername(String username);

    List<User> findNearbyUsers(String username);

    List<User> findUsersWithin3km(double latitude, double longitude);

    List<User> findUsersWithin3kmByCoordinate(double latitude, double longitude);
}
