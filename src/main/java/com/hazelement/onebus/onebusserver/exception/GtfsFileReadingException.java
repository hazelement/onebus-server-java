package com.hazelement.onebus.onebusserver.exception;

import java.io.IOException;

public class GtfsFileReadingException extends IOException {

    public GtfsFileReadingException(String message){
        super(message);
    }
}
