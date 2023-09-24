package com.boardinghouse.ianboardinghouse.repository;

import com.boardinghouse.ianboardinghouse.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {
}
