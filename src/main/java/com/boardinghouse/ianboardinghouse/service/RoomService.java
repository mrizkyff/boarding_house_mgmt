package com.boardinghouse.ianboardinghouse.service;

import com.boardinghouse.ianboardinghouse.model.Room;

import java.util.List;
import java.util.UUID;

public interface RoomService {
    Room getRoomOr404(UUID id);
    Room getRoom(UUID id);
    Room createRoom(Room room);
    Room updateRoom(UUID id, Room room);
    Boolean deleteRoom(UUID id);
    List<Room> findAll();
}
