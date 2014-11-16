<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${ctx}">WEB工具</a>
        </div>
        <div class="collapse navbar-collapse" id="navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${ctx}/ddm">动态管理</a></li>
                <li><a href="${ctx}/rfa">射频分析</a></li>
                <li><a href="${ctx}/dtm">诊断测试</a></li>
                    <shiro:hasRole name="admin">
                    <li><a href="${ctx}/druid">Druid监控</a></li>
                    </shiro:hasRole>
            </ul>
            <shiro:user>
                <ul class="nav navbar-nav navbar-right">
                    <p class="navbar-text"><shiro:principal property="name"/></p>
                    <li><a href="${ctx}/logout">退出</a></li>
                </ul>
            </shiro:user>
        </div>
    </div>
</nav>