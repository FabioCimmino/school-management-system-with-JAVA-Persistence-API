# school-management-system-with-JAVA-Persistence-API

In this project I designed and built a system for the management of middle and high schools.  
The framework I have decided to use to implement JAVA Persistence API is Eclipselink, while for the database I used MySQL. Maven was used for dependency management.  
Below is the UML diagram of the domain model:  
![UML diagram](UML.png)


##How to run it 

To run this project you need to create a MySQL database named "**mydb**" on the localhost port: 3306. The MySQL Server version is 5.7.
In the **persistence.xml** file the values of *user* and *password* must be changed according to your configuration.

To import the project follow the following steps:
1. Download the project from the gitLab repository
2. Open Eclipse
3. Select **File -> import -> Existing Maven Projects**
4. Select the downloaded project
5. After importing the project, make sure that the **Compiler compilance level** setting is set to 1.6 (**Project -> Properties -> Java Compiler -> Compiler compliance level**)

In case you fail to run the project tests for the following error "*java compiler level does not match the version of the installed java project facet*", please follow the steps below:
- Project -> Properties -> Project Facets
- Set the version of "*Java*" to 1.6
