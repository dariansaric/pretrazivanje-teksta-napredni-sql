<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: darian
  Date: 21.10.18.
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="" method="post">
    Datum od: <input type="date" name="date-start"><br/>
    Datum do: <input type="date" name="date-end"><br/>
    Granulacija:
    Dani<input type="radio" name="time" value="d" checked> Sati<input type="radio" name="time" value="h">
    <input type="submit">
</form>

<table>
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
