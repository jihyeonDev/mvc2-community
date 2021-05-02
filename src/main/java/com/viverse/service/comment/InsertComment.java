package com.vivers.service.comment;

import com.vivers.dao.CommentDAO;
import com.vivers.model.CommentDTO;
import com.vivers.service.Action;
import com.vivers.service.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertComment implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("execute InsertComment.java");
        request.setCharacterEncoding("UTF-8");
        int result = 0;
        int seq = (request.getParameter("seq") == null) ? 0 : 1;
        int parentCommentNum = (request.getParameter("parentCommentNum") == null) ? 0 : Integer.parseInt(request.getParameter("parentCommentNum"));

        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setBoardNum(Integer.parseInt(request.getParameter("boardNum")));
        commentDTO.setUserId(request.getParameter("userId"));
        commentDTO.setCommentContent(request.getParameter("commentContent").replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
        commentDTO.setLikeCount(0);
        if (seq == 1) { // if 일반댓 : 0 ; 대댓 : 1
            commentDTO.setSeq(1);
            commentDTO.setParentCommentNum(parentCommentNum);
        } else {
            commentDTO.setSeq(0);
        }

        CommentDAO commentDAO = CommentDAO.getInstance();
        result = commentDAO.insertComment(commentDTO);

        if(result == 1) {
            System.out.println("댓글 작성 성공");
        } else {
            System.out.println("댓글 작성 실패");
            return null;
        }

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/feed.do");
        return forward;
    }
}
