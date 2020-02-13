package com.hazelement.onebus.onebusserver.gtfs_loader;

import com.hazelement.onebus.onebusserver.exceptions.GtfsDataParsingException;
import com.hazelement.onebus.onebusserver.exceptions.GtfsException;
import com.hazelement.onebus.onebusserver.util.Counter;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

public class GtfsFileParser extends FileLoader{

    private String[] headers;
    private GtfsDataParser dataParser;

    public GtfsFileParser(String filename, String[] headers, GtfsDataParser dataParser) {
        super(filename);
        this.headers = headers;
        this.dataParser = dataParser;
    }

    /**
     * Read file and process per GtfsDataParser
     *
     * @return number of file line processed
     * @throws GtfsException
     */
    @Transactional
    public int loadToDB() throws GtfsException {
        final Counter counter = new Counter();
        GtfsLineParser gtfsLineParser = new GtfsLineParser(headers);
        try {
            this.readFileLineByLine(line -> {
                counter.increment();
                String[] items = line.split(",");
                try {
                    gtfsLineParser.parseData(items, dataParser);
                } catch (GtfsDataParsingException e) {
                    throw new GtfsException(e.getMessage());
                }
            });
        } catch (IOException e) {
            throw new GtfsException(e.getMessage());
        }
        return counter.getCount();
    }



}
