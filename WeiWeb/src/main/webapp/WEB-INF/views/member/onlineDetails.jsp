<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<title>查看详情</title>
	<%@ include file="/common/head.jsp"%>
	<link href="${base}/css/bootstrap-switch.css" rel="stylesheet?v=<wei:version/>" />
	<link href="${base}/css/user/userInfo.css?v=<wei:version/>" rel="stylesheet" />
</head>
<body class="gray-bg" style="cursor: auto;">
<div class="">

	<div class="ibox-content">
		<!-- 新版本 -->
		<form action="">
			<div class="border-module">
				<div class="group-border mb20">
					<div class="group-title">Session详情</div>
					<ul class="role-purview">
							<li>
								<div class="col-sm-12 pl0">
									<div class="form-group">
										<label class="label-left float-l">
											<span>SessionId：</span>
										</label>
										<div class="input-left" style="float:none;">

											<div class="clearfix">
												<div class="check-list">
													<span>${bo.sessionId }</span>
												</div>
											</div>
										</div>
									</div>
								</div>
							</li>
							<li>
								<div class="col-sm-12 pl0">
									<div class="form-group">
										<label class="label-left float-l">
											<span>Session创建时间：</span>
										</label>
										<div class="input-left" style="float:none;">

											<div class="clearfix">
												<div class="check-list">
													<span><fmt:formatDate value="${bo.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
												</div>
											</div>
										</div>
									</div>
								</div>
							</li>
							
							<li>
								<div class="col-sm-12 pl0">
									<div class="form-group">
										<label class="label-left float-l">
											<span>Session最后交互时间：</span>
										</label>
										<div class="input-left" style="float:none;">

											<div class="clearfix">
												<div class="check-list">
													<span><fmt:formatDate value="${bo.lastAccess}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
												</div>
											</div>
										</div>
									</div>
								</div>
							</li>
							
							
							<li>
								<div class="col-sm-12 pl0">
									<div class="form-group">
										<label class="label-left float-l">
											<span>Session状态：</span>
										</label>
										<div class="input-left" style="float:none;">

											<div class="clearfix">
												<div class="check-list">
													<span>${bo.sessionStatus eq true?"有效":"已踢出" }</span>
												</div>
											</div>
										</div>
									</div>
								</div>
							</li>
							
							
							<li>
								<div class="col-sm-12 pl0">
									<div class="form-group">
										<label class="label-left float-l">
											<span>SessionHost：</span>
										</label>
										<div class="input-left" style="float:none;">

											<div class="clearfix">
												<div class="check-list">
													<span>${bo.host}</span>
												</div>
											</div>
										</div>
									</div>
								</div>
							</li>
							
							<li>
								<div class="col-sm-12 pl0">
									<div class="form-group">
										<label class="label-left float-l">
											<span>Sessiontimeout：</span>
										</label>
										<div class="input-left" style="float:none;">

											<div class="clearfix">
												<div class="check-list">
													<span>${bo.timeout}&nbsp;(毫秒) = ${bo.timeout/1000}(秒) = ${bo.timeout/1000/60}(分钟)</span>
												</div>
											</div>
										</div>
									</div>
								</div>
							</li>
							
							<li>
								<div class="col-sm-12 pl0">
									<div class="form-group">
										<label class="label-left float-l">
											<span>昵称：</span>
										</label>
										<div class="input-left" style="float:none;">

											<div class="clearfix">
												<div class="check-list">
													<span>${bo.nickname}</span>
												</div>
											</div>
										</div>
									</div>
								</div>
							</li>
							
							
							<li>
								<div class="col-sm-12 pl0">
									<div class="form-group">
										<label class="label-left float-l">
											<span>Email/帐号：</span>
										</label>
										<div class="input-left" style="float:none;">

											<div class="clearfix">
												<div class="check-list">
													<span>${bo.email}</span>
												</div>
											</div>
										</div>
									</div>
								</div>
							</li>
							
							<li>
								<div class="col-sm-12 pl0">
									<div class="form-group">
										<label class="label-left float-l">
											<span>创建时间：</span>
										</label>
										<div class="input-left" style="float:none;">

											<div class="clearfix">
												<div class="check-list">
													<span><fmt:formatDate value="${bo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
												</div>
											</div>
										</div>
									</div>
								</div>
							</li>
							
							<li>
								<div class="col-sm-12 pl0">
									<div class="form-group">
										<label class="label-left float-l">
											<span>最后登录时间：</span>
										</label>
										<div class="input-left" style="float:none;">

											<div class="clearfix">
												<div class="check-list">
													<span><fmt:formatDate value="${bo.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
												</div>
											</div>
										</div>
									</div>
								</div>
							</li>
						
					</ul>

				</div>
			</div>
		</form>
	</div>
</div>
<script src="${base}/js/common/jquery/jquery.min.js?v=<wei:version/>"></script>
<script src="${base}/js/bootstrap.min.js?v=<wei:version/>"></script>
<%@ include file="/common/footer.jsp"%>
<script type="text/javascript" src="${base}/js/bootstrap-switch.js?v=<wei:version/>"></script>

</body>
</html>