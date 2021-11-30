# JIRA Client

This project aims to create an application which can gather the worklog data from the Jira software. 
The application can connect to the Jira Software via its API. The main class is the JiraClient class 
which exposes the available service methods.

## Requirements
- JDK 11

## Properties
The application is uses the properties from the `user-settings.properties` file to connect to the JIRA. 
This file should be created by the user with credentials. The `user-settings.properties.example` file serve as and example
of the above property file 

## Steps to run
1. **Clone the project**
   ```bash
   git clone https://github.com/karesz123/JIRAClient.git
   cd DevelopmentSetUp
   ```
2. Create the  `user-settings.properties` file on the base of the ` user-settings.properties.exmplae` file
3. Run the gradle build.
    `./gradlew clean build`