package com.asus.gdpt;

public class SettingItem {
	
	public int id;
	public String iconUrl;
	public String detail;
	public SettingItem(int int1, String string, String string2) {
		// TODO Auto-generated constructor stub
		this.id = int1;
		this.iconUrl = string;
		this.detail = string2;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
