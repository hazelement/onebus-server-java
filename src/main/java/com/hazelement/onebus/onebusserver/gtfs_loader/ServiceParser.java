package com.hazelement.onebus.onebusserver.gtfs_loader;

import com.hazelement.onebus.onebusserver.exception.GtfsFileReadingException;
import com.hazelement.onebus.onebusserver.model.Route;
import com.hazelement.onebus.onebusserver.model.Service;
import com.hazelement.onebus.onebusserver.repository.RouteRepository;
import com.hazelement.onebus.onebusserver.repository.ServiceRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


// todo consider refactor this with other parsers into one using factory
public class ServiceParser extends FileLoader {

    private ServiceRepository serviceRepository;
    private String[] headers = {
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

    public ServiceParser(ServiceRepository serviceRepository, String fileName) {
        super(fileName);
        this.serviceRepository = serviceRepository;
    }

    @Transactional
    public void loadToDB() throws GtfsFileReadingException {
        try {
            GtfsLineParser gtfsLineParser = new GtfsLineParser(headers);
            this.loadFile(line -> {
                String[] items = line.split(",");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

                gtfsLineParser.parseData(items, data -> {
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
            });
        } catch (IOException e) {
            throw new GtfsFileReadingException(e.getMessage());
        }
    }
}
