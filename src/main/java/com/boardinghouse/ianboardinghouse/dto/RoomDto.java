package com.boardinghouse.ianboardinghouse.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RoomDto(UUID id ,
                      @NotNull(message = "Building id is required")
                      UUID building_id ,
                      @NotNull(message = "Length is required")
                      Double length ,
                      @NotNull(message = "Width is required")

                      Double width ,
                      @NotNull(message = "Height is required")

                      Double height ,
                      @NotNull(message = "Price is required")

                      Integer price ,
                      @NotNull(message = "Name is required")

                      String name ,
                      String description) {
}
