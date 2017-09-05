$(function () {
    /***************************init****************************************/
    $("#permissionSelect").chosen(wei.defaults.chosenConfig);

    $('#isEnabledCheck').bootstrapSwitch();
    if (!$("input[name='iconId']").val()) {
        $("#menuIconDel").hide();
    }


    var $inputForm = $("#inputForm");

    $("#permissionSelect").on('change', function (evt, params) {
        intiServiceIsShow(params.selected);
    });

    function intiServiceIsShow(evt) {
    	debugger;
        if (evt!="1") {
            $(".menuPermission").hide();
        } else {
        	$(".menuPermission").show();
        }
    }

    intiServiceIsShow($("#permissionSelect").val());

    $.validator.addClassRules({
        required: {
            required: true
        },
        sort: {
            required: true,
            digits: true
        }
    });
    /********************删除图标***************************/
    $("#menuIconDel").click(function () {
        $("#iconDisplay").empty()
        $(this).hide();
    });

    /**********************监听switch改变***********************************/
    $('#isEnabledCheck').on('switchChange.bootstrapSwitch', function (e, state) {
        $("#status").val(state);
    });

    /***********************选择图标**********************************/
    $("#menuIcon").on("click", function () {
        $(this).attr("focus", "focus");
        layer.open({
            type: 2, //page层
            area: ['95%', '500px'],
            title: '添加图标',
            shade: 0.6, //遮罩透明度
            moveType: 1, //拖拽风格，0是默认，1是传统拖动
            shift: 1, //0-6的动画形式，-1不开启
            content: baseRoot + "/base/icon/libary/chosejsp",
        });
    });

    /************************************form 提交*********************************************/
    // 表单验证
    $inputForm.validate($.extend({}, {
        submitHandler: function (form) {
            $("button[type='submit']").attr("disabled", "disabled");
            $("button[type='submit']").addClass("disable-btn");
            $("button[type='submit']").removeClass("maincolor");
            $("button[type='submit']").removeClass("main-btn");
            wei.ajax.ajaxTagSu(baseRoot + "/permission/save.shtml",$inputForm.serialize(),function (message) {
                if (message.type == "success") {
                    parent.window.location.href = baseRoot + "/permission/list.shtml";
                    wei.dialog.layerMsg(null, null, null, function () {
                        wei.closedialog.closeIframe();
                    });
                } else {
                    $("button[type='submit']").removeAttr("disabled");
                    $("button[type='submit']").removeClass("disable-btn");
                    $("button[type='submit']").addClass("maincolor");
                    $("button[type='submit']").addClass("main-btn");
                    wei.dialog.errorlayerMsg(message.content);
                }
            });
        }
    }, wei.defaults.validateTooltip));

});


/****
 * 选择图标回调
 */
function iconCall(icon) {
    var v_html = "<input type='hidden' name='iconId' value='" + icon + "'>" + icon;
    $("#iconDisplay").empty();
    $("#iconDisplay").empty().append(v_html);
    $("[focus='focus']").removeAttr("focus");
    $("#menuIconDel").show();
};
	
	