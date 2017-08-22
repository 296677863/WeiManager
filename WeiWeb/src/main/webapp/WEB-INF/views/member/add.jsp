<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>添加人员</title>
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
				<a href="${base }/member/list.shtml">用户列表</a> > 添加用户信息
			</h5>
		</div>
		<div class="ibox-content">
			<!-- 新版 -->
			<form id="inputForm" action="${base}/member/save.shtml" method="post">
				<div class="border-module">
					<div class="group-border mb20" style="height: 350px;">
						<div class="group-title">基本信息</div>
						<div class="col-sm-12 pl0">
							<div class="col-sm-4 pl0">
								<div class="group-div">
									<label class="label-left"><span class="priority">*</span>E-mail：</label>
									<input type="text" placeholder="请输入E-mail" class="default-input required input-left " name="email" id="email" value="${bean.email }"></div>
							</div>
						</div>
						<div class="col-sm-12 pl0">
							<div class="col-sm-4 pl0">
								<div class="group-div">
									<label class="label-left"><span class="priority">*</span>昵称：</label>
									<input type="text" class="default-input required input-left"
										   placeholder="请输入用户名" name="nickname" id="nickname" value="${bean.nickname }"/>
								</div>
							</div>
							
						</div>
						
						<div class="col-sm-12 pl0">
							<div class="col-sm-4 pl0">
								<div class="group-div">
									<label class="label-left"><span class="priority">*</span>密码：</label>
									<input type="password" class="default-input required input-left"
										   placeholder="请输入密码" name="pswd" id="pswd" value="${bean.pswd }"/>
								</div>
							</div>
							

							

						</div>
						<div class="col-sm-12 pl0">
							<div class="col-sm-4 pl0">
								<div class="group-div">
									<label class="label-left "><span class="priority">*</span>确认密码：</label>
									<input type="password" placeholder="确认密码"  name="rePassword" id="rePassword" class="default-input required input-left " value="${bean.name }"></div>
							</div>

						</div>
						<div class="col-sm-12 pl0">
							
							<div class="col-sm-4 pl0">
								<div class="group-div">
									<label class="label-left">是否启用：</label>
									<input type="hidden" name="status" id="isEnabled" value="${bean.status }"/>
									<div class="switch" >
										<input type="checkbox" checked id="MySwitch" data-size="small" data-on-text="是" data-off-text="否"/>
									</div>

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
	<script type="text/javascript" src="${base}/js/member/member_add.js?v=<wei:version/>"></script>
	<script type="text/javascript" src="${base}/js/bootstrap-switch.js?v=<wei:version/>"></script>
	<script>
		$(function(argument) {
			$('#MySwitch').bootstrapSwitch();
			$("#isEnabled").val("1");
			$("#MySwitch").on('switchChange.bootstrapSwitch', function (e, state) {
				$("#isEnabled").val(state);
				 if(state==true){  
						 $("#isEnabled").val("1");  
		            }else{  
		            	$("#isEnabled").val("0");  
		            }  
			});
		});
    </script>	
</body>
</html>