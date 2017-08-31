var index = 0;  //父模板
var parentindex = 0; //编辑的父菜单
$(function () {

    init();
    $(document).on("click",".wechat-menu-ul .template-menu-li",function () {

        $(".wechat-menu-ul").find(".pre_menu_link").removeClass("pre_menu_link");

        $(this).addClass("pre_menu_link");
        index = $(this).index();
        $(".sub_pre_menu_box").hide();

       // $(this).find(".sub_pre_menu_box").show();

        $(".wechat-menu-ul").find(".template-menu-li").eq(index).addClass("pre_menu_link");
        var len =  $(".wechat-menu-ul").find(".template-menu-li").eq(index).find(".sub_pre_menu_list li").length;
        if(len > 0){
            $(this).find(".sub_pre_menu_box").show();
        }else{
            $(this).find(".sub_pre_menu_box").hide();
        }
    })

    /*删除子菜单  */
    $(document).on("click", ".delete-info", function () {
        var $li = $(this).closest("li");
        var editindex = $li.index();
        parentindex = $li.closest(".row-menu-div").index();
        $(".row-menu-div").eq(parentindex).find(".child-menu li").eq(editindex).remove();
        var ulli = $(".wechat-menu-ul").find(".template-menu-li").eq(parentindex);
        if (ulli.find(".jslevel2").length == 1) {
            ulli.find(".sub_pre_menu_box").hide();
        }
        ulli.find(".jslevel2").eq(editindex).remove();

    });

    /*上移  */
    $(document).on("click", ".up-info", function () {
        var liindex = $(this).closest("li").index();
        var menus = $(".row-menu-div").eq(index).find(".child-menu li").eq(liindex);
        var listmenu = $(".wechat-menu-ul").find(".template-menu-li").eq(index).find(".jslevel2").eq(liindex);
        if (menus.prev().size() > 0) {
            menus.insertBefore(menus.prev());
            listmenu.insertBefore(listmenu.prev());
        }

    });

    /*  下移*/
    $(document).on("click", ".down-info", function () {
        var liindex = $(this).closest("li").index();
        var menus = $(".row-menu-div").eq(index).find(".child-menu li").eq(liindex);
        var listmenu = $(".wechat-menu-ul").find(".template-menu-li").eq(index).find(".jslevel2").eq(liindex);
        if (menus.next().size() > 0) {
            menus.insertAfter(menus.next());
            listmenu.insertAfter(listmenu.next());
        }

    });

    //编辑父菜单
    $(document).on("click", ".edit-info", function () {
        var $menu = $(this).closest(".row-menu-div");
        var menuindex = $menu.index();
        if ($menu.data("status") == '0') {
            var that = $menu.find(".parent-menu-title");
            for (var i = 1; i < that.length; i++) {
                var value = that.eq(i).find("span").html();
                ;
                if (i == 2) {
                    that.eq(i).find("select").val($.trim(value));
                    that.eq(i).find("select").show();
                } else {
                    that.eq(i).find("input").show();
                    that.eq(i).find("input").val(value);
                }
            }
            that.find("span").hide();
            that.eq(0).find(".menus-num").show();
            that.css("padding-left", "0px");
            $menu.data("status", "1");
            $(this).text("[保存]")
        } else {
            var that = $menu.find(".parent-menu-title");
            for (var i = 1; i < that.length; i++) {
                var value = that.eq(i).find("input").val();
                if (i == 2) {
                    that.eq(i).find("select").hide();
                    that.eq(i).find("span").html(that.eq(i).find("select").val() || "click");
                } else {
                    that.eq(i).find("input").hide();
                    that.eq(i).find("span").html(value);
                }
                // if(hasChildNode($menu)){
                // 	if(i == 2){
                // 		that.eq(i).find("span").html("");
                // 	}
                // 	if(i == 3){
                // 		that.eq(i).find("span").html("");
                // 	}
                // }
                if ($.trim(that.eq(1).find("input").val()) == "") {
                    if (i == 2) {
                        that.eq(i).find("span").html("");
                    }
                    if (i == 3) {
                        that.eq(i).find("span").html("");
                    }
                }
                that.eq(i).find("span").show();


            }
            var editname = that.eq(1).find("input").val()
            $(".wechat-menu-ul").find(".template-menu-li").eq(menuindex).find("a").eq(0).html(editname);
            that.eq(0).find(".menus-num").show();
            $menu.data("status", "0");
            $(this).text("[编辑]");
        }
    })

    var v_option = '<option value="click">点击事件</option>' +
        '<option value="view">跳转URL</option>' +
        '<option value="scancode_push">扫码推事件</option>' +
        '<option value="scancode_waitmsg">扫码推事件且弹出</option>' +
        '<option value="pic_sysphoto">弹出系统拍照发图</option>' +
        '<option value="pic_photo_or_album">弹出拍照或者相册发图</option>' +
        '<option value="pic_weixin">弹出微信相册发图器</option>' +
        '<option value="location_select">弹出地理位置选择器</option>' +
        '<option value="media_id">下发消息</option>' +
        '<option value="view_limited">弹出地理位置选择器</option>';
    $.fn.extend({
        initWxSelect: function () {
            var $this = $(this);
            if ($this.find("option").length <= 0) {
                $this.append(v_option);
                $this.val($this.data("value") ? $this.data("value") : "click");
            }

        }
    })
    function initselect() {
        $("select").each(function () {
            var $this = $(this);
            $this.initWxSelect();
        });
    };
    initselect();


    function hasChildNode(obj) {
        return (obj.find(".child-menu li").length > 0 ? true : false);
    }

    //编辑子菜单
    $(document).on("click", ".childedit-info", function () {

        var $this = $(this);
        var $li = $(this).closest("li");
        var editindex = $li.index();
        parentindex = $li.closest(".row-menu-div").index();
        if ($li.data("status") == '0') {
            var that = $li.find(".menu-title");
            for (var i = 1; i < that.length; i++) {
                var value = that.eq(i).find("span").html();
                if (i == 2) {
                    that.eq(i).find("select").show();
                } else {
                    that.eq(i).find("input").show();
                    that.eq(i).find("input").val(value);
                }

            }
            that.find("span").hide();
            $(".chlid-img").show();
            that.css("padding-left", "0px");
            $li.data("status", "1");
            $this.text("[保存]")
        } else {
            var that = $li.find(".menu-title");
            for (var i = 1; i < that.length; i++) {
                var value = that.eq(i).find("input").val();
                if (i == 2) {
                    that.eq(i).find("select").hide();
                    that.eq(i).find("span").html(that.eq(i).find("select").val());
                } else {
                    that.eq(i).find("input").hide();
                    that.eq(i).find("span").html(value);
                }
                that.eq(i).find("span").show();
            }
            var editname = that.eq(1).find("input").val()
            $(".wechat-menu-ul").find(".template-menu-li").eq(parentindex).find(".jslevel2").eq(editindex).find(".js_l2Title").html(editname);
            if ($li.data("status") == '2') {
                $(".wechat-menu-ul").find(".template-menu-li").eq(parentindex).find(".sub_pre_menu_list .jslevel2").remove();
                var len = $(".row-menu-div").eq(parentindex).find(".child-menu li").length;
                var loadchild = $(".row-menu-div").eq(parentindex).find(".child-menu li");
                var childleng = loadchild.length;
                if(len>0 && parentindex == index){
                    $(".wechat-menu-ul").find(".template-menu-li").eq(parentindex).find(".sub_pre_menu_box").show();
                }
                for (var i = 0; i < childleng; i++) {
                    var addname = $(".row-menu-div").eq(parentindex).find(".child-menu li").eq(i).find(".childmenu-name").html();
                    var loadhtml = '<li class="jslevel2">' +
                        '<a href="javascript:void(0);" class="jsSubView" draggable="false">' +
                        '<span class="sub_pre_menu_inner js_sub_pre_menu_inner">' +
                        '<span class="js_l2Title">' + addname + '</span>' +
                        '</span>' +
                        '</a>' +
                        '</li>';
                    if (i < 5) {
                        $(".wechat-menu-ul").find(".template-menu-li").eq(parentindex).find(".sub_pre_menu_list").append(loadhtml);
                    }
                }
            }
            $(".chlid-img").show();
            $li.data("status", "0");
            $this.text("[编辑]");
        }

    });
    /* 添加子菜单 */
    $(document).on("click", ".add-child", function () {
        var $div = $(this).closest(".row-menu-div");
        var divindex = $div.index();
        var childli = $(".row-menu-div").eq(divindex).find(".child-menu");
        var lilength = childli.find("li").length;
        var childhtml = '<li data-status="2">' +
            '<div class="col-sm-12 pl0">' +
            '<div class="col-sm-9 pl0">' +
            '<div class="col-sm-3 col-val">' +
            '<div class="menu-title">' +
            '<span class="chlid-img"></span>' +
            '</div>' +
            '</div>' +
            '<div class="col-sm-3 pl0 col-val">' +
            '<div class="menu-title">' +
            '<span class="childmenu-name" style="display:none;"></span>' +
            '<input type="text" class="default-input"  style="width:80%" name="" value=""/>' +
            '</div>' +
            '</div>' +
            '<div class="col-sm-3 pl0 col-val">' +
            '<div class="menu-title">' +
            '<span class="childtouch-off" style="display:none;"></span>' +
            '<select name="type" class="default-input" style="width:80%" data-value="click">' +
            '<option value="click">点击事件</option>' +
            '<option value="view">跳转URL</option>' +
            '<option value="scancode_push">扫码推事件</option>' +
            '<option value="scancode_waitmsg">扫码推事件且弹出</option>' +
            '<option value="pic_sysphoto">弹出系统拍照发图</option>' +
            '<option value="pic_photo_or_album">弹出拍照或者相册发图</option>' +
            '<option value="pic_weixin">弹出微信相册发图器</option>' +
            '<option value="location_select">弹出地理位置选择器</option>' +
            '<option value="media_id">下发消息</option>' +
            '<option value="view_limited">弹出地理位置选择器</option>' +
            '</select>' +
            '</div>' +
            '</div>' +
            '<div class="col-sm-3 pl0 col-val">' +
            '<div class="menu-title">' +
            '<span class="childresponse" style="display:none;"></span>' +
            '<input type="text" class="default-input"  style="width:80%" name="" value=""/>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '<div class="col-sm-3 text-l pl0 pr0">' +
            '<a href="javascript:void(0)" class="childedit-info">[保存]</a>' +
            '<a href="javascript:void(0)" class="delete-info">[删除]</a>' +
            '<a href="javascript:void(0)" class="up-info">[上移]</a>' +
            '<a href="javascript:void(0)" class="down-info">[下移]</a>' +
            '</div>' +
            '</div>' +
            '</li>';
        if (lilength < 5) {
            childli.append(childhtml);
        } else {
            wei.dialog.layerMsg("最多只能添加5个子菜单",2);
        }

    })


    /*  所有菜单初始化*/
    function init() {
       $(".wechat-menu-ul").find(".template-menu-li").empty();
       var menuLength = $(".dictionary-list").find(".row-menu-div").length;
        for(var i = 0; i < menuLength; i++){
            var v_title = $(".row-menu-div").eq(i).find(".menus-name").html();
            var pre_menu_link = "";
            var pre_sub_menu_show ="";
            if(0 == i){
                pre_menu_link = 'pre_menu_link';
                pre_sub_menu_show = 'style="display: block;"';
            }
            var preli = '<li class="template-menu-li '+pre_menu_link+'">'+
                    '  <a href="javascript:void(0)">'+v_title+'</a>'+
                    '<div class="sub_pre_menu_box js_l2TitleBox" '+pre_sub_menu_show+'>'+
                    ' <ul class="sub_pre_menu_list">';
            var child = $(".dictionary-list").find(".row-menu-div").eq(i).find(".child-menu li")
            var clength = child.length;
            for(var j = 0; j < clength; j++){
                var cname = child.eq(j).find(".childmenu-name").html();
                var chtml = '<li class="jslevel2">' +
                    '<a href="javascript:void(0);" class="jsSubView" draggable="false">' +
                    '<span class="sub_pre_menu_inner js_sub_pre_menu_inner">' +
                    '<span class="js_l2Title">' + cname + '</span>' +
                    '</span>' +
                    '</a>' +
                    '</li>';
                preli+=chtml;
            }
            preli+='</ul> <i class="arrow arrow_out"></i><i class="arrow arrow_in"></i></div></li>';
            $(".wechat-menu-ul").append(preli);
        }
    }

    /**
     * 添加
     */
    $("#addButton").click(function () {
        if ($(".row-menu-div").length >= 3) {
            wei.dialog.layerMsg("最多添加三个主菜单",2);
            return;
        }
        $("#addclid").find(".index").text($(".row-menu-div").length + 1);
        $("#addclid").show();
    })
    $(".remove-info").click(function () {
        $("#addclid").hide()
        $("#addForm")[0].reset();
    })
    /**
     * 保存主菜单
     */
    $(document).on("click", ".save-info", function () {
        var formData = new Object();
        $.each($("#addForm").serializeArray(), function (i, item) {
            formData[item.name] = item.value;
        });

        var v_html = '<div><div class="row-menu-div" data-status="0">' +
            '    <div class="col-sm-12 pl0">' +
            '        <div class="col-sm-9 pl0">' +
            '            <div class="col-sm-3 col-val">' +
            '                <div class="parent-menu-title">' +
            '                    <span class="menus-num">' + ($(".row-menu-div").length + 1) + '</span>' +
            '                </div>' +
            '            </div>' +
            '            <div class="col-sm-3 pl0 col-val">' +
            '                <div class="parent-menu-title">' +
            '                    <span class="menus-name">' + formData["name"] + '</span>' +
            '                    <input type="text" class="default-input" style="display:none;width:80%"' +
            '                           name="name" value=" ' + formData["name"] + '"/>' +
            '                </div>' +
            '            </div>' +
            '            <div class="col-sm-3 pl0 col-val">' +
            '                <div class="parent-menu-title">' +
            '                    <span class="touch-off"> ' + formData["type"] + ' </span>' +
            '                    <select name="type" class="default-input" style="display:none;width:95%"' +
            '                            data-value=" ' + formData["type"] + '">' +
            '                           <option value="click">点击事件</option>' +
            '                           <option value="view">跳转URL</option>' +
            '                           <option value="scancode_push">扫码推事件</option>' +
            '                           <option value="scancode_waitmsg">扫码推事件且弹出</option>' +
            '                           <option value="pic_sysphoto">弹出系统拍照发图</option>' +
            '                           <option value="pic_photo_or_album">弹出拍照或者相册发图</option>' +
            '                           <option value="pic_weixin">弹出微信相册发图器</option>' +
            '                           <option value="location_select">弹出地理位置选择器</option>' +
            '                           <option value="media_id">下发消息</option>' +
            '                           <option value="view_limited">弹出地理位置选择器</option>' +
            '                    </select>' +
            '                </div>' +
            '            </div>' +
            '            <div class="col-sm-3 pl0 col-val">' +
            '                <div class="parent-menu-title">' +
            ' 				                    <span class="response"' +
            '                                          title=" ' + formData["content"] + ' "> ' + formData["content"] + ' </span>' +
            '                    <input type="text" class="default-input" style="display:none;width:80%"' +
            '                           name="content" value=" ' + formData["content"] + ' "/>' +
            '                </div>' +
            '            </div>' +
            '        </div>' +
            '        <div class="col-sm-3 text-l pl0 pr0">' +
            '            <a href="javascript:void(0)" class="edit-info">[编辑]</a>' +
            '            <a href="javascript:void(0)" class="add-child">[添加子菜单]</a>' +
            '            <a href="javascript:void(0)" class="remove-main">[删除]</a>' +
            '        </div>' +
            '    </div>' +
            '    <div class="child-menu"></div>' +
            '</div></div>';
        $("#infoForm").find(".dictionary-list").append($(v_html).html());
        insertPreMenu(formData);
        $("#addclid").hide()
        $("#addForm")[0].reset();
    });


    function insertPreMenu(formData) {
        var menuLength = $(".dictionary-list").find(".row-menu-div").length;
        var pre_menu_link ="";
        if(1 == menuLength){
            pre_menu_link = 'pre_menu_link';
        }
        var preli = '<li class="template-menu-li '+pre_menu_link+'">'+
            '  <a href="javascript:void(0)">'+formData["name"]+'</a>'+
            '<div class="sub_pre_menu_box js_l2TitleBox" >'+
            ' <ul class="sub_pre_menu_list">'+
            '</ul> <i class="arrow arrow_out"></i><i class="arrow arrow_in"></i></div></li>';
            $(".wechat-menu-ul").append(preli);
    }
    /**
     * 删除主菜单
     */
    $(document).on("click", '.remove-main', function () {
        parentindex = $(this).closest(".row-menu-div").index();
        $(".row-menu-div").eq(parentindex).remove();
        var ulli = $(".wechat-menu-ul").find(".template-menu-li").eq(parentindex).remove();
        $(".wechat-menu-ul").find(".template-menu-li").eq(0).addClass("pre_menu_link");
       var len =  $(".wechat-menu-ul").find(".template-menu-li").eq(0).find(".sub_pre_menu_list li").length;
        if(len > 0){
            $(".wechat-menu-ul").find(".template-menu-li").eq(0).find(".sub_pre_menu_box").show();
        }else{
            $(".wechat-menu-ul").find(".template-menu-li").eq(0).find(".sub_pre_menu_box").hide();
        }
        updateMainIndex();
    })

    /**
     * 更新主菜单索引
     */
    function updateMainIndex() {
        $(".row-menu-div").each(function (index, domEle) {
            $(this).find(".menus-num").text(index + 1);
        });
    }

    $("#saveButton").on("click", function () {
        wei.ajax.ajaxTagSu(baseRoot + "/base/wxmenu/update.shtml",{"jsonArr": getData()},function (message) {
            if (message.type == "success") {
                wei.dialog.layerMsg("保存成功！",1);
            } else {
                wei.dialog.errorlayerMsg(message.content,2);
            }
        });
    });

    function getData() {
        var objects = [];
        $(".dictionary-list .row-menu-div").each(function () {
            var object = new Object();
            var $menu = $(this);
            var that = $menu.find(".parent-menu-title");
            if ($.trim(that.eq(1).find("input").val()) != "") {
                for (var i = 1; i < that.length; i++) {
                    var value = that.eq(i).find("span").html();
                    setData(i, object, $.trim(value));
                }
                var childs = [];
                $menu.find(".child-menu li").each(function () {
                    var $li = $(this);
                    var object = new Object();
                    var that = $li.find(".menu-title");
                    for (var i = 1; i < that.length; i++) {
                        var value = that.eq(i).find("span").html();
                        setData(i, object, $.trim(value));
                    }
                    childs.push(object);
                })
                object["sub_button"] = childs;
                objects.push(object);
            }
        })
        return JSON.stringify(objects);
    }

    function setData(index, obj, val) {
        switch (index) {
            case 1:
                return obj["name"] = val || "";
            case 2:
                return obj["type"] = val || "click";
            case 3:
                return obj["content"] = val || "";
        }
    }


});


