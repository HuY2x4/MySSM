package com.zucc.kcgl.model;

import java.util.Date;

public class equipment {
	private int equId;			//设备id
	private String name;	
	private String type;		//设备种类
	private String version;		//设备型号
	private Date inDate;		//最近一次入库时间
	private Date outDate;		//最近一次出库时间
	private int price;			//价格
	private String owner;		//拥有者
	private String chargePersion;//负责人
	private String remark;		//备注
	private String state;		//状态  
	private String failureState;//维修状态
	private String failureStateRemark;//故障备注
	public int getEquId() {
		return equId;
	}
	public void setEquId(int equId) {
		this.equId = equId;
	}
	public Date getInDate() {
		return inDate;
	}
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
	public Date getOutDate() {
		return outDate;
	}
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}
	public int getEquid() {
		return equId;
	}
	public void setEquid(int id) {
		this.equId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Date getIndata() {
		return inDate;
	}
	public void setIndata(Date indata) {
		this.inDate = indata;
	}
	public Date getOutdata() {
		return outDate;
	}
	public void setOutdata(Date outdata) {
		this.outDate = outdata;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getChargePersion() {
		return chargePersion;
	}
	public void setChargePersion(String chargePersion) {
		this.chargePersion = chargePersion;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getFailureState() {
		return failureState;
	}
	public void setFailureState(String failureState) {
		this.failureState = failureState;
	}
	public String getFailureStateReamrk() {
		return failureStateRemark;
	}
	public void setFailureStateReamrk(String failureStateReamrk) {
		this.failureStateRemark = failureStateReamrk;
	}

}
