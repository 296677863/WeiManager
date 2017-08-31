<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

    <title>自定义菜单管理</title>
    <%@include file="/common/head.jsp" %>
    <link rel="stylesheet" type="text/css" href="${base}/css/config_input.css?v=<wei:version/>"/>
    <link href="${base}/css/weixin/wxMenu_index.css?v=<wei:version/>" rel="stylesheet"/>
    <style type="text/css">
        .parent-menu-title {
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            width: 95%;
        }

        .dictionary-div {
            height: 87px;
        }

    </style>
</head>
<body>

<div class="ibox-content" style="min-height: 505px;">
    <div class="width-r">
        <div class="preview">预览</div>
        <div>
            <div class="width-div" id="temp-one" style="display:block!important;">
                <img src="${base}/css/weixin/images/wechat-menu.jpg" width=100%/>
                <div class="wechat-footer">
                    <div class="keyboard"></div>
                    <div class="wechat-menu-div">
                        <ul class="wechat-menu-ul">

                        </ul>
                    </div>
                </div>

            </div>

        </div>
    </div>
    <div class="width-l">
        <div class="border-module">
            <div class="group-border mb20">
                <div class="group-title">微信菜单</div>

                <div class="dictionary-div">

                    <div class="add-child-menu">
                        <button class="m-btn maincolor main-btn" id="addButton" type="button">
                            <span>添加子菜单</span>
                        </button>
                    </div>

                    <div class="col-sm-12 pl0  dictionary-option">
                        <div class="col-sm-9 pl0">
                            <div class="col-sm-3 col-val">
                                <div class="title-bg">
                                    <span>显示顺序</span>

                                </div>

                            </div>
                            <div class="col-sm-3 pl0 col-val">
                                <div class="title-bg">
                                    <span>菜单名称</span>
                                </div>

                            </div>
                            <div class="col-sm-3 pl0 col-val position">
                                <div class="title-bg">
                                    <span>触发动作</span>
                                </div>

                            </div>
                            <div class="col-sm-3 pl0 col-val position">
                                <div class="title-bg">
                                    <span>响应动作</span>
                                </div>

                            </div>

                        </div>
                        <div class="col-sm-3 col-val pl0 pr0">
                            <div class="title-bg">
                                <span>操作</span>
                            </div>

                        </div>
                    </div>
                </div>
                <form id="addForm">
                    <div id="addclid" style="display:none;">
                        <div class="height-line">
                            <div class="col-sm-12 pl0">
                            <div class="col-sm-9 pl0">
                                <div class="col-sm-3 col-val">
                                    <div class="parent-menu-title">
                                        <span class="menus-num index"></span>
                                    </div>
                                </div>
                                <div class="col-sm-3 pl0 col-val">
                                    <div class="parent-menu-title">
                                        <input type="text" class="default-input" style="width:80%"
                                               name="name" value=""/>
                                    </div>
                                </div>

                                <div class="col-sm-3 pl0 col-val">
                                    <div class="parent-menu-title">
                                        <select name="type" class="default-input" style="width:80%"
                                                data-value=""></select>
                                    </div>

                                </div>

                                <div class="col-sm-3 pl0 col-val">
                                    <div class="parent-menu-title">
                                        <input type="text" class="default-input" style="width:80%"
                                               name="content" value=""/>
                                    </div>
                                </div>

                            </div>
                            <div class="col-sm-2 text-l pl0 pr0">
                                <a href="javascript:void(0)" class="save-info">[保存]</a>
                                <a href="javascript:void(0)" class="remove-info">[删除]</a>
                            </div>
                        </div>
                        </div>

                    </div>
                </form>

                <form id="infoForm">
                    <div class="dictionary-list">
                        <!-- 主菜单1 -->
                        <c:if test="${buttons[0] != null}">
                            <div class="row-menu-div" data-status="0">
                                <div class="col-sm-12 pl0">
                                    <div class="col-sm-9 pl0">
                                        <div class="col-sm-3 col-val">
                                            <div class="parent-menu-title">
                                                <span class="menus-num">1</span>
                                            </div>
                                        </div>
                                        <div class="col-sm-3 pl0 col-val">
                                            <div class="parent-menu-title">
                                                <span class="menus-name">${buttons[0].name}</span>
                                                <input type="text" class="default-input" style="display:none;width:80%"
                                                       name="name" value="${buttons[0].name }"/>
                                            </div>

                                        </div>
                                        <div class="col-sm-3 pl0 col-val">
                                            <div class="parent-menu-title">
                                                <span class="touch-off">${buttons[0].type}</span>
                                                <select name="type" class="default-input" style="display:none;width:95%"
                                                        data-value="${buttons[0].type}"></select>
                                            </div>

                                        </div>
                                        <div class="col-sm-3 pl0 col-val">
                                            <div class="parent-menu-title">
                                                <span class="response"
                                                      title="${buttons[0].content }">${buttons[0].content }</span>
                                                <input type="text" class="default-input" style="display:none;width:80%"
                                                       name="content" value="${buttons[0].content }"/>
                                            </div>

                                        </div>

                                    </div>
                                    <div class="col-sm-3 text-l pl0 pr0">
                                        <a href="javascript:void(0)" class="edit-info">[编辑]</a>
                                        <a href="javascript:void(0)" class="add-child">[添加子菜单]</a>
                                        <a href="javascript:void(0)" class="remove-main">[删除]</a>
                                    </div>
                                </div>
                                <div class="child-menu">
                                    <c:forEach items="${buttons[0].subs}" var="subs1" varStatus="status1">
                                        <li data-status="0">
                                            <div class="col-sm-12 pl0">
                                                <div class="col-sm-9 pl0">
                                                    <div class="col-sm-3 col-val">
                                                        <div class="menu-title">
                                                            <span class="chlid-img"></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-3 pl0 col-val">
                                                        <div class="menu-title">
                                                            <span class="childmenu-name" style="">${subs1.name}</span>
                                                            <input type="text" class="default-input"
                                                                   style="display:none;width: 80%; display: none;"
                                                                   name="name" value="${subs1.name}"/>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-3 pl0 col-val">
                                                        <div class="menu-title">
                                                            <span class="childtouch-off" style="">${subs1.type}</span>
                                                            <select name="type" class="default-input"
                                                                    style="display:none;width:80%"
                                                                    data-value="${subs1.type}"></select>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-3 pl0 col-val">
                                                        <div class="menu-title">
                                                            <span class="childresponse" style="">${subs1.content}</span>
                                                            <input type="text" class="default-input"
                                                                   style="display:none;width: 80%; display: none;"
                                                                   name="content" value="${subs1.content}"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-3 text-l pl0 pr0">
                                                    <a href="javascript:void(0)" class="childedit-info">[编辑]</a>
                                                    <a href="javascript:void(0)" class="delete-info">[删除]</a>
                                                    <a href="javascript:void(0)" class="up-info">[上移]</a>
                                                    <a href="javascript:void(0)" class="down-info">[下移]</a>
                                                </div>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:if>
                        <!-- 主菜单2 -->
                        <c:if test="${buttons[1] != null}">
                            <div class="row-menu-div" data-status="0">

                                <div class="col-sm-12 pl0">
                                    <div class="col-sm-9 pl0">
                                        <div class="col-sm-3 col-val">
                                            <div class="parent-menu-title">
                                                <span class="menus-num">2</span>
                                            </div>
                                        </div>
                                        <div class="col-sm-3 pl0 col-val">
                                            <div class="parent-menu-title">
                                                <span class="menus-name">${buttons[1].name}</span>
                                                <input type="text" class="default-input" style="display:none;width:80%"
                                                       name="name" value="${buttons[1].name }"/>
                                            </div>

                                        </div>
                                        <div class="col-sm-3 pl0 col-val">
                                            <div class="parent-menu-title">
                                                <span class="touch-off">${buttons[1].type}</span>
                                                <select name="type" class="default-input" style="display:none;width:80%"
                                                        data-value="${buttons[1].type}"></select>
                                            </div>

                                        </div>
                                        <div class="col-sm-3 pl0 col-val">
                                            <div class="parent-menu-title">
                                                <span class="response"
                                                      title="${buttons[1].content }">${buttons[1].content }</span>
                                                <input type="text" class="default-input" style="display:none;width:80%"
                                                       name="content" value="${buttons[1].content }"/>
                                            </div>

                                        </div>

                                    </div>
                                    <div class="col-sm-3 text-l pl0 pr0">
                                        <a href="javascript:void(0)" class="edit-info">[编辑]</a>
                                        <a href="javascript:void(0)" class="add-child">[添加子菜单]</a>
                                        <a href="javascript:void(0)" class="remove-main">[删除]</a>
                                    </div>
                                </div>
                                <div class="child-menu">
                                    <c:forEach items="${buttons[1].subs}" var="subs1" varStatus="status1">
                                        <li data-status="0">
                                            <div class="col-sm-12 pl0">
                                                <div class="col-sm-9 pl0">
                                                    <div class="col-sm-3 col-val">
                                                        <div class="menu-title">
                                                            <span class="chlid-img"></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-3 pl0 col-val">
                                                        <div class="menu-title">
                                                            <span class="childmenu-name" style="">${subs1.name}</span>
                                                            <input type="text" class="default-input"
                                                                   style="width: 80%; display: none;" name="name"
                                                                   value="${subs1.name}"/>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-3 pl0 col-val">
                                                        <div class="menu-title">
                                                            <span class="childtouch-off" style="">${subs1.type}</span>
                                                            <select name="type" class="default-input"
                                                                    style="display:none;width:80%"
                                                                    data-value="${subs1.type}"></select>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-3 pl0 col-val">
                                                        <div class="menu-title">
                                                            <span class="childresponse" style="">${subs1.content}</span>
                                                            <input type="text" class="default-input"
                                                                   style="width: 80%; display: none;" name="content"
                                                                   value="${subs1.content}"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-3 text-l pl0 pr0">
                                                    <a href="javascript:void(0)" class="childedit-info">[编辑]</a>
                                                    <a href="javascript:void(0)" class="delete-info">[删除]</a>
                                                    <a href="javascript:void(0)" class="up-info">[上移]</a>
                                                    <a href="javascript:void(0)" class="down-info">[下移]</a>
                                                </div>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:if>


                        <!-- 主菜单3 -->
                        <c:if test="${buttons[2] !=null }">
                            <div class="row-menu-div" data-status="0">

                                <div class="col-sm-12 pl0">
                                    <div class="col-sm-9 pl0">
                                        <div class="col-sm-3 col-val">
                                            <div class="parent-menu-title">
                                                <span class="menus-num">3</span>
                                            </div>
                                        </div>
                                        <div class="col-sm-3 pl0 col-val">
                                            <div class="parent-menu-title">
                                                <span class="menus-name">${buttons[2].name}</span>
                                                <input type="text" class="default-input" style="display:none;width:80%"
                                                       name="name" value="${buttons[2].name }"/>
                                            </div>

                                        </div>
                                        <div class="col-sm-3 pl0 col-val">
                                            <div class="parent-menu-title">
                                                <span class="touch-off">${buttons[2].type}</span>
                                                <select name="type" class="default-input" style="display:none;width:80%"
                                                        data-value="${buttons[2].type}"></select>
                                            </div>

                                        </div>
                                        <div class="col-sm-3 pl0 col-val">
                                            <div class="parent-menu-title">
                                                <span class="response"
                                                      title="${buttons[2].content }">${buttons[2].content }</span>
                                                <input type="text" class="default-input" style="display:none;width:80%"
                                                       name="content" value="${buttons[2].content }"/>
                                            </div>

                                        </div>

                                    </div>
                                    <div class="col-sm-3 text-l pl0 pr0">
                                        <a href="javascript:void(0)" class="edit-info">[编辑]</a>
                                        <a href="javascript:void(0)" class="add-child">[添加子菜单]</a>
                                        <a href="javascript:void(0)" class="remove-main">[删除]</a>
                                    </div>
                                </div>
                                <div class="child-menu">
                                    <c:forEach items="${buttons[2].subs}" var="subs1" varStatus="status1">
                                        <li data-status="0">
                                            <div class="col-sm-12 pl0">
                                                <div class="col-sm-9 pl0">
                                                    <div class="col-sm-3 col-val">
                                                        <div class="menu-title">
                                                            <span class="chlid-img"></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-3 pl0 col-val">
                                                        <div class="menu-title">
                                                            <span class="childmenu-name" style="">${subs1.name}</span>
                                                            <input type="text" class="default-input"
                                                                   style="width: 80%; display: none;" name="name"
                                                                   value="${subs1.name}"/>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-3 pl0 col-val">
                                                        <div class="menu-title">
                                                            <span class="childtouch-off" style="">${subs1.type}</span>
                                                            <select name="type" class="default-input"
                                                                    style="display:none;width:80%"
                                                                    data-value="${subs1.type}"></select>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-3 pl0 col-val">
                                                        <div class="menu-title">
                                                            <span class="childresponse" style="">${subs1.content}</span>
                                                            <input type="text" class="default-input"
                                                                   style="width: 80%; display: none;" name="content"
                                                                   value="${subs1.content}"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-3 text-l pl0 pr0">
                                                    <a href="javascript:void(0)" class="childedit-info">[编辑]</a>
                                                    <a href="javascript:void(0)" class="delete-info">[删除]</a>
                                                    <a href="javascript:void(0)" class="up-info">[上移]</a>
                                                    <a href="javascript:void(0)" class="down-info">[下移]</a>
                                                </div>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:if>


                    </div>
                    <button class="m-btn maincolor main-btn" id="saveButton" type="button"
                            style="margin-top: 20px;">
                        <span>保存</span>
                    </button>
                </form>
            </div>
        </div>
    </div>
    <%@ include file="/common/footer.jsp" %>
    <script type="text/javascript" src="${base}/js/weixin/wxMenu/customMenu.js?v=<wei:version/>"></script>

</body>
</html>