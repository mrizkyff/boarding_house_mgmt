package com.boardinghouse.ianboardinghouse.dto;

import java.util.UUID;

public record RoomDto(UUID id,
                      UUID building_id,
                      Double length,
                      Double width,
                      Double height,
                      Integer price,
                      String name,
                      String description) {
}
