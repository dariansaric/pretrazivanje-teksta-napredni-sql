<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="form" scope="request" type="nmbp.p1.web.form.AddForm"/>
<html>
<head>
    <title>Pretražianje teksta</title>
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
        Title:
        <input type="text" name="title" value="${form.title}">
    </label><br>
    <c:if test="${form.hasError('title')}">
        <div class="greska"><c:out value="${form.getError('title')}"/></div>
    </c:if>

    <label>
        Summary
        <input type="text" name="summary" value="${form.summary}">
    </label><br>
    <c:if test="${form.hasError('summary')}">
        <div class="greska"><c:out value="${form.getError('summary')}"/></div>
    </c:if>

    <label>
        Categories:
        <input type="text" name="categories" value="${form.categories}">
    </label><br>
    <c:if test="${form.hasError('categories')}">
        <div class="greska"><c:out value="${form.getError('categories')}"/></div>
    </c:if>

    <label>
        Description:
        <textarea rows="25" cols="100" name="description">${form.description}</textarea>
    </label><br>
    <c:if test="${form.hasError('description')}">
        <div class="greska"><c:out value="${form.getError('description')}"/></div>
    </c:if>

    <input type="submit" value="Add">
</form>
</body>
</html>
