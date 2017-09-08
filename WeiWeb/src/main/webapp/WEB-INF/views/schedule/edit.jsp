<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>添加定时任务</title>
<%@ include file="/common/head.jsp"%>
<link href="${base}/css/bootstrap-switch.css?v=<wei:version/>" rel="stylesheet" />
<style>
.check-list{
   margin-bottom: 0px;
       margin-top: 3px;
}
</style>
</head>
<body class="gray-bg" style="cursor: auto;">
	<div class="">
		<div class="ibox-title">
			<h5>
				<a href="${base }/schedule/list.shtml">任务列表</a> > 添加定时任务
			</h5>
		</div>
		<div class="ibox-content">
			<!-- 新版 -->
			<form id="inputForm" action="${base}/schedule/save.shtml" method="post">
				<div class="border-module">
					<div class="group-border mb20" style="height: 350px;">
						<div class="group-title">基本信息</div>
						
			 
						<div class="col-sm-12 pl0">
							<div class="col-sm-4 pl0">
								<div class="group-div">
									<label class="label-left"><span class="priority">*</span>bean名称：</label>
									<input type="text" placeholder="请输入bean名称" class="default-input required input-left " name="beanName" id="beanName" value="${bean.beanName }"></div>
							</div>
						</div>
						<div class="col-sm-12 pl0">
							<div class="col-sm-4 pl0">
								<div class="group-div">
									<label class="label-left"><span class="priority">*</span>方法名称：</label>
									<input type="text" class="default-input required input-left"
										   placeholder="请输入方法名称" name="methodName" id="methodName" value="${bean.methodName }"/>
								</div>
							</div>
							
						</div>
						
						<div class="col-sm-12 pl0">
							<div class="col-sm-4 pl0">
								<div class="group-div">
									<label class="label-left"><span class="priority">*</span>参数：</label>
									<input type="text" class="default-input required input-left"
										   placeholder="请输入参数" name="params" id="params" value="${bean.params }"/>
								</div>
							</div>
							

							

						</div>
						<div class="col-sm-12 pl0">
							<div class="col-sm-4 pl0">
								<div class="group-div">
									<label class="label-left "><span class="priority">*</span>cron表达式：</label>
									<input type="text" placeholder="cron表达式"  name="cronExpression" id="cronExpression" class="default-input required input-left " value="${bean.cronExpression }"></div>
							</div>

						</div>
						<div class="col-sm-12 pl0">
							
							<div class="col-sm-4 pl0">
								<div class="group-div">
									<label class="label-left">备注：</label>
									<input type="text" class="default-input required input-left" name="remark" id="remark" value="${bean.remark }"/>

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
	<script type="text/javascript" src="${base}/js/schedule/add.js?v=<wei:version/>"></script>
	<script type="text/javascript" src="${base}/js/bootstrap-switch.js?v=<wei:version/>"></script>
	
</body>
</html>