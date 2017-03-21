///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.sjfx.action;
//
////import org.springframework.web.struts.ActionSupport;
//import com.opensymphony.xwork2.ActionSupport;
//import com.sjfx.domain.User;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.Map;
//import net.sf.json.JSONObject;
//import org.apache.struts2.ServletActionContext;
//import org.apache.struts2.json.annotations.JSON;
//
///**
// *
// * @author lxq
// */
//public class UploadFile extends ActionSupport {
//
//	private static final long serialVersionUID = 1L;
//
//	private Map<String, Object> dataMap;
//	private String key = "Just see see";
//	private File file;
//	private int total;
//	private String name;
//	private int index;
//
//	@JSON(serialize = false)
//	public File getFile() {
//		return file;
//	}
//
//	public void setFile(File file) {
//		this.file = file;
//	}
//
//	@JSON(serialize = false)
//	public int getTotal() {
//		return total;
//	}
//
//	public void setTotal(int total) {
//		this.total = total;
//	}
//
//	@JSON(serialize = false)
//	public String getName() {
//		return name;
//	}
//
//	@JSON(serialize = false)
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public int getIndex() {
//		return index;
//	}
//
//	public void setIndex(int index) {
//		this.index = index;
//	}
//
//	@Override
//	public String execute() throws Exception {
//		JSONObject json = new JSONObject();
////		System.out.println("com.sjfx.action.UploadFile.execute()");
//		System.out.println(this.name);
//		System.out.println(file.getName());
//		System.out.println(this.index);
//
//		String root = ServletActionContext.getServletContext().getRealPath("/upload");
//		InputStream is = new FileInputStream(file);
//		OutputStream os = new FileOutputStream(new File(root, file.getName()));
//
//		byte[] buffer = new byte[500];
//		int length = 0;
//		while (-1 != (length = is.read(buffer, 0, buffer.length))) {
//			os.write(buffer);
//		}
//		os.close();
//		is.close();
//
//		dataMap = new HashMap<String, Object>();  
////        User user = new User();  
////        user.setUserName("张三");  
////        user.setPassword("123");  
////        dataMap.put("user", user);  
//        // 放入一个是否操作成功的标识  
//        dataMap.put("result", 0);  
//        // 返回结果  
//        return SUCCESS;  
//	}
//
//	public Map<String, Object> getDataMap() {
//		return dataMap;
//	}
//
//	//设置key属性不作为json的内容返回  
//	@JSON(serialize = false)
//	public String getKey() {
//		return key;
//	}
//}
