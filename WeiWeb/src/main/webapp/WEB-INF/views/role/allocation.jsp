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
<title>用户角色分配</title>
<%@include file="/common/head.jsp" %>
</head>
<body>
	<div class="ibox-content">
		<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper form-inline" role="grid">
			<div class="row" >
				<form id="inputForm"  method="post"
					class="form-horizontal m-t-xs" novalidate="novalidate">
					<div class="form-group col-sm-12 icon-right pb15">
							
						<div class="col-sm-5 pl0">
							<div class="group-div">
							<label class="label-left">名称:</label>
							<input type="text" class="default-input input-left " placeholder="请输入名称" name="name"  value=""/>
							</div>
						</div>
						<div class="col-sm-5 pl0">
						<div class="group-div">
						</div>	
						</div>		
						<div class="col-sm-2 pl0">
							<div class="icon-btn-div">
							<button class="m-btn maincolor main-btn" id="selectButton" type="button"><span>查询</span></button>
							<button class="m-btn maincolor main-btn" id="resetButton" type="reset"><span>重置</span></button>
						</div>
						</div>
					</div>
					<div class="form-group col-sm-12 icon-left">
						<button class="m-btn disable-btn" id="clearRoleButton" type="button"><span>清空用户角色</span></button>
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
	<script type="text/javascript">
	/************************************ 列表加载数据 ****************************/
		$(function () {
//		    var v_userType = wei.selectDetailAllSendXhr("STATUS");
		    var config = {
		        url: window.baseRoot + "/role/allocationData.shtml",
		        multiselect: true,
		        height: $(window).height() - $("#DataTables_Table_0_wrapper").height() - 150,
		        colNames: ['昵称','Email/帐号','状态','拥有的角色','操作'],
		        colModel: [
		            {
		                name: 'nickname',
		                index: 'nickname',
		                align: 'left',
		                width: 100,
		            },
		            {
		                name: 'email',
		                index: 'email',
		                align: 'left',
		                width: 150,
		                fixed: true,
		            },
		            {
		                name: 'status',
		                index: 'status',
		                align: 'left',
		                width: 100,
		            },
		             
		            {
		                name: 'roleNames',
		                index: 'roleNames',
		                width: 300,
		            },
		            {
		                width: 200,
		                fixed: true,
		                formatter: function (cellvalue, options, rowObject) {
		                    var result = "<a href=\"javascript:void(0)\" onclick=\"wei.dialog.openIrame('选择角色','{0}')\">[选择角色]</a>"
		                        .format(window.baseRoot + '/role/selectRole/'
		                            + rowObject.id+'.shtml');
		                    
		                    return result;
		                }
		            }]
		    }
		    var jqGrid = wei.grid(config);

		    jqGrid.bind("weijqgridSelectOne", function (event, aRowids, status) {
		       
		    });
		    jqGrid.bind("weijqgridSelectAll", function (event, aRowids, status) {
		       
		    });
		   
		});

	
	</script>
</body>

</html>
	