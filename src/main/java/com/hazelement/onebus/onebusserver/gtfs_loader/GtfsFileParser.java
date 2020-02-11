package com.hazelement.onebus.onebusserver.gtfs_loader;

import com.hazelement.onebus.onebusserver.exceptions.GtfsFileReadingException;
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
     * @throws GtfsFileReadingException
     */
    @Transactional
    public int loadToDB() throws GtfsFileReadingException {
        final Counter counter = new Counter();
        try {
            GtfsLineParser gtfsLineParser = new GtfsLineParser(headers);
            this.readFileLineByLine(line -> {
                counter.increment();
                String[] items = line.split(",");
                gtfsLineParser.parseData(items, dataParser);
            });
        } catch (IOException e) {
            throw new GtfsFileReadingException(e.getMessage());
        }
        return counter.getCount();
    }



}
