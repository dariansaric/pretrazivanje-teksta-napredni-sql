<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Pretraživanje teksta</title>
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
    <label for="query">Upit</label><input id="query" type="text" name="query" placeholder='npr. "Lord Of Dance" Tarzan'><br>
    <label>OR
        <input type="radio" name="operation" value="or" checked>
    </label>
    <label>AND
        <input type="radio" name="operation" value="and">
    </label>
    <input type="submit">
</form>
</body>
</html>
