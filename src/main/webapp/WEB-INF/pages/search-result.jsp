<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Pretražianje teksta</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/jquery-ui.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.2.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
    <script>
        $(document).ready(function () {
            $('#query').keypress(function () {
                $('#query').autocomplete({
                    source: "${pageContext.request.contextPath}/servleti/Auto",
                    select: function (event, ui) {
                        $('#query').val(ui.item.value);
                    }
                });
            });
        });
    </script>
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
    <h2>Pretraži filmove</h2>
    <form action="" method="post">
        <input type="text" name="query" class="form-control" placeholder='npr. "Lord Of Dance" Tarzan'>
        <div class="form-check form-check-inline">
            <label class="form-check-label">
                <input class="form-check-input" type="radio" value="or" name="operation" checked>
                OR
            </label>
        </div>
        <div class="form-check form-check-inline">
            <label class="form-check-label">
                <input class="form-check-input" type="radio" value="and" name="operation">
                AND
            </label>
        </div>
        <br/>
        <button type="submit" class="btn btn-primary">Pretraži</button>
    </form>
</div>
<div class="container">
    <label for="sql">Korišteni SQL upit:</label>
    <pre id="sql">
        ${requestScope.get("sql")}
    </pre>
</div>
<div class="container">
    <h3 class="mb-3 font-italic text-primary">Rezultati:</h3>
    <jsp:useBean id="results" scope="request" type="java.util.List"/>
    <c:forEach var="r" items="${results}">
        <p>${r}</p>
    </c:forEach>
</div>
<%--<jsp:useBean id="results" scope="request" type="java.util.List"/>--%>
<%--<c:forEach var="r" items="${results}">--%>
<%--<p>${r}</p>--%>
<%--</c:forEach>--%>
</body>
</html>
