package com.hazelement.onebus.onebusserver;


import com.hazelement.onebus.onebusserver.exception.GtfsFileReadingException;
import com.hazelement.onebus.onebusserver.gtfs_loader.RouteParser;
import com.hazelement.onebus.onebusserver.repository.RouteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class InMemoryDBTest {
    @Autowired
    private RouteRepository routeRepository;
    @Test
    void testLoadRouteDB (){
        try {
            File file = ResourceUtils.getFile("classpath:vancouver_transit/routes.txt");
            RouteParser routeParser = new RouteParser(routeRepository, file.getAbsolutePath());
            routeParser.loadToDB();
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }
}
