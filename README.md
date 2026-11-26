# RecipeRealm

A Java web app for registering, logging in, publishing recipes and browsing the latest 50 submissions. It uses Java Servlets, JSP, JDBC and MySQL.

## Run locally

Install JDK 17 or newer, Maven, Apache Tomcat 9 and MySQL 8. The servlet code uses the Servlet 4 `javax.servlet` API.

1. For a new database, run `mysql -u root -p < RecipeRealm_DB_Schema.sql`. For an existing database created with either original schema, back it up and run `mysql -u root -p < sql/upgrade-legacy.sql` once. Existing passwords are hashed and the plaintext field is cleared when each user next logs in successfully.
2. Create a dedicated MySQL user and grant it `SELECT`, `INSERT` and `UPDATE` on `reciperealm.*`. Set `RECIPEREALM_DB_USER` and `RECIPEREALM_DB_PASSWORD` in the environment that launches Tomcat. The optional `RECIPEREALM_DB_URL` defaults to `jdbc:mysql://localhost:3306/reciperealm`.
3. Run `mvn clean package` and copy `target/RecipeRealm.war` into Tomcat's `webapps` directory.
4. Start Tomcat and open http://localhost:8080/RecipeRealm/.

## Smoke test

Register, log in, save a recipe and confirm it appears on the home page after refreshing. In a private window, open the Add Recipe URL and confirm it redirects to login.
