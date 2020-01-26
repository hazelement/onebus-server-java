package com.hazelement.onebus.onebusserver.models;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"fk_trip", "fk_stop", "stop_sequence"}))
public class StopTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_trip")
    private Trip trip;

    // in seconds, can't use datetime because operating hours go beyond 24 hours
    private int arrival_time;
    private int departure_time;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_stop")
    private Stop stop;

    private int stop_sequence;
}
