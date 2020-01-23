package com.hazelement.onebus.onebusserver.models;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Stop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String stop_id;
    private String stop_name;
    private Float stop_lat;
    private Float stop_lon;

}