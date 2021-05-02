package com.vivers.model;

import java.sql.Timestamp;

public class TagDTO {
    private int boardNum;
    private String tagName;

    public int getBoardNum() {
        return boardNum;
    }

    public void setBoardNum(int boardNum) {
        this.boardNum = boardNum;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
