<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Pretraživanje teksta</title>
</head>
<body>
<nav>
    <ul>
        <li><b>Menu</b></li>
        <ul>
            <li><a href="${pageContext.request.contextPath}/servleti/add">Add</a></li>
            <%--TODO: napraviti ostale linkove--%>
            <li><a href="${pageContext.request.contextPath}/servleti/search">Search</a></li>
            <li>Analysis</li>
        </ul>
    </ul>
</nav>
</body>
</html>
