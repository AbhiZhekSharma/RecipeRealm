
# RecipeRealm – Share and Discover Recipes Online

## 👨‍🍳 Project Overview
RecipeRealm is a Java-based web application that allows users to create, share, and explore recipes. Users can register, log in, add recipes with images, and comment on other users’ creations. Admins can moderate the content to ensure quality and safety.

## 🛠 Tech Stack
- **Frontend:** HTML, CSS, JSP
- **Backend:** Java (Servlets, JDBC)
- **Database:** MySQL
- **Tools:** Apache Tomcat, Eclipse IDE, GitHub, XAMPP
- **Optional:** Bootstrap for styling

## 📁 Folder Structure
```
RecipeRealm/
├── WebContent/
│   ├── index.jsp
│   ├── login.jsp
│   ├── register.jsp
│   ├── addRecipe.jsp
│   └── viewRecipe.jsp
├── src/com/reciperealm/
│   ├── LoginServlet.java
│   ├── RegisterServlet.java
│   ├── AddRecipeServlet.java
│   ├── DBUtil.java
│   └── AdminServlet.java
├── WEB-INF/
│   └── web.xml
├── lib/
│   └── mysql-connector.jar
```

## 🧠 How to Run
1. Clone this repository.
2. Import the project into Eclipse IDE.
3. Set up MySQL and run the SQL script from `/sql/RecipeRealm_DB_Schema.sql`.
4. Add MySQL JDBC driver in `/lib`.
5. Deploy the project on Apache Tomcat.
6. Visit `http://localhost:8080/RecipeRealm` to start using the application.

## 📸 Screenshots
(Add screenshots of your UI here.)

## 🔐 Roles
- **User:** Register, Login, Add/View Recipes, Comment
- **Admin:** Approve Recipes, Moderate Comments

## 📌 Author
Abhishek – TheCodeOfTheDay | Galgotias University
