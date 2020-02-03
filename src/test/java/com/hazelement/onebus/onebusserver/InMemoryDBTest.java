package com.hazelement.onebus.onebusserver;


import com.hazelement.onebus.onebusserver.exception.GtfsFileReadingException;
import com.hazelement.onebus.onebusserver.gtfs_loader.RouteParser;
import com.hazelement.onebus.onebusserver.gtfs_loader.ServiceParser;
import com.hazelement.onebus.onebusserver.model.Route;
import com.hazelement.onebus.onebusserver.model.Service;
import com.hazelement.onebus.onebusserver.repository.RouteRepository;
import com.hazelement.onebus.onebusserver.repository.ServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


@Slf4j
@SpringBootTest
public class InMemoryDBTest {
    @Autowired private RouteRepository routeRepository;
    @Autowired private ServiceRepository serviceRepository;

    @Test
    void testLoadRouteTable (){
        try {
            File file = ResourceUtils.getFile("classpath:vancouver_transit/routes.txt");
            RouteParser routeParser = new RouteParser(routeRepository, file.getAbsolutePath());
            routeParser.loadToDB();
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
            File file = ResourceUtils.getFile("classpath:vancouver_transit/calendar.txt");
            ServiceParser serviceParser = new ServiceParser(serviceRepository, file.getAbsolutePath());
            serviceParser.loadToDB();
        } catch (IOException e) {
            fail(e.getMessage());
        }

        List<Service> serviceList = serviceRepository.findAll();
        log.info("Number of services " + serviceList.size());
        assertTrue(serviceList.size()>0);
    }
}
