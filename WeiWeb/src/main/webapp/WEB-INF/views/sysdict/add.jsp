<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<title>字典添加</title>
<%@include file="/common/head.jsp" %>
<style type="text/css">
.dictionary-div{
	    height: 87px;
}
.dictionary-title {
	padding-left: 0px;
}
</style>
</head>

<body  style="cursor: auto;">
		<div class="ibox-content" >
			<form id="headForm">
				<input type="hidden" name="dictId" id="paramId"  value="${bean.dictId}">
				<div class="border-module base-info">
				<div class="group-border mb20" style="min-height: 90px;">
					<div class="group-title">基本信息</div>
					<div class="col-sm-11 pl0" id="editstatus">
						<div class="col-sm-4 pl0">
							<div class="group-div"><label class="label-left"><span class="priority">*</span>字典名称：</label><input type="text" class="default-input required input-left " name="dictName" placeholder="请输入字典名称" value="${bean.dictName}" ></div>
						</div>
						<div class="col-sm-4 pl0">
							<div class="group-div">
								<label class="label-left"><span class="priority">*</span>字典类型：</label>
								<input type="text" class="default-input required input-left"
									 placeholder="请输入字典类型" name="dictType" value="${bean.dictType}" data-val="${bean.dictType}"/>
							</div>
						</div>
					
					</div>
					<!-- disable-btn -->
					<div class="col-sm-1 pl0">
						<button class="m-btn maincolor main-btn" id="editButton" type="button" data-status="2"><span>保存</span></button>
					</div>
				</div>
				</div>
				</form>
				<div class="border-module">
				<div class="group-border mb20 pb30" style="overflow: auto;">
					<div class="group-title">字典详情</div>
					<div class="form-group col-sm-12">
						<button class="m-btn maincolor main-btn" id="addButton" type="button"><span>添加</span></button>
					</div>
					<div class="dictionary-div">
						<div class="col-sm-12 pl0  dictionary-option">
							<div class="col-sm-10 pl0">
								<div class="col-sm-4 col-val">
									<div class="title-bg">
										<span>选项名称</span>
										
									</div>
									
								</div>
								<div class="col-sm-4 col-val">
									<div class="title-bg">
										<span>选项值</span>
									</div>
									
								</div>
								<div class="col-sm-4 col-val position">
									<div class="title-bg">
										<span>排序号</span>
									</div>
									
								</div>
							</div>
						</div>
					</div>
					<form id="addForm">
					</form>
					<form id="infoForm">
					<div class="dictionary-list">
						<ul>
							<c:forEach items="${bean.sysDictDetails}" var="detail" varStatus="status" >
								<li data-status="0">
									<input type="hidden" name="id" value="${detail.detailId}"/>
									<div class="col-sm-10 pl0">
										<div class="col-sm-4 col-val">
											<div class="dictionary-title">
												<span>${detail.detailName}</span>
												<input type="text" class="default-input"  style="display:none;width:80%" name="detailName" value="${detail.detailName}"/>
											</div>
											
										</div>
										<div class="col-sm-4 col-val">
											<div class="dictionary-title">
												<span>${detail.detailCode}</span>
												<input type="text" class=" default-input"  style="display:none;width:80%" name="detailCode" value="${detail.detailCode}"/>
											</div>
											
										</div>
										<div class="col-sm-4 col-val position">
											<div class="dictionary-title">
												<span>${detail.detailSort}</span>
												<input type="text" class="default-input" style="display:none;width:80%" name="detailSort" value="${detail.detailSort}"/>
											</div>
											
										</div>
									</div>
									<div class="col-sm-2 pl0 text-r">
										<a href="javascript:void(0)" class="edit-info">[编辑]</a>
										<a href="javascript:void(0)" class="delete-info">[删除]</a>
									</div>
								</li>
							
							</c:forEach>
						</ul>
					</div>
					</form>
				</div>
				</div>
	</div>
	<%@ include file="/common/footer.jsp"%>
	<script type="text/javascript" src="${base}/js/sysdict/sysdict_add.js?v=<wei:version/>"></script>
	<script>
	</script>
</body>

</html>
