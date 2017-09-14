
/************************************ 列表加载数据 ****************************/
$(function() {
	var config = {
		url : window.baseRoot + "/schedule/listData.shtml",
		multiselect : true,
        height: $(window).height() - $("#DataTables_Table_0_wrapper").height() - 150,
		colNames : ['任务ID','Spring Bean','方法名','参数','状态','定时表达式','创建时间','操作'],
		colModel : [
				{
					width : 250,
					name : 'jobId',
					index : 'jobId',
					align : 'left',
					key: true
				},
				{
					width : 300,
					name : 'beanName',
					index : 'beanName',
					align : 'left'
				},
				{
					width : 250,
					name : 'methodName',
					index : 'methodName',
					align : 'left'
				},
				{
					width : 250,
					name : 'params',
					index : 'params',
					align : 'left'
				},
				{
					width : 250,
					name : 'status',
					index : 'status',
					align : 'left'
				},
				{
					width : 340,
					name : 'cronExpression',
					index : 'cronExpression',
					align : 'left'
				},
				{
					width : 300,
					name : 'createTime',
					index : 'createTime',
					align : 'left'
				},
				{
					width : 340,
					fixed : true,
					formatter : function(cellvalue, options, rowObject) {
						var result = "<a href=\"javascript:void(0)\" onclick=\"wei.dialog.openIrame('编辑','{0}')\">[编辑]</a>"
						.format(window.baseRoot + '/schedule/edit/'
								+ rowObject.jobId+'.shtml');
						result = result + "<a href=\"javascript:void(0)\" onclick=\"wei.dialog.openIrame('详情','{0}')\">[详情]</a>"
						.format(window.baseRoot + '/schedule/info/'
								+ rowObject.jobId+'.shtml');
						if(rowObject.status=="0"){
							result = result + "<a href=\"javascript:void(0)\"  data-id=\"{0}\" class=\"pauseRow\">[暂停]</a>"
							.format(rowObject.jobId);
						}else{
							result = result + "<a href=\"javascript:void(0)\"  data-id=\"{0}\" class=\"resumeRow\" >[恢复]</a>"
							.format(rowObject.jobId);
						}
						result = result + "<a href=\"javascript:void(0)\" data-id=\"{0}\"  class=\"weiDeleteRow\">[删除]</a>"
						.format(rowObject.jobId);
						return result;
					}
				} ]
	}
	var jqGrid = wei.grid(config);
	
	
        
});


$(document).on("click",".resumeRow",function(){
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
	    content: '<div class="popup-div"><i class="iconfont pop-warning"></i><div class="pop-title"><p>确定恢复任务？</p><div class="pop-content"></div></div></div>',
	    btn1:function(index, layero){
	        layer.close(index);
	    },
	    btn2:function(index, layero){
	        layer.close(index);
	        wei.ajax.ajaxTagSu(window.baseRoot + '/schedule/resume/'
					+ v_id+'.shtml',{},function (message) {
	            if (message.type == "success") {
	                wei.dialog.layerMsg("运行成功！",null,null,function(){
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

$(document).on("click",".pauseRow",function(){
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
	    content: '<div class="popup-div"><i class="iconfont pop-warning"></i><div class="pop-title"><p>是否确定暂停任务？</p><div class="pop-content"></div></div></div>',
	    btn1:function(index, layero){
	        layer.close(index);
	    },
	    btn2:function(index, layero){
	        layer.close(index);
	        wei.ajax.ajaxTagSu(window.baseRoot + '/schedule/pause/'
					+ v_id+'.shtml',{},function (message) {
	            if (message.type == "success") {
	                wei.dialog.layerMsg("暂停成功！",null,null,function(){
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
