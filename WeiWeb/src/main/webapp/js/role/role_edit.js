
$(function(){
	
	var $inputForm = $("#inputForm");
	$inputForm.validate($.extend({},{
		rules : {
			name:{
				required : true
			}
		},
		messages : {
			
		},
		submitHandler: function(form) {   
			$("button[type='submit']").attr("disabled","disabled");
			 $("button[type='submit']").addClass("disable-btn");
			 $("button[type='submit']").removeClass("maincolor");
			 $("button[type='submit']").removeClass("main-btn");
			 
			$(form).find(":submit").prop("disabled", true);
			wei.ajax.ajaxTagSu(window.baseRoot+"/role/update.shtml",$inputForm.serialize(),function (message) {
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
	
});
