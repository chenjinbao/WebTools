<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <title>射频分析扫描</title>

        <link href="<c:url value="/static/bootstrap/3.3.1/css/bootstrap.min.css" />" type="text/css" rel="stylesheet" />
        <link href="<c:url value="/static/styles/signin.css" />" type="text/css" rel="stylesheet" />
        <link href="<c:url value="/static/styles/sticky-footer-navbar.css" />" type="text/css" rel="stylesheet">
        <script src="<c:url value="/static/jquery/jquery-1.11.1.js" />" type="text/javascript"></script>
        <script src="<c:url value="/static/jquery-validation/1.13.1/jquery.validate.js" />" type="text/javascript"></script>
        <script src="<c:url value="/static/jquery-validation/1.13.1/additional-methods.js" />" type="text/javascript"></script>
        <script src="<c:url value="/static/jquery-validation/1.13.1/localization/messages_zh.js" />" type="text/javascript"></script>
    </head>
    <body>
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <form id="rfaForm" class="form-horizontal" method="post"  action="<c:url value="/rfa" />" enctype="multipart/form-data" role="form">
                    <legend>
                        <small>射频分析扫描</small>
                    </legend>
                    <c:if test="${error != null}" >
                        <div class="alert alert-danger alert-dismissible" role="alert">
                            <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                            ${error}
                        </div>
                    </c:if>
                    <div class="form-group">
                        <label for="ruFile" class="col-sm-3 control-label">RU物理特性&nbsp;&nbsp;</label>
                        <div class="col-sm-9">
                            <input type="file" class="form-control required" id="ruFile" name="ruFile" accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-10 col-sm-offset-1">
                            <button type="submit" class="btn btn-lg btn-primary btn-block">扫描</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <script>
            $(document).ready(function () {
                $("#rfaForm").validate();
            });
        </script>
    </body>
</html>
