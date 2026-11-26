<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head><meta charset="utf-8"><title>Register | RecipeRealm</title></head>
<body>
<h1>Create an account</h1>
<c:if test="${param.error == 'invalid'}"><p>Enter a name, a valid email and a password of 8–128 characters.</p></c:if>
<c:if test="${param.error == 'duplicate'}"><p>An account already uses this email.</p></c:if>
<form action="<c:url value='/RegisterServlet'/>" method="post">
    <p><label>Name <input name="name" maxlength="100" autocomplete="name" required></label></p>
    <p><label>Email <input name="email" type="email" maxlength="100" autocomplete="username" required></label></p>
    <p><label>Password <input name="password" type="password" minlength="8" maxlength="128" autocomplete="new-password" required></label></p>
    <button>Register</button>
</form>
<p><a href="<c:url value='/login.jsp'/>">Log in</a></p>
</body>
</html>
