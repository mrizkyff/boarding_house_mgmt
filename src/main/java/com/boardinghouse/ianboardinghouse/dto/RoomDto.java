package com.boardinghouse.ianboardinghouse.dto;

import com.boardinghouse.ianboardinghouse.model.Building;
import com.boardinghouse.ianboardinghouse.model.Room;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

public record RoomDto(UUID id,
                      UUID building_id,
                      Double length,
                      Double width,
                      Double height,
                      Integer price,
                      String name,
                      String description) {
    public RoomDto(UUID id , UUID building_id , Double length , Double width , Double height , Integer price , String name , String description) {
        this.id = id;
        this.building_id = building_id;
        this.length = length;
        this.width = width;
        this.height = height;
        this.price = price;
        this.name = name;
        this.description = description;
    }
}
