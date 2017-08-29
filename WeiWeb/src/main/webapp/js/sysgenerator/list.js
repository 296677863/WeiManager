
/************************************ 列表加载数据 ****************************/
$(function() {
	
	var config = {
		url : window.baseRoot + "/sysgenerator/listData.shtml",
		multiselect : true,
        height: $(window).height() - $("#DataTables_Table_0_wrapper").height() - 150,
		colNames : ['表名','备注'],
		colModel : [
				{
					name : 'tableName',
					index : 'tableName',
					align : 'left',
					key:true
				},
				{
					name : 'comments',
					index : 'comments',
					align : 'left'
				}
				]
	}
	var jqGrid = wei.grid(config);
	$("#genButton").click( function() {
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
            content: '<div class="popup-div"><i class="iconfont pop-warning"></i><div class="pop-title"><p>是否确定生成？</p><div class="pop-content"></div></div></div>',
            btn1:function(index, layero){
                layer.close(index);
                $("#genButton").addClass("disable-btn");
                $("#genButton").addClass("maincolor main-btn");
                $("#table_list").jqGrid("resetSelection");

            },
            btn2:function(index, layero){
                layer.close(index);
                location.href='code.shtml?tables='+selectIds.join(",");
                $("#genButton").addClass("maincolor main-btn");
            }
        });
	});

});

