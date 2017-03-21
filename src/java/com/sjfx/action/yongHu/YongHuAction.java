/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjfx.action.yongHu;

import net.sf.json.JSONObject;
import com.sjfx.util.SJFXAction;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;

/**
 *
 * @author lulu
 */
@Controller
public class YongHuAction extends SJFXAction{
	@Resource(name="YongHuProcess")
	private YongHu yongHu;
	
	public String checkLogin() {
		System.out.println("111111111111111");
		String dlm = jsonObj.optString("dlm","");
		String mm = jsonObj.optString("mm","");
		
		 System.out.println(dlm);
		 System.out.println(mm);
		
		JSONObject result = yongHu.dengLu(dlm, mm, request.getSession());
		if (result.getInt("result")==0){
			result.put("url", "/yg/zm.do");
		}
		renderJSON(result);
//        return "sucess";
		//此处不可用return"sucess"，因为前面用了response。getWrite();这些相当于是已经使用了response返回，不可再用，否则会报错
		return null;
	}

	public String zhuoMian(){
		return feiFa ? RELOGIN : SUCCESS;
	}
	
	public String hengTiao(){
		return feiFa ? RELOGIN : SUCCESS;
	}
	
	public String caiDan(){
		return feiFa ? RELOGIN : SUCCESS;
	}

	public String yuanGong(){
		return feiFa ? LOGIN : SUCCESS;
	}
}
