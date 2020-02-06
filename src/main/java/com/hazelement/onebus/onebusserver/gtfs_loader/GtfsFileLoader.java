package com.hazelement.onebus.onebusserver.gtfs_loader;

import com.hazelement.onebus.onebusserver.exception.GtfsFileReadingException;
import com.hazelement.onebus.onebusserver.model.Route;
import com.hazelement.onebus.onebusserver.model.Service;
import com.hazelement.onebus.onebusserver.model.Shape;
import com.hazelement.onebus.onebusserver.repository.RouteRepository;
import com.hazelement.onebus.onebusserver.repository.ServiceRepository;
import com.hazelement.onebus.onebusserver.repository.ShapeRepository;
import com.sun.org.apache.xpath.internal.operations.Gt;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class GtfsFileLoader {
    public static void loadRouteData(RouteRepository routeRepository, String filename)
            throws GtfsFileReadingException {
        String ROUTE_ID = "route_id";
        String ROUTE_SHORT_NAME = "route_short_name";
        String ROUTE_LONG_NAME = "route_long_name";

        String[] headers = {ROUTE_ID, ROUTE_SHORT_NAME, ROUTE_LONG_NAME};
        GtfsFileParser gtfsFileParser = new GtfsFileParser(filename,
                headers,
                data -> {
                    Route route = Route.builder()
                            .route_id(data.get(ROUTE_ID))
                            .route_short_name(data.get(ROUTE_SHORT_NAME))
                            .route_long_name(data.get(ROUTE_LONG_NAME))
                            .build();
                    routeRepository.save(route);
                });
        gtfsFileParser.loadToDB();
    }

    public static void loadServiceData(ServiceRepository serviceRepository, String filename)
            throws GtfsFileReadingException {
        String SERVICE_ID = "service_id";
        String MONDAY = "monday";
        String TUESDAY = "tuesday";
        String WEDNESDAY = "wednesday";
        String THURSDAY = "thursday";
        String FRIDAY = "friday";
        String SATURDAY = "saturday";
        String SUNDAY = "sunday";
        String START_DATE = "start_date";
        String END_DATE = "end_date";

         String[] headers = {
                 SERVICE_ID,
                 MONDAY,
                 TUESDAY,
                 WEDNESDAY,
                 THURSDAY,
                 FRIDAY,
                 SATURDAY,
                 SUNDAY,
                 START_DATE,
                 END_DATE
         };
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        GtfsFileParser gtfsFileParser = new GtfsFileParser(filename,
                headers,
                data -> {
                    Service service = Service.builder()
                            .service_id(data.get(SERVICE_ID))
                            .monday(Integer.parseInt(data.get(MONDAY)))
                            .tuesday(Integer.parseInt(data.get(TUESDAY)))
                            .wednesday(Integer.parseInt(data.get(WEDNESDAY)))
                            .thursday(Integer.parseInt(data.get(THURSDAY)))
                            .friday(Integer.parseInt(data.get(FRIDAY)))
                            .saturday(Integer.parseInt(data.get(SATURDAY)))
                            .sunday(Integer.parseInt(data.get(SUNDAY)))
                            .start_date(simpleDateFormat.parse(data.get(START_DATE)))
                            .end_date(simpleDateFormat.parse(data.get(END_DATE)))
                            .build();
                    serviceRepository.save(service);
                });
        gtfsFileParser.loadToDB();
    }

    public static void loadShapeData(ShapeRepository shapeRepository, String filename)
            throws GtfsFileReadingException {
        String SHAPE_ID = "shape_id";
        String SHAPE_PT_LAT = "shape_pt_lat";
        String SHAPE_PT_LON = "shape_pt_lon";
        String SHAPE_PT_SEQUENCE = "shape_pt_sequence";

        String[] headers = {SHAPE_ID, SHAPE_PT_LAT, SHAPE_PT_LON, SHAPE_PT_SEQUENCE};
        GtfsFileParser gtfsFileParser = new GtfsFileParser(filename,
                headers,
                data -> {
                    Shape shape = Shape.builder()
                            .shape_id(data.get(SHAPE_ID))
                            .shape_pt_lat(Float.parseFloat(data.get(SHAPE_PT_LAT)))
                            .shape_pt_lon(Float.parseFloat(data.get(SHAPE_PT_LON)))
                            .shape_pt_sequence(Integer.parseInt(data.get(SHAPE_PT_SEQUENCE)))
                            .build();
                    shapeRepository.save(shape);

                });
        gtfsFileParser.loadToDB();
    }
}
