package com.asus.gdpt;

import java.util.List;

public class RecordMain {
	private int id;
    private List<RecordItem> mListData;

    public RecordMain(int id, List<RecordItem> mListData) {
        this.id = id;
        this.mListData = mListData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<RecordItem> getmListData() {
        return mListData;
    }

    public void setmListData(List<RecordItem> mListData) {
        this.mListData = mListData;
    }
}
