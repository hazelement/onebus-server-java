package com.hazelement.onebus.onebusserver.gtfs_loader;

import com.hazelement.onebus.onebusserver.exception.GtfsFileReadingException;

import java.text.ParseException;
import java.util.HashMap;

public class GtfsLineParser {

    private HashMap<String, Integer> headerColumnMap;
    private String[] headers;
    public GtfsLineParser(String[] headers){
        this.headers = headers;
    }

    public void parseData(String[] items, GtfsDataParser dataParser) throws ParseException {
        if (headerColumnMap == null) {
            headerColumnMap = new HashMap<>();
            for(String header:headers){
                headerColumnMap.put(header, -1);
            }
            for (int i = 0; i < items.length; i++) {
                String header = items[i];
                if (headerColumnMap.containsKey(header)) {
                    headerColumnMap.put(header, i);
                }
            }
        } else {
            HashMap<String, String> data = new HashMap<>();
            for(String header: headers){
                data.put(header, items[headerColumnMap.get(header)]);
            }
            dataParser.parseGtfsData(data);
        }
    }
}
