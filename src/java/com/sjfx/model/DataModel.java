/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjfx.model;

import java.util.Date;

/**
 *
 * @author lxq
 */
public class DataModel {
    private String area;
    private String outDepartment;
	private String inpatientNumber;//住院号
    private String name;
    private String sex;
    private int age;
    private String outDiagnosis;//出院诊断
    private Date outDate;

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getOutDepartment() {
		return outDepartment;
	}

	public void setOutDepartment(String outDepartment) {
		this.outDepartment = outDepartment;
	}

	public String getInpatientNumber() {
		return inpatientNumber;
	}

	public void setInpatientNumber(String inpatientNumber) {
		this.inpatientNumber = inpatientNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getOutDiagnosis() {
		return outDiagnosis;
	}

	public void setOutDiagnosis(String outDiagnosis) {
		this.outDiagnosis = outDiagnosis;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

    
}
