<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
        <%--$('#search').keypress(function() {--%>
        <%--$.ajax({--%>
        <%--url: "${pageContext.request.contextPath}/servleti/Auto",--%>
        <%--type: "get",--%>
        <%--data: '',--%>
        <%--success: function(data) {--%>
        <%--$('#search').autocomplete({--%>
        <%--source:data--%>
        <%--});--%>
        <%--}--%>
        <%--});--%>
        //    }) ;
        // });
        <%--$('#search').keypress(function () {--%>
        <%--$.ajax({--%>
        <%--url: "${pageContext.request.contextPath}/servleti/Auto",--%>
        <%--type: "post",--%>
        <%--data: '',--%>
        <%--success: function (data) {--%>
        <%--$("#search").autocomplete({--%>
        <%--source: data--%>
        <%--});--%>

        <%--}, error: function (data, status, er) {--%>
        <%--// console.log(data+"_"+status+"_"+er);--%>
        <%--},--%>

        <%--});--%>

        <%--});--%>
        <%--$(document).ready(function() {--%>
        <%--$("#search").autocomplete({--%>
        <%--source: "${pageContext.request.contextPath}/servleti/Auto"--%>
        <%--});--%>
        <%--});--%>
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
<form action="" method="post">
    <input id="query" type="text" name="query"><br>
    <input type="radio" name="operation" value="or" checked>OR
    <input type="radio" name="operation" value="and">AND
    <input type="submit">
</form>
</body>
</html>
