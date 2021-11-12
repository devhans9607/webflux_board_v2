package com.hans.rboard.repository;

import com.hans.rboard.entity.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface UserRepository extends R2dbcRepository<User, Long> {
}
