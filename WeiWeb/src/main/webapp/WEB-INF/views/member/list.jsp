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
<title>用户管理</title>
<%@include file="/common/head.jsp" %>
</head>
<body>
	<div class="ibox-content">
		<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper form-inline" role="grid">
			<div class="row" >
				<form id="inputForm"  method="post"
					class="form-horizontal m-t-xs" novalidate="novalidate">
					<div class="form-group col-sm-12 icon-right pb15">
							
						<div class="col-sm-5 pl0"><div class="group-div"><label class="label-left">昵称:</label><input type="text" class="default-input input-left " placeholder="请输入昵称" name="nickname"  value=""/></div></div>
						<div class="col-sm-5 pl0"><div class="group-div"><label class="label-left">Email/帐号:</label><input type="text" class="default-input input-left" placeholder="请输入Email" name="email"  value=""/>	</div></div>		
						<div class="col-sm-2 pl0">
							<div class="icon-btn-div">
							<button class="m-btn maincolor main-btn" id="selectButton" type="button"><span>查询</span></button>
							<button class="m-btn maincolor main-btn" id="resetButton" type="reset"><span>重置</span></button>
						</div>
						</div>
					</div>
					<div class="form-group col-sm-12 icon-left">
						<button class="m-btn maincolor main-btn weiOpenBtn" data-title="新增用户" data-url="${base}/member/add.shtml" 
							type="button"><span>新增</span></button>
						<button class="m-btn disable-btn" id="deleteButton" type="button"><span>批量删除</span></button>
					</div>
				</form>
			</div>
		</div>
		<div class="jqGrid_wrapper" style="height:400px;"> 
            <table id="table_list"></table>
            <div id="pager_list"></div>
        </div>
	</div>

	<%@ include file="/common/footer.jsp"%>
	<script type="text/javascript" src="${base}/js/member/member_list.js?v=<wei:version/>"></script>
</body>

</html>
	