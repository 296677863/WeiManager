<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <!--360浏览器优先以webkit内核解析-->

    <title>新闻发布模块</title>
    <%@ include file="/common/head.jsp" %>
    <link href="${base}/css/font-awesome.min.css?v=<wei:version/>" rel="stylesheet" type="text/css">
    <link href="${base}/css/animate.css?v=<wei:version/>" rel="stylesheet" type="text/css">

    <link rel="stylesheet" type="text/css" href="${base}/css/config_input.css?v=<wei:version/>"/>
    <link rel="stylesheet" type="text/css" href="${base}/css/weixin/wxImgArticle/edit.css?v=<wei:version/>"/>
    <style type="text/css">
        .img-div{
            position: relative;
            cursor: pointer;
            width: 100px;

        }
        .img-append-mask{
            display: none;
        }
        .img-append-mask>.iconfont{
            position: absolute;
            bottom: 0px;

            color: #fff;
            font-size: 20px;
            z-index: 4;
            line-height: 40px;
            left: 40%;
        }

        .img-mask{
            width: 100%;
            background: #000;
            opacity: 0.5;
            position: absolute;
            bottom: 0px;
            left: 0px;
            height: 40px;
            z-index: 3;
            line-height: 40px;
        }

    </style>

</head>

<body class="gray-bg">
<div class="ibox-content" style="min-height: 505px;">
    <form>
        <div class="news-edit">
            <div class="media-div">
                <div class="preview" style="margin-top: 35px;">多媒体</div>
                <div class="media-content" id="uploadImg">
                    <div class="media-list">
                        <i class="iconfont">&#xe60f;</i>
                        <div >图片</div>
                    </div>
                </div>
                <div class="media-content" id="uploadVideo">
                    <div class="media-list">
                        <i class="iconfont">&#xe60e;</i>
                        <div >视频</div>
                    </div>
                </div>

            </div>
            <div class="width-l">
                <div class="border-module">
                    <div class="group-border mb20" style="overflow: auto;">
                        <input type="hidden" id="newsId" name="id" value="${news.id }"/>
                        <input type="hidden" id="newCreateDate" name="createDate" value="<fmt:formatDate value="${news.createDate }" pattern="yyyy-MM-dd HH:mm:ss" />"/>
                        <div class="group-title">新闻信息</div>
                        <div class="col-sm-12 pl0 col-val">
                            <div class="group-div">
                                <label class="label-left">标题：</label>
                                <input type="text" class="default-input required input-left" name="title" id="newsTitle" value="${news.title }">
                            </div>
                        </div>
                        <div class="col-sm-12 pl0 col-val">
                            <div class="group-div">
                                <label class="label-left">作者(选填)：</label>
                                <input type="text" class="default-input required input-left" name="author" id="newsAuthor" value="${news.author }">
                            </div>
                        </div>
                        <div class="col-sm-12 pl0 col-val">
                            <div class="group-div">
                                <label class="label-left"></label>
                                <div class="input-left">
                                    <script id="editor" type="text/plain" style="width:100%; height: 400px;margin-top: 10px;">
                                        ${news.content}
                                    </script>
									 <%--<textarea id="editor" placeholder="这里输入内容" autofocus name="content">--%>
                                         <%--${news.content}--%>
                                     <%--</textarea>--%>
                                </div>

                            </div>
                        </div>
                        <div class="col-sm-12 pl0 col-val">
                            <div class="group-div">
                                <label class="label-left"></label>
                                <div class="input-left">

                                    <c:choose>
                                        <c:when test="${not empty news.href}">
                                            <div class="check-list link-div">
                                                <div class="check-div white-color" check="1" >
                                                    <i class="iconfont"></i>
                                                </div>
                                                <span>原文链接</span>
                                            </div>
                                            <input type="text" class="default-input text-link" style="display: block" id="newshref" value="${news.href }" placeholder="http://"/>

                                        </c:when>
                                        <c:otherwise>
                                            <div class="check-list link-div" >
                                                <div class="check-div" check="0">
                                                    <i class="iconfont"></i>
                                                </div>
                                                <span>原文链接</span>
                                            </div>
                                            <input type="text" class="default-input text-link" style="display: none" id="newshref" value="http://" placeholder="http://"/>
                                        </c:otherwise>
                                    </c:choose>

                                    <%--<div class="check-list link-div">--%>
                                        <%--<div class="check-div" check="0" id="checkNewsHref">--%>
                                            <%--<i class="iconfont"></i>--%>
                                        <%--</div>--%>
                                        <%--<span>原文链接</span>--%>
                                    <%--</div>--%>
                                    <%--<input type="text" class="default-input text-link" id="newshref" value="http://"--%>
                                           <%--placeholder="http://"/>--%>
                                </div>

                            </div>
                        </div>
                        <div class="col-sm-12 pl0 col-val">
                            <div class="group-div">
                                <label class="label-left">封面：</label>
                                <div class="input-left" style="margin-top: 3px;">
                                    小图片建议尺寸：200像素 * 200像素&nbsp;&nbsp;大图片建议尺寸：720像素 * 400像素封面
                                </div>

                            </div>
                        </div>
                        <div class="col-sm-12 pl0 col-val">
                            <div class="group-div">
                                <label class="label-left"></label>
                                <div class="input-left">
                                    <div class="cover-div dispalyCover">
                                        <c:if test="${not empty news.cover}">
                                            <div class="img-div">
                                                <input type="hidden" id="newsCoverId" name="cover" value="${news.cover}">
                                                <img  src="/upload/${news.cover}" width="100px" height="100px">
                                                <div class="img-append-mask">
                                                    <div class="img-mask"></div>
                                                    <i class="iconfont delete-icon">&#xe611;</i>
                                                </div>
                                            </div>
                                        </c:if>
                                        <%--<img src="${base}/resources/admin/base/images/people.jpg">--%>
                                    </div>
                                    <button class="m-btn maincolor main-btn"  type="button" style="float:left;margin-top: 2px;">
                                        <div id="uploadCover">&nbsp;上传封面&nbsp;</div>
                                    </button>
                                    <div class="check-list showtext">

                                        <c:choose>
                                            <c:when test="${news.showCover == true }">
                                                <div class="check-div white-color" check="1" id="newsShowCover">
                                                    <i class="iconfont"></i>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="check-div" check="0" id="newsShowCover">
                                                    <i class="iconfont"></i>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                    <span>封面图片显示在正文中</span>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="col-sm-12 pl0 col-val">
                        <div class="group-div">
                            <label class="label-left">摘要(选填)：</label>
                            <div class="input-left">
                                <textarea rows="5" class="default-input textarea-content" id="newsSummary" name="summary">${news.summary }</textarea>
                                <div class="count-div"><span class="number">0</span>/<span>120</span></div>
                            </div>

                        </div>
                    </div>
                    <div class="col-sm-12 pl0 col-val">
                        <div class="group-div">
                            <label class="label-left"></label>
                            <div class="input-left">
                                <button class="m-btn maincolor main-btn saveNews" type="button"
                                        style="float:left;margin-right:15px;">
                                    <span>上传保存</span>
                                </button>
                                <button class="m-btn maincolor main-btn cancleNews" type="button"
                                        style="float:left;margin-right:15px;">
                                    <span>取消</span>
                                </button>
                                <button class="m-btn maincolor main-btn priNews" type="button" style="float:left">
                                    <span>预览</span>
                                </button>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- 自定义js -->
<%@ include file="/common/footer.jsp" %>
<script src="${base}/js/ueditor/ueditor.config.js?v=<wei:version/>"></script>
<script src="${base}/js/ueditor/ueditor.all.min.js?v=<wei:version/>"></script>
<script src="${base}/js/ueditor/lang/zh-cn/zh-cn.js?v=<wei:version/>"></script>
<script src="${base}/js/weixin/wxImgArticle/edit.js?v=<wei:version/>"></script>
</body>

</html>