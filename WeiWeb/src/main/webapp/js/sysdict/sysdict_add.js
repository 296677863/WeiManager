/**
 * 
 */

$(function(){
	$.validator.addClassRules({
		dictName: {
			required: true
		},
		dictType: {
			required: true
		},
		detailName:{
			required: true
		},
		detailCode:{
			required:true
		},
		detailSort:{
			required: true,
			digits:true
		},
		sort: {
			required: true,
			digits:true
		}
	});
	
	$("#headForm").validate(wei.defaults.validateTooltip);
	
	$("#addForm").validate(wei.defaults.validateTooltip);
	
	/******************计算值越大越排后图标位置**************************/
	/*function iconPosition(){
		var width=$(".sort").width();
		var cursor= $(".cursor-icon").width()/2;
		var totle=width+cursor-30;
		$(".tips-wrap").css("left",totle);
		
	};*/
	/*$(window).resize(function(){
		iconPosition();
	});*/
	$(document).on("mouseover",".cursor-icon",function(){
		iconPosition();
		$(".tips-wrap").show();
	})
	$(document).on("mouseout",".cursor-icon",function(){
		$(".tips-wrap").hide();
	})
	//iconPosition();
	
/***************************************编辑保存切换并同步数据库**************************************************/
	$("#editButton").click(function(){
			var $this = $(this);
			var status=$this.data("status");
			if(status==1){
				$(".base-info").find(".default-input").removeClass("curror");
				$(".base-info").find(".default-input").removeAttr("disabled");
				$this.find("span").html("保存");
				$this.data("status","2");
				$("#dataLevel").trigger('chosen:updated');
			}else{
				var flag = $("#headForm").valid();
				if(flag){
					submitSelectClass($this);
				}
			}
		});
	
	function submitSelectClass($this){
		$.ajax({
			url : window.baseRoot + "/sysdict/save.shtml",
			type : "POST",
			dataType : "json",
			data : $("#headForm").serialize(),
			cache : false,
			success : function(message) {
				if (message.type == "success") {

                    wei.dialog.layerMsg(null,null,null,function(){
                        $this.data("status","1");
                        $this.find("span").html("编辑");
                        $(".base-info").find(".default-input").addClass("curror");
                        $(".base-info").find(".default-input").attr("disabled","disabled");
                        $("#dataLevel").trigger('chosen:updated');
                        if($.trim($("#paramId").val()) == ''){
                            $("#paramId").val($.parseJSON(message.content)["dictId"]);
                        }
                        wei.closedialog.iframeJqrefush();
                    });

				} else {
                    wei.dialog.layerMsg('保存失败！',2);
				}
			}
		});
	};
/**************************************添加********************************************************/
	$("#addButton").click(function(){
		var add_html = '<div class="height-line">'+
		'<div class="col-sm-10 pl0">'+
		'    <div class="col-sm-4">'+
		'        <input type="text" name="detailName" class="default-input required" maxlength="200" placeholder="选择名称"'+
		'               style="width:80%"/>'+
		'    </div>'+
		'    <div class="col-sm-4">'+
		'        <input type="text" name="detailCode" class="default-input required" maxlength="200" placeholder="选项值"'+
		'               style="width:80%"/>'+
		'    </div>'+
		'    <div class="col-sm-4 position">'+
		'        <input type="text" name="detailSort" class="default-input sort float-l" maxlength="9" placeholder="排序号"'+
		'               style="width:80%"/>'+
		'        <div class="tips-wrap">'+
		'            <span class="arrows"></span>'+
		'            <span class="tips-content">值越大越排后</span>'+
		'        </div>'+
		'        <i class="iconfont cursor-icon float-l">&#xe624;</i>'+
		'    </div>'+
		'</div>'+
		'<div class="col-sm-2 pl0 text-r line-h">'+
		'    <a href="javascript:void(0)" class="save-info">[保存]</a>'+
		'    <a href="javascript:void(0)" class="addForReset">[删除]</a>'+
		'</div>'+
		'</div>';
		if($.trim($("#addForm").html())==""){
			$("#addForm").append(add_html);
		}
	});
	$(document).on("click",".save-info",function(){
        if($.trim($("#paramId").val()) == ''){
        	if($("#headForm").valid()){
                wei.dialog.layerMsg("请先保存基本信息",2);
        	}
        	return;
        }
		
		var flag = $("#addForm").valid();
		if(flag){
			var formData = {};
	        $.each($("#addForm").serializeArray(), function (i, item) {
	            formData[item.name] = item.value;
	        });
	        formData["selectClass.id"]=$("#paramId").val();
	        submitSelectDetailForm(formData);
		}
		
	});
	
	function  addInfo(formData){
		var v_html = '<li data-status="0">'+
		'<input type="hidden" name="id" value="{id}"/>'+
		'	<div class="col-sm-10 pl0">'+
		'		<div class="col-sm-4 col-val">'+
		'			<div class="dictionary-title">'+
		'				<span>{detailName}</span>'+
		'				<input type="text" class="default-input" name="detailName" value="{detailName}"  style="display:none;width:80%"/>'+
		'			</div>'+
		'		</div>'+
		'		<div class="col-sm-4 col-val">'+
		'			<div class="dictionary-title"><span>{detailCode}</span>'+
		'			<input type="text" class="default-input" name="detailCode" value="{detailCode}" style="display:none;width:80%"/>'+
		'		</div>'+
		'		</div>'+
		'		<div class="col-sm-4 col-val position">'+
		'			<div class="dictionary-title"><span>{detailSort}</span>'+
		'			<input type="text" class="default-input" name="detailSort" value="{detailSort}" style="display:none;width:80%"/>'+
		'		</div>'+
		'		</div>'+
		'	</div>	'+
		'	<div class="col-sm-2 pl0 text-r line-h">'+
		'		<a href="javascript:void(0)" class="edit-info">[编辑]</a>'+
		'		<a href="javascript:void(0)" class="delete-info">[删除]</a>'+
		'	</div>'+
		'</li>';
	        v_html = v_html.format({id:formData["id"],detailName:formData["detailName"],detailCode:formData["detailCode"],detailSort:formData["detailSort"]});
	        $("#infoForm .dictionary-list").find("ul").prepend(v_html);
	}
	
	function submitSelectDetailForm(jsonData){
		$.ajax({
			url : window.baseRoot + "/base/selectClass/updateSelectDetail/",
			type : "POST",
			dataType : "json",
			data:jsonData,
			cache : false,
			success : function(message) {
				if (message.type == "success") {
                    wei.dialog.layerMsg(null,null,null,function(){
                        addInfo($.parseJSON(message.content));
                        $("#addForm").empty();
                    });
				} else {
                    wei.dialog.layerMsg('保存失败！',2);
				}
			}
		});
	};
	
	$(document).on("click",".addForReset",function(){
		$("#addForm").empty();
	})
/*************************详情*********************************/
	
	$(document).on("click",".edit-info",function(){
		var $this = $(this);
		var $li = $(this).closest("li");
		if($li.data("status") == '0'){
			var that=$li.find(".dictionary-title");
			for(var i=0;i<that.length;i++){
				var value=that.parent().eq(i).find(".dictionary-title span").html();
				that.parent().eq(i).find(".dictionary-title input").show();
			}
			that.find("span").hide();
			that.css("padding-left","0px");
			$li.data("status","1");
			$this.text("[保存]")
		}else{
			var that=$li.find(".dictionary-title");
			var formData = {};
			for(var i=0;i<that.length;i++){
				var value=that.parent().eq(i).find(".dictionary-title input").val();
				var v_name=that.parent().eq(i).find(".dictionary-title input").prop("name");
				 formData[v_name] = value;
			}
			formData["id"]=$li.find("input").val();
			formData["selectClass.id"]=$("#paramId").val();
			saveSelectDetailForm(formData,$this,$li,that);
		}
		
	});
	
	function  hideInput($this,$li,that){
		var that=$li.find(".dictionary-title");
		for(var i=0;i<that.length;i++){
			var value=that.parent().eq(i).find(".dictionary-title input").val();
			that.parent().eq(i).find(".dictionary-title span").html(value);
			that.parent().eq(i).find(".dictionary-title input").hide();
			that.parent().eq(i).next().val(value);
		}
		that.css("padding-left","");
		that.find("span").show();
		$li.data("status","0");
		$this.text("[编辑]");
	}
	
	function saveSelectDetailForm(jsonData,$this,$li,that){
		$.ajax({
			url : window.baseRoot + "/base/selectClass/updateSelectDetail/",
			type : "POST",
			dataType : "json",
			data:jsonData,
			cache : false,
			success : function(message) {
				if (message.type == "success") {
                    wei.dialog.layerMsg(null,null,null,function(){
                        hideInput($this,$li,that);
                    });
				} else {
                    wei.dialog.errorlayerMsg('保存失败！');
				}
			}
		});
	};
	
$(document).on("click",".delete-info",function(){
	var $this = $(this);
	var $li = $(this).closest("li");
	var v_id = $li.find("input").val();
	deleteSelectDetailForm(v_id,$li);
		
});
	
function deleteSelectDetailForm(v_id,$li){
	$.ajax({
		url : window.baseRoot + "/base/selectClass/deleteSelectDetail/",
		type : "POST",
		dataType : "json",
		data:{id:v_id},
		cache : false,
		success : function(message) {
			if (message.type == "success") {
                wei.dialog.layerMsg(null,null,null,function(){
                    $li.detach();
                });
			} else {
                wei.dialog.errorlayerMsg('保存失败！');
			}
		}
	});
};	
	

	
});