/**
 *
 */
if (window != top) {
    top.location.href = location.href;
}


$(function () {
    var v_time =1500;
    $('#kaptchaImage').click(function (event) {//生成验证码
        $(this).hide().attr('src', baseRoot+'/open/getGifCode.shtml?' + Math.floor(Math.random()*100) ).fadeIn();
        event.cancelBubble=true;
    });

    var $loginForm = $("#loginForm");
    var $enPassword = $("#enPassword");
    var $username = $("#username");
    var $password = $("#password");

    var $isRememberUsername = $("#isRememberUsername");
    var $yzm = $("#yzm");

    // 记住用户名
    if (getCookie("adminUsername") != null) {
        $isRememberUsername.prop("checked",true);
        $username.val(getCookie("adminUsername"));
        $password.focus();
    } else {
        $isRememberUsername.prop("checked",false);
        $username.focus();
    }


    $("#login-btn").click(function (event) {
        event.preventDefault();
        validation();
    });
    $(document).keydown(function (e) {
        var curKey = e.which;
        if (curKey == 13) {
            $("#login-btn").trigger("click");
        }
    });

    // 表单验证、记住用户名
    function validation() {
        if ($username.val() == "") {
            $username.focus();
            layer.msg("请输入您的用户名!",{time:v_time});
            return false;
        }
        if ($password.val() == "") {
            $password.focus();
            layer.msg("请输入您的密码!",{time:v_time});
            return false;
        }

        if ($yzm.val() == "") {
            $yzm.focus();
            layer.msg("请输入验证码!",{time:v_time});
            return false;
        }

        if ($isRememberUsername.prop("checked")) {
            addCookie("adminUsername", $username.val(), {expires: 7 * 24 * 60 * 60});
        } else {
            removeCookie("adminUsername");
        }
        $("#rememberMe").val($isRememberUsername.prop("checked"));

        getEncrytionMode();

    }

    function getEncrytionMode() {
    	debugger;
        var enPassword = MD5($password.val());
        var pswd = MD5($username.val() +"#" + $password.val()),
    	data = {pswd:pswd,email:$username.val(),vcode:$yzm.val(),rememberMe:$isRememberUsername.is(':checked')};
        var load = layer.load();
        $.ajax({
            type:"post",
            url:baseRoot+"/u/submitLogin.shtml",
            //提交的数据
            data:data,
            //返回数据的格式
            datatype: "json",
            //在请求之前调用的函数
            beforeSend:function(){
        		layer.msg('开始登录，请注意后台控制台。');
        	},
            //成功返回之后调用的函数
            success:function(result,textStatus, request){
            	layer.close(load);
	    		if(result && result.status != 200){
	    			layer.msg(result.message,function(){});
	    			$('.password').val('');
	    			return;
	    		}else{
	    			layer.msg('登录成功！');
	    			setTimeout(function(){
	    				//登录返回
		    			window.location.href= result.back_url || baseRoot+"/";
	    			},1000)
	    		}
            }   ,
            //调用执行后调用的函数
            complete: function(XMLHttpRequest, textStatus){
                layer.close(this.layerIndex);
            },
            //调用出错执行的函数
            error:function(e){
        		console.log(e,e.message);
        		layer.msg('请看后台Java控制台，是否报错，确定已经配置数据库和Redis',new Function());
        	}
        });

    }

    // 添加Cookie
    function addCookie(name, value, options) {
        if (arguments.length > 1 && name != null) {
            if (options == null) {
                options = {};
            }
            if (value == null) {
                options.expires = -1;
            }
            if (typeof options.expires == "number") {
                var time = options.expires;
                var expires = options.expires = new Date();
                expires.setTime(expires.getTime() + time * 1000);
            }
            document.cookie = encodeURIComponent(String(name)) + "=" + encodeURIComponent(String(value)) + (options.expires ? "; expires=" + options.expires.toUTCString() : "") + (options.path ? "; path=" + options.path : "") + (options.domain ? "; domain=" + options.domain : ""), (options.secure ? "; secure" : "");
        }
    }

    // 获取Cookie
    function getCookie(name) {
        if (name != null) {
            var value = new RegExp("(?:^|; )" + encodeURIComponent(String(name)) + "=([^;]*)").exec(document.cookie);
            return value ? decodeURIComponent(value[1]) : null;
        }
    }

    // 移除Cookie
    function removeCookie(name, options) {
        addCookie(name, null, options);
    }


});