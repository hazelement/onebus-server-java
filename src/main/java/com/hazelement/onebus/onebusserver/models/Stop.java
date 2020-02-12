package com.hazelement.onebus.onebusserver.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
// for builder to work with JPA
@Builder
@AllArgsConstructor
@NoArgsConstructor
//
public class Stop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String stopId;
    private String stopName;
    private Float stopLat;
    private Float stopLon;
}