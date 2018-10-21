<%--
  Created by IntelliJ IDEA.
  User: darian
  Date: 21.10.18.
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Analiza pretraživanja</title>
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
</body>
</html>
