package com.hazelement.onebus.onebusserver.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
// for builder to work with JPA
@Builder
@AllArgsConstructor
@NoArgsConstructor
//
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String route_id;
    private String route_short_name;
    private String route_long_name;
}
