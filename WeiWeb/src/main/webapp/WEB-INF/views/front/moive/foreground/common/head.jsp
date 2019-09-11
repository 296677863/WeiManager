<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--<div class="row-fluid">
	<div class="span12">
		<img src="${pageContext.request.contextPath}/images/logo.gif">
	</div>
</div>
<div class="row-fluid">
	<div class="span12">
		<div class="navbar">
		  <div class="navbar-inner">
		    <a class="brand" href="goIndex">首页</a>
		    <ul class="nav">
		       <c:forEach var="movieType" items="${movieTypeList}">
		       		<li style="float: left"><a href="movie?action=list&typeId=${movieType.type_id }">${movieType.type_name }</a></li>
		       </c:forEach>
		    </ul>
		  </div>
		</div>
	</div>
</div>--%>

<header class="navbar navbar-default navbar-static-top bs-docs-nav" id="top">
    <div class="container">
        <div class="navbar-header">

            <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target="#bs-navbar" aria-controls="bs-navbar" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="goIndex">首页</a>
        </div>
        <nav id="bs-navbar" class="collapse navbar-collapse">

            <ul class="nav navbar-nav">
                <c:forEach var="movieType" items="${movieTypeList}">
                    <li style="float: left"><a href="movie?action=list&typeId=${movieType.type_id }">${movieType.type_name }</a></li>
                </c:forEach>
            </ul>
        <%--    <ul class="nav navbar-nav navbar-right">
                <!-- <li><a href="http://mb.bootcss.com" onclick="_hmt.push(['_trackEvent', 'docv3-navbar', 'click', 'themes'])" target="_blank">主题/模板</a></li> -->
                <li><a href="" target="_blank">6</a></li>
            </ul>--%>
        </nav>
    </div>
</header>