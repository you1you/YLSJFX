/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjfx.action.keHu;

//import com.opensymphony.xwork2.ActionSupport;
import com.sjfx.model.UserModel;
import com.sjfx.util.ShuJuFX;
import com.sjfx.util.sjfxDB;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;

/**
 *
 * @author lxq
 */
//@Controller("KeHu")
public class KeHu extends ShuJuFX {
	public JSONObject addKeHu(UserModel user) {
		JSONObject result = new JSONObject();
		int result1 = -1;
		try {
			dao.insert(user);
			result.put("result", 0);
		} catch (Exception e) {
			result.put("result", -1);
			result.put("msg", "增加员工出错！");
		}
		return result;
	}

	public String updateKeHu(UserModel user) {
		System.out.println("修改客户");
//                if(dao.update(user))
                    return "0";
//                else return "-1";
		
	}

	public String delKeHu(String id) {
//		dao.deleteObjById("user", id);
		return "0";
	}

//	public JSONObject cxKeHu(String xb, String xm, int yx) {
//            
//		String hql = "from User where dlm='"+xm+"' and sex='"+xb+"'order by dlm";
//                String fyHql = "from User where dlm='"+xm+"' and sex='"+xb+"'";
//		JSONObject result = new JSONObject();
//		JSONArray array = new JSONArray();
//		int zys = -1;
//                if(xm.equals("")){
//                    hql = "from User where sex='"+xb+"' order by dlm";
//                    fyHql ="from User where sex='"+xb+"'";
//                }else if(xm.equals("")&& xb.equals("")){
//                    hql = "from User";
//                    fyHql ="from User ";
//                }
//                
//		List<UserModel> eList = sjfxDB.getResult(hql, null);
//		for (UserModel e : eList) {
//			Map<String, Object> map = new HashMap();
//			map.put("id", e.getId());
//                        map.put("mm", e.getPassword());
//			map.put("xm", e.getUserName());
//			map.put("xb",e.getSex());
//			map.put("lxfs",e.getPhone());
//                        map.put("dlm", e.getDlm());
//			array.add(map);
//		}
//		
//		result = sjfxDB.getPages(fyHql, yx);
//		result.put("result", 0);
//		result.put("sz", array);
//		return result;
//	}

}
