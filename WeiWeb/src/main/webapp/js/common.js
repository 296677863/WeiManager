window.selectIds
// 声明一个全局对象Namespace，用来注册命名空间
var Namespace = Namespace || {};
// 全局对象仅仅存在register函数，参数为名称空间全路径，如"MySoft.tools"
Namespace.register = function (fullNS) {
    // 将命名空间切成N部分, 比如MySoft、tools等
    var nsArray = fullNS.split('.');
    var sEval = "";
    var sNS = "";
    for (var i = 0; i < nsArray.length; i++) {
        if (i != 0) sNS += ".";
        sNS += nsArray[i];
        // 依次创建构造命名空间对象（假如不存在的话）的语句
        // 比如先创建Mysoft，然后创建tools，依次下去
        sEval += "if (typeof(" + sNS + ") == 'undefined') {" + sNS + " = {};}"
    }
    if (sEval != "") {
        eval(sEval);
    }
};
Namespace.register("wei");
wei = (function () {
    var obj = new Object();

    selectDetaiCache = {};//缓存所有的数据字典
    obj.selectDetailAllByAllClassCode = function (classCodes) {
        var retValue = null;
        if( $.isArray(classCodes) ){
            wei.ajax.ajaxTag(baseRoot + "/base/selectClass/findALLByAllCode",{
                classCodes: classCodes
            },false,'POST', 'json',function (data) {
                retValue =  $.parseJSON(data.content);
            })
        }
        return retValue;
    };
    //根据字典classCode 查询数据字典
    obj.selectDetailAllSendXhr = function (classCode) {
        var retValue = [];
        if (!selectDetaiCache[classCode]) {
            $.ajax({
                url: baseRoot + "/base/selectClass/findALLByCode",
                type: 'POST', //GET
                async: false,    //或false,是否异步
                data: {
                    classCode: classCode
                },
                timeout: 5000,    //超时时间
                dataType: 'json',
                success: function (data, textStatus, jqXHR) {
                    selectDetaiCache[classCode] = data;
                    retValue = selectDetaiCache[classCode];
                },
                error: function (xhr, textStatus) {
                    wei.dialog.layerMsg("获取字典数据错误", 5);
                }
            });
        } else {
            retValue =selectDetaiCache[classCode]
        }
        return retValue;

    };
    //根据数据字典查询指定的中文值
    obj.selfilter = function (arr, val) {
        if (val) {
            if ($.isArray(arr) && !$.isArray(val)) {
                for (var i = 0; i < arr.length; i++) {
                    var obj = arr[i];
                    if (val == obj.selectValue) {
                        return obj.selectLable;
                    }
                }
            }else if($.isArray(arr) && $.isArray(val)){
            	var vals=[];
            	for (var i = 0; i < arr.length; i++) {
                    var obj = arr[i];
                    for(var k = 0,kl = val.length;k<kl;k++){
                    	if (val[k] == obj.selectValue) {
                    		vals.push(obj.selectLable);
                        }
                    }
                    
                }
            	return vals.join(",");
            }
        } else {
            return "";
        }
    };

    obj.selfilterForClassCodes = function (obj,code ,val) {
        var ret = "";
        if (val) {
            if (!$.isEmptyObject(obj)) {
                var arr = obj[code];
                if ($.isArray(arr)) {
                    for (var i = 0; i < arr.length; i++) {
                        var obj = arr[i];
                        if (val == obj.selectValue) {
                            ret=  obj.selectLable;
                            break;
                        }
                    }
                }
            }
        }
            return ret;

    };

    //单个数据字典查询
    obj.selectClass = function (classCode, value) {
        var val;
        if (value != null & value != '') {
            $.ajax({
                url: baseRoot + "/base/selectClass/findByCode",
                async: false,
                type: "post",
                data: {'classCode': classCode, 'value': value},
                success: function (data) {
                    val = data;
                },
                error: function (xhr) {

                }
            })
        } else {
            val = '';
        }
        return val;
//      if(a.responseJSON==null){
//          return '';
//      }else{
//          return a.responseJSON;
//      }
    };
    /*********************************jqgrid********************************************/
    /**
     * 刷新jqgrid
     */
    obj.jqrefush = function (obj) {
        if(obj){
            try{
                obj = jQuery.parseJSON(obj);
            }catch (e) {
                obj={}
            }
            if(obj.id){
                var data = $("#table_list").getRowData(obj.id);
                if(!jQuery.isEmptyObject(data)){
                    $("#table_list").jqGrid('setRowData',obj.id,obj);
                }else{
                    $("#table_list").trigger("reloadGrid");
                }
            }else{
                $("#table_list").trigger("reloadGrid");
            }

        }else{
            $("#table_list").trigger("reloadGrid");
        }
    };
    /**
     * 创建jqgrid def为传入的jqgrid默认参数，cof为存放jgrdi容器和查询重置按钮
     */
    obj.grid = function (def, cof) {
        if (!cof) {
            cof = {};
        }
        if (!cof.table_list) {
            cof["table_list"] = "#table_list";
        }
        if (!cof.pager_list) {
            cof["pager_list"] = "#pager_list";
        }
        if (!cof.resetButton) {
            cof["resetButton"] = "#resetButton";
        }
        if (!cof.selectButton) {
            cof["selectButton"] = "#selectButton";
        }

        if (!cof.inputForm) {
            cof["inputForm"] = "#inputForm";
        }

        if (!cof.jqGrid_wrapper) {
            cof["jqGrid_wrapper"] = ".jqGrid_wrapper";
        }

        //计算表格高度
        calcGridHeight = function () {
            return $(window).height() - $("#DataTables_Table_0_wrapper").height() - 210;
        };

        var $table_list = $(cof.table_list);
        var $pager_list = $(cof.pager_list);
        var $resetButton = $(cof.resetButton);
        var $selectButton = $(cof.selectButton);
        var $inputForm = $(cof.inputForm);
        var $jqGrid_wrapper = $(cof.jqGrid_wrapper);
        var $deleteButton = $("#deleteButton");
        var defaults = {
            datatype: "json", //数据来源，本地数据
            mtype: "POST",//ajax提交方式。POST或者GET，默认GET
            height: calcGridHeight(),
            styleUI: "Bootstrap",
            autowidth: true,
            shrinkToFit: true,//此属性用来说明当初始化列宽度时候的计算类型，如果为ture，则按比例初始化列宽度。如果为false，则列宽度使用colModel指定的宽度
            //autoScroll: true,
            hidegrid: false,//启用或者禁用控制表格显示、隐藏的按钮，只有当caption 属性不为空时起效
            rowNum: 20,//设置表格可以显示的记录数
            rowList: [20, 50, 100],//用于改变显示行数的下拉列表框的元素数组。
            //colNames : ['模板', '模板编码', '操作'],//常用到的属性：name 列显示的名称；index 传到服务器端用来排序用的列名称；width 列宽度；align 对齐方式；sortable 是否可以排序
            pager: cof.pager_list,
            viewrecords: true,//是否要显示总记录数
            multiselect: false,//是否多选
            scrollrows: false,//滚动加载配置的参数
            scroll: false,
            pagerpos: 'left',//按钮显示位置
            // pgbuttons: true,//是否显示分页按钮
            loadtext: "努力为您加载中...",
            loadui: "block",
            recordtext: "已加载{1}条,共{2}条",
            emptyrecords: "暂无数据",
            rownumbers: true,//添加左侧行号
            jsonReader: {
                root: function (data) {
                    // json中代表实际模型数据的入口
                    return data.list;
                },
                page: function (data) {
                    // json中代表当前页码的数据
                    return data.pageNo;
                },
                total: function (data) {
                    // json中代表页码总数的数据
                    return data.totalPages;
                },
                records: function (data) {
                    // json中代表数据行总数的数据
                    return data.totalCount;
                },
                // 如果设为false，则jqGrid在解析json时，会根据name来搜索对应的数据元素（即可以json中元素可以不按顺序）；而所使用的name是来自于colModel中的name设定。
                repeatitems: false
            },
            prmNames: {
                page: "pageNumber", // 表示请求页码的参数名称
                rows: "pageSize", // 表示请求行数的参数名称
                sort: "orderProperty", // 表示用于排序的列名的参数名称
                order: "orderDirection", // 表示采用的排序方式的参数名称
                search: "_search", // 表示是否是搜索请求的参数名称
                //nd : "nd", // 表示已经发送请求的次数的参数名称
                //id : "id", // 表示当在编辑数据模块中发送数据时，使用的id的名称
                //oper : "oper", // operation参数名称（我暂时还没用到）
                //editoper : "edit", // 当在edit模式中提交数据时，操作的名称
                //addoper : "add", // 当在add模式中提交数据时，操作的名称
                //deloper : "del", // 当在delete模式中提交数据时，操作的名称
                subgridid: "id", // 当点击以载入数据到子表时，传递的数据名称
                npage: null,
                totalrows: "totalrows" // 表示需从Server得到总共多少行数据的参数名称，参见jqGrid选项中的rowTotal
            },
            gridComplete: function () {

                var rowNum = $table_list.jqGrid('getGridParam', 'records');
                var $emptyRecords = $(".empty-div");
                if (rowNum <= 0) {
                    if ($emptyRecords.html() == null) {
                        $table_list.parent().parent().append('<div class="empty-div" style="position: absolute; height: 100%;width: 100%;z-index: 100;">'
                            + '</div>');
                    }
                    $emptyRecords.show();
                } else {
                    $emptyRecords.hide();
                }
            },
            onSelectAll: function (aRowids, status) {
                if (status) {
                    window.selectIds = aRowids;
                    $deleteButton.removeClass("disable-btn");
                    $deleteButton.addClass("delete-color main-btn");
                } else {
                    window.selectIds = [];
                    $deleteButton.addClass("disable-btn");
                    $deleteButton.removeClass("delete-color main-btn");
                }

                $table_list.trigger("weijqgridSelectAll",[aRowids,status]);//绑定全选事件回调
            },
            onSelectRow: function (rowid, status) {
                var ids = $("#table_list").jqGrid("getGridParam", "selarrrow");
                if (ids.length > 0) {
                    window.selectIds = ids;
                    $deleteButton.removeClass("disable-btn");
                    $deleteButton.addClass("delete-color main-btn");
                } else {
                    window.selectIds = [];
                    $deleteButton.addClass("disable-btn");
                    $deleteButton.removeClass("delete-color main-btn");
                }
                $table_list.trigger("weijqgridSelectOne",[rowid,status]);//绑定多选事件回调

            },
            onSortCol:function (index,iCol,sortorder) {
                window.selectIds = [];
                $deleteButton.addClass("disable-btn");
                $deleteButton.removeClass("delete-color main-btn");
            },
            resizeStop: function (newwidth, index) {
                if ($(this).closest(".ui-jqgrid-bdiv").width() < $(this).width()) {
                    $(this).closest(".ui-jqgrid-bdiv").css({ "overflow-x": "auto" });
                }
            }

        }
        var jqgridParam = $.extend({}, defaults, def);
        var jqGrid = $table_list.jqGrid(jqgridParam);

        //添加刷新按钮
        if(!jqgridParam.scroll){
            jqGrid.jqGrid('navGrid', cof["pager_list"], {
                edit: false,
                add: false,
                del: false,
                search: false,
                position: "center"
            });
        }


        var columnNames = jqGrid.jqGrid('getGridParam','colModel');
        if(columnNames){
            for (i = 0; i < columnNames.length; i++) {
                jqGrid.setColProp(columnNames[i].index, { sortable: false });
            }
        }

        var $moveButton = $("#moveButton");
        //自定义监听reloadGrid
        $table_list.bind("reloadGrid",function () {
            window.selectIds = [];
            $deleteButton.addClass("disable-btn");
            $deleteButton.removeClass("delete-color main-btn");

        });
        /**
         * 给所有的input添加回车事件
         */
        $inputForm.find("input").each(function () {
            var $this = $(this);
            $this.keypress(function (e) {
                var key = e.which;
                if(13 == key){
                    $selectButton.trigger("click");
                }
            });
        });
        /***
         * 修复一个bug 当一个input表单会触发submit事件
         */
        $inputForm.append("<input style='display:none' />");

        $resetButton.click(function () {
            var formData = {};
            $.each($inputForm.serializeArray(), function (i, item) {
                formData[item.name] = "";
            });
            $table_list.jqGrid('setGridParam', {
                postData: formData, //发送数据
                page: 1
            }).trigger("reloadGrid"); //重新载入
        });

        $selectButton.click(function () {
            var formData = {};
            $.each($inputForm.serializeArray(), function (i, item) {
                formData[item.name] = item.value;
            });
            var v_url = $table_list.jqGrid("getGridParam","url");
            $table_list.jqGrid('setGridParam', {
                postData: formData, //发送数据
                page: 1,
                url:wei.urlUtil.geturlAddress(v_url)
            }).trigger("reloadGrid"); //重新载入
            $table_list.trigger("weijqgridSearch",formData);//绑定多选事件回调
        });

        // Add responsive to jqGrid
        $(window).bind('resize', function () {
            var width = $jqGrid_wrapper.width();
            $table_list.setGridWidth(width);
        });
        return jqGrid;

    };

    obj.getIndex = function (array,id){
        for(var i =0;i<array.length;i++){
            if(array[i] == id){
                return i;
            }
        }
        return -1;
    }

    return obj;
})();

/**
 * url 工具类
 */
Namespace.register("wei.urlUtil");
wei.urlUtil = (function () {
    var urlUtil = new Object();
    urlUtil.parseQueryString = function (url) {
        var reg_url = /^[^\?]+\?([\w\W]+)$/,
            reg_para = /([^&=]+)=([\w\W]*?)(&|$|#)/g,
            arr_url = reg_url.exec(url),
            ret = {};
        if (arr_url && arr_url[1]) {
            var str_para = arr_url[1], result;
            while ((result = reg_para.exec(str_para)) != null) {
                ret[result[1]] = result[2];
            }
        }
        return ret;
    };

    urlUtil.getUrlQueryString = function (url) {
        if (url.indexOf("?") != -1) {
            var arr = url.split("?");
            return "?" + arr[1];
        } else {
            return "";
        }
    };

    urlUtil.geturlAddress = function (url) {
        if (url.indexOf("?") != -1) {
            var arr = url.split("?");
            return arr[0];
        } else {
            return url;
        }
    };

    return urlUtil;

})();
/****
 * 时间处理函数
 */
Namespace.register("wei.formateDate");
wei.formateDate = (function () {
	var obj = new Object();
    /***
     * 2010-10-20 10:00:00
     * */
    obj.getLocalTime = function(nS) {
        return new Date(parseInt(nS)).toLocaleString();
    };
    /******
     * 2007/4/29 下午1:33:55
     * **/
    obj.getLocalTime1 = function getLocalTime(nS) {
        return new Date(parseInt(nS)).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
    };
    //2016/10/31
    obj.getDate=function getLocalTime(nS) {
        if(nS == null || nS== ""){
            return "";
        }
        var dateStr = new Date(parseInt(nS)).toLocaleString();
        var arr = dateStr.split(" ");
        return arr[0];
    }

    return obj;
})();
/*****
 * 所有的弹出框定义
 */
Namespace.register("wei.dialog");
wei.dialog = (function () {
	var obj = new Object();
    /***
	 * 打开iframe
     * @param title
     * @param url
     * @param width
     * @param height
     */
    obj.openIrame = function(title,url,width,height){
        width = width?width:"100%";
        height = height?height:"100%";
        layer.open({
            type: 2, //page层
            area: [width, height],
            title: title,
            shadeClose: true,
            shade: 0.2, //遮罩透明度
            move: false,
            content: url,
        });
    };
    /***
	 * iframe弹出框使用的提示框
     * @param content
     * @param title
     * @param icon
     * @param time
     */
    obj.msgIframe = function(content,title,icon,time){
        wei.dialog.layerMsg(title,icon,time,function () {
            wei.closedialog.closeIframeAdnFush(content);
        });
    };
    obj.msg = function(content,title,icon,time){
        wei.dialog.layerMsg(title,icon,time,function () {
            wei.jqrefush(content);
        });
    };
    obj.layerMsg = function (title,icon,time,callback) {
        title = !title?"保存成功！":title;
        icon = !$.isNumeric(icon)?1:icon;
        time =  !$.isNumeric(time)?1000:time;
        if(typeof callback === "function"){
            layer.msg(title, {icon: icon,time:time},function(){
                callback();
            });
        }else{
            layer.msg(title, {icon: icon,time:time});
        }

    };
    obj.errorlayerMsg = function (title,callback) {
        title = !title?"操作失败！":title;
        icon = 2;
        time =  2000;
        wei.dialog.layerMsg(title,icon,time,callback);
    };

	return obj;
})();
/********
 * 所有的关闭弹出框定义
 * *****/
Namespace.register("wei.closedialog");
wei.closedialog = (function () {
    var obj = new Object();
    /**
     * 关闭iframe弹出框
     */
    obj.closeIframe = function(){
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
    };

    /**
     * 关闭iframme 刷新jqgrid
     */
    obj.closeIframeAdnFush = function(content){
        $(parent.window)[0].wei.jqrefush(content);
        wei.closedialog.closeIframe();
    };

    obj.iframeJqrefush = function(content){
        $(parent.window)[0].wei.jqrefush(content);
    }

    return obj;
})();
/***************************************默认参数配置********************************************************/
Namespace.register("wei.defaults");
wei.defaults = (function () {
	var chosenConfig ={
        allow_single_deselect: true,
        disable_search:true,//禁用搜索按钮
        //disable_search_threshold: 10,//在单个选择上隐藏搜索输入，如果有少于n选项。
        width: "100%"//出现一个bug不显示宽度
	};
	var validateTooltip = {
        showErrors: function(errorMap, errorList) {
            var v_this = this;
            $.each(this.successList, function (index, value) {
                $(value).removeClass(v_this.settings.elErrorClass);
                $(value).tooltip('destroy');
            });


            $.each(errorList, function (index, value) {
                $(value.element).addClass(v_this.settings.elErrorClass);
                if($(value.element).attr("data-original-title") != value.message){
                    $(value.element).tooltip('destroy');
                }
                $(value.element).attr('title',value.message).tooltip({
                    placement: 'top',
                    trigger: 'manual',
                    delay: { show: 500, hide: 5000 }
                }).tooltip('show');

            });
        }
        //       unhighlight: function (element, errorClass, validClass) { //验证通过
        //            $(element).tooltip('destroy').removeClass(errorClass);
        //        },
        //        errorPlacement: function (label, element) {
        //     if($(element).attr('titleBak')&&$(element).attr('titleBak') != $(label).text()){
        //         $(element).tooltip('destroy'); /*必需*/
        //         $(element).attr('title', $(label).text()).tooltip({ trigger:'focus'}).tooltip("show");
        //     }else{
        //         if($(element).next().attr("role") == 'tooltip'){
        //         }else{
        //             $(element).attr('titleBak',$(label).text());
        //             $(element).attr('title', $(label).text()).tooltip({ trigger:'focus'}).tooltip("show");
        //         }
        //
        //     }
        //
        //           // if($(element).next().attr("role") == 'tooltip'){
        //           // }else{
        //             //    $(element).attr('title', $(label).text()).tooltip({ trigger:'focus'}).tooltip("show");
        //           // }
        //        }
	}

	return {
        chosenConfig:chosenConfig,
        validateTooltip:validateTooltip,
	}
})();
Namespace.register("wei.ajax");
wei.ajax = (function () {
	var obj = new Object();
    obj.ajaxTagSE =function(url, data, successfn, errorfn) {
        obj.ajaxTag(url,data,true,"post","json",successfn,errorfn);
    };

    obj.ajaxTagSu = function(url, data, successfn) {
        obj.ajaxTag(url,data,true,"post","json",successfn,null);

    };

    obj.ajaxTag =function(url, data, async, type, dataType, successfn, errorfn) {
        type = (type==null || type=="" || typeof(type)=="undefined")? "post" : type;
        dataType = (dataType==null || dataType=="" || typeof(dataType)=="undefined")? "json" : dataType;
        data = (data==null || data=="" || typeof(data)=="undefined")? {"date": new Date().getTime()} : data;
        $.ajax({
            type: type,
            async: async,
            data: data,
            url: url,
            dataType: dataType,
            beforeSend:function (XMLHttpRequest) {
                // this.layerIndex = layer.load(0, { shade: [0.5, '#393D49'] });
            },
            complete:function (XMLHttpRequest, textStatus) {
                // layer.close(this.layerIndex);
            },
            success: function(data, status, xhr){
                if(successfn){
                    successfn(data, status, xhr);
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown){
                if(errorfn){
                    errorfn(XMLHttpRequest, textStatus, errorThrown);
                }

            }
        });
    };

	return obj;
})();



$(function(){
    /***文本框的宽度***/
    $(".admin-text").width($(".prve-input").width()-75);
    $(window).resize(function () {
        $(".admin-text").width($(".prve-input").width()-75);
    })

    $(document).on("focus",".default-input",function(){
        $(this).addClass("border-input");
        $(this).next(".drown").addClass("drownhover");
    });

    $(document).on("blur",".default-input",function(){
        $(this).removeClass("border-input");
    });
    //复选框
    $(".check-div").on("click",function(){
        var check=$(this).attr("check");
        //white-color
        if(check==0){
            $(this).addClass("white-color");
            $(this).attr("check","1");
            return;
        }else{
            $(this).removeClass("white-color");
            $(this).attr("check","0");
        }
    });
    //单选按钮
    $(".radio-div").find(".radio-list").click(function(){

        $(".radio-div .radio-list").find(".radius").remove();
        $(this).find(".iconfont").after('<i class="radius"></i>');
    });
    //组合按钮
    var groupbutton=function(group) {
        $(group).find("button").click(function () {
            $(group).find("button").removeClass("select");
            $(this).addClass("select");
        });
    }
    //加载按钮
    $(".load").click(function(){
        $(this).prepend('<div class="load-icon"></div>');
        $(this).find("span").addClass("load-span");
        $(this).find("span").html("加载中");
        $(this).css("background-color","#50a0e5");
    });
    groupbutton(".group-btn");
    groupbutton(".group-icon-btn");

    //文本框中行政区划
    $(".administrative").click(function(){
		/*$(".administrative-div").show();*/
        $(".administrative-div").toggle();
    });
	/*$(".administrative").blur(function(){*/
	/*$(".administrative-div").hide();*/
	/*});*/
    /************** 主题************************************/
    $(".theme").click(function(){
        $(".them-div").toggle();
    });

    $(document).on("click",".skin-setttings .setings-item",function(){
    	var v_skin = $(this).data("skin");
        layer.open({
            type: 1, //page层
            area: ['400px', '180px'],
            title: '标题',
            btn: ['取 消', '确 定'],
            shade: 0.6, //遮罩透明度
            moveType: 1, //拖拽风格，0是默认，1是传统拖动
            shift: 1, //0-6的动画形式，-1不开启
            content: '<div class="popup-main" style="padding:20px;">更改皮肤会刷新页面，请确认您的页面操作是否已保存？</div>',
            btn1:function(index,dom){
                layer.close(index);
            },
            btn2:function(){
                wei.ajax.ajaxTagSu(baseRoot+"/open/skin.shtml",{ skin: v_skin},function (message) {
                	if (message.type == "success") {
                        window.location.reload(true);
                    }else{
                        if(message.content){
                            wei.dialog.layerMsg(message.content);
                        }else{
                            wei.dialog.errorlayerMsg('更换失败！');
                        }
                    }
                });
            }
        });

    });


});
/********************************扩展jqery插件***********************************************/
$(function(){

    $.fn.extend({
        //序列号表格中的input转成json数组
        tableObject:function(){
            var objects =[];
            $(this).find("tr").each(function(){
                var object = new Object();
                $(this).find("input").each(function(){
                    var name = $(this).attr("name");
                    var val = $(this).val();
                    object[name] = val || '';

                });
                if(!jQuery.isEmptyObject(object)){
                    objects.push(object);
                }

            });
            return JSON.stringify(objects);
        },
        toJsonArr:function(){
            var objects =[];
            var $obj = $(this);
            var object = new Object();
            $obj.find("ul li").each(function(){
                var object = new Object();
                $(this).find("input").each(function(){
                    var name = $(this).attr("name");
                    var val = $(this).val();
                    object[name] = val || '';
                });
                if(!jQuery.isEmptyObject(object)){
                    objects.push(object);
                }
            })
            return JSON.stringify(objects);
        }
    });

    $(document).ajaxComplete(function(evt, request, settings){
        var text = request.responseText;
        var data = null;
        try{
            data =  $.parseJSON(text);
		}catch(e) {
		}
        if(request.status == "401"){
            if(settings.url=="/base/website/photoUpload"){
            	return false;
			};
            var v_sessionstatus = request.getResponseHeader("sessionstatus");
            if(v_sessionstatus =="timeout"){
                //如果超时就处理 ，指定要跳转的页面
                top.layer.alert("会话过期,请重新登陆！", {icon: 5},function(){
                    if (window != top) {
                        top.location.reload();
                    }else{
                        location.reload();
                    }
                });
            } else {
                top.layer.alert( "系统异常，请联系系统管理员", {
                    title: "错误提醒",
                    icon: 2
                });
            }
        }  else if (request.status == "500") {
                //页面层
            top.layer.alert("系统异常，请联系系统管理员", {
                title: "错误提醒",
                icon: 2
            });
        }
    });
    /********************************************事件绑定*******************************************************/
    var $deleteButton = $("#deleteButton");
    var $reseBtn = $("#reseBtn");
    var $resePwdBtn = $("#resePwdBtn");

    // 删除
    $deleteButton.click( function() {
        var $this = $(this);
        if ($this.hasClass("disable-btn")) {
            return false;
        }
        layer.open({
            type: 1,
            title: false,
            closeBtn: 0,
            shadeClose: true,
            btn: ['取 消', '确 定'],
            skin: 'popup-one',
            content: '<div class="popup-div"><i class="iconfont pop-warning"></i><div class="pop-title"><p>是否确定删除？</p><div class="pop-content"></div></div></div>',
            btn1:function(index, layero){
                layer.close(index);
                $deleteButton.addClass("disable-btn");
                $deleteButton.addClass("maincolor main-btn");
                if($reseBtn.length>0){
                    $reseBtn.addClass("disable-btn");
                    $reseBtn.addClass("maincolor main-btn");
                }
                $("#table_list").jqGrid("resetSelection");

            },
            btn2:function(index, layero){
                layer.close(index);
                wei.ajax.ajaxTagSu("delete",{'ids':selectIds.join(",")},function (message) {


                    if (message.type == "success") {
                        wei.dialog.layerMsg("删除成功！",null,null,function(){
                            window.selectIds = [];
                            $deleteButton.addClass("disable-btn");
                            $deleteButton.removeClass("delete-color main-btn");
                            wei.jqrefush();
                        });
                    }else{
                        selectIds=[];
                        if(message.content){
                            wei.dialog.layerMsg(message.content,2);
                        }else{
                            wei.dialog.layerMsg('删除失败！',2);
                        }

                    }
                    $deleteButton.addClass("disable-btn");
                    $deleteButton.addClass("maincolor main-btn");
                    if($reseBtn.length>0){
                        $reseBtn.addClass("disable-btn");
                        $reseBtn.addClass("maincolor main-btn");
                    }
                    if($resePwdBtn.length>0){
                        $resePwdBtn.addClass("disable-btn");
                        $resePwdBtn.addClass("maincolor main-btn");
                    }

                });
            }
        });

    });


    /****
     * 通用弹出框
     */
    $(document).on("click",".weiOpenBtn",function(){
        var $this = $(this);
        var v_url = $this.data("url");
        var v_title = $this.data("title");
        var v_widht = $this.data("width")?$this.data("width"):"100%";
        var v_height = $this.data("height")?$this.data("height"):"100%";
        wei.dialog.openIrame(v_title,v_url,v_widht, v_height);
    });


    /****
     * 通用关闭弹出框
     */
    $(document).on("click",".weiColseOpenBtn",function(){
        wei.closedialog.closeIframe();
    });

    $(document).on("click",".weiDeleteRow",function(){
        var $this = $(this);
        var v_id = $this.data("id");
        var reload = $this.data("reload")?true:false;
        layer.open({
            type: 1,
            title: false,
            closeBtn: 0,
            shadeClose: true,
            btn: ['取 消', '确 定'],
            skin: 'popup-one',
            content: '<div class="popup-div"><i class="iconfont pop-warning"></i><div class="pop-title"><p>是否确定删除？</p><div class="pop-content"></div></div></div>',
            btn1:function(index, layero){
                layer.close(index);
            },
            btn2:function(index, layero){
                layer.close(index);
                wei.ajax.ajaxTagSu("delete",{'ids':v_id},function (message) {
                    if (message.type == "success") {
                        wei.dialog.layerMsg("删除成功！",null,null,function(){
                            if(true == reload){
                                document.location.reload();//当前页面
                            }else{
                                $("#table_list").trigger("reloadGrid");
                            }
                        });
                    }else{
                            wei.dialog.errorlayerMsg(message.content,2);
                    }
                });
            }
        });
    });


    /*******String方法扩展 有些浏览器不支持修复  不可以放到document.ready外边  会出线方法调用失败**********/
    String.prototype.startsWith = function(str){
        return (this.match("^"+str)==str)
    };

    String.prototype.endsWith = function(str){
        return (this.match(str+"$")==str)
    };

    /**
     **** 占位符替换
     **/
    String.prototype.format = function(args) {
        if (arguments.length>0) {
            var result = this;
            if (arguments.length == 1 && typeof (args) == "object") {
                for (var key in args) {
                    var reg=new RegExp ("({"+key+"})","g");
                    result = result.replace(reg, args[key]);
                }
            }
            else {
                for (var i = 0; i < arguments.length; i++) {
                    if(arguments[i]==undefined)
                    {
                        return "";
                    }
                    else
                    {
                        var reg=new RegExp ("({["+i+"]})","g");
                        result = result.replace(reg, arguments[i]);
                    }
                }
            }
            return result;
        }
        else {
            return this;
        }
    };


    $(document).on("click","[data-weiVideo]",function(){
        var v_index =  layer.load();
        var v_obj = $(this);//获取对象
        var v_src = v_obj.attr("data-weiVideo");//获取视频地址
        var v_imgsrc = v_obj.attr("src");//获取图片路径
       //判断容器存是否存在
        if($(".expand-div").length == 0){
            $(document.body).append("<div class=\"expand-div\"> </div>");
        };
        //判断容遮罩层是否存在
        if($(".photo-mask").length == 0){
            $(document.body).append("<div class=\"photo-mask\" style=' position: fixed; z-index: 10;  " +
                "bottom: 0; top: 0;  left: 0;  right: 0; background: rgba(0, 0, 0, 0.8); filter: alpha(opacity=20);  -moz-opacity: 0.8; opacity: 0.8;  display: none;'></div>");
        };
        //强制重写jwplayercss
        $("<style id='jwplayerFullScreeen'></style>").text(".jwplayer.jwfullscreen{    height: 95%!important;}").appendTo($("head"));
        //创建播放器容器
        var v_video="<div id='myplayer'></div>";
        /**插入标记*/
        var v_exDiv = $(".expand-div");
        v_exDiv.append(v_video);
        /***初始化*/
        jwplayer("myplayer").setup({//通过js调用播放器并安装到指定容器（container）内
            file: v_src,//调用视频文件
            width : 600,
            height:400,
            aspectratio:"24:10",//自适应宽高比例，如果设置宽高比，可设置宽度100%,高度不用设置支持的值：16:9,24:10,4:3;
            image:v_imgsrc,//视频预览图片
            controlbar: "over",//控制条位置
            screencolor :"#fff",//播放器颜色
            repeat:"false",//重复播放
            autostart:false,//自动播放
            primary:"flash", //选择flash播放还是html播放，默认为html5
           // modes配置项被用来指定渲染播放器JW Player所使用不同浏览器技术的顺序，JW Player使用的默认顺序为：
            modes : [
                { type : "flash" , src : baseRoot+"/resources/danger/js/plugin/jwplayer/jwplayer.flash.swf"},
                { type : "download" }
            ]
        });
        //obj这个参数是弹出框的整个对象
        var screenWidth = $(window).width(), screenHeigth = $(window).height();
        //当前窗口距离页面顶部的距离
        var objLeft = (screenWidth - v_exDiv.width()) / 2;
        ///弹出框距离左侧距离
        var objTop = (screenHeigth - v_exDiv.height()) / 2 ;
        ///弹出框距离顶部的距离
        v_exDiv .css({
            "left":objLeft + "px",
            "top":objTop + "px",
            "position":"fixed",
            "z-index":999,
            "border": "1px solid #ccc"
        });
        $(".photo-mask").fadeIn();
        v_exDiv.fadeIn(500);
        layer.close(v_index);

    });
    $(document).on("click",".photo-mask",function () {
        $(".expand-div").fadeOut("slow");
        $(".expand-div").remove();
        $(".photo-mask").hide();
        $("#jwplayerFullScreeen").remove();

    });

});


