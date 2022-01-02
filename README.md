# A simple JDBC wrapper

![Java CI with Gradle](https://github.com/Huskehhh/MySQL/workflows/Java%20CI%20with%20Gradle/badge.svg)

A simple, clean and effective JDBC wrapper built on top of [HikariCP](https://github.com/brettwooldridge/HikariCP)

## Setting up your project workspace

### Maven

To integrate this library in your project using maven, add these to your pom.xml

```xml

<repository>
    <id>husk</id>
    <url>https://maven.husk.pro/repository/maven-releases/</url>
</repository>
```

```xml

<dependency>
    <groupId>pro.husk</groupId>
    <artifactId>mysql</artifactId>
    <version>VERSION</version>
</dependency>
```          

### Gradle

Add this to repositories

```kotlin
maven {
    url = uri("https://maven.husk.pro/repository/maven-releases/")
}
```                  

And add this to dependencies

```kotlin
implementation("pro.husk:mysql:VERSION")
```

#### Note: it is assumed that your binary provides the relevant database connector library. [See here](https://github.com/brettwooldridge/HikariCP#popular-datasource-class-names)

#### What if I don't use a build tool

Alternatively, you can also just compile from
source, [download a compiled version](https://github.com/Huskehhh/MySQL/actions) and add it to your classpath, or supply
the files in your project workspace!

## Usage

### Create the database

```
// Create instance
MySQL mysql = new MySQL(url, username, password);
```

### Query

For queries, there's two different ways we can do it!

If we are just processing data, we can do it this way (so we don't have to clean up resources later! (Recommended))

#### Sync query

```
// Execute query
mysql.query("SELECT * from table WHERE id = 1;", results -> {
        if (results != null) {
            // do something
        }
        });
```            

...or you can get the ResultSet itself through

```
    try (ResultSet results = mysql.query(query)) {
        // Do something with the ResultSet

        // Then close statement (the ResultSet will close itself)
        results.getStatement().close();
        } catch (SQLException e) {
           e.printStackTrace();
        }
```

Please make sure you close resources, or you'll end up with a memory leak! D:

### Update

#### Sync update

```Java
int resultCode = mysql.update("INSERT INTO `whitelist` (`uuid`, `date_added`) VALUES ('"+uuid+"', CURRENT_DATE());")

// Check result, do something
```
