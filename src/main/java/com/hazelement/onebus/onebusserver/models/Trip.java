package com.hazelement.onebus.onebusserver.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String tripId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_route")
    private Route route;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_service")
    private Service service;

    // todo take another look at this, shape_id is not identical in shape file
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_shape")
    private Shape shape;
}
