
$(function(){
			var config = {
					 url:baseRoot+"/sysdict/listData.shtml",
					   multiselect: true,
                	   height: $(window).height() - $("#DataTables_Table_0_wrapper").height() - 150,
					   colNames: ['','字典名称', '字典类型', '操作'],//常用到的属性：name 列显示的名称；index 传到服务器端用来排序用的列名称；width 列宽度；align 对齐方式；sortable 是否可以排序
					   colModel: [
					              {
					            	  name:'dictId', 
					            	  index:'dictId',
					            	  hidden:true ,
					            	  key:true
					              },

					                 {
					                     name: 'dictName',
					                     index: 'dictName',
					                     width: 150,
					                     fixed:true 
					                 },
					                 {
					                     name: 'dictType',
					                     index: 'dictType',
					                 },
					                 {
					                	 width: 280,
					                	 fixed:true,
					                     formatter:function(cellvalue, options, rowObject){
					                    	 return "<a href=\"javascript:void(0)\" onclick=\"wei.dialog.openIrame('字典编辑','"+baseRoot+"/sysdict/edit.shtml?dictType="+rowObject.dictType+"')\">[编辑]</a>&nbsp;" +
					                    	 		"<a href=\"javascript:void(0)\" onclick=\"wei.dialog.openIrame('字典详情','"+baseRoot+"/sysdict/info.shtml?dictType="+rowObject.dictType+"')\">[详情]</a>&nbsp;" +
					                    	 		"<a href=\"javascript:void(0)\" data-id=\""+rowObject.dictId+"\" class=\"weiDeleteRow\" >[删除]</a>";
					                     }
					                 }
					                
					             ]
			}
			 var jqGrid = wei.grid(config);
		})
		
	
		