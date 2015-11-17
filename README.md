# GitHub Watchman

## What is GitHub Watchman

GitHub Watchman is a tool that enable you search some keywords within some users then generate a report in html.
e.g. `java -jar <watch-man-version.jar> <path a formatted csv file> <keywords you want to search>`

## How can I use it

1. You need to install [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) first

2. Run `./gradlew fatjar` to generate a jar file under `build/libs` directory, the jar name will look like `watchman-all-0.0.1.jar`

3. Run `java -jar ./build/libs/watchman-all-0.0.1.jar <Path to csv file> <keywords>`

## What does a formatted csv file look like

There are basically two columns, first column would be `Name`, while the second would be `Github Account`.

------------------------------------------------
| Name | Github Account                        |
------------------------------------------------
| test |  https://github.com/test              |
------------------------------------------------
| testwhatever | http://github.com/testwhatever|
------------------------------------------------
