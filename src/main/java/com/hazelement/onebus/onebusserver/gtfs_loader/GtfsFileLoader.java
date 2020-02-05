package com.hazelement.onebus.onebusserver.gtfs_loader;

import com.hazelement.onebus.onebusserver.exception.GtfsFileReadingException;
import com.hazelement.onebus.onebusserver.model.Route;
import com.hazelement.onebus.onebusserver.model.Service;
import com.hazelement.onebus.onebusserver.repository.RouteRepository;
import com.hazelement.onebus.onebusserver.repository.ServiceRepository;
import com.sun.org.apache.xpath.internal.operations.Gt;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class GtfsFileLoader {
    public static void loadRouteData(RouteRepository routeRepository, String filename)
            throws GtfsFileReadingException {
        String[] headers = {"route_id", "route_short_name", "route_long_name"};
        GtfsFileParser gtfsFileParser = new GtfsFileParser(filename,
                headers,
                data -> {
                    Route route = Route.builder()
                            .route_id(data.get("route_id").toString())
                            .route_short_name(data.get("route_short_name").toString())
                            .route_long_name(data.get("route_long_name").toString())
                            .build();
                    routeRepository.save(route);
                });
        gtfsFileParser.loadToDB();
    }

    public static void loadServiceData(ServiceRepository serviceRepository, String filename)
            throws GtfsFileReadingException {
         String[] headers = {
                "service_id",
                "monday",
                "tuesday",
                "wednesday",
                "thursday",
                "friday",
                "saturday",
                "sunday",
                "start_date",
                "end_date"};
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        GtfsFileParser gtfsFileParser = new GtfsFileParser(filename,
                headers,
                data -> {
                    Service service = Service.builder()
                            .service_id(data.get("service_id"))
                            .monday(Integer.parseInt(data.get("monday")))
                            .tuesday(Integer.parseInt(data.get("monday")))
                            .wednesday(Integer.parseInt(data.get("monday")))
                            .thursday(Integer.parseInt(data.get("monday")))
                            .friday(Integer.parseInt(data.get("monday")))
                            .saturday(Integer.parseInt(data.get("monday")))
                            .sunday(Integer.parseInt(data.get("monday")))
                            .start_date(simpleDateFormat.parse(data.get("start_date")))
                            .end_date(simpleDateFormat.parse(data.get("end_date")))
                            .build();
                    serviceRepository.save(service);
                });
        gtfsFileParser.loadToDB();
    }
}
