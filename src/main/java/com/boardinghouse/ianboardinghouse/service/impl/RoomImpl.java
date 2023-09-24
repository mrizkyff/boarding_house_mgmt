package com.boardinghouse.ianboardinghouse.service.impl;

import com.boardinghouse.ianboardinghouse.model.Room;
import com.boardinghouse.ianboardinghouse.repository.RoomRepository;
import com.boardinghouse.ianboardinghouse.service.RoomService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
public class RoomImpl implements RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Override
    public Room getRoomOr404(UUID id) {
        Optional<Room> room = roomRepository.findById(id);
        return room.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Room not found"));
    }

    @Override
    public Room getRoom(UUID id) {
        return getRoomOr404(id);
    }

    @Override
    public Room createRoom(Room room) {
        try {
            Room result = roomRepository.save(room);
            return getRoomOr404(result.getId());
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY , "Failed to create room");
        }

    }

    @Override
    public Room updateRoom(UUID id , Room room) {
        Room roomOr404 = getRoomOr404(id);
        try {
            if (roomOr404 != null) {
                roomOr404.setName(room.getName());
                roomOr404.setDescription(room.getDescription());
                roomOr404.setBuilding(room.getBuilding());
                roomOr404.setLength(room.getLength());
                roomOr404.setHeight(room.getHeight());
                roomOr404.setWidth(room.getWidth());
                roomOr404.setPrice(room.getPrice());
                roomRepository.save(room);
            }
            return getRoomOr404(id);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY , "Failed to update room");
        }
    }

    @Override
    public Boolean deleteRoom(UUID id) {
        Room result = getRoomOr404(id);
        if (result != null) {
            roomRepository.delete(result);
            return true;
        }
        throw new ResponseStatusException(HttpStatus.BAD_GATEWAY , "Failed to delete room");
    }

    @Override
    public List<Room> findAll() {
        List<Room> rooms = roomRepository.findAll();
        if (!rooms.isEmpty()) {
            return rooms;
        }
        return new ArrayList<>();
    }

}