$(function () {
    var theme = "default";
    if(window.skin != 'blue'){
        theme = "blank";
    }
    var editor = UE.getEditor('editor',{
        toolbars: [
            [ 'undo', 'redo', '|', 'rowspacingtop', 'rowspacingbottom', 'lineheight','imagefloat','fontsize','touppercase','tolowercase','fontfamily','fontborder','backcolor','blockquote','horizontal','removeformat','formatmatch',
                'bold','italic','underline','strikethrough','forecolor','indent', 'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify','paragraph','insertparagraph','insertorderedlist','insertunorderedlist',
                'link','unlink','lineheight','date','time',,'rowspacing']
        ], elementPathEnabled:false,wordCount:false,autoHeightEnabled: false,enableAutoSave: false,autoSyncData:false,
        allowDivTransToP:false,xssFilterRules:false,inputXssFilter:false,outputXssFilter:false,autoFloatEnabled:false,
        theme:theme,
    });

    $(window).resize(function () {
        simditorwidth();
    });

    calcnumber();
    simditorwidth();
    height();

    $(document).on("click",".link-div .check-div",function () {
        if ($(this).attr("check") == 1) {
            $(".text-link").show();
        } else {
            $(".text-link").hide();
        }
    })
    $(".textarea-content").keyup(function () {
        calcnumber();
    })
    //计算文本域里的文字
    function calcnumber() {
        var num = $(".textarea-content").val().length;
        if (num <= 120) {
            $(".number").html(num);
        }
    }

    function height() {
        var height = $(".group-border").height() + 30;
        $(".media-div").height(height);
    }

    function simditorwidth() {
        var width1 = $(".default-input").width() + 8;
        $(".simditor").width(width1);
    }

    /*******************************图片处理**********************************************/

    /*******************图片上传*****************/
    var webuploaderImg = {
        formData: {'entityType': 'newsContentImg'},
        pick: {
            id: '#uploadImg',
            multiple: true
        }
    };

    var uploaderImg = $.upload(webuploaderImg);

    // 图片处理
    uploaderImg.on('uploadSuccess', function (file, response) {
        //ue.execCommand("insertimage",{src:"/upload/"+obj.filePath});
        var img = '<p><img src="/upload/' + response.filePath + '"  style="width:100%; margin:0 auto;"/></p>';
        editor.execCommand( 'inserthtml', img );
    });


    /*************************视频上传***********************************/

    var webuploaderVideo = {
        formData: {'entityType': 'newsContentVideo'},
        pick: {
            id: '#uploadVideo',
            multiple: true
        },
        accept: {
            title: 'video',
            extensions: 'mp4',
            mimeTypes: 'video/mp4'
        },
        fileSingleSizeLimit: 10 * 1024 * 1024,//10M  单个文件
    };
    var uploaderVideo = $.upload(webuploaderVideo);

    // 视频处理
    uploaderVideo.on('uploadSuccess', function (file, response) {
        var video = '<p><video webkit-playsinline  class="video-js vjs-default-skin" preload="meta"  controls="controls"  style=" width:100%;height:320px" data-setup="{}">' +
            '<source src="/upload/' + response.filePath + '"  type="video/mp4"/>' +
            '</video></p>';
        editor.execCommand( 'inserthtml', video );
    });

    /*******************************************封面处理************************************************/

    /**************************封面上传**************************************************/

    $(document).on("mouseover", ".img-div", function () {
        $(this).find(".img-append-mask").show();
    });
    $(document).on("mouseout", ".img-div", function () {
        $(this).find(".img-append-mask").hide();
    });
    var webuploaderSingeImg = {
        formData: {'entityType': 'cover'},
        pick: {
            id: '#uploadCover',
            multiple: false
        }
    };

    var uploaderCover = $.upload(webuploaderSingeImg);
    $("#uploadCover").parent("div").css("width", "10rem");
    if ($(".dispalyCover").find(".img-div").length != 0) {
        uploaderCover.disable();
    }

    uploaderCover.on('uploadSuccess', function (file, response) {
        var str = '	<div class="img-div" >' +
            '<input type="hidden" id="newsCoverId"  value="' + response.filePath + '">' +
            '<img  src="/upload/' + response.filePath + '" width="100px" height="100px">' +
            '<div class="img-append-mask">' +
            '<div class="img-mask"></div>' +
            '<i class="iconfont delete-icon">&#xe611;</i>' +
            '</div>' +
            '</div>';

        $(".dispalyCover").append(str);
        uploaderCover.disable();
    });

    $(document).on("click", ".iconfont.delete-icon", function () {
        var $this = $(this);
        if ($this.parent("div").hasClass("img-append-mask")) {
            var filepath = $(this).closest(".img-div").find("input").first().val();
            wei.ajax.ajaxTagSu(baseRoot + "/base/wximgarticle/deleteCover.shtml",{id: $("#newsId").val()},function (message) {
                if (message.type == "success") {
                    wei.ajax.ajaxTagSu(baseRoot + "/base/uploadFile/delUpload.shtml",{filePath: filepath},function (message) {
                        if (message.type == "success") {
                            wei.dialog.layerMsg("删除成功！",null,null,function(){
                                $(".dispalyCover").empty();
                                $(".photo-bg").find("img").remove();
                                $(".photo-bg").find("i").show();
                                uploaderCover.enable();
                            });
                        } else {
                            wei.dialog.errorlayerMsg(message.content);
                        }
                    });
                } else {
                    wei.dialog.errorlayerMsg(message.content);
                }
            });
        }
    });


    /**********************************保存*************************************/

    //启用提交
    function  ableSubBtn ($this){
        $this.removeAttr("disabled");

    };
    //禁用提交
    function  disSubBtn($this){
        $this.attr({"disabled":"disabled"});
    };

    function getDataInfo() {
        var data ={
            id:$("#newsId").val(),
            title:$.trim($("#newsTitle").val()),
            author:$("#newsAuthor").val(),
            content:editor.body.innerHTML,
            summary:$("#newsSummary").val(),
            cover:$("#newsCoverId").val(),
            createDate:$("#newCreateDate").val()
        };
        if($.trim($("#newsTitle").val()) ==""){
            wei.dialog.layerMsg("标题不允许为空!",2);
            return;
        }
        var v_checkNewsHref = $(".link-div .check-div").attr("check");
        if(v_checkNewsHref == 1){
            data.href = $("#newshref").val();
            if(!data.href.startsWith("http://")){
                wei.dialog.layerMsg("请以http://开头",2);
                return;
            }
        }
        var v_newsShowCover = $("#newsShowCover").attr("check");
        if(v_newsShowCover == 1){
            data.showCover = true;
        }else{
            data.showCover = false;
        }
        return data;
    }

    $(".saveNews").click(function(){
    	var $this = $(this);
        disSubBtn($this);
        var data = getDataInfo();
        if(!data){
            ableSubBtn($this);
            return ;
        }
        wei.ajax.ajaxTagSu(baseRoot+"/base/wximgarticle/save.shtml",data,function (message) {
            if (message.type == "success") {
                wei.dialog.msgIframe(message.content);
            }else{
                wei.dialog.layerMsg(message.content,2,null,function(){
                    ableSubBtn($this);
                });
            }
        });
	});

    $(".cancleNews").click(function () {
        wei.closedialog.closeIframe();
    });
    $(".priNews").click(function () {
        var $this = $(this);
        disSubBtn($this);
        var data = getDataInfo();
        if(!data){
            ableSubBtn($this);
            return ;
        }
        wei.ajax.ajaxTagSu(baseRoot+"/base/wximgarticle/exhibitionsave.shtml",data,function (message) {
            if (message.type == "success") {
                window.open(baseRoot+"/base/wximgarticle/viewPage.shtml")
                ableSubBtn($this);
            }else{
                wei.dialog.layerMsg(message.content,5,null,function(){
                    ableSubBtn($this);
                });
            }
        });
    });

});
