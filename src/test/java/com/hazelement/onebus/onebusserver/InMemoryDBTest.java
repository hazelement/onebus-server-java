package com.hazelement.onebus.onebusserver;


import com.hazelement.onebus.onebusserver.gtfs_loader.GtfsFileLoader;
import com.hazelement.onebus.onebusserver.model.Route;
import com.hazelement.onebus.onebusserver.model.Service;
import com.hazelement.onebus.onebusserver.model.Shape;
import com.hazelement.onebus.onebusserver.repository.RouteRepository;
import com.hazelement.onebus.onebusserver.repository.ServiceRepository;
import com.hazelement.onebus.onebusserver.repository.ShapeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


@Slf4j
@SpringBootTest
public class InMemoryDBTest {
    @Autowired private RouteRepository routeRepository;
    @Autowired private ServiceRepository serviceRepository;
    @Autowired private ShapeRepository shapeRepository;

    String TEST_GTFS_FOLDER = "classpath:vancouver_transit/";

    @Test
    void testLoadRouteTable (){
        try {
            File file = ResourceUtils.getFile(TEST_GTFS_FOLDER + "routes.txt");
            GtfsFileLoader.loadRouteData(routeRepository, file.getAbsolutePath());
        } catch (IOException e) {
            fail(e.getMessage());
        }

        List<Route> routeList = routeRepository.findAll();
        log.info("Number of routes " + routeList.size());
        assertTrue(routeList.size()>0);
    }

    @Test
    void testLoadServiceTable (){
        try {
            File file = ResourceUtils.getFile(TEST_GTFS_FOLDER + "calendar.txt");
            GtfsFileLoader.loadServiceData(serviceRepository, file.getAbsolutePath());
        } catch (IOException e) {
            fail(e.getMessage());
        }

        List<Service> serviceList = serviceRepository.findAll();
        log.info("Number of services " + serviceList.size());
        assertTrue(serviceList.size()>0);
    }

    @Test
    void testLoadShapeTable (){
        try {
            File file = ResourceUtils.getFile(TEST_GTFS_FOLDER + "shapes.txt");
            GtfsFileLoader.loadShapeData(shapeRepository, file.getAbsolutePath());
        } catch (IOException e) {
            fail(e.getMessage());
        }

        List<Shape> shapeList = shapeRepository.findAll();
        log.info("Number of shapes " + shapeList.size());
        assertTrue(shapeList.size()>0);
    }
}
