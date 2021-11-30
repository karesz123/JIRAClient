package org.jiraclient;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import lombok.Getter;
import lombok.Setter;
import org.jiraclient.authentication.Authentication;
import org.jiraclient.configuration.Configuration;
import org.jiraclient.exception.ConfigurationException;
import org.jiraclient.exception.WorklogJSONProcessingException;
import org.jiraclient.service.WorklogService;

import java.time.LocalDate;

public class JiraClient {

    @Setter @Getter
    private JiraRestClient jiraRestClient;
    @Setter @Getter
    private WorklogService worklogService = new WorklogService();

    public JiraClient() {
        setWorklogService(new WorklogService());
    }

    public void asyncConnect() throws ConfigurationException {
        Authentication authentication = new Configuration().createAuthentication();
        setJiraRestClient(new AsynchronousJiraRestClientFactory().create(authentication.getServerUri(), authentication));
    }

    public String worklogByActualMonth() throws WorklogJSONProcessingException {
        worklogService.setJiraRestClient(jiraRestClient);
        return worklogService.searchForWorklogsForActualMonth();
    }

    public String worklogBetweenDate(LocalDate fromDate, LocalDate toDate) throws WorklogJSONProcessingException {
        worklogService.setJiraRestClient(jiraRestClient);
        return worklogService.searchForWorklogsBetweenDate(fromDate, toDate);
    }
}
