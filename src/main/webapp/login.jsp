<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head><meta charset="utf-8"><title>Log in | RecipeRealm</title></head>
<body>
<h1>Log in</h1>
<c:if test="${param.registered == '1'}"><p>Account created. You can now log in.</p></c:if>
<c:if test="${param.error == '1'}"><p>Incorrect email or password.</p></c:if>
<form action="<c:url value='/LoginServlet'/>" method="post">
    <p><label>Email <input name="email" type="email" maxlength="100" autocomplete="username" required></label></p>
    <p><label>Password <input name="password" type="password" maxlength="128" autocomplete="current-password" required></label></p>
    <button>Log in</button>
</form>
<p><a href="<c:url value='/register.jsp'/>">Create an account</a></p>
<p><a href="<c:url value='/index.jsp'/>">Browse recipes</a></p>
</body>
</html>
