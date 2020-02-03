package com.hazelement.onebus.onebusserver.gtfs_loader;

import java.text.ParseException;
import java.util.HashMap;

public interface GtfsDataParser {

    public void parseGtfsData(HashMap<String, String> data) throws ParseException;
}
