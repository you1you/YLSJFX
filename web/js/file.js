
$(document).ready(function () {

	$("#submit").click(function () {
		var uploadfile = document.getElementById("file").files[0];
		alert(uploadfile.name);
		PostFile(uploadfile, 0);
	});

});

function PostFile(file, i) {
	var name = file.name, //文件名
			size = file.size, //总大小shardSize = 2 * 1024 * 1024,    
			shardSize = 1 * 1024 * 1024, //以4MB为一个分片
			shardCount = Math.ceil(size / shardSize);  //总片数
	if (i >= shardCount) {
		return;
	}
	//计算每一片的起始与结束位置
	var start = i * shardSize,
			end = Math.min(size, start + shardSize);
	//构造一个表单，FormData是HTML5新增的
	var form = new FormData();
	form.append("file", file.slice(start, end));  //slice方法用于切出文件的一部分
	form.append("total", shardCount);  //总片数
	form.append("name", name);  //总片数
	form.append("index", i);        //当前是第几片

	//Ajax提交
	$.ajax({
		url: "upload.action",
		type: "POST",
		data: form,
//        async: true, //异步
		dataType: "json",
		processData: false, //很重要，告诉jquery不要对form进行处理
		contentType: false, //很重要，指定为false才能形成正确的Content-Type
		error: function () {
			console.log("error出错了");
		},
		success: function (data) {
			//alert(data.data);
			var num = Math.ceil(data.index * 100 / shardCount);
			$("#output").text(num + '%');
			PostFile(file, data.index+1);

		}
	});
}

