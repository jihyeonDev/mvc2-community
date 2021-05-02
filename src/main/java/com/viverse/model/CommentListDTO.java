package com.vivers.model;

import java.sql.Timestamp;

public class CommentListDTO {
    private String userId;
    private int boardNum;
    private int commentNum;
    private Timestamp commentRegDate;
    private String status;

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

    public Timestamp getCommentRegDate() {
        return commentRegDate;
    }

    public void setCommentRegDate(Timestamp commentRegDate) {
        this.commentRegDate = commentRegDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
