# Test Project: TAKE 1

## Overview
Aim: Create a BDD based framework using Cucumber, TestNG, using a Maven based Java Project. 

## Dev Env
- Editor: VScode
- OS: macOS Ventura 13.1


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

## Environment Setup
### Getting the cucumber tests to run
- Using VS Code create the Java Project using the sample archtype
- Using the pom.xml here as an example added the required dependencies. 
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
    - [10 Minute Tutorial](https://cucumber.io/docs/guides/10-minute-tutorial/?lang=java)
- Maven
    - https://maven.apache.org/install.html
    - https://maven.apache.org/download.cgi
- Setup
    - [Part 1- Cucumber (BDD) with TestNG](https://www.youtube.com/watch?v=XnkNsl88vho)

## Troubleshooting
- [VSCode Maven error `The compiler compliance specified is 1.7 but a JRE 17 is used`](https://stackoverflow.com/questions/60498063/vscode-maven-error-the-compiler-compliance-specified-is-1-7-but-a-jre-13-is-use)
- Maven build failures:

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