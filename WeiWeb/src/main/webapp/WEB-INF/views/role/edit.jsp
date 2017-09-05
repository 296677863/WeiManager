<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>编辑角色</title>
<%@ include file="/common/head.jsp"%>
<link href="${base}/css/bootstrap-switch.css?v=<wei:version/>"
	rel="stylesheet" />

<style>
	.group-div{
	    margin-bottom: 15px;
	}
	.role-purview li{
		     min-height: 36px;
	    border-bottom: 1px dashed #515151;
	    margin-top: 8px;
	    overflow: auto;
	}
	
	.form-group{
		margin-bottom:0px!important;
		
	}
</style>
</head>
<body style="cursor: auto;">
	<div class="">

		<div class="ibox-content">
			<!-- 新版本 -->
			<form id="inputForm">
				<input type="hidden" name="id" value="${bean.id }" /> 
				<div class="border-module">
					<div class="group-border mb20" style="height: 250px;">
						<div class="group-title">基本信息</div>
						<div class="col-sm-12 pl0">
							<div class="col-sm-3 pl0">
								<div class="group-div">
									<label class="label-left">名称：</label> <input type="text"
										class="default-input required input-left " name="name"
										id="name" value="${bean.name}">
								</div>
							</div>
							
							<div class="col-sm-3 pl0">
								<div class="group-div">
									<label class="label-left">角色类型：</label> 
									<input type="text"
										class="default-input required input-left " name="type"
										id="type" value="${bean.type}">
								</div>
							</div>
							
						</div>
						
						<div class="col-sm-12 pl0">
							<div class="col-sm-4 pl0">
								<div class="group-div">
									<label class="label-left"></label>
									<div class="input-group input-left">
										<button class="m-btn maincolor main-btn" type="submit">
											<span>保存</span>
										</button>
									</div>
								</div>
							</div>

						</div>
					</div>

				</div>
			
			</form>
		</div>
	</div>
	<script src="${base}/js/common/jquery/jquery1.8.3.min.js?v=<wei:version/>"></script>
	<script src="${base}/js/bootstrap.min.js?v=<wei:version/>"></script>
	<%@ include file="/common/footer.jsp"%>
	<script type="text/javascript"
		src="${base}/js/role/role_edit.js?v=<wei:version/>"></script>
	<script type="text/javascript"
		src="${base}/js/bootstrap-switch.js?v=<wei:version/>"></script>
</body>
</html>