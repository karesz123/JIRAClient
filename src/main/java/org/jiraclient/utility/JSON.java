package org.jiraclient.utility;

import com.atlassian.jira.rest.client.api.domain.Worklog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.jiraclient.exception.WorklogJSONProcessingException;

import java.util.List;

public class JSON {

    public static String toJSON(List<Worklog> worklogs) throws WorklogJSONProcessingException {
        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JodaModule());
        try {
            return objectMapper.writeValueAsString(worklogs);
        } catch (JsonProcessingException e) {
            throw new WorklogJSONProcessingException(e.getMessage());
        }
    }
}
