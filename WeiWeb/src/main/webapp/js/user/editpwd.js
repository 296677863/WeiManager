
jQuery.validator.addMethod("checkPwd",function(value, element, param){
    var newpassword=$("#password").val();
	var oldpassword=$("#oldPwd").val();


	if(newpassword!=oldpassword){
		return true;
	}else{
		return false;
	}
},"旧密码不能和新密码相同");
	$().ready(function() {
		
		var $inputForm = $("#inputForm");
		// 表单验证
		$inputForm.validate($.extend({},{
			rules : {
				oldPwd:{
					required : true,
					remote:{
						type:"post",
						url:window.baseRoot+"/user/checkPwd.shtml",
						data:{
							'pwd': function() {return $("#oldPwd").val();}
						}
					}
				},
				password : {
					required : true,
					minlength : 4,
					maxlength : 20,
					checkPwd:true
				},
				rePassword : {
					required : true,
					equalTo : "#password"
				}
			},
			messages : {
				oldPwd : {
					remote : "旧密码不正确"
				}
			},
			submitHandler: function(form) {
				$("button[type='submit']").attr("disabled","disabled");
				 $("button[type='submit']").addClass("disable-btn");
				 $("button[type='submit']").removeClass("maincolor");
				 $("button[type='submit']").removeClass("main-btn");

				 wei.ajax.ajaxTagSu(window.baseRoot+"/user/updatePwd.shtml",{pwd:$("#oldPwd").val(),newPwd:$("#password").val()},
				 function (message) {
                     if (message.type == "success") {
                         wei.dialog.layerMsg("修改成功，请重新登陆！",null,null,function(){
                             window.top.location.href=window.baseRoot+"/u/login.shtml";
                         });
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

		$("#closeButton").click(function () {
			wei.closedialog.closeIframe();
		});

	});
	
	
