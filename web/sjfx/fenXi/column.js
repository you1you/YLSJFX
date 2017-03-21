var keHu = {"sz": [], "seq": -1, "yx": 1, "zys": 0};
$(document).ready(function () {
    $("#canvasDiv").setListBottom(keHu, cxKeHu);
    $("#canvasDiv").built();
    $("#divCx4Colum").popTopBar("查询科室疾病信息");
//    $("#divCx4Colum").popTopBar("室疾病信息");
//    $("#divCx4Colum").find("input,select").r2t();
//    $("#divtblCx4Colum").find("input,select").r2t();
    $("#logo").click(function () {
        $("#divCx4Colum").show();
        $("#board").css("z-index", $("#divCx4Colum").css("z-index") - 1).show();
    });
//    cd4KeHu();
});
//定义数据
$(function () {
	var data = [
		{name: 'IE', value: 35.75, color: '#a5c2d5'},
		{name: 'Chrome', value: 29.84, color: '#cbab4f'},
		{name: 'Firefox', value: 24.88, color: '#76a871'},
		{name: 'Safari', value: 6.77, color: '#9f7961'},
		{name: 'Opera', value: 2.02, color: '#a56f8f'},
		{name: 'Other', value: 0.73, color: '#6f83a5'}
	];

	new iChart.Column2D({
		render: 'canvasDiv',
		data: data,
		title: 'Top 5 Browsers from 1 to 29 Feb 2012',
		showpercent: true,
		decimalsnum: 2,
		width: 800,
		height: 400,
		coordinate: {
			background_color: '#fefefe',
			scale: [{
					position: 'left',
					start_scale: 0,
					end_scale: 40,
					scale_space: 8,
					listeners: {
						parseText: function (t, x, y) {
							return {text: t + "%"}
						}
					}
				}]
		}
	}).draw();
});

function cxKeHu() {
//    var json = {"yx": keHu.yx};
//    json.mc = $("#tblCx4Kh_mc").val();
//    json = {"jsonObj": JSON.stringify(json)};
//    $.ajax({
//        url: "/whwr/getKeHus.action",
//        data: json,
//        dataType: "json",
//        type: "post",
//        cache: false,
//        error: function (msg, textStatus) {
//            failureResp(msg.responseText);
//        },
//        success: function (json) {
//            if (checkResult(json)) {
//                jxKeHu(json);
//                $("#divCx4Kh").hide();
//                $("#board").hide();
//            }
//        }
//    });
}