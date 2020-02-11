package com.hazelement.onebus.onebusserver.models;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String trip_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_route")
    private Route route;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_service")
    private Service service;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_shape")
    private Shape shape;
}
