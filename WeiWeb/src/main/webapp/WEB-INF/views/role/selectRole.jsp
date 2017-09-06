<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>用户角色分配</title>
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
				<input type="hidden" name="userId" value="${userId }" />
				<input type="hidden" name="ids" id="rolesArray" />
				<div class="border-module">
					<div class="group-border mb20">
						<div class="group-title">角色分配</div>
						<ul class="role-purview">
							<li>
							<div class="col-sm-12 pl0">
									<div class="form-group">
										<div class="input-left" style="float: none;">
											<div class="clearfix">
												<div class="checked-list">
													<div class="check-div  selectAll" check="0"
														id="">
														<i class="iconfont"></i>
													</div>
													<span>全选</span>
												</div>
											</div>
										</div>
									</div>
							</div>
							</li>
							<li>
							<c:forEach items="${bos}" var="rolebo" varStatus="status">
								<div class="col-sm-12 pl0">
									<div class="form-group">
										<div class="input-left" style="float: none;">
											<div class="clearfix">
														<div class="checked-list menuList">
															<c:if test="${rolebo.marker ne 0 }">
																<div class="check-div white-color" name="menuCheck"
																	check="1" menu="${rolebo.id }" >
																	<i class="iconfont"></i>
																</div>
															</c:if>
															<c:if test="${rolebo.marker eq 0 }">
																<div class="check-div" name="menuCheck" check="0"
																	menu="${rolebo.id }" >
																	<i class="iconfont"></i>
																</div>
															</c:if>
															<span>${rolebo.name }</span>
														</div>
													
												
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
							</li>
						</ul>
						
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
			</form>
		</div>
	</div>
	<script src="${base}/js/common/jquery/jquery1.8.3.min.js?v=<wei:version/>"></script>
	<script src="${base}/js/bootstrap.min.js?v=<wei:version/>"></script>
	<%@ include file="/common/footer.jsp"%>
	<script type="text/javascript">
	$(function(){
		getDataInfo();
		function  getDataInfo(){
			var ids = [];
			$(".check-div[name='menuCheck']").each(function(){
				var $this =$(this);
				var v_check=$this.attr("check");
				var v_menu_id=$this.attr("menu");
				if(v_check == 1){
					ids.push(v_menu_id);
				}
			
			});
			$("#rolesArray").val(ids.join(","));
		}
		$(".selectAll").click(function(){
			var v_check=$(this).attr("check");
			$(".check-div").each(function(){
				if(v_check == 1){
					$(this).addClass("white-color");
					$(this).attr("check","1");
				}else{
					$(this).removeClass("white-color");
					$(this).attr("check","0");
				}
			});
		});
		var $inputForm = $("#inputForm");
		$inputForm.validate($.extend({},{
			rules : {
				
			},
			messages : {
				
			},
			submitHandler: function(form) {   
				$("button[type='submit']").attr("disabled","disabled");
				 $("button[type='submit']").addClass("disable-btn");
				 $("button[type='submit']").removeClass("maincolor");
				 $("button[type='submit']").removeClass("main-btn");
				 
				$(form).find(":submit").prop("disabled", true);
				getDataInfo()
				wei.ajax.ajaxTagSu(window.baseRoot+"/role/addRole2User.shtml",$inputForm.serialize(),function (message) {
	                if (message.type == "success") {
	                    wei.dialog.msgIframe(message.content);
	                }else{
	                    $("button[type='submit']").removeAttr("disabled");
	                    $("button[type='submit']").removeClass("disable-btn");
	                    $("button[type='submit']").addClass("maincolor");
	                    $("button[type='submit']").addClass("main-btn");
	                    wei.dialog.errorlayerMsg(message.content);
	                }
	            });
				return false;
			}
		},wei.defaults.validateTooltip));
		
	});
	
	</script>
	<script type="text/javascript"
		src="${base}/js/bootstrap-switch.js?v=<wei:version/>"></script>
</body>
</html>