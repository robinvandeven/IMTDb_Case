package com.testcoders.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Logging {
    public Logger logger = LogManager.getLogger(Logging.class);
    }



