package com.vivers.model;

import java.sql.Timestamp;

public class BoardDTO {
	private int boardNum; // AUTO_INCREMENT
	private String userId;
	private String boardType; // 0-일반글 / 1-그림글
	private String secretPost; // 0-전체공개 / 1-비공개
	private int  reportCount;
	private String content;
	private Timestamp regDate; // DEFAULT CURRENT_TIMESTAMP
	private int readCount; // 0
	private int likeCount; // 0
	private int shareCount; // 0
	private String artFile;

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

	public String getBoardType() {
		return boardType;
	}

	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}

	public String getSecretPost() {
		return secretPost;
	}

	public void setSecretPost(String secretPost) {
		this.secretPost = secretPost;
	}

	public int getReportCount() {
		return reportCount;
	}

	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getShareCount() {
		return shareCount;
	}

	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}

	public String getArtFile() {
		return artFile;
	}

	public void setArtFile(String artFile) {
		this.artFile = artFile;
	}
}
