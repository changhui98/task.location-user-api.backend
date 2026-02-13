package com.sph.location_user.user.domain.repository;

import com.sph.location_user.user.domain.entity.User;

public interface UserRepository {

    boolean existsByUsername(String username);

    User save(User user);
}
