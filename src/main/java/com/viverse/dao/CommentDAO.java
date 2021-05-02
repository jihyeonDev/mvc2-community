package com.vivers.dao;

import com.vivers.db.DBConnect;
import com.vivers.model.CommentDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    private static CommentDAO instance = new CommentDAO();
    private CommentDAO() { }
    public static CommentDAO getInstance() { return instance; }

    // 댓글 쓰기
    public int insertComment(CommentDTO commentDTO) {
        int result = 0;

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnect.getConnection();

            String sql = "INSERT INTO comment(board_num, user_id, comment_content, cm_reg_date, like_count, seq, parent_comment_num ) " +
                    " VALUES(?, ?, ?, SYSDATE(), ?, ?, ? ) ";
            ps = con.prepareStatement(sql);

            ps.setInt(1, commentDTO.getBoardNum());
            ps.setString(2, commentDTO.getUserId());
            ps.setString(3, commentDTO.getCommentContent());
            ps.setInt(4, commentDTO.getLikeCount());
            ps.setInt(5, commentDTO.getSeq());
            ps.setInt(6, commentDTO.getParentCommentNum());

            result = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeDB(null, ps, con);
        }

        return result;
    }

    // Get 댓글 상세
    public CommentDTO selectCommentOne(int commentNum) {
        CommentDTO commentDTO = new CommentDTO();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnect.getConnection();

            String sql = "SELECT * FROM comment WHERE comment_num=? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, commentNum);

            rs = ps.executeQuery();
            if(rs.next()) {
                commentDTO.setCommentNum(rs.getInt("comment_num"));
                commentDTO.setBoardNum(rs.getInt("board_num"));
                commentDTO.setUserId(rs.getString("user_id"));
                commentDTO.setCommentContent(rs.getString("comment_content"));
                commentDTO.setCmRegDate(rs.getTimestamp("cm_reg_date"));
                commentDTO.setLikeCount(rs.getInt("like_count"));
                commentDTO.setSeq(rs.getInt("seq"));
                commentDTO.setParentCommentNum(rs.getInt("parent_comment_num"));
            } else { }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeDB(rs, ps, con);
        }

        return commentDTO;
    }

    // Get 댓글 List
    public List<CommentDTO> selectCommentList(int parentCommentNum) {
        List<CommentDTO> commentDTOList = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnect.getConnection();
            String sql = "";
            sql = "SELECT * FROM comment ORDER BY comment_num AND ref ";
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setCommentNum(rs.getInt("comment_num"));
                commentDTO.setBoardNum(rs.getInt("board_num"));
                commentDTO.setUserId(rs.getString("user_id"));
                commentDTO.setCommentContent(rs.getString("comment_content"));
                commentDTO.setCmRegDate(rs.getTimestamp("cm_reg_date"));
                commentDTO.setLikeCount(rs.getInt("like_count"));
                commentDTO.setSeq(rs.getInt("seq"));
                commentDTO.setParentCommentNum(rs.getInt("parent_comment_num"));
                commentDTO.setParentCommentNum(rs.getInt("ref"));

                commentDTOList.add(commentDTO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeDB(rs, ps, con);
        }

        return commentDTOList;
    }

    // delete 댓글
    public int deleteComment(int commentNum){
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnect.getConnection();
            String sql = "DELETE FROM comment WHERE comment_num=? ";
            ps = con.prepareStatement(sql);
            result = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeDB(null, ps, con);
        }
        return result;
    }

    // update 댓글
    public int updateComment(CommentDTO commentDTO) {
        int result = 0;

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnect.getConnection();

            String sql = "UPDATE comment SET comment_content=? WHERE comment_num=? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, commentDTO.getCommentContent());
            ps.setInt(2, commentDTO.getCommentNum());

            result = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeDB(null, ps, con);
        }

        return result;
    }

    // like count Up
    public int updateLikeCount(int commentNum) {
        int result = 0;

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnect.getConnection();

            String sql = "UPDATE comment SET like_count=like_count+1 WHERE comment_num=? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, commentNum);

            result = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeDB(null, ps, con);
        }

        return result;
    }
}
