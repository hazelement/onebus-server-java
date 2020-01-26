package com.hazelement.onebus.onebusserver.models;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
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
