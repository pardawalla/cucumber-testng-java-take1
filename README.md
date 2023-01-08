# Test Project: TAKE 1

## Overview
Aim: Create a BDD based framework using Cucumber, TestNG, using a Maven based Java Project. Using Selenium webdriver for web browser automation, and App]ache HTTPClient v5 for the REST API tests. 
Working Tests:
- ***Web UI test:***  
  - *Scenario: Find and verify details of an existing user car listing on Trademe* 
        This scenario using the listing # and the number plate as the keywords. Currently it is checking for the listing with id # 3897609660 and number plate NEC128. If the test fails please check that the listing still exists. If it doesn't update the test data to match an existing listing. 
- ***API tests:***
    - *Scenario: St John is in the list of charities in Trandme Sandbox environment*
        Using the TradeMe Sandbox environment retrieve a list of charities, and confirm that `St John` is included in the list.
    - *Scenario: I can make a POST Request*
        Send a post request, and verify that it was successful using httpbin.org

Only the Chrome browser is supported for the web UI tests. All dev and testing was done using the Chrome browser and the VS Code editor. 

### To run the tests:
- Download the appropriate version of the ChromeDriver, (based on your OS and Chrome ver), from [here](https://chromedriver.chromium.org/downloads), and place it the `../src/test/resources/drivers/` folder. Please ensure the name of the dirver is `chromedriver`, and not `chromedriver.exe` etc. 
- Check the listing `https://www.trademe.co.nz/a/motors/cars/toyota/c-hr/listing/3897609660` still exists. If not, find another used car listing and upate the scenario data in the 'src/test/resources/featureFiles/TmSearchUsedCars.feature' file
- Rename `data.txt.eample` file to `data.txt`
   Note: The POST requests uses bearer auth tokens to make the requests. So the idea of `data.txt.example` is to show how one can store the token in a file. As `data.txt` is not tracked, it provides a safe guard from the token being accidently committed to the repo by mistake. httpbin.org is a test site, so that's why there is no need to update the bearer token, as it excepts fake tokens.
- From the project directory, on a terminal, run the command `mvn clean test` 
- The result run can also be viewed using the html file found at `src/target/cucumber-reports/index.html`

## Dev Env
- Editor: VScode
- OS: macOS Ventura 13.1
- Browser: Chrome Version 108.0.5359.124 (Official Build) (x86_64)


## Requirements
### Need to be installed
- Java: 17
    ```
    % java -version
    JAVA JDK used:
    openjdk 17.0.5 2022-10-18
    OpenJDK Runtime Environment Temurin-17.0.5+8 (build 17.0.5+8)
    OpenJDK 64-Bit Server VM Temurin-17.0.5+8 (build 17.0.5+8, mixed mode, sharing)
    ```
- VS Code Extentions
    - Extension Pack for Java
    - ~~[Cucumber (Gherkin) plugin](https://marketplace.visualstudio.com/items?itemName=alexkrechik.cucumberautocomplete)~~ With this plugin I couldn't get the go to definition to work. So ended up using the other one. 
    - [Cucumber plugin](https://marketplace.visualstudio.com/items?itemName=CucumberOpen.cucumber-official)
- Maven: 3.8.7
    ```
    % mvn -version
    Apache Maven 3.8.7 (b89d5959fcde851dcb1c8946a785a163f14e1e29)
    Maven home: /Users/.../apache-maven-3.8.7
    Java version: 17.0.5, vendor: Eclipse Adoptium, runtime: /Library/Java/JavaVirtualMachines/temurin-17.jdk/Contents/Home
    Default locale: en_AU, platform encoding: UTF-8
    OS name: "mac os x", version: "13.1", arch: "x86_64", family: "mac"
    ```
### Added via pom.xml file. 
- TestNG: 7.7.1
- Cucumber modules for Maven
    - cucumber-java: 7.10.0
    - cucumber-testng: 7.10.0
- Maven Surefile Plugin: 3.0.0-M7
- Selenium
    - selenium-java
    - [Install a Selenium library](https://www.selenium.dev/documentation/webdriver/getting_started/install_library/)
- Aapache HttpClient 5.2.1 API
  - https://hc.apache.org/httpcomponents-client-5.2.x/index.html
  - https://hc.apache.org/httpcomponents-client-5.2.x/current/httpclient5/apidocs/overview-summary.html

## Environment Setup
### Getting the cucumber tests to run
- Using VS Code create the Java Project using the sample archtype
- Using the pom.xml [here](https://github.com/pardawalla/automation-test-v2/blob/main/pom.xml) as an example added the required dependencies. 
- use `mvn test` to run the test. 

### Notes:
The cucumber integrated tests can be run via the terminal using `mvn test`, but not the VS Code test runner!!! ~~No clue why not!!!~~
[x] Figure out how to run testng tests via VS Code test runner (i.e. the tests that show up in the `Testing` window (i.e., when you click the `beaker` icon.)
    - Looks like this is not supported. Possible work arounds. Create a testng.xml and then run it. 
        - [Support run TestNG suites toward XML file #596](https://github.com/microsoft/vscode-java-test/issues/596)
        - [Visual Studio Code: How to Run TestNG Suite]( https://wenijinew.medium.com/visual-studio-code-run-testng-suite-87b50d8e1b7f)
[ ] Figure out how to run and debug tests when running them using `mvn test`. 
[ ] Add more realistic tests

## References
- Cucumber
    - [homepage](https://cucumber.io/)
    - [Checking Assertions - Using TestNG's assertions](https://cucumber.io/docs/cucumber/checking-assertions/#java)
    - [Tools](https://cucumber.io/docs/tools/general/)
    - Tutorials
        - [10 Minute Tutorial](https://cucumber.io/docs/guides/10-minute-tutorial/?lang=java)
        - [Cucumber + Selenium Java, LetCode with Koushik](https://www.youtube.com/playlist?list=PL699Xf-_ilW6oK3_otMtu7BPqiy0VlkE-) - For understanding Cucumber, Selneium and Java
        - [Part 1- Cucumber (BDD) with TestNG](https://www.youtube.com/watch?v=XnkNsl88vho) - For settin up Eclipse and configurations
        - [Cucumber JVM: Hooks](https://zsoltfabok.com/blog/2012/09/cucumber-jvm-hooks/)
        - [Cucumber Data Tables](https://www.baeldung.com/cucumber-data-tables)
        - [Using data table types in Cucumber-JVM](https://www.ontestautomation.com/using-data-table-types-in-cucumber-jvm/)
    - Examples/Sample Code
        - CucumberOptions settings: https://stackoverflow.com/questions/70711506/how-to-execute-test-runner-with-multiple-tags-in-cucumber
- Maven
    - https://maven.apache.org/install.html
    - https://maven.apache.org/download.cgi
- Java
    - Examples/Sample Code
        -[How to convert a String into an ArrayList?](https://stackoverflow.com/questions/7347856/how-to-convert-a-string-into-an-arraylist)
        -[Using data table types in Cucumber-JVM](https://github.com/basdijkstra/ota-examples/tree/master/cucumber-data-tables/src/test/java/stepdefinitions)
- JSON
    - The https://jsontostring.com/ site can be easily used to covert a JSON object to a JSON String.
    - [JSON Escape / Unescape](https://www.freeformatter.com/json-escape.html): Escapes or unescapes a JSON string removing traces of offending characters that could prevent parsing.


## Future Task list
[ ] Look into https://reports.cucumber.io/

## Troubleshooting
- [VSCode Maven error `The compiler compliance specified is 1.7 but a JRE 17 is used`](https://stackoverflow.com/questions/60498063/vscode-maven-error-the-compiler-compliance-specified-is-1-7-but-a-jre-13-is-use)
- Maven build failures:
    - Error
    ```
    SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
    SLF4J: Defaulting to no-operation (NOP) logger implementation
    SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
    ```
    Add the SLF4J dependencies to the pom.xml. See https://stackoverflow.com/questions/7421612/slf4j-failed-to-load-class-org-slf4j-impl-staticloggerbinder

    - In the terminal enter the command `mvn -e test`, and this resulted with the error:
     `org.apache.maven.lifecycle.LifecycleExecutionException: Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:2.22.1:test (default-test) on project take1: There are test failures.`
    To fix this added the surefire plugin. However, that did not resolve the issue. So then looked at `target/surefire-reports/testng-results.xml` and saw the error:
    ```
      <exception class="java.lang.IllegalArgumentException">
    <message>
      <![CDATA[path must exist: /Users/hussain/repos/take1/src/test/resources/featuresFiles/example.feature]]>
    </message>
    ```
    It was then I realized that the path was wrong. It was supposed to be `featureFiles` not `featuresFile`! 
    Lesson: Just use the VSCode copy path options, e.g. right click->Copy Path
