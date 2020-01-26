package com.hazelement.onebus.onebusserver.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String route_id;
    private String route_short_name;
    private String route_long_name;
}
