<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>权限分配</title>
<%@ include file="/common/head.jsp"%>
<link href="${base}/css/bootstrap-switch.css?v=<wei:version/>"
	rel="stylesheet" />
<style type="text/css">
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
				<input type="hidden" name="id" value="${bean.id }" /> <input
					type="hidden" name="createDate"
					value="<fmt:formatDate value='${bean.createDate}' pattern='yyyy-MM-dd HH:mm:ss' />" />
				<input type="hidden" name="authoritiesArray" id="authoritiesArray" />
				<div class="border-module">
					<div class="group-border mb20">
						<div class="group-title">权限分配</div>
						<ul class="role-purview">

							<c:forEach items="${menus}" var="menu">
								<c:if test="${menu.level==2 }">
								
											<li>
												<div class="col-sm-12 pl0">
													<div class="form-group">
														<label class="label-left float-l"> <span>${menu.name}</span>
														</label>
														<div class="input-left" style="float: none;">

															<div class="clearfix">
																<div class="checked-list">
																	<div class="check-div  selectAll" check="0"
																		id="${menu.id }">
																		<i class="iconfont"></i>
																	</div>
																	<span>全选</span>
																</div>
																<c:forEach items="${menus}" var="subMenu">
																	<c:if test="${subMenu.parent.id == menu.id }">
																		<c:set value="false" var="isCheck"></c:set>
																		<c:forEach items="${bean.menus}" var="hasAuth">
																			<c:if test="${hasAuth.id ==  subMenu.id}">
																				<c:set value="true" var="isCheck"></c:set>
																			</c:if>
																		</c:forEach>

																		<div class="checked-list menuList">
																			<c:if test="${isCheck }">
																				<div class="check-div white-color" name="menuCheck"
																					check="1" menu="${subMenu.id }"
																					parentMenu="${menu.id }">
																					<i class="iconfont"></i>
																				</div>
																			</c:if>
																			<c:if test="${!isCheck }">
																				<div class="check-div" name="menuCheck" check="0"
																					menu="${subMenu.id }" parentMenu="${menu.id }">
																					<i class="iconfont"></i>
																				</div>
																			</c:if>
																			<span>${subMenu.name }</span>
																		</div>
																	</c:if>
																</c:forEach>
															</div>
														</div>
													</div>
												</div>
											</li>
								</c:if>
							</c:forEach>
						</ul>
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
		src="${base}/js/bootstrap-switch.js?v=<wei:version/>"></script>
</body>
</html>