package com.hazelement.onebus.onebusserver.gtfs_loader;

import com.hazelement.onebus.onebusserver.exceptions.GtfsFileReadingException;
import com.hazelement.onebus.onebusserver.models.Route;
import com.hazelement.onebus.onebusserver.models.Service;
import com.hazelement.onebus.onebusserver.models.Shape;
import com.hazelement.onebus.onebusserver.models.Stop;
import com.hazelement.onebus.onebusserver.repositories.RouteRepository;
import com.hazelement.onebus.onebusserver.repositories.ServiceRepository;
import com.hazelement.onebus.onebusserver.repositories.ShapeRepository;
import com.hazelement.onebus.onebusserver.repositories.StopRepository;

import java.text.SimpleDateFormat;

public class GtfsFileLoader {
    public static int loadRouteData(RouteRepository routeRepository, String filename)
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
        return gtfsFileParser.loadToDB() - 1; // excluding header
    }

    public static int loadServiceData(ServiceRepository serviceRepository, String filename)
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
        return gtfsFileParser.loadToDB() - 1;
    }

    public static int loadShapeData(ShapeRepository shapeRepository, String filename)
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
        return gtfsFileParser.loadToDB() - 1;
    }

    public static int loadStopData(StopRepository stopRepository, String filename) throws GtfsFileReadingException {
        String STOP_ID = "stop_id";
        String STOP_NAME = "stop_name";
        String STOP_LAT = "stop_lat";
        String STOP_LON = "stop_lon";

        String[] headers = {STOP_ID, STOP_NAME, STOP_LAT, STOP_LON};

        GtfsFileParser gtfsFileParser = new GtfsFileParser(filename,
                headers,
                data -> {
                    Stop stop = Stop.builder()
                            .stop_id(data.get(STOP_ID))
                            .stop_name(data.get(STOP_NAME))
                            .stop_lat(Float.valueOf(data.get(STOP_LAT)))
                            .stop_lon(Float.valueOf(data.get(STOP_LON)))
                            .build();
                    stopRepository.save(stop);
                });
        return gtfsFileParser.loadToDB() - 1;
    }
}
