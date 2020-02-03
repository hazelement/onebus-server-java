package com.hazelement.onebus.onebusserver.gtfs_loader;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

@Slf4j
public class FileLoader{


    private String pathToFile;
    public FileLoader(String pathToFile){
        this.pathToFile = pathToFile;
    }

    public void loadFile(FileLineParser fileLineParser) throws IOException {
        BufferedReader reader;
        try{
            log.debug("Loading file at " + pathToFile);
            reader = new BufferedReader(new FileReader(pathToFile));
            String line = reader.readLine();
            while (line!=null){
                log.debug(line);
                try {
                    fileLineParser.parseFileLine(line);
                } catch (ParseException e){
                    throw new IOException(e.getMessage());
                }
                line = reader.readLine();
            }
        } catch (IOException e){
            log.error(e.getMessage());
            throw e;
        }

    }
}
