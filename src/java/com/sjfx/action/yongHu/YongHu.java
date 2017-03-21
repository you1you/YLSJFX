/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjfx.action.yongHu;

import com.sjfx.model.UserModel;
import com.sjfx.util.ShuJuFX;
import java.util.List;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 *
 * @author lxq
 */

@Service("YongHuProcess")
@SuppressWarnings("unchecked")
public class YongHu extends ShuJuFX{
	public JSONObject dengLu(String dlm, String mm, HttpSession session) {
		String hql = "from UserModel where dlm='" + dlm + "' and password='" + mm + "'";
		List<UserModel> dlList = dao.find(hql);
		JSONObject json = new JSONObject();

		if (dlList.size() > 0) {
			UserModel dl = dlList.get(0);
			hql = "from UserModel where id='" + dl.getId()+ "'";
			List<UserModel> ygList = dao.find(hql);
			if (ygList.size() > 0) {
				UserModel yg = (UserModel) ygList.get(0);
				session.setAttribute("sex", yg.getSex());
				session.setAttribute("dlm", dlm);
				session.setAttribute("ygbh", yg.getId());
				json.put("result", 0);
			} else {
				json.put("result", -2);
				json.put("msg", "没有相关的员工记录！");
			}
		} else {
			json.put("result", -1);
			json.put("msg", "用户名和密码不正确！");
		}
		return json;
	}
}
