/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjfx.action.keHu;

import com.sjfx.model.UserModel;
import com.sjfx.util.SJFXAction;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import net.sf.json.JSONObject;
import org.hibernate.Session;

/**
 *
 * @author lxq
 */ 

public class KeHuAction extends SJFXAction{
	private JSONObject jsonObj;
	private UserModel user;
	private JSONObject result;
	
	@Resource(name="KeHu")
	private KeHu kehu;
	
	public void cxKeHu(){
		PrintWriter out = null;
		try {
			JSONObject result = new JSONObject();
			jsonObj = JSONObject.fromObject(this.request.getParameter("jsonObj"));
//			result = kehu.cxKeHu(jsonObj.optString("xb"),jsonObj.optString("mc"),  jsonObj.optInt("yx"));
			out = this.response.getWriter();
			out.print(result.toString());//这句out.print是绝对不能少的
		} catch (Exception ex) {
			Logger.getLogger(KeHuAction.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			out.close();
		}
	}
	
	public void updateKeHu() throws IOException{
            String result1 = "";
                JSONObject result = new JSONObject();
		user = new UserModel();
		jsonObj = JSONObject.fromObject(this.request.getParameter("jsonObj"));
		user.setDlm(jsonObj.optString("dlm"));
		user.setPassword(jsonObj.optString("mm"));
		user.setSex(jsonObj.optString("xb"));
		user.setUserName(jsonObj.optString("mc"));
                user.setPhone(jsonObj.optString("lxfs"));
                user.setId(jsonObj.optString("id"));
		
		result1 = kehu.updateKeHu(user);
                result.put("result", result1);
                user = null;
		PrintWriter out = this.response.getWriter();
		out.print(result.toString());//这句out.print是绝对不能少的
		out.close();
	}
	
	public void addKeHu() throws IOException{
		JSONObject result = new JSONObject();
		user = new UserModel();
		jsonObj = JSONObject.fromObject(this.request.getParameter("jsonObj"));
		user.setDlm(jsonObj.optString("dlm"));
		user.setPassword(jsonObj.optString("mm"));
		user.setSex(jsonObj.optString("xb"));
		user.setUserName(jsonObj.optString("mc"));
                user.setPhone(jsonObj.optString("lxfs"));
		
		result = kehu.addKeHu(user);
                user = null;
		PrintWriter out = this.response.getWriter();
		out.print(result.toString());//这句out.print是绝对不能少的
		out.close();
	}
	
	public void delKeHu() throws IOException{
                JSONObject result = new JSONObject();
                String jieguo = "-1";
		System.out.println("删除客户");
                jsonObj = JSONObject.fromObject(this.request.getParameter("jsonObj"));
                jieguo = kehu.delKeHu(jsonObj.optString("id"));
                result.put("result", jieguo);
                PrintWriter out = this.response.getWriter();
		out.print(result.toString());//这句out.print是绝对不能少的
		out.close();
	}
}
