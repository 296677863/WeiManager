
	var ids = [];
	
	$().ready(function() {
		
		var $inputForm = $("#inputForm");
		// 表单验证
		$inputForm.validate($.extend({},{
			rules : {
				email:{
					required : true,
					email:true,
					remote:{
						type:"get",
						url:window.baseRoot+"/member/check_email.shtml",
						data:{
							'email': function() {return $("#email").val();}
						}
					}
				},
				pswd : {
					required : true,
					minlength : 4,
					maxlength : 20
				},
				rePassword : {
					required : true,
					equalTo : "#pswd"
				},
				nickname : {
					required : true,
					
				}
			},
			messages : {
				email : {
					remote : "Email已存在"
				}
			},
			submitHandler: function(form) {
				
				$("#rePassword").attr("disabled",true);
				$("button[type='submit']").attr("disabled","disabled");
				 $("button[type='submit']").addClass("disable-btn");
				 $("button[type='submit']").removeClass("maincolor");
				 $("button[type='submit']").removeClass("main-btn");

				 wei.ajax.ajaxTagSu(window.baseRoot+"/member/save.shtml",$inputForm.serialize(),function (message) {
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
			window.location.href=window.baseRoot+'/member/list.shtml';
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
	
