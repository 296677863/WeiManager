<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>编辑</title>
    <%@ include file="/common/head.jsp" %>
</head>

<body style="cursor: auto;">
<div class="ibox-content">
    <form id="inputForm">
        <input type="hidden" name="id" id="paramId" value="${bean.id}">
        <div class="border-module base-info">
            <div class="group-border mb20">
                <div class="group-title"></div>
                <div class="col-sm-11 pl0">
                    <div class="col-sm-4 pl0">
                        <div class="group-div"><label class="label-left"><span
                                class="priority">*</span>旧密码：</label><input type="password"
                                                                            class="default-input required input-left "
                                                                            name="oldPwd" placeholder="请输入旧密码"
                                                                            maxlength="10" minlength="4" id="oldPwd">
                        </div>
                    </div>
                    <div class="col-sm-4 pl0">
                        <div class="group-div">
                            <label class="label-left"><span class="priority">*</span>新密码：</label>
                            <input type="password" class="default-input required input-left"
                                   placeholder="请输入新密码" name="password" id="password" maxlength="10" minlength="4" value=""/>
                        </div>
                    </div>

                    <div class="col-sm-4 pl0">
                        <div class="group-div">
                            <label class="label-left"><span class="priority">*</span>确认密码：</label>
                            <input type="password" class="default-input required input-left"
                                   placeholder="请输入确认密码" name="rePassword" id="rePassword" maxlength="10" minlength="4" value=""/>
                        </div>
                    </div>

                </div>
                <!-- disable-btn -->
                <div class="col-sm-1 pl0 text-right">
                    <button class="m-btn secondary" id="closeButton" type="button">
                        <span>关闭</span></button>
                    <button class="m-btn maincolor main-btn" id="saveButton" type="submit">
                        <span>保存</span></button>
                </div>
            </div>
        </div>
    </form>
</div>

<%@ include file="/common/footer.jsp" %>
<script type="text/javascript"
        src="${base }/js/user/editpwd.js?v=<citms:version/>"></script>
</body>
</html>