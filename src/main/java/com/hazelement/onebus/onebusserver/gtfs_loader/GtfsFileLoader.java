package com.hazelement.onebus.onebusserver.gtfs_loader;

import com.hazelement.onebus.onebusserver.exceptions.GtfsFileReadingException;
import com.hazelement.onebus.onebusserver.models.*;
import com.hazelement.onebus.onebusserver.repositories.*;
import com.hazelement.onebus.onebusserver.util.GtfsTimeConverter;

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
                            .routeId(data.get(ROUTE_ID))
                            .routeShortName(data.get(ROUTE_SHORT_NAME))
                            .routeLongName(data.get(ROUTE_LONG_NAME))
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
                            .serviceId(data.get(SERVICE_ID))
                            .monday(Integer.parseInt(data.get(MONDAY)))
                            .tuesday(Integer.parseInt(data.get(TUESDAY)))
                            .wednesday(Integer.parseInt(data.get(WEDNESDAY)))
                            .thursday(Integer.parseInt(data.get(THURSDAY)))
                            .friday(Integer.parseInt(data.get(FRIDAY)))
                            .saturday(Integer.parseInt(data.get(SATURDAY)))
                            .sunday(Integer.parseInt(data.get(SUNDAY)))
                            .startDate(simpleDateFormat.parse(data.get(START_DATE)))
                            .endDate(simpleDateFormat.parse(data.get(END_DATE)))
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
                            .shapeId(data.get(SHAPE_ID))
                            .shapePtLat(Float.parseFloat(data.get(SHAPE_PT_LAT)))
                            .shapePtLon(Float.parseFloat(data.get(SHAPE_PT_LON)))
                            .shapePtSequence(Integer.parseInt(data.get(SHAPE_PT_SEQUENCE)))
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
                            .stopId(data.get(STOP_ID))
                            .stopName(data.get(STOP_NAME))
                            .stopLat(Float.valueOf(data.get(STOP_LAT)))
                            .stopLon(Float.valueOf(data.get(STOP_LON)))
                            .build();
                    stopRepository.save(stop);
                });
        return gtfsFileParser.loadToDB() - 1;
    }

    public static int loadTripData(
            TripRepository tripRepository,
            RouteRepository routeRepository,
            ServiceRepository serviceRepository,
            ShapeRepository shapeRepository,
            String filename)
            throws GtfsFileReadingException {
        String TRIP_ID = "trip_id";
        String ROUTE = "route";
        String SERVICE = "service";
        String SHAPE = "shape";

        String[] headers = {TRIP_ID, ROUTE, SERVICE, SHAPE};

        GtfsFileParser gtfsFileParser = new GtfsFileParser(filename,
                headers,
                data -> {
                    Route route = routeRepository.findByRouteId(data.get(ROUTE));
                    Service service = serviceRepository.findByServiceId(data.get(SERVICE));
                    Shape shape = shapeRepository.findByShapeId(data.get(SHAPE));

                    Trip trip = Trip.builder()
                            .tripId(data.get(TRIP_ID))
                            .route(route)
                            .service(service)
                            .shape(shape)
                            .build();
                    tripRepository.save(trip);
                });
        return gtfsFileParser.loadToDB() - 1;
    }

    public static int loadStopTimeData(StopTimeRepository stopTimeRepository,
                                       TripRepository tripRepository,
                                       StopRepository stopRepository,
                                       String filename)
            throws GtfsFileReadingException {
        String TRIP = "trip";
        String ARRIVAL_TIME = "arrival_time";
        String DEPARTURE_TIME = "departure_time";
        String STOP = "stop";
        String STOP_SEQUENCE = "stop_sequence";

        String[] headers = {TRIP, ARRIVAL_TIME, DEPARTURE_TIME, STOP, STOP_SEQUENCE};

        GtfsFileParser gtfsFileParser = new GtfsFileParser(filename,
                headers,
                data -> {
                    Long arrival_time = GtfsTimeConverter.toSeconds(data.get(ARRIVAL_TIME));
                    Long depature_time = GtfsTimeConverter.toSeconds(data.get(DEPARTURE_TIME));
                    Trip trip = tripRepository.findByTripId(data.get(TRIP));
                    Stop stop = stopRepository.findByStopId(data.get(STOP));

                    StopTime stopTime = StopTime.builder()
                            .trip(trip)
                            .arrival_time(arrival_time)
                            .departure_time(depature_time)
                            .stop(stop)
                            .stop_sequence(Integer.parseInt(data.get(STOP_SEQUENCE)))
                            .build();

                    stopTimeRepository.save(stopTime);
                });
        return gtfsFileParser.loadToDB() - 1;
    }


}
