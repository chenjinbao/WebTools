<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory" %>
<%
    //设置返回码200，避免浏览器自带的错误页面
    response.setStatus(200);
    //记录日志
    Logger logger = LoggerFactory.getLogger("500.jsp");
    logger.error(exception.getMessage(), exception);
%>

<!DOCTYPE html>
<html>
    <head>
        <title>500 - 系统内部错误</title>

        <link href="<c:url value="/static/bootstrap/3.3.1/css/bootstrap.min.css" />" type="text/css" rel="stylesheet" />
        <link href="<c:url value="/static/styles/signin.css" />" type="text/css" rel="stylesheet" />
        <link href="<c:url value="/static/styles/sticky-footer-navbar.css" />" type="text/css" rel="stylesheet">
        <script src="<c:url value="/static/jquery/jquery-1.11.1.js" />" type="text/javascript"></script>
        <script src="<c:url value="/static/jquery-validation/1.13.1/jquery.validate.js" />" type="text/javascript"></script>
        <script src="<c:url value="/static/jquery-validation/1.13.1/additional-methods.js" />" type="text/javascript"></script>
        <script src="<c:url value="/static/jquery-validation/1.13.1/localization/messages_zh.js" />" type="text/javascript"></script>
    </head>

    <body>
        <h2>500 - 系统发生内部错误.</h2>
    </body>
</html>
