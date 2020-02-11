package com.hazelement.onebus.onebusserver;


import com.hazelement.onebus.onebusserver.gtfs_loader.GtfsFileLoader;
import com.hazelement.onebus.onebusserver.models.Route;
import com.hazelement.onebus.onebusserver.models.Service;
import com.hazelement.onebus.onebusserver.models.Shape;
import com.hazelement.onebus.onebusserver.models.Stop;
import com.hazelement.onebus.onebusserver.repositories.RouteRepository;
import com.hazelement.onebus.onebusserver.repositories.ServiceRepository;
import com.hazelement.onebus.onebusserver.repositories.ShapeRepository;
import com.hazelement.onebus.onebusserver.repositories.StopRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


@Slf4j
@SpringBootTest
public class InMemoryDBTests {
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ShapeRepository shapeRepository;
    @Autowired
    private StopRepository stopRepository;

    String TEST_GTFS_FOLDER = "classpath:vancouver_transit/";

    // todo test exceptions

    @Test
    void loadRouteTable_WithFileName_ExpectedBehavior() {

        int numEntries = -1;
        try {
            File file = ResourceUtils.getFile(TEST_GTFS_FOLDER + "routes.txt");
            numEntries = GtfsFileLoader.loadRouteData(routeRepository, file.getAbsolutePath());
        } catch (IOException e) {
            fail(e.getMessage());
        }

        List<Route> routeList = routeRepository.findAll();
        log.info("Number of routes " + routeList.size());
        assertEquals(routeList.size(), numEntries);
    }

    @Test
    void loadServiceTable_WithFileName_ExpectedBehavior() {
        int numEntries = -1;
        try {
            File file = ResourceUtils.getFile(TEST_GTFS_FOLDER + "calendar.txt");
            numEntries = GtfsFileLoader.loadServiceData(serviceRepository, file.getAbsolutePath());
        } catch (IOException e) {
            fail(e.getMessage());
        }

        List<Service> serviceList = serviceRepository.findAll();
        log.info("Number of services " + serviceList.size());
        assertEquals(serviceList.size(), numEntries);
    }

    @Test
    void loadShapeTable_WithFileName_ExpectedBehavior() {
        int numEntries = -1;
        try {
            File file = ResourceUtils.getFile(TEST_GTFS_FOLDER + "shapes.txt");
            numEntries = GtfsFileLoader.loadShapeData(shapeRepository, file.getAbsolutePath());
        } catch (IOException e) {
            fail(e.getMessage());
        }

        List<Shape> shapeList = shapeRepository.findAll();
        log.info("Number of shapes " + shapeList.size());
        assertEquals(shapeList.size(), numEntries);
    }

    @Test
    void loadStopTable_WithFileName_ExpectedBehavior() {
        int numEntries = -1;
        try {
            File file = ResourceUtils.getFile(TEST_GTFS_FOLDER + "stops.txt");
            numEntries = GtfsFileLoader.loadStopData(stopRepository, file.getAbsolutePath());
        } catch (IOException e) {
            fail(e.getMessage());
        }

        List<Stop> stopList = stopRepository.findAll();
        log.info("Number of stops " + stopList.size());
        assertEquals(stopList.size(), numEntries);
    }
}
