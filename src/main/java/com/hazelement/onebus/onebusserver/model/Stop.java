package com.hazelement.onebus.onebusserver.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Stop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String stop_id;
    private String stop_name;
    private Float stop_lat;
    private Float stop_lon;

}