<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%response.setStatus(200);%>

<!DOCTYPE html>
<html>
    <head>
        <title>404 - 页面不存在</title>

        <link href="<c:url value="/static/bootstrap/3.3.1/css/bootstrap.min.css" />" type="text/css" rel="stylesheet" />
        <link href="<c:url value="/static/styles/signin.css" />" type="text/css" rel="stylesheet" />
        <link href="<c:url value="/static/styles/sticky-footer-navbar.css" />" type="text/css" rel="stylesheet">
        <script src="<c:url value="/static/jquery/jquery-1.11.1.js" />" type="text/javascript"></script>
        <script src="<c:url value="/static/jquery-validation/1.13.1/jquery.validate.js" />" type="text/javascript"></script>
        <script src="<c:url value="/static/jquery-validation/1.13.1/additional-methods.js" />" type="text/javascript"></script>
        <script src="<c:url value="/static/jquery-validation/1.13.1/localization/messages_zh.js" />" type="text/javascript"></script>
    </head>

    <body>
        <h2>404 - 页面不存在.</h2>
        <p><a href="<c:url value="/"/>">返回首页</a></p>
    </body>
</html>