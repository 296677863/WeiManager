<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <!--360浏览器优先以webkit内核解析-->
    <title></title>
    <link rel="shortcut icon" href="favicon.ico">
    <jsp:include page="/common/head.jsp"></jsp:include>
	<link href="${base}/css/index/index.css?v=<wei:version/>" rel="stylesheet" />
	<link rel="stylesheet" href="${base}/css/index/index_v1.css?v=<wei:version/>" />


</head>

<body class="gray-bg">
    <div class="row  border-bottom white-bg dashboard-header">
        <div class="col-sm-12">
            <div class="login-record font-color-one">你好：<span class="user-name"><span class="font-color-five">${user.email }</span><c:if test="${not empty user.nickname }">（${user.nickname }）</c:if></span>，上次登录：<span class="last-login font-color-three"><fmt:formatDate value="${user.lastLoginTime }" pattern="yyyy-MM-dd" /></span>，上次登录IP：<span class="login-ip font-color-three"></span><!-- <span class="login-address font-color-three">湖北武汉</span> --></div>
        	<!-- <div class="user-right">
            	<i class="iconfont">&#xe61e;</i>
            	<a href="javascript:void(0)">个人中心</a>
        	</div> -->
        </div>
        <div class="col-sm-8 welcome-left">
        	<div class="number-one">
        		<div class="shortcut-div">
        			<div class="title-left font-color-one">快捷方式</div>
        			
        			<div class="title-right font-color-five edit-right" data-status="0"><i class="fa fa-edit" style="margin-left: 15px;"></i><span>编辑</span></div>
        			<div class="title-right font-color-five" id="quickSetting"><i class="fa fa-cog"></i>设置</div>
        		</div>
        		<div class="shortcut">
        			<ul>
        				
		        				<li>
		        					
		        							<div data-click="" class="shortcut-icon" data-id="1">
				        					</div>
		        						
		        					<p class="shortcut-title font-color-one"></p>
		        				</li>
        				
        			</ul>
        		</div>
        	</div>
        	<div class="number-one">
        		
        		<div>
        			<div  id="echartsLlineChart" style="height: 200px;margin-left: 20px;overflow: hidden;"></div>
        		</div>
        	</div>
        	<div class="number-one">
        		<div class="shortcut-div">
        			<div class="title-left font-color-one">版本信息</div>
        		</div>
        		<div class="system-div">
        			<div class="system-left font-color-one">
        				<ul>
        					<li>系统版本：<span>weiV1.1.0</span></li>
        					<li>版权所有：<span></span></li>
        					<li>技术支持：<span>zed</span></li>
        					<li>官方网站：<span></span></li>
        					<li>联系电话：<span></span></li>
        				</ul>
        			</div>
        			<div class="system-right">
        				<div class="qr-code"><img alt="" style="width:110px;height: 110px;" src="${base}/images/index/getqrcode.jpg"></div>
        				<p>关注zed</p>
        			</div>
        		</div>
        	</div>
        	
        </div>
        <div class="col-sm-4">
        	<div class="log-content">
	        	<div class="user-log font-color-one">系统更新日志</div>
	        	
	        			<div class="log-time font-color-one">&nbsp;<fmt:formatDate value="${title.createDate}" pattern="yyyy-MM-dd" /></div>
			        	<ul class="font-color-three log-list">
			        				<ol></ol>
			        			
			        			
			        			
			        		
			        	</ul>
	        	
	        	
           </div>
        </div>
        
    </div>

   <%@include file="/common/footer.jsp" %> 
		<script src="${base}/js/homepage/homepage.js?v=<wei:version/>"></script>
</body>

</html>