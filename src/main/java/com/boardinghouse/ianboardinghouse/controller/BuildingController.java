package com.boardinghouse.ianboardinghouse.controller;

import com.boardinghouse.ianboardinghouse.dto.BuildingDto;
import com.boardinghouse.ianboardinghouse.model.Building;
import com.boardinghouse.ianboardinghouse.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/building")
public class BuildingController {

    @Autowired
    BuildingService buildingService;

    @PostMapping("")
    public BuildingDto createBuilding(@RequestBody @Valid BuildingDto buildingDto){
        Building building = buildingService.mapToEntity(buildingDto);
        Building result = buildingService.createBuilding(building);
        return buildingService.mapToDto(result);
    }

    @GetMapping("")
    public List<BuildingDto> getAllBuilding(){
        return buildingService.findAll().stream().map(building -> buildingService.mapToDto(building)).collect(Collectors.toList());
    }

    @PutMapping("{id}")
    public BuildingDto updateBuilding(@PathVariable UUID id, @RequestBody @Valid BuildingDto buildingDto){
        Building building = buildingService.mapToEntity(buildingDto);
        Building result = buildingService.updateBuilding(id, building);
        return buildingService.mapToDto(result);
    }

    @GetMapping("{id}")
    public BuildingDto getBuilding(@PathVariable UUID id){
        Building building = buildingService.getBuilding(id);
        return buildingService.mapToDto(building);
    }

    @DeleteMapping("{id}")
    public boolean deleteBuilding(@PathVariable UUID id){
        return buildingService.deleteBuilding(id);
    }
}
