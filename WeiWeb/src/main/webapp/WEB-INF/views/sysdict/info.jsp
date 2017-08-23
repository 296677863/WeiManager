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
	.dictionary-title {
		padding-left: 0px;
	}
</style>
</head>

<body  style="cursor: auto;">
		<div class="ibox-content" style="min-height: 505px;">

			<form>
				<div class="border-module">
				<div class="group-border mb20" style="min-height: 90px;">
					<div class="group-title">基本信息</div>
					<div class="col-sm-11 pl0">
						<div class="col-sm-4 pl0">
							<div class="group-div"><label class="label-left">字典名称：</label>
							<div class="input-left lh-div">${bean.dictName}</div>
							<%-- <input type="text" class="default-input required input-left " value="${bean.name}"> --%>
							</div>
						</div>
						<div class="col-sm-4 pl0">
							<div class="group-div">
								<label class="label-left">字典类型：</label>
								<div class="input-left lh-div">${bean.dictType}</div>
								
							</div>
						</div>
					
					</div>
					
				</div>
				</div>
				
				<div class="border-module">
				<div class="group-border mb20 pb30" style="overflow: auto;">
					<div class="group-title">字典选项</div>
					
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
										
					<div class="dictionary-list">
						<ul>
						<c:forEach items="${bean.sysDictDetails}" var="detail" varStatus="status" >
							<li>
								<div class="col-sm-10 pl0">
									<div class="col-sm-4 col-val">
										<div class="dictionary-title">
											<span>${detail.detailName}</span>
										</div>
									</div>
									<div class="col-sm-4 col-val">
										<div class="dictionary-title"><span>${detail.detailCode}</span></div>
									</div>
									<div class="col-sm-4 col-val position">
										<div class="dictionary-title"><span>${detail.detailSort}</span></div>
									</div>
								</div>	
								
							</li>
						</c:forEach>
						</ul>
					</div>
				</div>
				</div>
			</form>
	</div>
	<%@ include file="/common/footer.jsp"%>
	<script type="text/javascript" src="${base}/js/sysdict/sysdict_edit.js?v=<wei:version/>"></script>
</body>

</html>
