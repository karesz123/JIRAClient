package org.jiraclient.configuration;

import org.jiraclient.authentication.Authentication;
import org.jiraclient.exception.ConfigurationException;

import java.io.IOException;
import java.util.Properties;

final public class Configuration {

    private final String CONFIG_FILE = "user-settings.properties";

    public Authentication createAuthentication() throws ConfigurationException {
        try {
            return basicHttpAuthenticationHandler(load());
        }
        catch (IOException ioException) {
            throw new ConfigurationException();
        }
    }

    private Properties load() throws IOException {
        Properties properties = new Properties();
        properties.load(getClass().getClassLoader().getResourceAsStream(CONFIG_FILE));
        return properties;
    }

    private Authentication basicHttpAuthenticationHandler(Properties properties) {
        return new Authentication(properties.getProperty("name"), properties.getProperty("password"), properties.getProperty("URI"));
    }
}
