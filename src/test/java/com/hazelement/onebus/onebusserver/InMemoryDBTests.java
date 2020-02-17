package com.hazelement.onebus.onebusserver;


import com.hazelement.onebus.onebusserver.gtfs_loader.GtfsFileLoader;
import com.hazelement.onebus.onebusserver.models.*;
import com.hazelement.onebus.onebusserver.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class InMemoryDBTests {
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ShapeRepository shapeRepository;
    @Autowired
    private StopRepository stopRepository;
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private StopTimeRepository stopTimeRepository;

    String TEST_GTFS_FOLDER = "classpath:calgary_transit/";

    // todo test exceptions
    // todo add code coverage report
    // todo test deletion and foreign key cascade

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

        Stop stop = stopRepository.findByStopId("3481");
        assertNotNull(stop);
    }

    @Test
    void loadTripTable_WithFileName_ExpectedBehavior() {
        loadRouteTable_WithFileName_ExpectedBehavior();
        loadServiceTable_WithFileName_ExpectedBehavior();
        loadShapeTable_WithFileName_ExpectedBehavior();

        int numEntries = -1;
        try {
            File file = ResourceUtils.getFile(TEST_GTFS_FOLDER + "trips.txt");
            numEntries = GtfsFileLoader.loadTripData(
                    tripRepository,
                    routeRepository,
                    serviceRepository,
                    shapeRepository,
                    file.getAbsolutePath());
        } catch (IOException e) {
            fail(e.getMessage());
        }

        List<Trip> tripList = tripRepository.findAll();
        log.info("Number of trips " + tripList.size());
        assertEquals(tripList.size(), numEntries);

        Trip trip = tripRepository.findByTripId("50167819");
        assertNotNull(trip);
    }

    @Test
    void loadStopTimeTable_WithFileName_ExpectedBehavior() {
        loadTripTable_WithFileName_ExpectedBehavior();
        loadStopTable_WithFileName_ExpectedBehavior();

        int numEntries = -1;
        try {
            File file = ResourceUtils.getFile(TEST_GTFS_FOLDER + "stop_times.txt");
            numEntries = GtfsFileLoader.loadStopTimeData(
                    stopTimeRepository,
                    tripRepository,
                    stopRepository,
                    file.getAbsolutePath());
        } catch (IOException e) {
            fail(e.getMessage());
        }

        List<StopTime> stopTimeList = stopTimeRepository.findAll();
        log.info("Number of stop times " + stopTimeList.size());
        assertEquals(stopTimeList.size(), numEntries);
    }


}
