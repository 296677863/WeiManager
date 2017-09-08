
/************************************ 列表加载数据 ****************************/
$(function() {
	var config = {
		url : window.baseRoot + "/schedule/listData.shtml",
		multiselect : true,
        height: $(window).height() - $("#DataTables_Table_0_wrapper").height() - 150,
		colNames : ['任务ID','Spring Bean','方法名','参数','状态','定时表达式','创建时间','操作'],
		colModel : [
				{
					name : 'jobId',
					index : 'jobId',
					align : 'left',
					key: true
				},
				{
					name : 'beanName',
					index : 'beanName',
					align : 'left'
				},
				{
					name : 'methodName',
					index : 'methodName',
					align : 'left'
				},
				{
					name : 'params',
					index : 'params',
					align : 'left'
				},
				{
					name : 'status',
					index : 'status',
					align : 'left'
				},
				{
					name : 'cronExpression',
					index : 'cronExpression',
					align : 'left'
				},
				{
					name : 'createTime',
					index : 'createTime',
					align : 'left'
				},
				{
					width : 320,
					fixed : true,
					formatter : function(cellvalue, options, rowObject) {
						var result = "<a href=\"javascript:void(0)\" onclick=\"wei.dialog.openIrame('编辑','{0}')\">[编辑]</a>"
						.format(window.baseRoot + '/schedule/edit/'
								+ rowObject.jobId+'.shtml');
						result = result + "<a href=\"javascript:void(0)\" onclick=\"wei.dialog.openIrame('详情','{0}')\">[详情]</a>"
						.format(window.baseRoot + '/schedule/info/'
								+ rowObject.jobId+'.shtml');
						result = result + "<a href=\"javascript:void(0)\" onclick=\"wei.dialog.openIrame('暂停','{0}')\">[暂停]</a>"
						.format(window.baseRoot + '/role/info/'
								+ rowObject.jobId+'.shtml');
						result = result + "<a href=\"javascript:void(0)\" onclick=\"wei.dialog.openIrame('恢复','{0}')\">[恢复]</a>"
						.format(window.baseRoot + '/role/info/'
								+ rowObject.jobId+'.shtml');
						result = result + "<a href=\"javascript:void(0)\" onclick=\"wei.dialog.openIrame('立即执行','{0}')\">[立即执行]</a>"
						.format(window.baseRoot + '/role/info/'
								+ rowObject.jobId+'.shtml');
						result = result + "<a href=\"javascript:void(0)\" data-id=\"{0}\"  class=\"weiDeleteRow\">[删除]</a>"
						.format(rowObject.jobId);
						return result;
					}
				} ]
	}
	var jqGrid = wei.grid(config);

});

