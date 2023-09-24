package com.boardinghouse.ianboardinghouse.service.impl;

import com.boardinghouse.ianboardinghouse.dto.BuildingDto;
import com.boardinghouse.ianboardinghouse.model.Building;
import com.boardinghouse.ianboardinghouse.repository.BuildingRepository;
import com.boardinghouse.ianboardinghouse.service.BuildingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class BuildingImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;


    @Override
    public Building getBuildingOr404(UUID id) {
        Optional <Building> building = buildingRepository.findById(id);
        return building.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Building not found!"));
    }

    @Override
    public Building createBuilding(Building building) {
        try{
            Building result = buildingRepository.save(building);
            return getBuildingOr404(result.getId());
        }
        catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Failed to create building");
        }
    }

    @Override
    public Building updateBuilding(UUID id, Building building) {
        Building result = getBuildingOr404(id);
        try{
            if (result != null){
                result.setName(building.getName());
                result.setAddress(building.getAddress());
                result.setNoOfRoom(building.getNoOfRoom());
                buildingRepository.save(result);
            }
            return getBuildingOr404(id);
        }
        catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Failed to update building");
        }
    }

    @Override
    public boolean deleteBuilding(UUID id) {
        Building result = getBuildingOr404(id);
        if (result != null){
            buildingRepository.delete(result);
            return true;
        }
        throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Failed to delete building");
    }

    @Override
    public Building getBuilding(UUID id) {
        return getBuildingOr404(id);
    }

    @Override
    public List<Building> findAll() {
        List<Building> buildings = buildingRepository.findAll();
        if (!buildings.isEmpty()){
            return buildings;
        }
        return new ArrayList<>();
    }

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public BuildingDto mapToDto(Building building) {
        return objectMapper.convertValue(building, BuildingDto.class);
    }

    @Override
    public Building mapToEntity(BuildingDto buildingDto) {
        return objectMapper.convertValue(buildingDto, Building.class);
    }
}
