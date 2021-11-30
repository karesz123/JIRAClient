package org.jiraclient.authentication;

import com.atlassian.jira.rest.client.auth.BasicHttpAuthenticationHandler;
import lombok.Getter;
import lombok.Setter;

import java.net.URI;

public class Authentication extends BasicHttpAuthenticationHandler {

    @Setter @Getter
    private URI serverUri;

    public Authentication(String username, String password, String serverUri) {
        super(username, password);
        setServerUri(URI.create(serverUri));
    }
}
