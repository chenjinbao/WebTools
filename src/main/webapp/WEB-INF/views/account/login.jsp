<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
    <head>
        <title>登录页</title>

        <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
        <meta http-equiv="Cache-Control" content="no-store" />
        <meta http-equiv="Pragma" content="no-cache" />
        <meta http-equiv="Expires" content="0" />

        <link href="<c:url value="/static/bootstrap/3.3.1/css/bootstrap.min.css" />" type="text/css" rel="stylesheet" />
        <link href="<c:url value="/static/styles/signin.css" />" type="text/css" rel="stylesheet" />
        <link href="<c:url value="/static/styles/sticky-footer-navbar.css" />" type="text/css" rel="stylesheet">
        <script src="<c:url value="/static/jquery/jquery-1.11.1.js" />" type="text/javascript"></script>
        <script src="<c:url value="/static/jquery-validation/1.13.1/jquery.validate.js" />" type="text/javascript"></script>
        <script src="<c:url value="/static/jquery-validation/1.13.1/localization/messages_zh.js" />" type="text/javascript"></script>
    </head>

    <body>
        <div class="container">
            <form id="loginForm" action="<c:url value="/login" />" method="post"  class="form-signin" role="form">
                <%
                    String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
                    if (error != null) {
                %>
                <div class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    登录失败，请重试.
                </div>
                <%
                    }
                %>
                <h2 class="form-signin-heading">请登录</h2>
                <input type="text" id="username" name="username" class="form-control required" placeholder="用户名" required autofocus>
                <input type="password" id="password" name="password" class="form-control required" placeholder="密码" required>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" id="rememberMe" name="rememberMe"> 记住我
                    </label>
                </div>

                <div class="control-group">
                    <div class="controls">
                        <input id="submit_btn" class="btn btn-lg btn-primary btn-block" type="submit" value="登录"/>
                    </div>
                </div>
            </form>
        </div>

        <script src="<c:url value="/static/bootstrap/3.3.1/js/bootstrap.js" />" type="text/javascript"></script>
        <script src="<c:url value="/static/js/ie10-viewport-bug-workaround.js" />" type="text/javascript"></script>
        <script>
            $(document).ready(function () {
                $("#loginForm").validate();
            });
        </script>
    </body>
</html>
