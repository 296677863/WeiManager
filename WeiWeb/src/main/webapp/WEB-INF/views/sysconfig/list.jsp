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
<title>系统参数配置列表</title>
<%@include file="/common/head.jsp" %>

</head>
<body>
	<div class="ibox-content">
		<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper form-inline" role="grid">
			<div class="row" >
				<form id="inputForm"  method="post"
					class="form-horizontal m-t-xs" novalidate="novalidate">
					<div class="form-group col-sm-12 icon-right pb15">
							
						<div class="col-sm-6 pl0">
							<div class="col-sm-9"> 
							
								<input type="text" class="default-input input-left " placeholder="字典名称/代码" name="keyWord"  value=""/>
							</div>
							<div class="col-sm-3">
								<div class="icon-btn-div">
									<button class="m-btn maincolor main-btn" id="selectButton" type="button"><span>搜索</span></button>
								</div>
							</div>
						</div>
						 
						
					</div>
					<div class="form-group col-sm-12 icon-left">
						<button class="m-btn maincolor main-btn weiOpenBtn" data-title="新增数据字典" data-url="${base}/sysdict/add.shtml" 
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
	<script type="text/javascript" src="${base}/js/sysconfig/config_list.js?v=<wei:version/>"></script>
</body>

</html>