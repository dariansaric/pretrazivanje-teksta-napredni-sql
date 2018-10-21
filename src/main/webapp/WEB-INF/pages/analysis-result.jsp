<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Napredni SQL - analiza pretraživanja</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <ul class="navbar-nav">
        <li class="nav-item active">
            <button class="btn btn-info" onclick="location.href='${pageContext.request.contextPath}/'">Naslovna</button>
        </li>
        <li class="nav-item">
            <button class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/servleti/add'">
                Add
            </button>
            <%--<a class="nav-link" href="${pageContext.request.contextPath}/servleti/add">Add</a>--%>
        </li>
        <li class="nav-item">
            <button class="btn btn-primary"
                    onclick="location.href='${pageContext.request.contextPath}/servleti/search'">Search
            </button>
            <%--<a class="nav-link" href="${pageContext.request.contextPath}/servleti/search">Search</a>--%>
        </li>
        <li class="nav-item">
            <button class="btn btn-primary"
                    onclick="location.href='${pageContext.request.contextPath}/servleti/analysis'">Analysis
            </button>
            <%--<a class="nav-link" href="${pageContext.request.contextPath}/servleti/analysis">Analysis</a>--%>
        </li>
    </ul>
</nav>
<div class="container">
    <h2>Analiza povijesti pretraživanja pomoću pivotiranja</h2>

    <form action="" method="post">
        <div class="form-group">
            <label for="start">Datum od:</label>
            <input type="date" name="date-start" id="start">
        </div>
        <div class="form-group">
            <label for="end">Datum do:</label>
            <input type="date" name="date-end" id="end">
        </div>
        <div class="form-check form-check-inline">
            <label class="form-check-label">
                <input class="form-check-input" type="radio" value="d" name="time" checked>
                Dani
            </label>
        </div>
        <br/>
        <div class="form-check form-check-inline">
            <label class="form-check-label">
                <input class="form-check-input" type="radio" value="h" name="time">
                Sati
            </label>
        </div>
        <br/>
        <button type="submit" class="btn btn-primary">Đorđe, analiziraj</button>
    </form>
</div>
<div class="container">
    <table class="table table-hover table-striped table-bordered">
        <thead class="thead-dark">
        <tr>
            <jsp:useBean id="headers" scope="request" type="java.util.List"/>
            <c:forEach var="h" items="${headers}">
                <th>${h}</th>
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
</div>

</body>
</html>
