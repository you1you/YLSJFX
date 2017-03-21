//var webSocket = new WebSocket('ws://localhost:8080/mySSH/websocket');
//var file;//这个是文件对象
//var headBuf;//这个是头buffer的view
//var fileBuf;//这个是总文件的buffer
//var fileId = "";
//var sliceNum = 0;
//var idBuffer = null;

var DangAn = {"sz": [], "seq": -1, "yx": 1, "zys": 0};
$(document).ready(function () {
	$("#tblDangAn").setListBottom(DangAn, cxDangAn);
	$("#tblDangAn").built();
	$("#divCx4Da").popTopBar("查询文档信息");
	$("#divFrmDa").popTopBar("客户信息");
//    $("#divFrmDa").find("input,select").r2t();
//    $("#divCx4Da").find("input,select").r2t();
	$("#logo").click(function () {
		$("#divCx4Da").show();
		$("#board").css("z-index", $("#divCx4Da").css("z-index") - 1).show();
	});
	cd4DangAn();
});

function cd4DangAn() {
	$("#tblDangAn").contextMenu("cd4Da", {
		bindings: {
			"zjDa": function (t) {
				zjDangAn();
			},
			"xgDa": function (t) {
				xgDangAn();
			},
			"scDa": function (t) {
				scDangAn();
			}
		}
	});
}

function cxDangAn() {

}

function zjDangAn() {
	isEditKh = false;
	DaBianHao = "";
	$("#tblFrmDa input[type=text]").val("").removeAttr("readonly");
	$("#divFrmDa").css("z-index", "10").show();
	$("#board").css("z-index", $("#divFrmDa").css("z-index") - 1).show();
	$("#tblFrmDa_mc").focus();

}

function xgDangAn() {
	isEditKh = true;
	if (DangAn.sz[DangAn.seq] === undefined || DangAn.sz[DangAn.seq] === null) {
		return alert("请选择客户");
	}
	var da = DangAn.sz[DangAn.seq];
	DaBianHao = da.id;
	$("#tblFrmDa input[type=text]").val("").removeAttr("readonly");
	$("#tblFrmDa_mc").val(da.mc);
	$("#tblFrmDa_bz").val(da.bz);
	$("#divFrmDa").css("z-index", "10").show();
	$("#board").css("z-index", $("#divFrmDa").css("z-index") - 1).show();
	$("#tblFrmDa_mc").focus();
}

function check4Sjhm() {
	if ($("#tblFrmDa_mc").val() === "") {
		alert("请输入姓名");
		return false;
	}
	return true;
}

function scDangAn() {
//    if (DangAn.sz[DangAn.seq] === undefined || DangAn.sz[DangAn.seq] === null) {
//        return alert("请选择客户");
//    }
//    if (!confirm("是否删除客户" + DangAn.sz[DangAn.seq].mc + "?"))
//        return false;
//    var json = {"id": DangAn.sz[DangAn.seq].id};
//    json = {"jsonObj": JSON.stringify(json)};
//    $.ajax({
//        url: "/whwr/delKeHu.do",
//        data: json,
//        dataType: "json",
//        type: "post",
//        cache: false,
//        //error:function(msg, textStatus){failureResp(msg.responseText);},
//        success: function (json) {
//            if (checkResult(json)) {
//                cxKeHu();
//            }
//        }
//    });
}

//webSocket.onerror = function (event) {
//	onError(event);
//};
//
//webSocket.onopen = function (event) {
//	webSocket.binaryType = "arraybuffer";
//	//在建立连接的时候声明，websocket的二进制类型为arraybuffer，方便后面的数据交互
//	onOpen(event);
//};
//
//webSocket.onmessage = function (event) {
//	onMessage(event);
//};
////接收到信息后响应
//function onMessage(event) {
//	var buffer;
//	buffer = event.data;
//	var msg = new Int8Array(buffer);
//	var str1 = msg[0];
//	var str = "";
////							alert(str1+"    "+idBuffer);
//	if (str1 === 0 && idBuffer !== null) {//成功
//		//解码
//		var buffer1 = buffer.slice(1, 33);//w文件uuid
////								var buffer2 = buffer.slice(33, buffer.byteLength);
//		var pylBuf = buffer.slice(33, 37);
//		var pyl = new Int32Array(pylBuf);
//		var len = msg[37];
//		var buffer2 = buffer.slice(38, buffer.byteLength);
////								alert(buffer2.byteLength);
//		idBuffer = String.fromCharCode.apply(null, new Uint8Array(buffer1));
//		var newsBuffer = String.fromCharCode.apply(null, new Int16Array(buffer2));
//		str = str1 + idBuffer + pyl[0] + len + newsBuffer;
//		sendFile(file);
//
//	}
//
//	if (idBuffer === null) {//这是第一次传输，即传输包头的时候的返回
//		var buffer1 = buffer.slice(1, 33);
//		var buffer2 = buffer.slice(33, buffer.byteLength);
//		idBuffer = String.fromCharCode.apply(null, new Uint8Array(buffer1));
//		var newsBuffer = String.fromCharCode.apply(null, new Int16Array(buffer2));
//		str = str1 + idBuffer + newsBuffer;
////							var view=new Int8Array(sendSize);
//		console.log("接收到二进制大小为:" + buffer.byteLength + "第一个byte为:" + str1);
//		console.log("后台传输过来的字符串为:" + idBuffer);
//		console.log("后台传输过来的字符串为2:" + newsBuffer);
//		sendFile(file);
//	}
////	document.getElementById('messages').innerHTML
////			+= '<br />' + str;
//}
//
//function sendFile(file) {
//
//	var sendSize = 500000;
//	var reader = new FileReader();
//	reader.readAsArrayBuffer(file);
//	reader.onload = function () {
//		fileBuf = this.result;
//		//文件头部叠加切片
//		var bufLen = fileBuf.byteLength;
//		var sendBuffer = new ArrayBuffer(sendSize + 8);
//		var sendFileBuf;
//
//		var viewHead = new Int8Array(headBuf);//头部buffer
//		var viewSendBuf = new Int8Array(sendBuffer);
//		viewSendBuf.set(viewHead);
////									for(var i=0;i<viewHead.length;i++){//打印查看ViewData方法是否可用
////										console.log(viewSendBuf[i]+"      "+viewHead[i]);
////											
////									}
//		console.log(sliceNum);
//		if (sliceNum * sendSize + sendSize <= bufLen) {
//			sendFileBuf = fileBuf.slice(sliceNum * sendSize, sliceNum * sendSize + sendSize);
//			sliceNum++;
//			var sendFileView = new Int8Array(sendFileBuf);
//			var sendView = new Int8Array(sendFileBuf.byteLength + headBuf.byteLength);
////										var sendView = new Int8Array(sendFileBuf.byteLength + headBuf.byteLength+32+4);
//			sendView.set(headBuf);
//			sendView.set(sendFileView, 8);
////											var sendView=new Int8Array(sendFileBuf);
//			webSocket.send(sendView);
//		} else {
//			sendFileBuf = fileBuf.slice(sliceNum * sendSize, fileBuf.byteLength);
//			var sendFileView = new Int8Array(sendFileBuf);
//			var sendView = new Int8Array(sendFileBuf.byteLength + headBuf.byteLength);
//			sendView.set(headBuf);
//			sendView.set(sendFileView, 8);
////											var sendView=new Int8Array(sendFileBuf);
//			webSocket.send(sendView);
//			sliceNum = 0;
//			webSocket.close();
//		}
//	}
//}
//
////头部及其信息
//function bufferMessage(files) {
//	file = files[0];
//
//	var buffer = new ArrayBuffer(1024 * 2);
//	var len = new Uint32Array(buffer, 0, 1);
//	len[0] = "4294967213";  //包头长
//	var verify = new Int16Array(buffer, 4, 1);
//	verify[0] = 456;
//	var SendID = new Int8Array(buffer, 6, 1);
//	SendID[0] = 79;
//	var MsgType = new Int8Array(buffer, 7, 1);
//	MsgType[0] = 127;
//	console.log("信息长度:" + len[0]);
//	console.log("识别码:" + verify[0]);
//	console.log("ID号：" + SendID[0]);
//	console.log("文件信息类别:" + MsgType[0]);
//	//以下是文件信息包
//
//	var SfileName = file.name;
//	var fileNameLen = new Int16Array(buffer, 8, 1);
//	fileNameLen[0] = SfileName.length;
//	var len = fileNameLen[0];
//	var fileName = new Int16Array(buffer, 10, len);
//	//使用for循环将文件大小写入进去
//	for (var i = 0; i < len; i++) {
//		fileName[i] = SfileName.charCodeAt(i);
//		console.log(SfileName[i] + "的字节码值:" + SfileName.charCodeAt(i));
//	}
//	var fileSize = file.size;
//	var sFileSize = fileSize.toString();
//	var fileLen = sFileSize.length;
//	console.log("文件长度为:" + fileSize);
//	var fileSizeBuf = new Int8Array(buffer, 10 + len * 2, 18);
//	for (var i = 18 - fileLen, j = 0; i < 18; i++, j++) {//这个用于将文件的长度从后面开始写入
//		fileSizeBuf[i] = sFileSize[j];
//	}
//
//	var viewBuffer = new Int8Array(buffer);
////							var viewHeadBuf=new Int8Array(headBuf);
//	headBuf = viewBuffer.subarray(0, 8);
//	for (var i = 0; i < 8; i++) {
//		console.log(viewBuffer[i] + "转" + headBuf[i]);
//	}
//
//	webSocket.send(buffer);
//}
//function webClose() {
//	webSocket.close();
//}
//
//function onOpen(event) {
////	document.getElementById('messages').innerHTML
////			= '链接打开';
//}
//
//function onError(event) {
//	alert(event.data);
//}
//
//function start(files) {
//	bufferMessage(files);
//	document.getElementById('tblFrmDa_mc').innerHTML=files[0].name;
//}

function start(files) {
	document.getElementById('tblFrmDa_mc').innerHTML = files[0].name;

	$.ajax({
		url: "/whwr/getKeHus.action",
		data: form,
		dataType: "json",
		type: "post",
		cache: false,
		error: function (msg, textStatus) {
			failureResp(msg.responseText);
		},
		success: function (json) {
			if (checkResult(json)) {
				jxKeHu(json);
				$("#divCx4Kh").hide();
				$("#board").hide();
			}
		}
	});
}