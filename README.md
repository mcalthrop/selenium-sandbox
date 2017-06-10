# selenium-sandbox

_playing with Selenium_


## Prerequisites

You will need the following installed:

- Java 8
- Maven 3.5
- Google Chrome


## Running

### Normal

To run the Selenium tests:

```bash
mvn clean verify
```

This will download all the drivers needed in order run the Selenium tests, and then run those tests.

> **NOTE**: by default, classes containing Selenium tests must end it `IT` (Integration Test) to be picked up.

### Re-download drivers

If you want to force Maven to re-download the drivers, delete the `selenium_standalone_binaries` directory.

Alternatively, run the following command:

```bash
mvn clean verify -Doverwrite.binaries=true
```


## Credits

Thanks to [github.com/Ardesco/Selenium-Maven-Template](https://github.com/Ardesco/Selenium-Maven-Template) for much of the starter code for the `pom.xml`.
