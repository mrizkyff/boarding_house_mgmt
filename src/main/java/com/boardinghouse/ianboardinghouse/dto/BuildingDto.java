package com.boardinghouse.ianboardinghouse.dto;

import com.boardinghouse.ianboardinghouse.model.Room;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

public record BuildingDto(UUID id,
                          @NotBlank(message = "Name is required")
                          @Size(max = 255, message = "Max is 255 characters")
                          String name,
                          @NotBlank(message = "Address is required")
                          @Size(max = 255, message = "Max is 255 characters")
                          String address,
                          @NotNull(message = "No of room is required")
                          Integer noOfRoom,
                          List<Room> rooms) {
}
