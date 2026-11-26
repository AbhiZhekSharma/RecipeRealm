<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head><meta charset="utf-8"><title>Add recipe | RecipeRealm</title></head>
<body>
<h1>Add a recipe</h1>
<c:if test="${param.error == '1'}"><p>Enter a title, ingredients and steps within the size limits.</p></c:if>
<form action="<c:url value='/AddRecipeServlet'/>" method="post">
    <p><label>Title <input name="title" maxlength="100" required></label></p>
    <p><label>Ingredients <textarea name="ingredients" required></textarea></label></p>
    <p><label>Steps <textarea name="steps" required></textarea></label></p>
    <button>Save recipe</button>
</form>
<p><a href="<c:url value='/index.jsp'/>">Back to recipes</a></p>
</body>
</html>
