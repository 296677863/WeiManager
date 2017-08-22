/****/
(function($,undefined) {
	
	String.prototype.format=function()  
	{  
	  if(arguments.length==0) return this;  
	  for(var s=this, i=0; i<arguments.length; i++)  
	    s=s.replace(new RegExp("\\{"+i+"\\}","g"), arguments[i]);  
	  return s;  
	}; 
	
	formatSize=function(r,t,e){
		var n;
		for(e=e||["B","K","M","G","TB"];(n=e.shift())&&r>1024;)r/=1024;
		return("B"===n?r:r.toFixed(t||2))+n;
		}
	
	$.fn.uploadDefaults={
			auto:true,// [可选] [默认值：false] 设置为 true 后，不需要手动调用上传，有文件选择即开始上传
			swf: baseRoot+'/resources/admin/base/js/plugins/webuploader/Uploader.swf',//定义uploadify.swf的路径。
			accept: {
	            title: 'Images',
	            extensions: 'gif,jpg,jpeg,bmp,png',
	            //mimeTypes: 'image/*'//使用image/* 存在一个bug 谷歌最新版本，点击上传按钮，弹出系统上传选择框反应超慢 #2011
	            mimeTypes:'image/gif,image/jpg,image/jpeg,image/bmp,image/png'
	        },
	        disableGlobalDnd: true,//是否禁掉整个页面的拖拽功能
	        chunked: false,
	        server:baseRoot+'/base/uploadFile/imageUpload',//定义服务器端上传数据处理请求,
	        formData:{ },
	        fileSingleSizeLimit :10*1024*1024,//10M  单个文件
	        duplicate:true,//允许重复上传
	        //runtimeOrder:"html5"
		};
	
	
	$.upload=function(uploadParam){
		var uploaddeFault = $.extend({},$.fn.uploadDefaults,uploadParam);
		var v_id = uploaddeFault.pick.id;
		var v_webUploader = WebUploader.create(uploaddeFault)
		v_webUploader.on("error",function(errName,fileSize,object ){
			switch(errName){
				case"Q_EXCEED_NUM_LIMIT":
                    wei.dialog.layerMsg("最多只能上传{0}个文件".format(fileSize),2);
					break;
				case"F_EXCEED_SIZE":
                    wei.dialog.layerMsg("文件大小不能超过{0}".format(formatSize(fileSize,"0")),2);
					break;
				case"F_EXCEED_COMPRESS_SIZE":
                    wei.dialog.layerMsg("图片尺寸太大，压缩后不能超过{0}，请缩小图片尺寸再试".format(v_webUploader.compress.afterCompressSizeLimit?v_webUploader.compress.afterCompressSizeLimit/1048576+"M":"2M"),2);
					break;
				case"Q_TYPE_DENIED":
                    wei.dialog.layerMsg("文件必须为以下格式：{0}".format(v_webUploader.options.accept[0].extensions).replace(/,/g,", "),2);
					break;
				}
		});
		v_webUploader.on("uploadStart",function(){
				this.layerIndex = layer.load();
		})
		
		v_webUploader.on("uploadComplete",function(file){
				 layer.close(this.layerIndex);
		});

		v_webUploader.on("uploadError",function(file){
            wei.dialog.layerMsg("上传文件出错!",2);
		});
		
		return v_webUploader;
	}
	
	
	
})(jQuery)	
 
