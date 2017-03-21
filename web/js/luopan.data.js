var lp_CangKuList={"sz":[],"xh":-1};
var lp_GangWeiList={"sz":[],"xh":-1};
var lp_GongXuFenLeiList={"sz":[],"xh":-1};
var lp_GongXuList={"sz":[],"xh":-1};
var lp_GongXuMoBanList={"sz":[],"xh":-1};
var lp_GongYingShangList={"sz":[],"xh":-1};
var lp_KeGuanCangKuList={"sz":[],"xh":-1};
var lp_KeHuList={"sz":[],"xh":-1};
var lp_WuZiZiDianList={"sz":[],"xh":-1};
var lp_WuZiDaLeiList={"sz":[],"xh":-1};
var lp_WuZiLeiBieList={"sz":[],"xh":-1};
var lp_XuQiuLeiBieList={"sz":[],"xh":-1};
var lp_XuQiuBiaoZhunList={"sz":[],"xh":-1};
var lp_XuQiuList={"sz":[],"xh":-1};
var lp_YuanGongList={"sz":[],"xh":-1};
var lp_ZiDianFenLeiList={"sz":[],"xh":-1};
var lp_ZiDianList={};
var curList;
//var curEventObjId;
var preEventObjData;

//事件
function setEvent(){
	
}

/********************************查询函数*********************************/
function lp_getData(url, upData, dataObj, funName){
	var s = $.ajax({
		url: url,
		data: upData,
		dataType: "json",
		type: "post",
		cache: false,
		error: function(msg, textStatus){ failureResp(msg.responseText); },
		success:function(json){ if(checkResult(json)){ lp_setData(dataObj, json, funName); } }
	});
}

function lp_setData(dataObj, json, funName){
	dataObj.sz=json.sz;
	dataObj.xh=-1;
	if ((funName===null)||(funName!==undefined)) funName(json);
}

//查询仓库
function lp_cxCangKu(funName){
	var json = {"jsonObj":"{}"};
	lp_getData("/ck/cxCk.do", json, lp_CangKuList, funName);
}

//查询组织工序
function lp_cxGangWei(fenLei, funName){
	var json = {"jsonObj":"{}"};
	lp_getData("/qy/cxGw.do", json, lp_GangWeiList, funName);
}

//查询组织工序
function lp_cxGongXu(fenLei, funName){
	var json = {"jsonObj":"{}"};
	lp_getData("/zn/cxGx.do", json, lp_GongXuList, funName);
}

//查询工序分类
function lp_cxGongXuFenLei(funName){
	var json = {"jsonObj":"{}"};
	lp_getData("/zn/cxGxfl.do", json, lp_GongXuFenLeiList, funName);
}

//查询工序模板
function lp_cxGongXuMoBan(funName){
	var json = {"jsonObj":"{}"};
	lp_getData("/zn/cxGxmb.do", json, lp_GongXuMoBanList, funName);
}

//查询供应商
function lp_cxGongYingShang(funName){
	//var json = {"jsonObj":"{}"};
	lp_getData("/gys/cxGys4Kit.do", null, lp_GongYingShangList, funName);
}

//查询我可管的仓库
function lp_cxKeGuanCangKu(funName){
	var json = {"jsonObj":"{}"};
	lp_getData("/ck/cxKgck.do", json, lp_KeGuanCangKuList, funName);
}

//查询客户
function lp_cxKeHu(keHuMingCheng, funName){
	var json = {"khmc":keHuMingCheng};
	json={"jsonObj":JSON.stringify(json)};
	lp_getData("/kh/cxKh.do", json, lp_KeHuList, funName);
}

//查询物资大类
function lp_cxWuZiDaLei(funName){
	var json = {"jsonObj":"{}"};
	lp_getData("/ck/cxWzdl.do", json, lp_WuZiDaLeiList, funName);
}

//查询物资类别
function lp_cxWuZiLeiBie(funName){
	var json = {"jsonObj":"{}"};
	lp_getData("/ck/cxWzlb.do", json, lp_WuZiLeiBieList, funName);
}

//查询物资字典
function lp_cxWuZiZiDian(funName){
	var json = {"jsonObj":"{}"};
	lp_getData("/ck/cxWzzd.do", json, lp_WuZiZiDianList, funName);
}

//查询需求
function lp_cxXuQiu(xuQiuMingCheng, funName){
	var json = {"mc":xuQiuMingCheng};
	json = {"jsonObj":JSON.stringify(json)};
	lp_getData("/zn/cxXq.do", json, lp_XuQiuList, funName);
}

//查询需求类别
function lp_cxXuQiuLeiBie(funName, dataObj){
	var data = dataObj ? dataObj : lp_XuQiuLeiBieList;
	var json = {"jsonObj":"{}"};
	lp_getData("/zn/cxXqlb.do", json, data, funName);
}

//查询需求模板
function lp_cxXuQiuMoBan(funName){
	var json = {"jsonObj":"{}"};
	lp_getData("/zn/cxXqmb.do", json, lp_XuQiuBiaoZhunList, funName);
}

//查询员工
function lp_cxYuanGong(funName, xingMing, dataObj){
	var json = {"xm":xingMing};
	json={"jsonObj":JSON.stringify(json)};
	lp_getData("/qy/cxQyyg.do", json, lp_YuanGongList, funName);
}

//查询组织字典
function lp_cxZhuZiZiDian(fenLei, funName){
	lp_ZiDianList[fenLei]={"sz":[],"xh":-1};
	var json = {"flbh":fenLei};
	json = {"jsonObj":JSON.stringify(json)};
	lp_getData("/zn/cxZzzd.do", json, lp_ZiDianList[fenLei], funName);
}

//查询字典分类
function lp_cxZiDianFenLei(funName){
	var json = {"jsonObj":"{}"};
	lp_getData("/zn/cxZdfl.do", json, lp_ZiDianFenLeiList, funName);
}