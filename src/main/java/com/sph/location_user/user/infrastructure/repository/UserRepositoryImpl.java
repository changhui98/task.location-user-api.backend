package com.sph.location_user.user.infrastructure.repository;

import com.sph.location_user.user.domain.entity.User;
import com.sph.location_user.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public boolean existsByUsername(String username) {

        return userJpaRepository.existsByUsername(username);
    }

    @Override
    public User save(User user) {

        return userJpaRepository.save(user);
    }
}
