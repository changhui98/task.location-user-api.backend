package com.sph.location_user.user.infrastructure.repository;

import com.sph.location_user.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {


}
