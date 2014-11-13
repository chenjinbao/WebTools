<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <title>动态管理扫描</title>

        <link href="<c:url value="/static/bootstrap/3.3.0/css/bootstrap.min.css" />" type="text/css" rel="stylesheet" />
        <link href="<c:url value="/static/styles/signin.css" />" type="text/css" rel="stylesheet" />
        <link href="<c:url value="/static/styles/sticky-footer-navbar.css" />" type="text/css" rel="stylesheet">
        <script src="<c:url value="/static/jquery/jquery-1.11.1.min.js" />" type="text/javascript"></script>
        <script src="<c:url value="/static/jquery-validation/1.13.1/jquery.validate.min.js" />" type="text/javascript"></script>
        <script src="<c:url value="/static/jquery-validation/1.13.1/additional-methods.min.js" />" type="text/javascript"></script>
        <script src="<c:url value="/static/jquery-validation/1.13.1/localization/messages_zh.min.js" />" type="text/javascript"></script>
    </head>
    <body>
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <form id="ddmForm" class="form-horizontal" method="post"  action="<c:url value="/ddm" />" enctype="multipart/form-data" role="form">
                    <div class="form-group">
                        <label for="boardFile" class="col-sm-2 control-label">物理设备定义</label>
                        <div class="col-sm-10">
                            <input type="file" class="form-control required" id="boardFile" name="boardFile" accept=".xls,.xlsx">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="ruFile" class="col-sm-2 control-label">RU物理特性</label>
                        <div class="col-sm-10">
                            <input type="file" class="form-control required" id="ruFile" name="ruFile" accept=".xls,.xlsx">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-lg btn-primary btn-block">扫描</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
<!--        <script>
            $(document).ready(function () {
                $("#ddmForm").validate();
            });
        </script>-->
    </body>
</html>
