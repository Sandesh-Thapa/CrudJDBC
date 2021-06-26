# CRUD Application with JDBC

Java console application having all CRUD functionality with JDBC

## Database Schema
##### Create database student

```PHP
CREATE DATABASE `student` /*!40100 DEFAULT CHARACTER SET utf8mb4 */
```
##### Create Table student
```PHP
CREATE TABLE `student` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `roll` int(11) NOT NULL,
 `first_name` varchar(12) NOT NULL,
 `last_name` varchar(12) NOT NULL,
 `address` varchar(25) NOT NULL,
 `contact` bigint(11) NOT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4
```
---

### Configuration File

Modify the src/config/Config.java file according to your database credentials and also your root url

``` Java
//Database Configuration
Connection con = DriverManager.getConnection("jdbc:mysql://localhost/student?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","__YOUR_DATABASE_USERNAME", "__YOUR_DATABASE_PASSWORD");
```

JDBC jar file
- mysql-connector-java-6.0.6.jar