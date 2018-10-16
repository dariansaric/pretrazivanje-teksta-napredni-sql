<%--
  Created by IntelliJ IDEA.
  User: darian
  Date: 16.10.18.
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/servleti/search" method="post">
    <input type="text" name="query" placeholder="Enter your search"><br>
    <input type="radio" name="operation" value="or" checked>OR
    <input type="radio" name="operation" value="and">AND
    <input type="submit">
</form>
Used SQL query:<br>
${requestScope.get("sql")}<br>
Rezultati:<br>
<jsp:useBean id="results" scope="request" type="java.util.List"/>
<c:forEach var="r" items="${results}">
    <p>${r}</p>
</c:forEach>
</body>
</html>
