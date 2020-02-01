package com.hazelement.onebus.onebusserver.gtfs_loader;

import java.util.HashMap;

public interface GtfsDataParser {

    public void parseGtfsData(HashMap<String, Object> data);
}
