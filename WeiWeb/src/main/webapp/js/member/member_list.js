/**
 * #######################################################################################
 * 列表加载数据
 */
var $resePwdBtn = $("#resePwdBtn");
var $deleteButton = $("#deleteButton");

$(function () {
//    var v_userType = wei.selectDetailAllSendXhr("STATUS");
    var config = {
        url: window.baseRoot + "/member/listData.shtml",
        multiselect: true,
        height: $(window).height() - $("#DataTables_Table_0_wrapper").height() - 150,
        colNames: ['昵称','Email/帐号','登录状态','创建时间','最后登录时间','操作'],
        colModel: [
            {
                name: 'nickname',
                index: 'nickname',
                align: 'left',
                width: 100,
            },
            {
                name: 'email',
                index: 'email',
                align: 'left',
                width: 150,
                fixed: true,
            },
            {
                name: 'status',
                index: 'status',
                align: 'left',
                width: 100,
            },
             
            {
                name: 'createTime',
                index: 'createTime',
                formatter: function (cellvalue, options, rowObject) {
                    return wei.formateDate.getDate(cellvalue);
                }
            },
            {
                name: 'lastLoginTime',
                index: 'lastLoginTime',
                formatter: function (cellvalue, options, rowObject) {
                    return wei.formateDate.getDate(cellvalue);
                }
            },
            {
                width: 200,
                fixed: true,
                formatter: function (cellvalue, options, rowObject) {
                    var result = "<a href=\"javascript:void(0)\" onclick=\"wei.dialog.openIrame('用户编辑','{0}')\">[编辑]</a>"
                        .format(window.baseRoot + '/member/edit.shtml'
                            + rowObject.id);
                    if (rowObject.status) {
                        result = result
                            + '<a href="javascript:updateStatus(\'{0}\',false)">[停用]</a>'
                                .format(rowObject.id);
                    } else {
                        result = result
                            + '<a href="javascript:updateStatus(\'{0}\',true)">[启用]</a>'
                                .format(rowObject.id);
                    }
                    result = result + "<a href=\"javascript:void(0)\" onclick=\"wei.dialog.openIrame('用户详情','{0}')\">[详情]</a>"
                            .format(window.baseRoot + '/member/info.shtml'
                                + rowObject.id);
                    return result;
                }
            }]
    }
    var jqGrid = wei.grid(config);

    jqGrid.bind("weijqgridSelectOne", function (event, aRowids, status) {
        if (window.selectIds.length > 0) {
            $resePwdBtn.removeClass("disable-btn");
            $resePwdBtn.addClass("maincolor main-btn");
        } else {
            $resePwdBtn.addClass("disable-btn");
            $resePwdBtn.removeClass("maincolor main-btn");
        }
    });
    jqGrid.bind("weijqgridSelectAll", function (event, aRowids, status) {
        if (window.selectIds.length > 0) {
            $resePwdBtn.removeClass("disable-btn");
            $resePwdBtn.addClass("maincolor main-btn");
        } else {
            $resePwdBtn.addClass("disable-btn");
            $resePwdBtn.removeClass("maincolor main-btn");
        }
    });
    $resePwdBtn
        .click(function () {
            var $this = $(this);
            if ($this.hasClass("disable-btn")) {
                return false;
            }
            $.ajax({
                url: window.baseRoot
                + "/base/admin/restPwd",
                type: "POST",
                data: {
                    "ids": selectIds.join(",")
                },
                dataType: "json",
                cache: false,
                success: function (message) {
                    if (message.type == "success") {
                        wei.jqrefush();
                        $deleteButton.addClass("disable-btn");
                        $deleteButton.removeClass("maincolor main-btn");

                        $resePwdBtn.addClass("disable-btn");
                        $resePwdBtn.removeClass("maincolor main-btn");
                        layer.tips(message.content, $resePwdBtn);
                    }
                }
            });

        });
});

/**
 * ##########################################################################################
 * 停用/启用
 */
function updateStatus(id, isEnable) {
    var msg = "";
    if (isEnable) {
        msg = "是否确定启用？";
    } else {
        msg = "是否确定停用，停用后该账号将不能正常使用？";
    }
    layer
        .open({
            type: 1,
            title: false,
            closeBtn: 0,
            shadeClose: true,
            btn: ['取 消', '确 定'],
            skin: 'popup-one',
            content: '<div class="popup-div"><i class="iconfont pop-warning"></i><div class="pop-title"><p>'
            + msg
            + '</p><div class="pop-content"></div></div></div>',
            btn1: function (index, layero) {
                layer.close(index);
            },
            btn2: function (index, layero) {
                layer.close(index);

                var $checkedIds = $("#listTable input[name='ids']:enabled:checked");
                $.ajax({
                    url: window.baseRoot + "/base/admin/updateStatus/"
                    + id + "/" + isEnable,
                    type: "POST",
                    dataType: "json",
                    cache: false,
                    success: function (message) {
                        if (message.type == "success") {
                            wei.jqrefush();
                            $deleteButton.addClass("disable-btn");
                            $deleteButton.removeClass("maincolor main-btn");

                            $resePwdBtn.addClass("disable-btn");
                            $resePwdBtn.removeClass("maincolor main-btn");
                        } else {
                            wei.dialog.layerMsg('当前用户不能停用！', 2);
                        }
                    }
                });

            }
        });
}
