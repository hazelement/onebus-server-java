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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"shapeId", "shapePtSequence"}),
        indexes = {@Index(name = "shape_id_index", columnList = "shapeId", unique = false)})

public class Shape {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String shapeId;
    private float shapePtLat;
    private float shapePtLon;
    private int shapePtSequence;
}
