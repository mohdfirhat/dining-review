# Dining Review SpringBoot

This project aims to demostrate the CRUD(Create,Read,Update,Delete) operation done on an API. The project has 3 Entity which are User, Restaurant and Review where User-Review and Restaurant-Review both has a One to Many relationship.

It has a static HTML page where user is able to POST,PUT and DELETE data into the H2-database. The database uses JPA and Hibernate to update the SQL database.

# Installation

1. Copy github repository url(https://github.com/mohdfirhat/dining-review.git).
2. Open Intellij and select the Get from VCS button.
3. Paste the Repo Git URL in the provided input window then click on the Clone button.
4. The Repo is now cloned into your Intelliji IDE and ready
5. Run the DiningApplication.java file located at (src/main/java/com/example/dining).
6. TomCat will be running at localhost:4001

# How to Use

When DiningApplication.java is running, open up your browser http://localhost:4001/ .
You wil be able to simple User Table containing all the User available in the H2 database.
You will also be able to Create, Update and Delete User and the data will be reflected.
User will also be able to view the database at http://localhost:4001/h2-console .(username="" password="").
