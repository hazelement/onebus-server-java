package com.hazelement.onebus.onebusserver.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"fk_trip", "fk_stop", "stop_sequence"}))
public class StopTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_trip", nullable = false)
    private Trip trip;

    // in seconds, can't use datetime because operating hours go beyond 24 hours
    private long arrival_time;
    private long departure_time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_stop", nullable = false)
    private Stop stop;

    private int stop_sequence;
}
