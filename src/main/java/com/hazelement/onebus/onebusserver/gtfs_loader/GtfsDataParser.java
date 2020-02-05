package com.hazelement.onebus.onebusserver.gtfs_loader;

import java.text.ParseException;
import java.util.HashMap;

public interface GtfsDataParser {

    void parseGtfsData(HashMap<String, String> data) throws ParseException;
}
