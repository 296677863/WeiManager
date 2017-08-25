$(function(){
	
	var config = {
			 url:baseRoot+"/sysconfig/getSysConfigs.shtml",
			   multiselect: true,
			   colNames: ['字典名称', '系统参数值', '系统类型','系统参数描述','操作'],//常用到的属性：name 列显示的名称；index 传到服务器端用来排序用的列名称；width 列宽度；align 对齐方式；sortable 是否可以排序
			   colModel: [
			                 {
			                     name: 'paramName',
			                 },
			                 {
			                     name: 'paramValue',
			                 },
			                 {
			                     name: 'paramType',
			                 },
			                 {
			                     name: 'showName',
			                 },
			                 {
			                	 width: 150,
			                	 fixed:true,
			                     formatter:function(cellvalue, options, rowObject){
			                    	 return "<a href=\"javascript:void(0)\" onclick=\"wei.dialog.openIrame('系统配置编辑','"+baseRoot+"/base/system/config/input/"+rowObject.id+"')\">[编辑]</a>"+
										 	"<a href=\"javascript:void(0)\" data-id=\""+rowObject.id+"\" class=\"weiDeleteRow\" >[删除]</a>";
			                     }
			                 }
			                
			             ]
	}
	 var jqGrid = wei.grid(config);
	
});