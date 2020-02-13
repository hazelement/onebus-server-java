package com.hazelement.onebus.onebusserver.exceptions;

import java.text.ParseException;

public class GtfsDataParsingException extends ParseException {

    public GtfsDataParsingException(String message) {
        super(message, 0);
    }
}
