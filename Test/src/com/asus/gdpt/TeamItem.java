package com.asus.gdpt;

public class TeamItem {

 public int id;
 public String iconUrl;
 public String actionUrl;
 public String title;
public TeamItem(int id, String iconUrl, String actionUrl, String title) {
	this.id = id;
	this.iconUrl = iconUrl;
	this.actionUrl = actionUrl;
	this.title = title;
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
public String getActionUrl() {
	return actionUrl;
}
public void setActionUrl(String actionUrl) {
	this.actionUrl = actionUrl;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
 
 
 
	
}
