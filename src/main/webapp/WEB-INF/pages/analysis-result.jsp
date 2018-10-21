<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: darian
  Date: 21.10.18.
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Analiza pretraživanja - podaci</title>
</head>
<body>
<nav>
    <ul>
        <li><b>Menu</b></li>
        <ul>
            <li><a href="${pageContext.request.contextPath}/servleti/add">Add</a></li>
            <li><a href="${pageContext.request.contextPath}/servleti/search">Search</a></li>
            <li><a href="${pageContext.request.contextPath}/servleti/analysis">Analysis</a></li>
        </ul>
    </ul>
</nav>
<form action="" method="post">
    <label>
        Datum od:
        <input type="date" name="date-start">
    </label><br/>
    <label>
        Datum do:
        <input type="date" name="date-end">
    </label><br/>
    Granulacija:
    <label>
        Dani
        <input type="radio" name="time" value="d" checked>
    </label>
    <label>
        Sati
        <input type="radio" name="time" value="h">
    </label>
    <input type="submit">
</form>

<table border="1">
    <thead>
    <tr>
        <jsp:useBean id="headers" scope="request" type="java.util.List"/>
        <c:forEach var="h" items="${headers}">
            <td>${h}</td>
        </c:forEach>
    </tr>
    </thead>
    <jsp:useBean id="results" scope="request" type="java.util.List"/>
    <c:forEach var="r" items="${results}">
        <tr>
            <td>${r.query}</td>
            <c:forEach var="num" items="${r.data}">
                <td>${num}</td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>
</body>
</html>
