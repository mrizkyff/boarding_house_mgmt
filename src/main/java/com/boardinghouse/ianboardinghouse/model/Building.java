package com.boardinghouse.ianboardinghouse.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "master_building")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String address;
    private int noOfRoom;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "building")
    private List<Room> rooms = new ArrayList<>();
}
