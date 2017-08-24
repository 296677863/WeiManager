
/************************************ 列表加载数据 ****************************/
$(function() {
	$(".chosen-select").chosen(wei.defaults.chosenConfig);
    $(".chosen-single").find("span").width($("input[name='name']").width());
	$(window).bind('resize', function () {
		$(".chosen-single").find("span").width($("input[name='name']").width());
	});
	var config = {
		url : window.baseRoot + "/permission/listData.shtml",
		multiselect : true,
        height: $(window).height() - $("#DataTables_Table_0_wrapper").height() - 150,
		colNames : ['权限名称','角色类型','操作'],
		colModel : [
				{
					name : 'name',
					index : 'name',
					align : 'left'
				},
				{
					name : 'url',
					index : 'url',
					align : 'left'
				},
				{
					width : 280,
					fixed : true,
					formatter : function(cellvalue, options, rowObject) {
						var result = "<a href='javascript:void(0)' onclick=\"wei.dialog.openIrame('成员管理','{0}')\">[成员管理]</a>"
							.format(window.baseRoot + '/permission/memberList.shtml/'+rowObject.id);
						if (!rowObject.isSystem) {
							result = result + "<a href=\"javascript:void(0)\" onclick=\"wei.dialog.openIrame('角色编辑','{0}')\">[编辑]</a>"
							.format(window.baseRoot + '/permission/input.shtml/'
									+ rowObject.id);
							result = result + "<a href=\"javascript:void(0)\" onclick=\"wei.dialog.openIrame('详情','{0}')\">[详情]</a>"
							.format(window.baseRoot + '/permission/info.shtml/'
									+ rowObject.id);
							result = result + "<a href=\"javascript:void(0)\" data-id=\"{0}\"  class=\"weiDeleteRow\">[删除]</a>"
							.format(rowObject.id);
						} 
						return result;
					}
				} ]
	}
	var jqGrid = wei.grid(config);

});

