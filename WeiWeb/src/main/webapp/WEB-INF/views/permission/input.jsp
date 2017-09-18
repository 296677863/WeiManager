<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <title>添加菜单及权限</title>
    <%@include file="/common/head.jsp" %>
    <link href="${base}/css/bootstrap-switch.css?v=<wei:version/>" rel="stylesheet"/>
    <link href="${base}/css/menu/menu.css?v=<wei:version/>" rel="stylesheet"/>
</head>

<body style="cursor: auto;">

<div class="ibox-content">
    <!-- 新区域-->
    <form id="inputForm">
        <input type="hidden" value="${bean.id}" name="id" id="menuId">
        <input type="hidden" value="${bean.level}" name="level" id="levelId">
        <div class="border-module">
            <div class="group-border mb20">
                <div class="group-title">基本信息</div>
                <div class="col-sm-12 pl0">
                	<div class="col-sm-4 pl0">
                        <div class="group-div">
                        <label class="label-left "><span class="prompt">*</span>权限类型：</label>
                         <select name="" id="permissionSelect" class="choose-select default-input"  style="width: 95%">
                                <option value="0" <c:if test="${bean.level eq null}">selected</c:if>>基本权限</option>
                                <option value="1" <c:if test="${bean.level ne null}">selected</c:if>>菜单权限</option>      
                         </select>
                       </div>
                    </div>
                    
                     <div class="col-sm-4 pl0">
                        <div class="group-div">
                        <label class="label-left "><span class="prompt">*</span>权限名称：</label>
                        <input type="text" class="default-input required input-left " placeholder="请输入权限名称"
                                   id="paramName" name="name" value="${bean.name }">
                       </div>
                    </div>
                    
                    <div class="col-sm-4 pl0">
                        <div class="group-div">
                        	<label class="label-left "><span class="prompt" for="ParamUrl">*</span>权限地址：</label>
                            <input type="text" class="default-input required input-left " id="ParamUrl" name="url"
                                   value="${bean.url}" placeholder="请输入权限地址">
                        </div>
                    </div>
                </div>
                <div class="col-sm-12 pl0">
                    <div class="col-sm-4 pl0">
                        <div class="group-div">
                            <label class="label-left"><span class="prompt">*</span>排&nbsp;&nbsp;&nbsp;&nbsp;序：</label>
                            <input type="text" class="default-input required sort input-left"
                                   placeholder="请输入排序" value="${bean.sort}" id="paramSort" name="sort" maxlength="9"/>
                        </div>
                    </div>
                    
                    <div class="col-sm-4 pl0" style="height: 47px">
                        <div class="group-div">
                            <label class="label-left">是否启用：</label>
                            <div class="switch">
                                <input type="checkbox" name="status"
                                       <c:if test="${bean.status == true }">checked="checked"</c:if> id="isEnabledCheck"
                                       type="checkbox" data-size="small" data-on-text="是" data-off-text="否"/>
                                <input type="hidden" name="status" id="status"
                                       value="${bean.status == true?true:false}"/>
                            </div>
                        </div>
                    </div>
          		 </div>
            	<div class="col-sm-12 pl0">
                     <div class="col-sm-4 pl0 menuPermission">
                        <div class="group-div">
                            <label class="label-left">上级菜单：</label>
                            <c:choose>
                                <c:when test="${fn:length(parents) >0}">
                                    <select name="parent" class="choose-select default-input"   id="parentSelect" style="width: 95%">
                                       	<option value="">请选择上级菜单</option>
                                        <c:forEach items="${parents}" var="parent1">
                                            <option value="${parent1.id}"
                                                    <c:if test="${parent1.id eq bean.parent}">selected</c:if>>${parent1.name}</option>
                                        </c:forEach>
                                    </select>
                                </c:when>
                                <c:otherwise>
                                    <input type="text" class="default-input required input-left curror"
                                           placeholder="请输入上级菜单" value="" disabled="disabled"/>
                                    <input type="hidden" name="parent" value="${bean.parent}">
                                </c:otherwise>
                            </c:choose>

                        </div>
                    </div>

                    
                    <div class="col-sm-4 pl0 menuPermission">
                        <div class="group-div">
                            <label class="label-left">菜单图标：</label>
								<span id="iconDisplay">
									${bean.iconId}<input type="hidden" name="iconId"
                                                         value="<c:out value="${bean.iconId}" escapeXml="true"></c:out>"/>
								</span>
                            <a href="javascript:void(0)" id="menuIconDel" class="delete-icon">删除</a>
                            <button class="m-btn maincolor main-btn" id="menuIcon" type="button">
                                <span>选择</span>
                            </button>
                        </div>
                    </div>
                </div>
                 <div class="col-sm-12 pl0">
                    <div class="col-sm-4 pl0">
                        <div class="group-div">
                            <label class="label-left"></label>
                            <div class="input-group input-left">
                                <button class="m-btn maincolor main-btn" type="submit">
                                    <span>保存</span>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
             
           </div>

        </div>
    </form>
</div>




<%@ include file="/common/footer.jsp" %>
<script type="text/javascript" src="${base}/js/bootstrap-switch.js?v=<wei:version/>"></script>
<script type="text/javascript" src="${base}/js/permission/permission_input.js?v=<wei:version/>"></script>
</body>

</html>