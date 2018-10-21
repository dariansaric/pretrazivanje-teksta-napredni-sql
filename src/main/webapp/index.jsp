<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Pretraživanje teksta</title>
    <!-- Bootstrap core CSS -->
    <link href="http://getbootstrap.com/docs/4.1/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body class="text-center">

<h1 class="h3 mb-3 font-italic text-primary ">Napredni Modeli i Baze Podataka - Pretraživanje Teksta i Napredni SQL</h1>
<img class="mb4"
     src="https://azure.microsoft.com/svghandler/postgresql/?width=600&height=315" alt="" width="150" height="100">
<img class="mb-4" src="http://www.clker.com/cliparts/7/4/b/6/13373556381963102819fer.logo_-hi.png" alt="" width="100"
     height="75">
<br/><br/>
<button class="btn btn-lg btn-primary" onclick="location.href='${pageContext.request.contextPath}/servleti/add'">Add
</button>
<button class="btn btn-lg btn-primary" onclick="location.href='${pageContext.request.contextPath}/servleti/search'">
    Search
</button>
<button class="btn btn-lg btn-primary" onclick="location.href='${pageContext.request.contextPath}/servleti/analysis'">
    Analysis
</button>
</body>
</html>
