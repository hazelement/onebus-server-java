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
