<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%
String base = request.getContextPath();

%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta http-equiv="expires" content="0" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Cache-Control" content="no-cache" />
    <title></title>

    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="<%=base%>/css/bootstrap.min.css?v=<wei:version/>"
	rel="stylesheet">
    <link href="<%=base%>/css/animate.css?v=<wei:version/>"
	rel="stylesheet">
    <link href="<%=base%>/css/style.css?v=<wei:version/>"
	rel="stylesheet">

</head>

<body class="gray-bg">


    <div class="middle-box text-center animated fadeInDown">
        <h3 class="font-bold"></h3>

        <div class="error-desc">

            <h2>系统提示</h2>
			<hr>
			<p>您已经被系统踢出。请重新登录或者联系管理员！</p>
        </div>
    </div>

    <!-- 全局js -->
    <script type="text/javascript" src="<%=base%>/js/common/jquery/jquery.min.js?v=<wei:version/>"></script>
    <script type="text/javascript" src="<%=base%>/js/bootstrap.min.js?v=<wei:version/>"></script>


</body>

</html>
