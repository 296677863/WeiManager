<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="data_list">
	<div class="dataHeader navi">
	${navCode} 
	</div>
	<div class="datas news_type_list">
		<ul>
			<c:forEach var="MovieType" items="${MovieListWithType }">
				<li><span>[<fmt:formatDate value="${MovieType.movie_date }" type="date" pattern="yyyy-MM-dd"/>]</span>&nbsp;&nbsp;<a href="movie?action=show&movieId=${MovieType.movie_id }" title="${MovieType.movie_name }">${fn:substring(MovieType.movie_name,0,42) }</a></li>
			</c:forEach>
		</ul>
	</div>
	<div>
	${pageCode }
	</div>
</div>