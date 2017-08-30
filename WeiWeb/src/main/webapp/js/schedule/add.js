
	var ids = [];
	
	$().ready(function() {
		
		var $inputForm = $("#inputForm");
		// 表单验证
		$inputForm.validate($.extend({},{
			rules : {
			
				methodName : {
					required : true,
				},
				beanName : {
					required : true,
					
				}
			},
			messages : {
				
			},
			submitHandler: function(form) {
				
				$("button[type='submit']").attr("disabled","disabled");
				 $("button[type='submit']").addClass("disable-btn");
				 $("button[type='submit']").removeClass("maincolor");
				 $("button[type='submit']").removeClass("main-btn");

				 wei.ajax.ajaxTagSu(window.baseRoot+"/schedule/save.shtml",$inputForm.serialize(),function (message) {
                     if (message.type == "success") {
                         wei.dialog.msgIframe(message.content);
                     }else{
                         $("button[type='submit']").removeAttr("disabled");
                         $("button[type='submit']").removeClass("disable-btn");
                         $("button[type='submit']").addClass("maincolor");
                         $("button[type='submit']").addClass("main-btn");
                         wei.dialog.errorlayerMsg(message.content);
                     }
                 });
				return false;
				
			}
		},wei.defaults.validateTooltip));
		
		$(".secondary").click(function(){
			window.location.href=window.baseRoot+'/schedule/listData.shtml';
		});

	});
	
	function getIndex(array,id){
		for(var i =0;i<array.length;i++){
			if(array[i] == id){
				return i;
			}
		}
		return -1;
	}
	
