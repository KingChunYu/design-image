package com.asus.gdpt;

import java.util.List;

public class TeamMain {

	private int id;
	private List<TeamItem> mTeamList;
	public TeamMain(int id, List<TeamItem> mTeamList) {
		this.id = id;
		this.mTeamList = mTeamList;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<TeamItem> getmTeamList() {
		return mTeamList;
	}
	public void setmTeamList(List<TeamItem> mTeamList) {
		this.mTeamList = mTeamList;
	}
	
	
}
