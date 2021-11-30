package org.jiraclient.exception;

import org.codehaus.jackson.JsonProcessingException;

public class WorklogJSONProcessingException extends JsonProcessingException {

    private final static String MESSAGE = "There was an error while processing the workload.";

    public WorklogJSONProcessingException() {
        super(MESSAGE);
    }

    public WorklogJSONProcessingException(String msg) {
        super(msg);
    }
}
