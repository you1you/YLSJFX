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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

public class CommonAction
  extends ActionSupport
  implements ServletRequestAware, ServletResponseAware
{
  protected HttpServletRequest request;
  protected HttpServletResponse response;
  
  public void setServletRequest(HttpServletRequest request)
  {
    this.request = request;
  }
  
  public void setServletResponse(HttpServletResponse response)
  {
    this.response = response;
  }
  
  public void logout()
  {
    this.request.getSession().invalidate();
    this.response.setStatus(301);
    this.response.setHeader("Location", "/DocMgmtSvr/");
  }
}
