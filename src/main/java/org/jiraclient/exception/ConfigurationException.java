package org.jiraclient.exception;

import java.io.IOException;

public class ConfigurationException extends IOException {

    private final static String MESSAGE = "There was an error while loading the configuration file.";

    public ConfigurationException() {
        super(MESSAGE);
    }
}
