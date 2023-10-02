package com.boardinghouse.ianboardinghouse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "master_room")
public class Room {
    @Id
    @GeneratedValue
    private UUID id;

    private Double length;
    private Double width;
    private Double height;
    private Integer price;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "building_id")
    @JsonIgnore
    private Building building;

//    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL)
//    private User user;
}