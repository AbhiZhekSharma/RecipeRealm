<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head><meta charset="utf-8"><title>RecipeRealm</title></head>
<body>
<h1>RecipeRealm</h1>
<c:choose>
    <c:when test="${empty sessionScope.userId}">
        <nav><a href="<c:url value='/login.jsp'/>">Log in</a> · <a href="<c:url value='/register.jsp'/>">Register</a></nav>
    </c:when>
    <c:otherwise>
        <nav><a href="<c:url value='/AddRecipeServlet'/>">Add recipe</a></nav>
    </c:otherwise>
</c:choose>
<c:if test="${param.saved == '1'}"><p>Recipe saved.</p></c:if>
<h2>Recent recipes</h2>
<c:if test="${empty recipes}"><p>No recipes have been added yet.</p></c:if>
<c:forEach items="${recipes}" var="recipe">
    <article>
        <h3><c:out value="${recipe.title}"/></h3>
        <h4>Ingredients</h4>
        <p style="white-space: pre-wrap"><c:out value="${recipe.ingredients}"/></p>
        <h4>Steps</h4>
        <p style="white-space: pre-wrap"><c:out value="${recipe.steps}"/></p>
    </article>
</c:forEach>
</body>
</html>
