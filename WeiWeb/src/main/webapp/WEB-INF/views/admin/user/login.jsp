<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="/WEB-INF/tld/wei.tld" prefix="wei" %>
<%
    String base = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + base + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"></base>
	<meta charset="UTF-8">
	<title>微管理</title>
	<shiro:authenticated><%response.sendRedirect("index.shtml");%></shiro:authenticated>
	<link rel="stylesheet" href="js/common/layer/skin/layer.css?v=<wei:version/>" />
	<link rel="stylesheet" href="css/login/login.css?v=<wei:version/>" />
</head>
<body>
<div class="index-postion">
	<div class="banner"></div>
	<div class="title"></div>
	<div class="content">
		<form id="loginForm">
			<input type="hidden" id="enPassword" name="enPassword" />
			<input type="hidden" id="rememberMe" name="rememberMe" />
			<div class="position-div">
				<div class="login-div">
					<div class="login">
						<div class="user-div">
					         <i class='icon1'></i>
							<input type="text" placeholder="用户名"  class="input-text" id="username" name="username"/>
							<%--<div class="message">错误信息提示的地方</div>--%>
						</div>
						<input type="password" placeholder="密码"   style="width:0;height:0;float:left;visibility:hidden"/>
						<div class="user-div">
						 <i class='icon2'></i>
							<input  placeholder="密码" type="password" autocomplete="off" class="input-text" id="password" />
							<%--<div class="message">错误信息提示的地方</div>--%>
						</div>
						<div class="user-div clearfix">
							<div class="l-user">
								<i class='icon3'></i>
								<input  placeholder="验证码"  type="text" name="captcha" autocomplete="off" class="input-text" id="yzm" />
							</div>
							<div class="r-user">
								<div class='txts'><img class="kaptcha" src="${base}/open/getGifCode.shtml" id="kaptchaImage" /></div>
							</div>
						 <%--<i class='icon3'></i>--%>
                        	<%--<input  placeholder="验证码" value='验证码' type="txt" autocomplete="off" class="input-text" id="yzm" />--%>
                        	<%--<div class='txts'><img class="kaptcha" src="${base}/kaptcha/kaptcha.jpg" id="kaptchaImage" /></div>--%>
                        <%--<div class="message">错误信息提示的地方</div>--%>
                        </div>
                        <div class="user-div">
                           	<p><input type="checkbox" class="check-input" id="isRememberUsername" />记住我30天</p>
                         </div>
						<div class="btn-div login-btn" id="login-btn" >
                          登录
						</div>
					</div>
				</div>
			</div>

		</form>
		<div class="footer">
			<div>
				<p ></p>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" >
	var baseRoot = "${base}"
</script>
<script type="text/javascript" src="js/common/jquery/jquery1.8.3.min.js?v=<wei:version/>"></script>
<script type="text/javascript" src="js/common/layer/layer.js?v=<wei:version/>"></script>
<script type="text/javascript" src="js/common/MD5.js?v=<wei:version/>"></script>
<script type="text/javascript" src="js/login/login.js?v=<wei:version/>"></script>
</body>