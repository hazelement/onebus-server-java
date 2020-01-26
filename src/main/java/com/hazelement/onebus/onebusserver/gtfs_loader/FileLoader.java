package com.hazelement.onebus.onebusserver.gtfs_loader;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
public class FileLoader{


    private String pathToFile;
    public FileLoader(String pathToFile){
        this.pathToFile = pathToFile;
    }

    public void loadFile(GtfsParser gtfsParser){
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(pathToFile));
            String line = reader.readLine();
            while (line!=null){
                log.debug(line);
                gtfsParser.parseGtfsLine(line);
                line = reader.readLine();
            }
        } catch (IOException e){
            log.error(e.getMessage());
        }

    }
}
