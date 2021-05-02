package com.vivers.model;

import java.sql.Timestamp;

public class CommentDTO {
	private int commentNum;
	private int boardNum;
	private String userId;
	private String commentContent;
	private Timestamp cmRegDate;
	private int likeCount;
	private int seq;
	private int parentCommentNum;
	private int ref;

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Timestamp getCmRegDate() {
		return cmRegDate;
	}

	public void setCmRegDate(Timestamp cmRegDate) {
		this.cmRegDate = cmRegDate;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getParentCommentNum() {
		return parentCommentNum;
	}

	public void setParentCommentNum(int parentCommentNum) {
		this.parentCommentNum = parentCommentNum;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}
}
