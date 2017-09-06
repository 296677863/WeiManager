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
<title>菜单列表</title>
<%@include file="/common/head.jsp" %>
<link rel="stylesheet" href="${base}/css/treetable/treeTable.css?v=<wei:version/>" />
<link rel="stylesheet" href="${base}/css/menu/menu.css?v=<wei:version/>" />
</head>

<body class="gray-bg">
	<form id="listForm" action="${base}/menu/list.shtml" method="post">
		<div class="ibox-content" style="min-height: 505px;">
			 <div>
	           <!--   <button class="m-btn maincolor main-btn weiOpenBtn" data-title="添加一级菜单" data-url="${base}/menu/add.shtml" type="button"><span>添加一级菜单</span></button>-->
	        </div>
	        <div class="menu-table">
	        	
	        	<table id="treeTable1" style="width:100%">
				    <tr>
				        <td>排序</td>
				        <td>菜单名称</td>
				        <td>访问</td>
	        			 <!-- <td>操作</td>-->
				    </tr>
				    <c:forEach items="${parent}" var="bean" varStatus="status">
				    	<tr id="${bean.id }">
					        <td>${status.index+1}</td>
		        			<td><span controller="true"></span>${bean.name}</td>
		        			<td></td>
		        			 <!-- <td>
		        				<div class="operation">
			        				<a href="javascript:void(0)" onclick="wei.dialog.openIrame('添加子菜单','${base}/base/menu/addSub/${bean.id}')">[添加子菜单]</a>
			        				<a href="javascript:void(0)" onclick="wei.dialog.openIrame('菜单编辑','${base}/base/menu/input/${bean.id}')">[编辑]</a>
			        				<a href="javascript:void(0)" class="weiDeleteRow" data-id="${bean.id}" data-reload="true">[删除]</a>
		        				</div>
		        			</td>-->
				    	</tr>
						<c:set value="0" var="subMenuInxex"/>
				    	<c:forEach items="${sub}" var="sub" varStatus="subStatus">
				    		<c:if test="${bean.id==sub.parent}">
								<c:set value="${subMenuInxex+1}" var="subMenuInxex"/>
					    		<tr id="${sub.id }" pId="${bean.id }">
							        <td>${status.index+1}.${subMenuInxex}</td>
				        			<td>${sub.name}</td>
				        			<td><a href="javascript:void(0)">${sub.url}</a></td>
				        			<!--<td>
				        				<div class="operation">
					        				<a href="javascript:void(0)" onclick="wei.dialog.openIrame('菜单编辑','${base}/menu/input.shtml/${sub.id}')">[编辑]</a>
					        				<a href="javascript:void(0)" class="weiDeleteRow" data-id="${sub.id}" data-reload="true">[删除]</a>
				        				</div>
				        			</td>-->
							    </tr>
						    </c:if>
				    	</c:forEach>
				    </c:forEach>
				</table>
	        </div>
		</div>
	</form>

	<%@ include file="/common/footer.jsp"%>
	<script type="text/javascript" src="${base}/js/common/jquery/treetable/jquery.treeTable.js?v=<wei:version/>" ></script>
	<script>
	
	    $(function(){
	    	 $("#treeTable1").treeTable({expandLevel : 1,column:0});
	    });
	
	</script>
	
</body>

</html>