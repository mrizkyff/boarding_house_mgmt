package com.boardinghouse.ianboardinghouse.service;

import com.boardinghouse.ianboardinghouse.dto.BuildingDto;
import com.boardinghouse.ianboardinghouse.model.Building;

import java.util.List;
import java.util.UUID;

public interface BuildingService {
    Building getBuildingOr404(UUID id);
    Building createBuilding(Building building);
    Building updateBuilding(UUID id, Building building);
    boolean deleteBuilding(UUID id);
    Building getBuilding(UUID id);
    List<Building> findAll();

    BuildingDto mapToDto(Building building);
    Building mapToEntity(BuildingDto buildingDto);
}
