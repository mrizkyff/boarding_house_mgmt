package com.boardinghouse.ianboardinghouse.controller;

import com.boardinghouse.ianboardinghouse.dto.RoomDto;
import com.boardinghouse.ianboardinghouse.model.Building;
import com.boardinghouse.ianboardinghouse.model.Room;
import com.boardinghouse.ianboardinghouse.service.BuildingService;
import com.boardinghouse.ianboardinghouse.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    @Autowired
    RoomService roomService;

    @Autowired
    BuildingService buildingService;

    @PostMapping("")
    public RoomDto createRoom(@RequestBody RoomDto roomDto) {
        // get building first
        Building building = buildingService.getBuildingOr404(roomDto.building_id());
        Room room = new Room();
        room.setBuilding(building);
        room.setHeight(roomDto.height());
        room.setLength(roomDto.length());
        room.setPrice(roomDto.price());
        room.setName(roomDto.name());
        room.setDescription(roomDto.description());
        Room result = roomService.createRoom(room);
        return roomService.mapToDto(result);
    }

    @GetMapping("")
    public List<RoomDto> getAllRoom() {
        return roomService.findAll().stream().map(building -> roomService.mapToDto(building)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public RoomDto getRoom(@PathVariable UUID id) {
        return roomService.mapToDto(roomService.getRoomOr404(id));
    }

    @PutMapping("/{id}")
    public RoomDto updateRoom(@PathVariable UUID id , @RequestBody RoomDto roomDto) {
        // get building first
        Building building = buildingService.getBuildingOr404(roomDto.building_id());
        Room room = new Room();
        room.setBuilding(building);
        room.setHeight(roomDto.height());
        room.setLength(roomDto.length());
        room.setWidth(roomDto.width());
        room.setPrice(roomDto.price());
        room.setName(roomDto.name());
        room.setDescription(roomDto.description());
        Room result = roomService.updateRoom(id, room);
        return new RoomDto(
                result.getId(),
                result.getBuilding().getId(),
                result.getLength(),
                result.getWidth(),
                result.getHeight(),
                result.getPrice(),
                result.getName(),
                result.getDescription());
    }

    @DeleteMapping("/{id}")
    public Boolean deleteRoom(@PathVariable UUID id) {
        return roomService.deleteRoom(id);
    }
}
