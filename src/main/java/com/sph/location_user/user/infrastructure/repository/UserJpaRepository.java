package com.sph.location_user.user.infrastructure.repository;

import com.sph.location_user.user.domain.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserJpaRepository extends JpaRepository<User, Long> {


    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

    @Query(value = """
        SELECT u.*
        FROM p_user u
        WHERE u.username <> :username
        AND ST_DWithin(
            u.location,
            (SELECT location FROM p_user WHERE username = :username), 3000)
        """, nativeQuery = true)
    List<User> findNearbyUsers(@Param("username") String username);
}
