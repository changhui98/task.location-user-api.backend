package com.sph.location_user.user.infrastructure.repository;

import com.sph.location_user.user.domain.entity.User;
import com.sph.location_user.user.domain.repository.UserRepository;
import java.util.List;
import java.util.Optional;
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

    @Override
    public Optional<User> findByUsername(String username) {

        return userJpaRepository.findByUsername(username);
    }

    @Override
    public List<User> findNearbyUsers(String username) {

        return userJpaRepository.findNearbyUsers(username);
    }

    @Override
    public List<User> findUsersWithin3km(double latitude, double longitude) {

        return userJpaRepository.findUsersWithin3km(latitude, longitude);
    }

    @Override
    public List<User> findUsersWithin3kmByCoordinate(double latitude, double longitude) {

        return userJpaRepository.findUsersWithin3kmByCoordinate(latitude, longitude);
    }

    @Override
    public List<User> findAll() {

        return userJpaRepository.findAll();
    }
}
