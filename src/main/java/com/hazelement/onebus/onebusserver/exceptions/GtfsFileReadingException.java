package com.hazelement.onebus.onebusserver.exceptions;

import java.io.IOException;

public class GtfsFileReadingException extends IOException {

    public GtfsFileReadingException(String message){
        super(message);
    }
}
