<%--
  Created by IntelliJ IDEA.
  User: Rafi
  Date: 13/06/2023
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>hello</h1>
    <h1 id="email"><%= request.getAttribute("email")%></h1>

    <script src="${pageContext.request.contextPath}/public/assets/js/hello.js"></script>
</body>
</html>
