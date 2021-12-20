<p align="center">
  <img src="https://github.com/Achille-Deribreux/P6_Deribreux_Achille/blob/develop/logo.png" alt="PayMyBuddy Logo"/>
</p>

# OpenClassrooms Application Developer Java Project 6

A local person-to-person payment application with mySql DB.<br>
Spring boot backend <br>
Vue.js frontend

## Prerequisites

Things you need : 

- Java 17
- Maven
- Mysql 8+

## Install project 

<p><u>Database</u></p>
<ul>
<li>Create your DB</li>
<li>Execute "sql_structure.sql" in your db to create your table structure</li>
<li>Execute "sql_demodata.sql" to have demo data in your tables </li>
</ul>
<p>


<p><u>Back-end</u></p>
<ul>
<li>Setup your DB address in application.properties at "spring.datasource.url"</li>
<li>Launch it with "mvn spring-boot:run -Dspring-boot.run.arguments="--spring.datasource.username=YOUR_USERNAME --spring.datasource.password=YOUR_PASSWORD"</li>
</ul>



<p><u>Front-end</u></p>
<ul>
<li>Go into /front-end </li>
<li>run npm install</li>
<li>run "npm run serve" </li>
<li>Access the app at http://localhost:8080/ </li>
<li>demo users mail are "a@d.be, j@d.be, john@doe.com" and password is : "mdp"</li>
</ul>


### Class Diagram

<img src="https://github.com/Achille-Deribreux/P6_Deribreux_Achille/blob/develop/P6_UML_DIAGRAMME_CLASSE.jpg" alt="Class Diagram"/>

### UML Data

<img src="https://github.com/Achille-Deribreux/P6_Deribreux_Achille/blob/develop/P6_UML_BDD.jpg" alt="Data Diagram"/>




