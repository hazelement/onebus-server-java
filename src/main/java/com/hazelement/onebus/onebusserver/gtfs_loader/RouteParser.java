package com.hazelement.onebus.onebusserver.gtfs_loader;

import com.hazelement.onebus.onebusserver.exception.GtfsFileReadingException;
import com.hazelement.onebus.onebusserver.model.Route;
import com.hazelement.onebus.onebusserver.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

public class RouteParser extends FileLoader {

    private RouteRepository routeRepository;
    private String[] headers = {"route_id", "route_short_name", "route_long_name"};

    public RouteParser(RouteRepository routeRepository, String fileName) {
        super(fileName);
        this.routeRepository = routeRepository;
    }

    @Transactional
    public void loadToDB() throws GtfsFileReadingException {
        try {
            GtfsLineParser gtfsLineParser = new GtfsLineParser(headers);
            this.loadFile(line -> {
                String[] items = line.split(",");
                gtfsLineParser.parseData(items, data -> {
                    Route route = Route.builder()
                            .route_id(data.get("route_id"))
                            .route_short_name(data.get("route_short_name"))
                            .route_long_name(data.get("route_long_name"))
                            .build();
                    routeRepository.save(route);
                });
            });
        } catch (IOException e){
            throw new GtfsFileReadingException(e.getMessage());
        }
    }
}
