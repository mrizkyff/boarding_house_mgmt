package com.boardinghouse.ianboardinghouse.dto;

import java.util.UUID;

public record BuildingDto(UUID id, String name, String address, String noOfRoom) {
}
