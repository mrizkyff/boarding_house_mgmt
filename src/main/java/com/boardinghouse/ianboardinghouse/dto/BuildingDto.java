package com.boardinghouse.ianboardinghouse.dto;

import com.boardinghouse.ianboardinghouse.model.Room;

import java.util.List;
import java.util.UUID;

public record BuildingDto(UUID id,
                          String name,
                          String address,
                          String noOfRoom,
                          List<Room> rooms) {
}
