package com.vivers.model;

public class MyLikeBoardDTO {
    private String userId;
    private int boardNum;
    private int myLikeBoardNum;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getBoardNum() {
        return boardNum;
    }

    public void setBoardNum(int boardNum) {
        this.boardNum = boardNum;
    }

    public int getMyLikeBoardNum() {
        return myLikeBoardNum;
    }

    public void setMyLikeBoardNum(int myLikeBoardNum) {
        this.myLikeBoardNum = myLikeBoardNum;
    }
}
