package com.boardinghouse.ianboardinghouse.repository;

import com.boardinghouse.ianboardinghouse.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BuildingRepository extends JpaRepository<Building, UUID> {

}
