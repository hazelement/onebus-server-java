package com.hazelement.onebus.onebusserver.gtfs_loader;

import java.text.ParseException;

public interface FileLineParser {
    public void parseFileLine(String line) throws ParseException;
}
