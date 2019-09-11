<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/style/news.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
</head>
<body>
<div class="container" style="width: 1100px !important">
<jsp:include page="common/head.jsp"/>

<div class="row-fluid">
	<div class="span8">
		<jsp:include page="${mainPage }"/>
	</div>
	<div class="span4">
		<div class="data_list right_news_list">
			<div class="dataHeader">最近更新</div>
			<div class="datas">
				<ul>
					<c:forEach var="newsMovieList" items="${newsMovieList }">
						<li><a href="movie?action=show&movieId=${newsMovieList.movie_id }" title="${newsMovieList.movie_name }">${fn:substring(newsMovieList.movie_name,0,22) }</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="data_list right_news_list">
			<div class="dataHeader">热门电影</div>
			<div class="datas">
				<ul>
					<c:forEach var="HotMovieList" items="${HotMovieList }">
								<li><a
									href="movie?action=show&movieId=${HotMovieList.movie_id }"
									title="${HotMovieList.movie_name }">${fn:substring(HotMovieList.movie_name,0,22) }</a></li>
							</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</div>

<jsp:include page="common/foot.jsp"/>
</div>
</body>
</html>