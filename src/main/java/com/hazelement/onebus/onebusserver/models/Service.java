package com.hazelement.onebus.onebusserver.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
// for builder to work with JPA
@Builder
@AllArgsConstructor
@NoArgsConstructor
//
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String service_id;

    private int monday;
    private int tuesday;
    private int wednesday;
    private int thursday;
    private int friday;
    private int saturday;
    private int sunday;
    private Date start_date;
    private Date end_date;
}
