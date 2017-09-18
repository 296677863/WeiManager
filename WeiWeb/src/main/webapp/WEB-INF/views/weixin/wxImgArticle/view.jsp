<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/wei.tld" prefix="wei" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <title>新闻预览</title>
	<style type="text/css">
	
		/*可以在这里添加你自己的css*/
		 *{
			margin:0px;
			padding:0px;
		}
		
		img {
		max-width: 100%;
		width: 100%;
		margin: 0 auto;
		}
		body {
		overflow-y: scroll !important;
		/* padding: 8px 20px; */
		font-family: "Helvetica Neue",Helvetica,"Hiragino Sans GB","Microsoft YaHei",Arial,sans-serif;
		}
		.view {
		word-break: break-all;
		}
		.vote_area {
		display: block;
		}
		.vote_iframe {
		background-color: transparent;
		border: 0 none;
		height: 100%;
		}
		#edui1_imagescale{display:none !important;}
		
		p{
			padding: 8px 10px;
		    width:95%;
		    margin:0 auto;
			white-space: normal;
		}
		#activity-detail{
			width:640px;
			margin:0 auto;
		}
		
		.rich_media_title{
		font-size:30px;
		    line-height: 50px;
		        width: 95%;
		    margin: 0 auto;
		        margin-top: 10px;
		}
		.rich_media_meta_list{
		font-size:22px;
		color:#666;
		    line-height: 35px;
		       width: 95%;
		    margin: 0 auto;
		   
		}
		.rich_media_content p{
		 font-size:26px;
		 line-height: 45px;
		 margin-bottom:10px;
		
		} 
	</style>
   	<script type="text/javascript" src="${base}/js/common/jquery/jquery.min.js?v=<wei:version/>"></script>
    <script type="text/javascript">var phoneScale=parseInt(window.screen.width)/640;document.write('<meta name="viewport" content="width=640, minimum-scale = '+phoneScale+", maximum-scale = "+phoneScale+', target-densitydpi=device-dpi">')</script>
	<link  href="${base}/js/ueditor/third-party/video-js/mediaelementplayer.css?v=<wei:version/>"  rel="stylesheet" type="text/css"></link>
	<script  src="${base}/js/ueditor/third-party/video-js/mediaelement-and-player.js?v=<wei:version/>"></script>

	<script type="text/javascript">
		 $(function(){
		       $("video").each(function(){
		    	   var $obj = $(this);
//		    	   $obj.attr("poster",$obj.data("poster"));
		    	   $obj.attr("height","342");
		    	   $obj.attr("width","608");
		       })
		       $('video').mediaelementplayer();
		}) 
	
	</script>
</head>
<body id="activity-detail" class="zh_CN ">
<div class="rich_media">
    <div class="rich_media_inner" style="padding-bottom: 78px"><h2 class="rich_media_title" id="activity-name"> ${news.title } </h2>
        <div class="rich_media_meta_list"><em id="post-date" class="rich_media_meta text"><fmt:formatDate value="${news.createDate }" pattern="yyyy-MM-dd" /></em> <span
                class="rich_media_meta link nickname" href="javascript:void(0);" id="post-user" style="word-break:break-all">${news.summary}</span></div>
        <div id="page-content">
            <div id="img-content">
            	<c:if test="${news.showCover == true}">
            		<p class="rich_media_thumb" id="media">
            			<c:if test="${not empty news.cover}">
            			    <img  src="/upload/${news.cover}">
            			</c:if>
                 	</p>
            	</c:if>
                
                <div class="rich_media_content" id="js_content" style="padding-top: 60px">
                	${news.content }
                </div>
                <c:if test="${not empty news.href }">
                	 <div class="rich_media_tool" id="js_toobar" style="float: right;">
	                   	<div id="js_read_area" class="media_tool_meta link_primary meta_primary"  ><a href="${news.href}" style="color: #8c8c8c;text-decoration: none;">阅读全文</a>
	                       	<span id="readNum"></span>
	                    </div>
                	</div>
                </c:if>
               
            </div>
        </div>
    </div>
</div>


</body>
</html>