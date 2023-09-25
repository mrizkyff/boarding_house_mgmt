package com.boardinghouse.ianboardinghouse.repository;

import com.boardinghouse.ianboardinghouse.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
