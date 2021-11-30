package org.jiraclient.service;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.Worklog;
import lombok.Setter;
import org.jiraclient.exception.WorklogJSONProcessingException;
import org.jiraclient.utility.JSON;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.LinkedList;
import java.util.List;

public class WorklogService {

    private final String WORKLOG_JQL = "worklogDate > %s and worklogDate < %s and worklogAuthor = %s";
    private final String CURRENT_USER = "tru-belo";

    @Setter
    private JiraRestClient jiraRestClient;

    public String searchForWorklogsForActualMonth() throws WorklogJSONProcessingException {
        return searchForWorklogsBetweenDate(YearMonth.now().atDay(1), YearMonth.now().atEndOfMonth());
    }

    public String searchForWorklogsBetweenDate(LocalDate fromDate, LocalDate toDate) throws WorklogJSONProcessingException {
        List<Worklog> authorsWorklogs = new LinkedList<>();
        issuesByMonthAndWorklogsAuthor(fromDate, toDate, CURRENT_USER)
                .forEach(issues -> authorsWorklogs.addAll(filterWorklogsByAuthor(worklogsForIssue(issues), CURRENT_USER)));

        return JSON.toJSON(authorsWorklogs);
    }

    public Iterable<Issue> issuesByMonthAndWorklogsAuthor(LocalDate startDate, LocalDate endDate, String worklogAuthor) {
        return jiraRestClient.getSearchClient()
                .searchJql(String.format(WORKLOG_JQL, startDate, endDate, worklogAuthor))
                .claim()
                .getIssues();
    }

    private Iterable<Worklog> worklogsForIssue(Issue issue) {
        return jiraRestClient.getIssueClient()
                .getIssue(issue.getKey())
                .claim()
                .getWorklogs();
    }

    private List<Worklog> filterWorklogsByAuthor(Iterable<Worklog> worklogs, String author){
        List<Worklog> authorsWorklogs = new LinkedList<>();
        worklogs.forEach(worklog -> {
            if (worklog.getAuthor().getName().equals(author))
                authorsWorklogs.add(worklog);
        });
        return authorsWorklogs;
    }
}
