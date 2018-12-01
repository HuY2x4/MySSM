package com.zucc.kcgl.model;

import java.util.Date;
import java.util.List;

public class Equipment {
	private int equId;
	private String equName;
	private String type;
	private String version;
	private Date inDate;
	private Date outDate;
	private int price;
	private String owner;
	private String manager;
	private String remark;
	private String state;
	private String img;
	private List<Application> listApplication;
	private List<EquRecord> listEquRecorf;
	private List<SucApplication> listSucApplication;
	public int getEquId() {
		return equId;
	}
	public void setEquId(int equId) {
		this.equId = equId;
	}
	public String getEquName() {
		return equName;
	}
	public void setEquName(String equName) {
		this.equName = equName;
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
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public List<Application> getListApplication() {
		return listApplication;
	}
	public void setListApplication(List<Application> listApplication) {
		this.listApplication = listApplication;
	}
	public List<EquRecord> getListEquRecorf() {
		return listEquRecorf;
	}
	public void setListEquRecorf(List<EquRecord> listEquRecorf) {
		this.listEquRecorf = listEquRecorf;
	}
	public List<SucApplication> getListSucApplication() {
		return listSucApplication;
	}
	public void setListSucApplication(List<SucApplication> listSucApplication) {
		this.listSucApplication = listSucApplication;
	}
	@Override
	public String toString() {
		return "Equipment [equId=" + equId + ", equName=" + equName + ", type="
				+ type + ", version=" + version + ", inDate=" + inDate
				+ ", outDate=" + outDate + ", price=" + price + ", owner="
				+ owner + ", manager=" + manager + ", remark=" + remark
				+ ", state=" + state + ", img=" + img + ", listApplication="
				+ listApplication + ", listEquRecorf=" + listEquRecorf
				+ ", listSucApplication=" + listSucApplication + "]";
	}
	
	
}
