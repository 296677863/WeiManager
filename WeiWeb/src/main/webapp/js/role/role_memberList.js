$(function() {
	var config = {
		url : window.baseRoot + "/role/memberListData.shtml?roleId="+roleId,
		multiselect : false,
		colNames : [ '用户名', 'E-mail', '姓名', '部门', '最后登录日期', '最后登录IP', '状态',
				'创建日期', '操作' ],
		colModel : [
				{
					name : 'username',
					index : 'username',
					align : 'left',
					width : 100,
					fixed : true,
				},
				{
					name : 'email',
					index : 'email',
					align : 'left',
					width : 150,
					fixed : true,
				},
				{
					name : 'name',
					index : 'name',
					align : 'left',
					width : 100,
					fixed : true,
				},
				{
					name : 'department',
					index : 'department',
					align : 'left'
				},
				{
					name : 'loginDate',
					index : 'loginDate',
					formatter : function(cellvalue, options, rowObject) {
						return wei.formateDate.getDate(cellvalue);
					}
				},
				{
					name : 'loginIp',
					index : 'loginIp',
				},
				{
					name : 'isEnabled',
					index : 'isEnabled',
					formatter : function(cellvalue, options, rowObject) {
						if (cellvalue) {
							return '<font class="admin-state subcolor-three">正常</font>';
						} else {
							return '<font class="admin-state subcolor-one">停用</font>';
						}

					}
				},
				{
					name : 'createDate',
					index : 'createDate',
					formatter : function(cellvalue, options, rowObject) {
						return wei.formateDate.getDate(cellvalue);
					}
				},
				{
					width : 150,
					fixed : true,
					formatter : function(cellvalue, options, rowObject) {
						var result = "<a href=\"javascript:void(0)\" onclick=\"deleteInfo('{0}')\">[删除]</a>"
								.format(rowObject.id);
						return result;
					}
				} ]
	}
	var jqGrid = wei.grid(config);
	
	deleteInfo = function(id){
		layer.open({
			  type: 1,
			  title: false,
			  closeBtn: 0,
			  shadeClose: true,
			  btn: ['取 消', '确 定'],
			  skin: 'popup-one',
			  content: '<div class="popup-div"><i class="iconfont pop-warning"></i><div class="pop-title"><p>是否确定删除该角色下选择的用户？</p><div class="pop-content"></div></div></div>',
			  btn1:function(index, layero){
				    layer.close(index);
					$("#table_list").jqGrid("resetSelection");
				   
			  },
			  btn2:function(index, layero){
					layer.close(index);
					wei.ajax.ajaxTagSu(window.baseRoot+"/role/delUser",{'ids':id,"roleId":roleId},
					function (message) {
                        if (message.type == "success") {
                            wei.dialog.errorlayerMsg("删除成功！",null,null,function(){
                                wei.jqrefush();
                            });
                        }else{
                            selectIds=[];
                            if(message.content){
                                wei.dialog.layerMsg(message.content,2);
                            }else{
                                wei.dialog.errorlayerMsg('删除失败！');
                            }
                        }
                    });
			  }
		 });
	}
});