<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="data_list">
	<div class="dataHeader navi">
		${navCode}
	</div>
	<div>
		<div class="news_title"><h3>${movie.movie_name }</h3></div>
		<div><img alt="" src="${movie.imageName }" width="700" height="500"></div>
		<div class="news_info">
			发布时间：[<fmt:formatDate value="${movie.movie_date }" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>]&nbsp;&nbsp;导演：${movie.movie_director }
			&nbsp;&nbsp;电影类型：[${movieType.type_name }]&nbsp;&nbsp;浏览次数：${movie.click }
		</div>
		
		<div >
		<table style="height: 44px; width: 770px; ">
		<tr align="center">
		<td style="width: 200px; "><a href="${movie.url }" >下载地址</a></td>
		<td style="width: 264px; ">
		<a href="view?movieId=${movie.movie_id }">在线观看</a>
		
		</td>
		</tr>
		</table>
			
		</div>
		
	</div>
	<div class="upDownPage">
		<!-- ${pageCode } -->
	</div>
</div>

