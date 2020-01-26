package com.hazelement.onebus.onebusserver.gtfs_loader;

import com.hazelement.onebus.onebusserver.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RouteParser extends FileLoader{
    @Autowired
    private RouteRepository routeRepository;

    public RouteParser(String fileName){
        super(fileName);
        this.loadFile(line -> {
            // todo write line to db using routeRepo
        });
    }


}
