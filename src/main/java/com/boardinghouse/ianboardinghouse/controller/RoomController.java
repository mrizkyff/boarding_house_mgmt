package com.boardinghouse.ianboardinghouse.controller;

import com.boardinghouse.ianboardinghouse.dto.RoomDto;
import com.boardinghouse.ianboardinghouse.model.Building;
import com.boardinghouse.ianboardinghouse.model.Room;
import com.boardinghouse.ianboardinghouse.service.BuildingService;
import com.boardinghouse.ianboardinghouse.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    @Autowired
    RoomService roomService;

    @Autowired
    BuildingService buildingService;

    @PostMapping("")
    public RoomDto createRoom(@RequestBody @Valid RoomDto roomDto) {
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
        return getRoom(result.getId());
    }

    @GetMapping("")
    public List<RoomDto> getAllRoom() {
        List<Room> rooms = roomService.findAll();
        List<RoomDto> responses = new ArrayList<>();
        for (Room room: rooms){
            RoomDto roomDto = new RoomDto(
                    room.getId() ,
                    room.getBuilding().getId() ,
                    room.getLength() ,
                    room.getWidth() ,
                    room.getHeight() ,
                    room.getPrice() ,
                    room.getName() ,
                    room.getDescription());
            responses.add(roomDto);
        }
        return responses;
    }

    @GetMapping("/{id}")
    public RoomDto getRoom(@PathVariable UUID id) {
        Room room = roomService.getRoom(id);
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

    @PutMapping("/{id}")
    public RoomDto updateRoom(@PathVariable UUID id , @RequestBody @Valid RoomDto roomDto) {
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
        roomService.updateRoom(id , room);
        return getRoom(id);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteRoom(@PathVariable UUID id) {
        return roomService.deleteRoom(id);
    }
}
