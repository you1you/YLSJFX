/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjfx.util;

/**
 *
 * @author lxq
 */
import com.opensymphony.xwork2.ActionSupport;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
 

public class SJFXAction extends ActionSupport  implements ServletRequestAware, ServletResponseAware
{
  protected Logger logger = Logger.getLogger(getClass());
  protected static String RELOGIN = "reLogin";
  protected static int MAXRESULTS = 15;
  protected static String JX_SHIBAI = "解析上传数据出错！";
  protected static String DATA_NONE = "缺少有效的提交数据！";
  protected HttpServletRequest request;
  protected HttpServletResponse response;
  protected String ygbh;
  protected boolean dengLu;
  protected boolean feiFa;
  protected JSONObject jsonObj;
  
  public void setServletRequest(HttpServletRequest request)
  {
    this.request = request;
    HttpSession session = request.getSession();
	this.ygbh = ((String)session.getAttribute("ygbh"));
	this.feiFa = (this.ygbh == null);
	this.dengLu = !feiFa;
    //this.feiFa = (this.dengLu = (this.qybh == null) || (this.ygbh == null) ? false : true);
  }
  
  public void setServletResponse(HttpServletResponse response)
  {
    this.response = response;
  }
  
  
  public void setJsonObj(String jsonObj)
  {
    try
    {
      this.jsonObj = JSONObject.fromObject(jsonObj);
    }
    catch (Exception ex)
    {
      this.jsonObj = null;
      this.logger.error(ex.getMessage());
    }
  }
  
  
  protected void renderJSON(JSONObject json)
  {
    this.response.setContentType("application/json; charset=utf-8");
    try
    {
      this.response.getWriter().write(json.toString());
    }
    catch (Exception ex)
    {
      this.logger.debug(ex.getMessage());
    }
    finally {}
  }
  
  public String reLogin()
  {
    JSONObject result = new JSONObject();
    result.put("result", Integer.valueOf(-99));
    renderJSON(result);
    return null;
  }
  
  protected String chuCuoFanHui(String fanHuiXinXi)
  {
    this.logger.error("error!!!");
    String parentMethod = java.lang.Thread.currentThread().getStackTrace()[2].getMethodName();
    this.logger.debug(parentMethod + fanHuiXinXi);
    JSONObject result = new JSONObject();
    result.put("result", Integer.valueOf(-1));
    result.put("msg", fanHuiXinXi);
    renderJSON(result);
    return null;
  }
  
  protected JSONObject getErrResult(String msg)
  {
    JSONObject json = new JSONObject();
    json.put("result", Integer.valueOf(-1));
    json.put("msg", msg);
    return json;
  }
}
