var mkList = [];
$(document).ready(function () {
    $("dd").hide();
    wdQuanXian();
    $.each($("dt"), function () {
        $(this).click(function () {
            $("#dt1 dd").not($(this).next()).slideUp();
            $(this).next().slideToggle();
            $("dt.selected").not($(this)).removeClass("selected");
            $(this).toggleClass("selected");
        });
    });

    $.each($("li"), function () {
        $(this).click(function () {
            $("li").removeClass("selected");
            $(this).addClass("selected");
        });
    });
});

function wdQuanXian2() {
    var json = {"ym": 12};
    json = {"jsonObj": JSON.stringify(json)};
    alert("123");
    var s = $.ajax({
        url: "/luopan/a01s.action",
        data: json,
        dataType: "json",
        type: "post",
        cache: false,
        error: function (msg, textStatus) {
            failureResp(msg.responseText);
        },
        success: function (json) {
            alert(JSON.stringify(json));
        }
    });
}

function wdQuanXian() {
//    var json = {};
//    json = {"jsonObj": JSON.stringify(json)};
//    var s = $.ajax({
//        url: "/luopan/getQx.action",
//        data: json,
//        dataType: "json",
//        type: "post",
//        cache: false,
//        success: function (json) {
//            saoMiao(json.qx);
//        }
//    });
//	alert(document.cookie.split(";")[0].split("=")[1]);
	var actor=parseInt(document.cookie.split(";")[0].split("=")[1]);
	switch(actor){
		case 1://系统管理员
			$("#200000").remove();
			break;
		case 2://2代表部门管理员
			$("dt").each(function(){
				if($(this).attr("id")!=="400000"){
					$(this).remove();
				}
			});
			break;
		case 3://3代表档案管理员
			$("#100000").remove();
			$("#200000").remove();
			break;
		case 4://4代表档案管理员
			$("dt").each(function(){
				if($(this).attr("id")!=="400000"){
					$(this).remove();
				}
			});
			break;
		case 5://5代表普通员工
			$("dt").each(function(){
				if($(this).attr("id")!=="400000"){
					$(this).remove();
				}
			});
			break;
		default :break;
	}
}

function saoMiao(ja) {
    $("dt").each(function () {
        var id = parseInt($(this).attr("id") / 10000);
        for (var i = 0; i < ja.length; i++) {
            var j = parseInt(ja[i] / 10000);
            if (j === id) {
                $(this).show();
                break;
            }
        }
        $(".xs").show();
    });
    $("li").each(function () {
        var id = parseInt($(this).attr("id") / 10);
        for (var i = 0; i < ja.length; i++) {
            var j = parseInt(ja[i] / 10);
            if (j === id) {
                $(this).show();
                break;
            }
        }
        $(".xs").show();
    });
}

function gzq(title, url, id) {
	if (id) {
		parent.wz.addDiv(title, url, id);
	}
}