<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
    <head>
        <title>Web工具:<sitemesh:title/></title>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
        <meta http-equiv="Cache-Control" content="no-store" />
        <meta http-equiv="Pragma" content="no-cache" />
        <meta http-equiv="Expires" content="0" />

        <link type="image/x-icon" href="<c:url value="/static/images/favicon.ico" />" rel="shortcut icon">

        <sitemesh:head/>
    </head>

    <body>
        <%@ include file="/WEB-INF/layouts/header.jsp"%>

        <div class="container">
            <sitemesh:body/>
        </div>

        <%@ include file="/WEB-INF/layouts/footer.jsp"%>
        <script src="<c:url value="/static/bootstrap/3.3.0/js/bootstrap.js" />" type="text/javascript"></script>
        <script src="<c:url value="/static/js/ie10-viewport-bug-workaround.js" />" type="text/javascript"></script>
    </body>
</html>