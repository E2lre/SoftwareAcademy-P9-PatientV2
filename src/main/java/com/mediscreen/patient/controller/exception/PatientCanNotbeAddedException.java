package com.mediscreen.patient.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class PatientCanNotbeAddedException extends Exception {
    private static final Logger logger = LogManager.getLogger(PatientCanNotbeAddedException.class);
    public PatientCanNotbeAddedException(String s) {
        super(s);
        logger.error(s);
    }
}
