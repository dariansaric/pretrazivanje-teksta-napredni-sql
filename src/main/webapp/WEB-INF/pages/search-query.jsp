<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath}/css/jquery-ui.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.2.js"/>
    <script src="${pageContext.request.contextPath}/js/jquery-ui.js"/>
    <script>

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
                    // console.log(data+"_"+status+"_"+er);
                },

            });

        });

    </script>
</head>
<body>
<form id="search" action="" method="post">
    <input type="text" name="query" placeholder="Enter your search"><br>
    <input type="radio" name="operation" value="or" checked>OR
    <input type="radio" name="operation" value="and">AND
    <input type="submit">
</form>
</body>
</html>
