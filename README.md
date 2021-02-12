# Mars Rover Kata
This console application simulates driving the Mars Rover Kata using a Spring Boot Console Application. 
Even NASA has realized the Power of Spring.

## Requirements
* Java 11+
* Maven

Tested on Java 11 - AdoptOpenJDK.

## Usage
The application is package to run from the command line, and expects four non-named parameters:
1. Initial X location of the Kata as int between -10 and 10
2. Initial Y location of the Kata as int between -10 and 10
3. Direction of the Kata as String: NORTH, EAST, SOUTH, WEST
4. Driving instruction using:
   * F -> Move forward on current heading
   * B -> Move backwards on current heading
   * L -> Rotate left by 90 degrees
   * R -> Rotate right by 90 degrees
   * Example: FFRBLLFF

Example Spring Maven command:
```
mvn spring-boot:run -D spring-boot.run.arguments="2 3 NORTH FFRBLLFF"
```

Or pure jar:
```
java -jar .\mars-rover-kata-1.0.0.jar 2 3 NORTH FFRBLLFF
```
## CheckStyle
The library is using Google Java Style, CheckStyle configuration included: ./google_checks.xml