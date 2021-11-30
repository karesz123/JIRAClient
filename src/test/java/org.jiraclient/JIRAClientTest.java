package org.jiraclient;

import org.jiraclient.exception.ConfigurationException;
import org.jiraclient.exception.WorklogJSONProcessingException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.YearMonth;


@Test
public class JIRAClientTest {

    public void connectTest() {
        JiraClient jiraClient = new JiraClient();
        try {
            jiraClient.asyncConnect();
            jiraClient.getJiraRestClient()
                    .getUserClient()
                    .getUser("test")
                    .claim();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void worklogActualMonthTest() throws ConfigurationException, WorklogJSONProcessingException {
        JiraClient jiraClient = new JiraClient();
        jiraClient.asyncConnect();

        String jsonWoklogs = jiraClient.worklogByActualMonth();
        Assert.assertNotEquals(jsonWoklogs, "[]");
        Assert.assertNotEquals(jsonWoklogs, null);
    }

    public void worklogNextMonthTest() throws ConfigurationException, WorklogJSONProcessingException {
        JiraClient jiraClient = new JiraClient();
        jiraClient.asyncConnect();

        Assert.assertEquals(jiraClient.worklogBetweenDate(
                YearMonth.now().plusMonths(1).atDay(1),
                YearMonth.now().plusMonths(1).atEndOfMonth()), "[]");
    }
}
