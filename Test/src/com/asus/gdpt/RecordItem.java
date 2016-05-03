package com.asus.gdpt;

public class RecordItem {

	private int id;
	private String dormitoryNum;
	private String account;
	private String content;

	public RecordItem(int id, String dormitoryNum, String account, String content) {
		this.id = id;
		this.dormitoryNum = dormitoryNum;
		this.account = account;
		this.content = content;
	}

	public String getDormitoryNum() {
		return dormitoryNum;
	}

	public void setDormitoryNum(String dormitoryNum) {
		this.dormitoryNum = dormitoryNum;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
