<%--
  Created by IntelliJ IDEA.
  User: darian
  Date: 16.20.18.
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link href="css/jquery-ui.css" rel="stylesheet">
    <script src="js/jquery-1.11.2.js"/>
    <script src="js/jquery-ui.js"/>
    <script>
        $(function () {

            $('#search').keypress(function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/servleti/Auto",
                    type: "post",
                    data: '',
                    success: function (data) {
                        $("#search").autocomplete({
                            source: data
                        });

                    }, error: function (data, status, er) {
                        // console.log(data + "_" + status + "_" + er);
                    },

                });

            });

        });
    </script>
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
<form id="search" action="${pageContext.request.contextPath}/servleti/search" method="post">
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
