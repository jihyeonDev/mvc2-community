package com.vivers.dao;

import com.vivers.db.DBConnect;
import com.vivers.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
	private static BoardDAO instance = new BoardDAO();
	private BoardDAO() { }
	public static BoardDAO getInstance() {
		return instance;
	}
	
	// 글쓰기
	public int insert(BoardDTO boardDTO) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBConnect.getConnection();

			String sql = "INSERT INTO board(user_id, board_type, secret_post, report_count, content, " +
					" reg_date, read_count, like_count, share_count, artfile ) " +
					" VALUES(?, ?, ?, ?, ?, SYSDATE(), ?, ?, ?, ?) ";
			ps = con.prepareStatement(sql);

			ps.setString(1, boardDTO.getUserId());
			ps.setString(2, boardDTO.getBoardType());
			ps.setString(3, boardDTO.getSecretPost());
			ps.setInt(4, boardDTO.getReportCount());
			ps.setString(5, boardDTO.getContent());
			ps.setInt(6, boardDTO.getReadCount());
			ps.setInt(7, boardDTO.getLikeCount());
			ps.setInt(8, boardDTO.getShareCount());
			ps.setString(9, boardDTO.getArtFile());

			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeDB(null, ps, con);
		}
		
		return result;
	}

	// 파일 첨부
	public int insertFile(String file) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBConnect.getConnection();

			String sql = "INSERT INTO file(board_num, file) VALUES(LAST_INSERT_ID(), ?) ";
			ps = con.prepareStatement(sql);
			ps.setString(1, file);

			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeDB(null, ps, con);
		}

		return result;
	}

	// 총 데이터 개수 구하기
	public int getPostCount() {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBConnect.getConnection();

			String sql = "SELECT COUNT(*) FROM board ";
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeDB(rs, ps, con);
		}

		return result;
	}


	// 글 1개 정보 구하기
	public BoardDTO getPost(int boardNum) {
		BoardDTO dto = new BoardDTO();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBConnect.getConnection();
			String sql = "SELECT * FROM board WHERE board_num=? ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, boardNum);
			rs = ps.executeQuery();

			if(rs.next()) {
				dto.setBoardNum(rs.getInt("board_num"));
				dto.setUserId(rs.getString("user_id"));
				dto.setBoardType(rs.getString("board_type"));
				dto.setSecretPost(rs.getString("secret_post"));
				dto.setReportCount(rs.getInt("report_count"));
				dto.setContent(rs.getString("content"));
				dto.setRegDate(rs.getTimestamp("reg_date"));
				dto.setReadCount(rs.getInt("read_count"));
				dto.setLikeCount(rs.getInt("like_count"));
				dto.setShareCount(rs.getInt("share_count"));
				dto.setArtFile(rs.getString("artfile"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeDB(rs, ps, con);
		}

		return dto;
	}

	// 첨부파일 구하기
	public FileDTO getFile(int boardNum) {
		FileDTO fileDTO = new FileDTO();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBConnect.getConnection();

			String sql = "SELECT * FROM file WHERE board_num=? ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, boardNum);
			rs = ps.executeQuery();

			if(rs.next()) {
				fileDTO.setBoardNum(rs.getInt("board_num"));
				fileDTO.setFile(rs.getString("file"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeDB(rs, ps, con);
		}

		return fileDTO;
	}

	// 첨부파일 목록 구하기
	public List<FileDTO> selectFileList() {
		List<FileDTO> fileDTOList = new ArrayList<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBConnect.getConnection();

			String sql = "SELECT * FROM file ";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while(rs.next()) {
				FileDTO fileDTO = new FileDTO();
				fileDTO.setBoardNum(rs.getInt("board_num"));
				fileDTO.setFile(rs.getString("file"));

				fileDTOList.add(fileDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeDB(rs, ps, con);
		}

		return fileDTOList;
	}

	// feed list 글 목록 구하기
	public List<BoardDTO> getPostList(int start, int end) {
		List<BoardDTO> list = new ArrayList<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBConnect.getConnection();

			String sql = "SELECT * FROM board ORDER BY board_num DESC LIMIT ?, ? ";
			ps = con.prepareStatement(sql);

			ps.setInt(1, start);
			ps.setInt(2, end);
			rs = ps.executeQuery();

			while(rs.next()) {
				BoardDTO boardDTO = new BoardDTO();

				boardDTO.setBoardNum(rs.getInt("board_num"));
				boardDTO.setUserId(rs.getString("user_id"));
				boardDTO.setBoardType(rs.getString("board_type"));
				boardDTO.setSecretPost(rs.getString("secret_post"));
				boardDTO.setReportCount(rs.getInt("report_count"));
				boardDTO.setContent(rs.getString("content"));
				boardDTO.setRegDate(rs.getTimestamp("reg_date"));
				boardDTO.setReadCount(rs.getInt("read_count"));
				boardDTO.setLikeCount(rs.getInt("like_count"));
				boardDTO.setShareCount(rs.getInt("share_count"));
				boardDTO.setArtFile(rs.getString("artfile"));

				list.add(boardDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeDB(rs, ps, con);
		}

		return list;
	}

	// artfeed list 글 목록 구하기
	public List<BoardDTO> getartfeedList(int start, int end) {
		List<BoardDTO> list = new ArrayList<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBConnect.getConnection();

			String sql = "SELECT * FROM board WHERE board_type='1' ORDER BY board_num DESC LIMIT ?, ? ";
			ps = con.prepareStatement(sql);

			ps.setInt(1, start);
			ps.setInt(2, end);

			rs = ps.executeQuery();

			while(rs.next()) {
				BoardDTO boardDTO = new BoardDTO();

				boardDTO.setBoardNum(rs.getInt("board_num"));
				boardDTO.setUserId(rs.getString("user_id"));
				boardDTO.setBoardType(rs.getString("board_type"));
				boardDTO.setSecretPost(rs.getString("secret_post"));
				boardDTO.setReportCount(rs.getInt("report_count"));
				boardDTO.setContent(rs.getString("content"));
				boardDTO.setRegDate(rs.getTimestamp("reg_date"));
				boardDTO.setReadCount(rs.getInt("read_count"));
				boardDTO.setLikeCount(rs.getInt("like_count"));
				boardDTO.setShareCount(rs.getInt("share_count"));
				boardDTO.setArtFile(rs.getString("artfile"));

				list.add(boardDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeDB(rs, ps, con);
		}

		return list;
	}

	// myfeed list 글 목록 구하기
	public List<BoardDTO> getmyfeedList(int start, int end, String userId) {
		List<BoardDTO> list = new ArrayList<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBConnect.getConnection();

			String sql = "SELECT * FROM board WHERE user_id=? ORDER BY board_num DESC LIMIT ?, ? ";
			ps = con.prepareStatement(sql);

			ps.setString(1, userId);
			ps.setInt(2, start);
			ps.setInt(3, end);

			rs = ps.executeQuery();

			while(rs.next()) {
				BoardDTO boardDTO = new BoardDTO();

				boardDTO.setBoardNum(rs.getInt("board_num"));
				boardDTO.setUserId(rs.getString("user_id"));
				boardDTO.setBoardType(rs.getString("board_type"));
				boardDTO.setSecretPost(rs.getString("secret_post"));
				boardDTO.setReportCount(rs.getInt("report_count"));
				boardDTO.setContent(rs.getString("content"));
				boardDTO.setRegDate(rs.getTimestamp("reg_date"));
				boardDTO.setReadCount(rs.getInt("read_count"));
				boardDTO.setLikeCount(rs.getInt("like_count"));
				boardDTO.setShareCount(rs.getInt("share_count"));
				boardDTO.setArtFile(rs.getString("artfile"));

				list.add(boardDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeDB(rs, ps, con);
		}

		return list;
	}

	// 글 수정
	public int updatePost(BoardDTO boardDTO, FileDTO fileDTO) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBConnect.getConnection();

			String sql = "UPDATE board SET board_type=?, secret_post=?, content=? WHERE board_num=? ";
			ps = con.prepareStatement(sql);
			ps.setString(1, boardDTO.getBoardType());
			ps.setString(2, boardDTO.getSecretPost());
			ps.setString(3, boardDTO.getContent());
			ps.setInt(4, boardDTO.getBoardNum());
			result = ps.executeUpdate();

			sql = "UPDATE file SET file=? WHERE board_num=? ";
			ps = con.prepareStatement(sql);
			ps.setString(1, fileDTO.getFile());
			ps.setInt(2, fileDTO.getBoardNum());
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeDB(null, ps, con);
		}

		return result;
	}
	
	// 글 삭제
	public int deletePost(int boardNum) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBConnect.getConnection();

			String sql = "DELETE FROM board WHERE board_num=? ";
			ps = con.prepareStatement(sql);

			ps.setInt(1, boardNum);
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeDB(null, ps, con);
		}

		return result;
	}

	// 조회수 증가
	public void readCountUp(int boardNum) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBConnect.getConnection();

			String sql = "UPDATE board SET read_count=read_count+1 WHERE board_num=? ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, boardNum);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeDB(null, ps, con);
		}
	}

	// get my 좋아요 글
	public MyLikeBoardDTO getMyLikeBoard(String userId, int boardNum) {
		MyLikeBoardDTO myLikeBoardDTO = new MyLikeBoardDTO();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBConnect.getConnection();

			String sql = "SELECT * FROM mylike_board WHERE user_id=? AND board_num=? ";
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setInt(2, boardNum);

			rs = ps.executeQuery();
			if(rs.next()) {
				myLikeBoardDTO.setUserId(rs.getString("user_id"));
				myLikeBoardDTO.setBoardNum(rs.getInt("board_num"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeDB(rs, ps, con);
		}

		return myLikeBoardDTO;
	}

	// select 좋아요 리스트
	public List<MyLikeBoardDTO> selectMyLikeBoardList() {
		List<MyLikeBoardDTO> myLikeBoardDTOList = new ArrayList<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBConnect.getConnection();

			String sql = "SELECT * FROM mylike_board ";
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();
			while(rs.next()) {
				MyLikeBoardDTO myLikeBoardDTO = new MyLikeBoardDTO();

				myLikeBoardDTO.setUserId(rs.getString("user_id"));
				myLikeBoardDTO.setBoardNum(rs.getInt("board_num"));
				myLikeBoardDTO.setMyLikeBoardNum(rs.getInt("mylike_board_num"));

				myLikeBoardDTOList.add(myLikeBoardDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeDB(rs, ps, con);
		}

		return myLikeBoardDTOList;
	}

	// insert my 좋아요글
	public int insertMyLikeBoard(String userId, int boardNum) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBConnect.getConnection();

			System.out.println(userId);
			System.out.println(boardNum);
			// board 좋아요 수 증가
			String sql = "UPDATE board SET like_count=like_count+1 WHERE board_num=? ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, boardNum);
			result = ps.executeUpdate();
			if(result == 1) {
				System.out.println("좋아요 수 증가 성공");
			} else {
				System.out.println("종아요 수 증가 실패");
			}

			// 내 좋아요 테이블에 추가
			String sql2 = "INSERT INTO mylike_board(user_id, board_num) VALUES(?, ?) ";
			ps = con.prepareStatement(sql2);
			ps.setString(1, userId);
			ps.setInt(2, boardNum);
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeDB(null, ps, con);
		}

		return result;
	}

	// insert like list
	public int insertLikeList(LikeListDTO likeListDTO) {
		int result = 0;

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBConnect.getConnection();

			String sql = "INSERT INTO like_list(user_id, board_num, comment_num, like_reg_date, status ) VALUES(?, ?, ?, SYSDATE(), ? ) ";
			ps = con.prepareStatement(sql);

			ps.setString(1, likeListDTO.getUserId());
			ps.setInt(2, likeListDTO.getBoardNum());
			ps.setInt(3, likeListDTO.getCommentNum());
			ps.setString(4, likeListDTO.getStatus());

			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeDB(null, ps, con);
		}

		return result;
	}

	// delete my 좋아요글
	public int deleteMyLikeBoard(String userId, int boardNum) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBConnect.getConnection();

			// board 좋아요 수 감소
			String sql = "UPDATE board SET like_count=like_count-1 WHERE board_num=? ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, boardNum);
			ps.executeUpdate();

			// 내 좋아요 테이블에서 삭제
			String sql2 = "DELETE FROM mylike_board WHERE user_id=? AND board_num=? ";
			ps = con.prepareStatement(sql2);
			ps.setString(1, userId);
			ps.setInt(2, boardNum);
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeDB(null, ps, con);
		}

		return result;
	}

	// get my 북마크글
	public MyBookmarkDTO getMyBookmarkBoard(String userId, int boardNum) {
		MyBookmarkDTO myBookmark = new MyBookmarkDTO();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBConnect.getConnection();

			String sql = "SELECT * FROM mybookmark_board WHERE user_id=? AND board_num=? ";
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setInt(2, boardNum);

			rs = ps.executeQuery();
			if(rs.next()) {
				myBookmark.setUserId(rs.getString("user_id"));
				myBookmark.setBoardNum(rs.getInt("board_num"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeDB(rs, ps, con);
		}

		return myBookmark;
	}

	// select 북마크 리스트
	public List<MyBookmarkDTO> selectMyBookmarkList() {
		List<MyBookmarkDTO> myBookmarkList = new ArrayList<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBConnect.getConnection();

			String sql = "SELECT * FROM mybookmark_board ";
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();
			while(rs.next()) {
				MyBookmarkDTO myBookmarkDTO = new MyBookmarkDTO();
				myBookmarkDTO.setUserId(rs.getString("user_id"));
				myBookmarkDTO.setBoardNum(rs.getInt("board_num"));

				myBookmarkList.add(myBookmarkDTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeDB(rs, ps, con);
		}

		return myBookmarkList;
	}

	// insert my북마크글
	public int insertMyBookmarkBoard(String userId, int boardNum) {
		int result = 0;

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBConnect.getConnection();

			String sql = "INSERT INTO mybookmark_board(user_id, board_num) VALUES(?, ?) ";
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setInt(2, boardNum);

			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeDB(null, ps, con);
		}

		return  result;
	}

	// delete my북마크글
	public int deleteMyBookmarkBoard(String userId, int boardNum) {
		int result = 0;

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBConnect.getConnection();

			String sql = "DELETE FROM mybookmark_board WHERE user_id=? AND board_num=? ";
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setInt(2, boardNum);

			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeDB(null, ps, con);
		}

		return result;
	}

	// 글 공유 수 증가
	public int shareCountUp(int boardNum) {
		int result = 0;

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBConnect.getConnection();

			String sql = "UPDATE board SET share_count=share_count+1 WHERE board_num=? ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, boardNum);

			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeDB(null, ps, con);
		}

		return result;
	}

	// 글 신고 기능
	public int reportPost(int boardNum) {
		int result = 0;

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBConnect.getConnection();

			String sql = "UPDATE board SET report_count=report_count+1 WHERE board_num=? ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, boardNum);

			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeDB(null, ps, con);
		}

		return result;
	}

	// 태그 insert
	public int insertTag(String tagName) {
		int result = 0;

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBConnect.getConnection();

			String sql = "INSERT INTO tag VALUE (LAST_INSERT_ID(), ? ) ";
			ps = con.prepareStatement(sql);
			ps.setString(1, tagName);

			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeDB(null, ps, con);
		}

		return result;
	}

	// 태그 get
	public TagDTO selectTag(int boardNum) {
		TagDTO tagDTO = new TagDTO();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBConnect.getConnection();

			String sql = "SELECT * FROM tag WHERE board_num=? ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, boardNum);

			rs = ps.executeQuery();
			if(rs.next()) {
				tagDTO.setBoardNum(rs.getInt("board_num"));
				tagDTO.setTagName(rs.getString("tag_name"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeDB(rs, ps, con);
		}
		return tagDTO;
	}

	// select 태그 리스트
	public List<TagDTO> selectTagList() {
		List<TagDTO> tagDTOList = new ArrayList<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBConnect.getConnection();

			String sql = "SELECT * FROM tag ";
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				TagDTO tagDTO = new TagDTO();
				tagDTO.setBoardNum(rs.getInt("board_num"));
				tagDTO.setTagName(rs.getString("tag_name"));

				tagDTOList.add(tagDTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeDB(rs, ps, con);
		}

		return tagDTOList;
	}

	// 태그 delete (원본 글 삭제되면 삭제)
	public int deleteTag(int boardNum) {
		int result = 0;

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBConnect.getConnection();

			String sql = "DELETE FROM tag WHERE board_num=? ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, boardNum);

			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeDB(null, ps, con);
		}

		return result;
	}

	// search by 글 내용
	public List<BoardDTO> boardSearchByContent(String content, int start, int end, String type) {
		List<BoardDTO> boardDTOList = new ArrayList<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBConnect.getConnection();

			String sql = "";
			if(type.equals("tag")) {
				sql = "select * from board where board_num=(select board_num from tag where tag_name like CONCAT('%',?,'%')) ORDER BY board_num DESC LIMIT ?, ? ";

			} else if(type.equals("aka")) {
				sql = "SELECT * FROM board WHERE user_id LIKE CONCAT('%',?,'%') ORDER BY board_num DESC LIMIT ?, ? "; // 아이디로 먼저 테스트

			} else {
				sql = "SELECT * FROM board WHERE content LIKE CONCAT('%',?,'%') ORDER BY board_num DESC LIMIT ?, ? ";
			}

			ps = con.prepareStatement(sql);
			ps.setString(1, content);
			ps.setInt(2, start);
			ps.setInt(3, end);

			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setBoardNum(rs.getInt("board_num"));
				boardDTO.setUserId(rs.getString("user_id"));
				boardDTO.setBoardType(rs.getString("board_type"));
				boardDTO.setSecretPost(rs.getString("secret_post"));
				boardDTO.setReportCount(rs.getInt("report_count"));
				boardDTO.setContent(rs.getString("content"));
				boardDTO.setRegDate(rs.getTimestamp("reg_date"));
				boardDTO.setReadCount(rs.getInt("read_count"));
				boardDTO.setLikeCount(rs.getInt("like_count"));
				boardDTO.setShareCount(rs.getInt("share_count"));
				boardDTO.setArtFile(rs.getString("artfile"));

				boardDTOList.add(boardDTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeDB(rs, ps, con);
		}

		return boardDTOList;
	}

	// select 인기 태그 리스트
	public List<TagDTO> popularTagList() {
		List<TagDTO> tagDTOList = new ArrayList<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBConnect.getConnection();

			String sql = "SELECT tag_name FROM (SELECT t.tag_name, b.like_count, b.read_count, b.share_count  FROM board b, tag t WHERE b.board_num=t.board_num AND " +
					" b.reg_date > DATE_ADD(NOW(), INTERVAL -7 DAY ) GROUP BY b.board_num) " +
					" AS t ORDER BY t.like_count DESC, t.read_count DESC, t.share_count DESC LIMIT 0, 10 ";
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();
			while(rs.next()) {
				TagDTO tagDTO = new TagDTO();
				tagDTO.setTagName(rs.getString("tag_name"));

				tagDTOList.add(tagDTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeDB(rs, ps, con);
		}

		return tagDTOList;
	}

}
