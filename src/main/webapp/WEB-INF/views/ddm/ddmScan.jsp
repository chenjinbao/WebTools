<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <title>动态管理扫描</title>

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
            <div class="col-md-8 col-md-offset-2">
                <form id="ddmForm" class="form-horizontal" method="post"  action="<c:url value="/ddm" />" enctype="multipart/form-data" role="form">
                    <c:if test="${error != null}" >
                        <div class="col-sm-8 col-sm-offset-2 alert alert-danger alert-dismissible" role="alert">
                            <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                            ${error}
                        </div>
                    </c:if>
                    <div class="form-group">
                        <label for="boardFile" class="col-sm-2 col-sm-offset-2 control-label">物理设备定义</label>
                        <div class="col-sm-6">
                            <input type="file" class="form-control required" id="boardFile" name="boardFile" accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="ruFile" class="col-sm-2 col-sm-offset-2 control-label">RU物理特性&nbsp;&nbsp;</label>
                        <div class="col-sm-6">
                            <input type="file" class="form-control required" id="ruFile" name="ruFile" accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sdrVer" class="col-sm-2 col-sm-offset-2 control-label">SDR平台版本</label>
                        <div class="col-sm-6">
                            <select class="form-control required" required id="sdrVer" name="sdrVer">
                                <c:forEach items="${versions}" var="version">
                                    <option value="${version.version}">${version.display}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-8">
                            <button type="submit" class="btn btn-lg btn-primary btn-block">扫描</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <script>
            $(document).ready(function () {
                $("#ddmForm").validate();
            });
        </script>
    </body>
</html>
