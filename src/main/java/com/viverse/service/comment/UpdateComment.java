package com.vivers.service.comment;

import com.vivers.dao.CommentDAO;
import com.vivers.model.CommentDTO;
import com.vivers.service.Action;
import com.vivers.service.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class UpdateComment implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("execute ShareBoard.java");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        int commentNum = Integer.parseInt(request.getParameter("commentNum"));
        String userId = request.getParameter("userId");

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentNum(commentNum);
        commentDTO.setCommentContent(request.getParameter("commentContent"));

        CommentDAO commentDAO = CommentDAO.getInstance();
        CommentDTO oldCommentDTO = commentDAO.selectCommentOne(commentNum);

        if (oldCommentDTO.getUserId().equals(userId)) {
            int result = commentDAO.updateComment(commentDTO);
            if (result == 1) {
                System.out.println("댓글 수정 성공");
            } else {
                System.out.println("댓글 수정 실패");
            }
        } else {
            out.println("<script>");
            out.println("alert('댓글 수정에 실패했습니다.');");
            out.println("history.back();");
            out.println("</script>");

            return null;
        }

        ActionForward forward = new ActionForward();
        forward.setRedirect(true);
        forward.setPath("/feed.do");
        return forward;
    }
}
