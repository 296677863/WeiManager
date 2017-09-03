<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>角色--成员管理</title>
<%@include file="/common/head.jsp" %>
</head>
<body>
	<div class="ibox-content">
		<div class="jqGrid_wrapper" style="height:400px;"> 
            <table id="table_list"></table>
            <div id="pager_list"></div>
        </div>
	</div>

	<%@ include file="/common/footer.jsp"%>
	<script type="text/javascript">
		var roleId = '${roleId}';
	</script>
	<script type="text/javascript" src="${base}/js/role/role_memberList.js?v=<wei:version/>"></script>
</body>

</html>
	