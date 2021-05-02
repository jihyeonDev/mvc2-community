package com.vivers.model;

import java.sql.Timestamp;

public class LikeListDTO {
    private String userId;
    private int boardNum;
    private int commentNum;
    private Timestamp likeRegDate;
    private String status;
    private int likeListNum;

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

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public Timestamp getLikeRegDate() {
        return likeRegDate;
    }

    public void setLikeRegDate(Timestamp likeRegDate) {
        this.likeRegDate = likeRegDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLikeListNum() {
        return likeListNum;
    }

    public void setLikeListNum(int likeListNum) {
        this.likeListNum = likeListNum;
    }
}
