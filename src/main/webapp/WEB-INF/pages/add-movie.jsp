<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="form" scope="request" type="nmbp.p1.web.form.AddForm"/>
<html>
<head>
    <title>Pretražianje teksta</title>
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
    <h2>Unos Filma</h2>
    <form action="" method="post">
        <div class="form-group">
            <label for="title">Naslov</label>
            <input type="text" id="title" class="form-control" placeholder="Naslov filma..." name="title"
                   value="${form.title}">
            <c:if test="${form.hasError('title')}">
                <span class="help-block">${form.getError('title')}</span>
            </c:if>
        </div>
        <div class="form-group">
            <label for="summary">Sažetak</label>
            <input type="text" id="summary" class="form-control" placeholder="Sažetak..." name="summary"
                   value="${form.summary}">
            <c:if test="${form.hasError('summary')}">
                <span class="help-block">${form.getError('summary')}</span>
            </c:if>
        </div>
        <div class="form-group">
            <label for="categories">Kategorije</label>
            <input type="text" id="categories" class="form-control" placeholder="Kategorija;Kategorija..."
                   name="categories" value="${form.categories}">
            <c:if test="${form.hasError('categories')}">
                <span class="help-block">${form.getError('categories')}</span>
            </c:if>
        </div>
        <div class="form-group">
            <label for="description">Opis filma</label>
            <textarea id="description" class="form-control" rows="5" cols="10" placeholder="Kratak opis filma..."
                      name="description">${form.description}</textarea>
            <c:if test="${form.hasError('description')}">
                <span class="help-block">${form.getError('description')}"</span>
            </c:if>
        </div>
        <button type="submit" class="btn btn-primary">Dodaj Film</button>
    </form>
</div>
</body>
</html>
