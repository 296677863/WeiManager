/**
 * 
 */

$(function(){
	
			var config = {
					 url:baseRoot+"/base/wximgarticle/listData.shtml",
					   multiselect: true,
					   colNames: ['标题', '作者','原文链接地址','创建时间', '操作'],//常用到的属性：name 列显示的名称；index 传到服务器端用来排序用的列名称；width 列宽度；align 对齐方式；sortable 是否可以排序
					   colModel: [
					                 {
					                     name: 'title',
					                 },
					                 {
					                     name: 'author',
					                 },
					                 {
					                     name: 'href',
					                 },
					                 {
					                     name: 'createDate',
					                     formatter : function(cellvalue, options, rowObject) {
					                         return wei.formateDate.getLocalTime(cellvalue);
					                     }
					                 },
					                 {
					                	 width: 150,
					                	 fixed:true,
					                     formatter:function(cellvalue, options, rowObject){
					                    	 var btn = '<a href="javascript:void(0)" class="weiOpenBtn" data-url="{0}/base/wximgarticle/edit/{1}" data-title="新闻编辑">[编辑]</a>'.format(baseRoot,rowObject.id);
					                    	 	btn+= '<a href="{0}/base/wximgarticle/view/{1}" target="_blank">[预览]</a>'.format(baseRoot,rowObject.id);
					                    	 return btn;
					                     }
					                 }
					                
					             ]
			}
			 var jqGrid = wei.grid(config);
			
		})