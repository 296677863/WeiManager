<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>详情</title>
<%@ include file="/common/head.jsp"%>
<link href="${base}/css/bootstrap-switch.css?v=<wei:version/>" rel="stylesheet" />
<style>
	.group-div{
	    margin-bottom: 15px;
	}
	
	.form-group{
		margin-bottom:0px!important;
		
	}
</style>
</head>
<body class="gray-bg" style="cursor: auto;">
	<div class="">
		
		<div class="ibox-content">
		<!-- 新版本 -->
			<form action="">
				<div class="border-module">
				<div class="group-border mb20" style="min-height: 120px;overflow: auto;">
					<div class="group-title">基本信息</div>
					<div class="col-sm-12 pl0">
						<div class="col-sm-6 pl0">
							<div class="group-div">
								<label class="label-left">名称：</label>
								<div class="input-left lh-div">${bean.beanName}</div>
							</div>
						</div>
						<div class="col-sm-6 pl0">
							
						</div>
						</div>
						<div class="col-sm-12 pl0">
						
							
							<div class="col-sm-6 pl0">
							<div class="group-div">
								<label class="label-left">方法名称：</label>
								 
								   <div class="input-left lh-div">${bean.methodName}</div>
								  
							</div>
							</div>
						</div>
						<div class="col-sm-12 pl0">
							<div class="col-sm-6 pl0">
									<div class="group-div">
										<label class="label-left">参数：</label>
										 
										   <div class="input-left lh-div">${bean.params }</div>
										  
									</div>
							</div>
						</div>
							<div class="col-sm-12 pl0">
							<div class="col-sm-6 pl0">
									<div class="group-div">
										<label class="label-left">cron表达式：</label>
										 
										   <div class="input-left lh-div">${bean.cronExpression }</div>
										  
									</div>
							</div>
						</div>
							<div class="col-sm-12 pl0">
							<div class="col-sm-6 pl0">
									<div class="group-div">
										<label class="label-left">备注：</label>
										 
										   <div class="input-left lh-div">${bean.remark }</div>
										  
									</div>
							</div>
						</div>
						
					</div>
					
				</div>
				
			</form>
		</div>
	</div>
	<%@ include file="/common/footer.jsp"%>
	<script type="text/javascript" src="${base}/js/bootstrap-switch.js?v=<wei:version/>"></script>
	 
</body>
</html>