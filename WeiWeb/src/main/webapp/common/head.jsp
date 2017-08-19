<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="/WEB-INF/tld/wei.tld" prefix="wei" %>
<!-- base css -->
<link rel="stylesheet" href="${base}/css/jqgrid/ui.jqgrid.css?v=<wei:version/>" />
<link rel="stylesheet" type="text/css" href="${base}/css/chosen.css?v=<wei:version/>" />
<link rel="stylesheet" type="text/css" href="${base}/css/bootstrap.min.css?v=<wei:version/>" />
<link rel="stylesheet" type="text/css" href="${base}/css/font-awesome.min.css?v=<wei:version/>"/>
<link rel="stylesheet" type="text/css" href="${base}/css/demo.css?v=<wei:version/>" />
<link rel="stylesheet" type="text/css" href="${base}/css/dataTables.bootstrap.css?v=<wei:version/>"/>
<link rel="stylesheet" type="text/css" href="${base}/css/style.css?v=<wei:version/>" />
<c:choose>
	<c:when test="${skin eq 'blue'}">
		<link rel="stylesheet" type="text/css" id="skin"  href="${base}/css/skin.css?v=<wei:version/>" />
	</c:when>
	<c:otherwise>
		<link rel="stylesheet" type="text/css" id="skin"  href="${base}/css/blackskin.css?v=<wei:version/>" />
	</c:otherwise>
</c:choose>

<link rel="stylesheet" type="text/css" type="text/css" href="${base}/css/color.css?v=<wei:version/>"/>
<link rel="stylesheet" type="text/css" href="${base}/css/common.css?v=<wei:version/>" />
<!-- extends csss -->
<link rel="stylesheet" type="text/css" href="${base}/css/extends/common.css?v=<wei:version/>" />
<link rel="stylesheet" type="text/css" href="${base}/js/webuploader/webuploader.css?v=<wei:version/>" />
<link rel="stylesheet" href="${base}/css/IconLibrary/iconfont.css?v=<wei:version/>" />

<script type="text/javascript">
window.baseRoot = "${base}";
window.skin = "${skin}";

</script>
