package com.hazelement.onebus.onebusserver.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"shape_id", "shape_pt_sequence"}))
public class Shape {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String shape_id;
    private float shape_pt_lat;
    private float shape_pt_lon;
    private int shape_pt_sequence;
}
