<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Cache-Control" content="no-cache" />
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
		<title>管理中心 </title>
		<!--[if lt IE 8]>
    	<meta http-equiv="refresh" content="0;ie.html" />
    	<![endif]-->
    	<%@include file="/common/head.jsp" %>
        <link rel="shortcut icon" href="favicon.ico">
        <link href="${base}/css/index/index.css?v=<wei:version/>" rel="stylesheet" />
        <link rel="stylesheet" href="${base}/css/index/remediation.css?v=<wei:version/>">
    </head>
    <body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
		<div id="wrapper">
		<div class="headerleft bg-color">
			<div style="width: 380px;margin: 0 auto;">
			<img src="${base}/images/index/jinghui.png" class="index-logo" /><span class="font-color-one" style="font-size: 20px">${main_top_name}</span>
			</div>
		</div>
		<div class="row border-bottom" style="margin-left: 100%;">
           <nav class="navbar navbar-static-top bg-color" role="navigation" style="margin-bottom: 0">
			<ul class="nav navbar-top-links">
				<div class="index-message">
					<div class="tips-message">
						<a href="javascript:void(0)" class="font-color-one"><i class="fa fa-bell"></i></a>
					</div>
					<div class="dropdown-menu dropdown-alerts dropdown-message">
						<div class="tips-title">消息提醒</div>
						<ul>
							
						</ul>
					</div>
				</div>
			
				<div class="index-help">
					<div class="dropdown J_tabClose font-color-one" data-toggle="dropdown">
						<i class="iconfont">&#xe60c;</i>&nbsp;帮助
						<span class="caret"></span>
					</div>
					<ul role="menu" class="dropdown-menu dropdown-menu-right user-dropdown maincolor">
						<li><a href="">操作手册</a></li>
					</ul>
				</div>

				<div class="roll-nav roll-right all-menu">
					<div class="dropdown J_tabClose font-color-one" data-toggle="dropdown">
					   <i class="iconfont">&#xe60b;</i>
					     
					   <span class="caret"></span>

					</div>
					<ul role="menu" class="dropdown-menu dropdown-menu-right user-dropdown maincolor">
						<li class="J_tabShowActive">
						<%--	<a class="J_menuItem" href="" data-index="-1">
	                                       		个人中心
	                          </a>--%>
						</li>
							<li class="J_tabShowActive">
							<a class="J_menuItem" href="${base}/user/userInfo.shtml" data-index="-1">
	                                       		账号信息
	                          </a>
						</li>
						<li class="J_tabShowActive">
							<a class="weiOpenBtn" data-title="密码修改" data-url="${base}/user/editpwd.shtml" data-height="50%" data-width="40%">
	                                       		密码修改
	                          </a>
						</li>
						
						<input id="username" type="hidden" value='""'/>
						
						
						
						<li class="J_tabCloseOther">
							<a href="javascript:void(0);" onclick="logout();">退出</a>
						</li>
						
					</ul>
			  </div>
			  <div class="theme">
			  	<i class="fa fa-tasks"></i>
			  	主题
			  </div>
			</ul>
		  </nav>
         </div>
        <!--左侧导航开始-->
		<nav class="bg-color navbar-static-side" role="navigation">
				<div class="nav-close"><i class="fa fa-close"></i>
				</div>

				<div class="sidebar-collapse">

					<div class="navbar-minimalize navbar-headerleft maincolor">
					  <i class="iconfont font-color-four">&#xe607;</i>
					 </div>
					<ul class="nav" id="side-menu">
						<c:forEach items="${menus}" var="menu" varStatus="status">
							<c:if test="${menu.level==1 }">
								<li>
									<a>
										<c:choose>
											<c:when test="${menu.iconId != null && menu.iconId ne ''}">
												${menu.iconId}
											</c:when>
											<c:otherwise>
												<i class="fa fa-bar-chart${status.index } " ></i>
											</c:otherwise>
										</c:choose>
										<span class="nav-label">${menu.name}</span>

									</a>
									<!--collapse" aria-expanded="false" style="height: 0px;" 修复页面进来后要看到左侧菜单展开在收起来的bug-->
									<ul class="nav nav-second-level collapse" aria-expanded="false">
										<c:forEach items="${menus}" var="sub" varStatus="m" >
											<c:if test="${sub.parent eq menu.id}">

													<li>
														<a class="J_menuItem" href="${base}<c:if test="${!fn:startsWith(sub.url, '/')}">/</c:if>${sub.url}"   data-index="${m.index}">
															<%--<c:if test="${sub.iconId != null && sub.iconId ne ''}">--%>
																<%--${sub.iconId}--%>
															<%--</c:if>--%>
															<span class="nav-label">${sub.name}</span>
														</a>
													</li>

											</c:if>
									</c:forEach>
									</ul>
								</li>
							</c:if>

						</c:forEach>

					</ul>
				</div>
			</nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">

            <div class="row content-tabs">
					<button class="roll-nav roll-left J_tabLeft"><span class="glyphicon glyphicon-backward" aria-hidden="true"></span>
                </button>
					<nav class="page-tabs J_menuTabs">
					   <div class="page-tabs-content">
							<a href="javascript:;" class="active J_menuTab" data-id="homepage.jsp">首页</a>
						</div>

					</nav>
					<button class="roll-nav roll-right J_tabRight"><span class="glyphicon glyphicon-forward" aria-hidden="true"></span>
                </button>

				<div class="btn-group1 roll-nav roll-right">
						<button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                    </button>
						<ul role="menu" class="dropdown-menu dropdown-menu-right">
							<li class="J_tabShowActive">
								<a>定位当前选项卡</a>
							</li>
							<li class="divider"></li>
							<li class="J_tabCloseAll">
								<a>关闭全部选项卡</a>
							</li>
							<li class="J_tabCloseOther">
								<a>关闭其他选项卡</a>
							</li>
						</ul>
					</div>
				</div>

            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="${base}/open/homepage.shtml" frameborder="0" data-id="homepage.jsp" seamless></iframe>
            </div>
           <!--  <div class="footer">
                <div class="pull-right">&copy; 2014-2015 <a href="http://www.zi-han.net/" target="_blank">zihan's blog</a>
                </div>
            </div> -->
        </div>
        <!--右侧部分结束-->
            <!--主题-->
        <div class="them-div">
        	<div id="right-sidebar" class="sidebar-open">
            <div class="slimScrollDiv" style="position: relative; width: auto; height: 100%; padding-bottom: 120px;"><div class="sidebar-container" style="width: auto; height: 100%;">

                <ul class="nav nav-tabs navs-3">

                    <li class="active">
                        <a data-toggle="tab" href="#tab-1">主题</a>
                    </li>
                </ul>

                <div class="tab-content">
                    <div id="tab-1" class="tab-pane active">
                        <div class="sidebar-title">
                            <h3> <i class="fa fa-comments-o"></i> 主题设置</h3>
                            <small><i class="fa fa-tim"></i> 你可以从这里选择和预览主题的布局和样式，这些设置会被保存在本地，下次打开的时候会直接应用这些设置。</small>
                        </div>
                        <div class="skin-setttings">
                            <div class="title">皮肤选择</div>
                            <div class="setings-item black-skin nb" data-skin="default">
                                <span class="skin-name">
                                   
                                        默认皮肤
                                    
                                </span>
                            </div>
                            <div class="setings-item m-blue-skin nb" data-skin="blue">
                                <span class="skin-name ">
                       
                                         蓝色主题
                        
                    </span>
                            </div>
                        </div>
                    </div>
                </div>

            </div><div class="slimScrollBar" style="width: 4px; position: absolute; top: 0px; opacity: 0.4; display: none; border-radius: 7px; z-index: 99; right: 1px; height: 485px; background: rgb(0, 0, 0);"></div><div class="slimScrollRail" style="width: 4px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; opacity: 0.4; z-index: 90; right: 1px; background: rgb(51, 51, 51);"></div></div>
        </div>
        </div>
      <!--主题结束-->

    </div>
	<%@include file="/common/footer.jsp" %>

	<!-- 全局js -->
	<script src="${base}/js/common/jquery/metisMenu/jquery.metisMenu.js?v=<wei:version/>"></script>
	<script src="${base}/js/common/jquery/slimscroll/jquery.slimscroll.min.js?v=<wei:version/>"></script>
	<script type="text/javascript" src="${base}/js/common/content.js?v=<wei:version/>"></script>
	<!-- 自定义js -->
	<script src="${base}/js/common/hplus.js?v=<wei:version/>"></script>
	<script src="${base}/js/common/contabs.js?v=<wei:version/>" type="text/javascript" ></script>
	<script type="text/javascript">
	$(function(){
		$("#side-menu >li").each(function(){
			if($(this).find("ul").size() < 0){
				$(this).find("span.fa.arrow").remove();
			}
		});
	

	});

	/**退出*/
	function logout(){
		var load = layer.load();
		$.getJSON('${base}/u/logout.shtml',{},function(result){
			layer.close(load);
			if(result && result.status == 200){
				layer.msg('退出成功');
				window.location.reload(true);
				return !1;
			}else{
				layer.msg('退出失败，重试！');
			}
		});
	}

	var contractInterface = (function () {
		//滚动到指定选项卡
		var scrollToTab = function (element) {
					var marginLeftVal = calSumWidth($(element).prevAll()), marginRightVal = calSumWidth($(element).nextAll());
					// 可视区域非tab宽度
					var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".J_menuTabs"));
					//可视区域tab宽度
					var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
					//实际滚动宽度
					var scrollVal = 0;
					if ($(".page-tabs-content").outerWidth() < visibleWidth) {
						scrollVal = 0;
					} else if (marginRightVal <= (visibleWidth - $(element).outerWidth(true) - $(element).next().outerWidth(true))) {
						if ((visibleWidth - $(element).next().outerWidth(true)) > marginRightVal) {
							scrollVal = marginLeftVal;
							var tabElement = element;
							while ((scrollVal - $(tabElement).outerWidth()) > ($(".page-tabs-content").outerWidth() - visibleWidth)) {
								scrollVal -= $(tabElement).prev().outerWidth();
								tabElement = $(tabElement).prev();
							}
						}
					} else if (marginLeftVal > (visibleWidth - $(element).outerWidth(true) - $(element).prev().outerWidth(true))) {
						scrollVal = marginLeftVal - $(element).prev().outerWidth(true);
					}
					$('.page-tabs-content').animate({
						marginLeft: 0 - scrollVal + 'px'
					}, "fast");
				},
		//计算元素集合的总宽度
				calSumWidth = function (elements) {
					var width = 0;
					$(elements).each(function () {
						width += $(this).outerWidth(true);
					});
					return width;
				};
		return {
			openTab: function (options) {
				var defaults = {
					"url": "", //打开页面地址
					"tabName": "",//打开标签页显示名称,只有在url不存在的情况下才有作用
					"refresh": false,//如果页面存在是否刷新当前页面
					"isNavigateTab": true,//打开页面地址是否在左侧单行栏上
					"success": function () { } //打开成功后调用子页面的方法名称
				};
				var opts = $.extend(false, defaults, options);
				if (opts.url) {
					// 获取标识数据
					var dataUrl, dataIndex, menuName, menuItem, flag = true;
					if (opts.isNavigateTab) {
						$("#side-menu .J_menuItem").each(function () {
							dataUrl = $(this).attr('href');
							dataIndex = $(this).data('index');
							menuName = $.trim($(this).text());
							if (dataUrl && opts.url.indexOf(dataUrl) == 0) {
								menuItem = this;
								return false;
							}
						});
					} else {
						menuName = opts.tabName;
						dataUrl = opts.url;
						dataIndex = $("#side-menu .J_menuItem").length + 1 + $('.J_menuTab').length;
						menuItem = {};
					}
					if (menuItem) {
						// 选项卡菜单已存在
						$('.J_menuTab').each(function () {
							if ($(this).data('id') == dataUrl) {
								if (!$(this).hasClass('active')) {
									$(this).addClass('active').siblings('.J_menuTab').removeClass('active');
									scrollToTab(this);
									// 显示tab对应的内容区
									$('.J_mainContent .J_iframe').each(function () {
										if ($(this).data('id') == dataUrl) {
											if (opts.refresh || opts.url.indexOf("forPageChange") > -1) {
												$(this).attr("src", opts.url);
											}

											$(this).show().siblings('.J_iframe').hide();

											if ($.isFunction(opts.success)) {
												//调用子页面方法
												opts.success.call(this.contentWindow);
											}

											return false;
										}
									});
								}else{
                                          $('.J_mainContent .J_iframe').each(function () {
                                              if ($(this).data('id') == dataUrl) {
                                                  if (opts.refresh || opts.url.indexOf("forPageChange") > -1) {
                                                      $(this).attr("src", opts.url);
                                                  }

                                                  $(this).show().siblings('.J_iframe').hide();

                                                  if ($.isFunction(opts.success)) {
                                                      //调用子页面方法
                                                      opts.success.call(this.contentWindow);
                                                  }

                                                  return false;
                                              }
                                          });
								}
								flag = false;
								return false;
							}
						});
						// 选项卡菜单不存在
						if (flag) {
							var str = '<a href="javascript:;" class="active J_menuTab" data-id="' + dataUrl + '">' + menuName + ' <i class="fa fa-times-circle"></i></a>';
							$('.J_menuTab').removeClass('active');

							// 添加选项卡对应的iframe
							var str1 = '<iframe class="J_iframe" name="iframe' + dataIndex + '" width="100%" height="100%" src="' + opts.url + '" frameborder="0" data-id="' + dataUrl + '" seamless></iframe>';
							$('.J_mainContent').find('iframe.J_iframe').hide().parents('.J_mainContent').append(str1);

							//显示loading提示
							var loading = layer.load();

							$('.J_mainContent iframe:visible').load(function () {
								if ($.isFunction(opts.success)) {
									//调用子页面方法
									opts.success.call(this.contentWindow);
								}
								//iframe加载完成后隐藏loading提示
								layer.close(loading);
							});
							// 添加选项卡
							$('.J_menuTabs .page-tabs-content').append(str);
							scrollToTab($('.J_menuTab.active'));
						}
					}
				}
			},
			openFixedTab: function (options) {
				var defaults = {
					"id": "", //打开页面data-id值
					"refresh": false,//如果页面存在是否刷新当前页面
					"success": function () { } //打开成功后调用子页面的方法名称
				};
				var opts = $.extend(false, defaults, options);
				if (opts.id) {
					var menuitem=$('.J_iframe[data-id="' + opts.id + '"]').has(".tab_fixed");
					if (menuitem) {
						if (!$(menuitem).hasClass('active')) {
							$(menuitem).addClass('active').siblings('.J_menuTab').removeClass('active');
							scrollToTab(this);
							// 显示tab对应的内容区
							$('.J_mainContent .J_iframe').each(function () {
								if ($(this).data('id') == opts.id) {
									var src=$(this).attr('src');
									if (opts.refresh || !src) {
										$(this).attr("src", src || $(this).attr('original-src'));
									}

									$(this).show().siblings('.J_iframe').hide();

									if ($.isFunction(opts.success)) {
										//调用子页面方法
										opts.success.call(this.contentWindow);
									}
									return false;
								}
							});
						}
					}
				}
			},
			UpdateUesrImage: function (AttachmentId) {
				//提供给子页面 重新定义头像的更新
				if (AttachmentId != null) {
					$('#UserIcon').attr('src', '/api/SysManage/Image/GetImage/' + AttachmentId);
				}
			},
			GetArea: function () {
				var objArea = $("#content-main");
				var opts = {
					width: objArea.width(),
					height: objArea.height(),
					left: objArea.offset().left,
					top: objArea.offset().top
				}
				return opts;
			}

		}
	})();

  $(function(){
		
	  
	  
      $(document).on("click",".message-num",function () {
          $(".dropdown-message").toggle();
      });

    
      $(document).on("click","[data-click]",function () {
          var $this = $(this);
          contractInterface.openTab({ "url": encodeURI( $this.data("click")),"refresh":true});
      });

    

	 

  });

			</script>
	</body>
</html>