/**
 * 
 */

$(function(){
	$(document).on("click","[data-click]",function () {
		var $this = $(this);
		var v_status = $(".edit-right").data("status");
		if(0 == v_status){
			parent.contractInterface.openTab({ "url": encodeURI( $this.data("click"))});
		}else{

		}
	});
	
	$("#quickSetting").on("click",function(){
		var v_status = $(".edit-right").data("status");
		if( 1 != v_status){
			layer.open({
				type: 2, //page层
				area: ['100%', '100%'],
				title: "快捷菜配置",
				shadeClose: true,
				shade: 0.2, //遮罩透明度
				move: false,
				content: baseRoot+"/base/menu/quickSetting",
				end:function () {
					reQuickMenu();
				}
			});
		}else{
			layer.tips('请先点击保存!',$(".edit-right"));
		}


	});

	function  reQuickMenu() {
		$.ajax({
			url : window.baseRoot + "/base/menu/getQuickMenu/",
			type : "POST",
			dataType : "json",
			cache : false,
			success : function(data) {
				var v_html ="";
				for(var i = 0; i < data.length; i ++){
					var quickMenu = data[i];
					var quickHtmlbtn='data-click="'+window.baseRoot;
					if(quickMenu["menuUrl"] && !quickMenu["menuUrl"].startsWith("/")){
						quickHtmlbtn+='/';
					}
					quickHtmlbtn+=quickMenu["menuUrl"]+'"';
					var quickHtml ='<li>'
					if($.trim(quickMenu["menuIconId"])){
						quickHtml+='<div class="shortcut-icon"'+quickHtmlbtn+' data-id="'+quickMenu["quickmenuId"]+'">'+quickMenu["menuIconId"]+'</div>';
					}else{
						quickHtml+='<div class="shortcut-icon"'+quickHtmlbtn+' data-id="'+quickMenu["quickmenuId"]+'"></div>';
					}
					quickHtml+='<p class="shortcut-title font-color-one">'+quickMenu["menuName"]+'</p></li>';
					v_html+=quickHtml;
				}
				var $v_ul = $(".shortcut ul").empty();
				$v_ul.html(v_html);
			}
		});
	}
	
	var height=$(".welcome-left").height()-25;
	$(".log-content").height(height);
	
	$(".edit-right").click(function(){
		var $this = $(this);
		var v_status = 	$this.data("status");
		if(1 == v_status){
			$(".shortcut-icon").find(".close-icon").remove();
			$this.find("samp").text("编辑");
			$this.data("status",0);
		}else{
		$(".shortcut").find(".shortcut-icon").append('<i class="iconfont close-icon">&#xe620;</i>');
			$this.find("samp").text("保存");
			$this.data("status",1);
		}
	})
	$(document).on("click",".shortcut .close-icon",function(evnet){
		var $this = $(this);
		var v_quickMenuId = $this.parent().data("id");
		console.log(v_quickMenuId);
		citms.ajax.ajaxTagSu(baseRoot+"/base/menu/deleteMenuQuick",{menuQucikId:v_quickMenuId},function (message) {
            if (message.type == "success") {
                $this.closest("li").remove();
                citms.dialog.layerMsg("删除成功！");
            }else{
                citms.dialog.errorlayerMsg(message.content);
            }
        });
		evnet.preventDefault();
	})
});