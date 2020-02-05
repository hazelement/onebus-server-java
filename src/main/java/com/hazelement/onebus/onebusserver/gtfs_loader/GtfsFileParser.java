package com.hazelement.onebus.onebusserver.gtfs_loader;

import com.hazelement.onebus.onebusserver.exception.GtfsFileReadingException;
import com.hazelement.onebus.onebusserver.model.Route;
import jdk.jfr.internal.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

public class GtfsFileParser extends FileLoader{

    private String[] headers;
    private GtfsDataParser dataParser;

    public GtfsFileParser(String filename, String[] headers, GtfsDataParser dataParser){
        super(filename);
        this.headers = headers;
        this.dataParser = dataParser;
    }

    @Transactional
    public void loadToDB() throws GtfsFileReadingException {
        try {
            GtfsLineParser gtfsLineParser = new GtfsLineParser(headers);
            this.readFileLineByLine(line -> {
                String[] items = line.split(",");
                gtfsLineParser.parseData(items, dataParser);
            });
        } catch (IOException e){
            throw new GtfsFileReadingException(e.getMessage());
        }
    }



}
