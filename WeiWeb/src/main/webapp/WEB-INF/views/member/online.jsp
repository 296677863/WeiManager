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
<title>在线用户</title>
<%@include file="/common/head.jsp" %>
</head>
<body>
	<div class="ibox-content">
		
			<div id="DataTables_Table_0_wrapper"
					class="dataTables_wrapper form-inline" role="grid">
					<div class="row">
						<div class="col-sm-6">
							<h2>当前在线用户</h2>
		<!-- 					<button class="m-btn maincolor main-btn" id="addBtn" type="button"><span>添加</span></button> -->
		<!-- 					<button class="m-btn disable-btn" id="deleteButton" type="button"><span>批量删除</span></button> -->
		<!-- 					<button class="m-btn disable-btn" id="reseBtn" type="button"><span>密码重置</span></button> -->
						</div>
						<div class="col-sm-6">
							<div class="search-div">
		<!-- 							<input type="text" class="default-input" id="searchInput"/> -->
		<!-- 							<div class="search"><i class="iconfont"></i></div> -->
							</div>
						</div>
					</div>
					<table
						class="table table-striped table-bordered table-hover dataTables-example dataTable"
						id="listTable" aria-describedby="DataTables_Table_0_info">
						<thead>
							<tr role="row">
								<th>SessionID</th>
								<th>昵称</th>
								<th>Email/帐号</th>
								<th>创建回话</th>
								<th>回话最后活动</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
						
							<c:forEach items="${list}" var="bean">  
							<tr class="gradeA">
								<td>
								  <div class="ellipsis-div"title="${bean.sessionId }">
									${bean.sessionId }
									</div>
								</td>
								<td>
								  <div class="ellipsis-div"title="${bean.nickname}">
									${bean.nickname}
								  </div>
								</td>
								<td>
								  <div class="ellipsis-div"title="${bean.email }">
									${bean.email }
									</div>
								</td>
								<td>
								  <div class="ellipsis-div"title="${bean.startTime }">
									<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</div>
								</td>
								<td>
								  <div class="ellipsis-div"title="${bean.lastAccess }">
								<fmt:formatDate value="${bean.lastAccess}" pattern="yyyy-MM-dd HH:mm:ss"/>
									
									</div>
								</td>
								<td>
								   <div class="ellipsis-div"title="${bean.sessionStatus }">
									${bean.sessionStatus eq true?"有效":"已踢出" }
									</div>
								</td>
							
								<td>
									<a class="weiOpenBtn" data-title="详情" data-url="${base}/member/onlineDetails/${bean.sessionId}.shtml">[查看详情]</a>
									<a class="changeSessionStatus" href="javascript:void(0);" sessionId="${bean.sessionId}" status="${(bean.sessionStatus eq true?1:0)}">${(bean.sessionStatus eq true?'[踢出]':'[激活]')}</a>
									
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
		</div>
		
		
	</div>

	<%@ include file="/common/footer.jsp"%>
	
	<script type="text/javascript">
		$(function(){
			$(".changeSessionStatus").on('click',function(){
				var self = $(this);
				var text = $.trim(self.text());
				var index = layer.confirm("确定"+ text +"？",function(){
					changeSessionStatus(self.attr('sessionId'),self.attr('status'),self);
					layer.close(index);
				});
			});
		});
		//改变状态
		function changeSessionStatus(sessionIds,status,self){
			status = !parseInt(status);
			//loading
			var load = layer.load();
			$.post("${base}/member/changeSessionStatus.shtml",{status:status,sessionIds:sessionIds},function(result){
				layer.close(load);
				if(result && result.status == 200){
					return self.text("["+result.sessionStatusText+"]"),
								self.attr('status',result.sessionStatus),
									self.parent().prev().text(result.sessionStatusTextTd);
									layer.msg('操作成功'),!1;
				}else{
					return layer.msg(result.message,function(){}),!1;
				}		
			},'json');
		}
	
	</script>
</body>

</html>
	