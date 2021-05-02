package com.vivers.service.comment;

import com.vivers.dao.CommentDAO;
import com.vivers.model.CommentDTO;
import com.vivers.service.Action;
import com.vivers.service.ActionForward;
import com.vivers.service.board.PrintResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class DeleteComment implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("execute InsertComment.java");
        PrintWriter out = response.getWriter();

        int commentNum = Integer.parseInt(request.getParameter("commentNum"));
        String userId = request.getParameter("userId");

        CommentDAO commentDAO = CommentDAO.getInstance();
        CommentDTO commentDTO = commentDAO.selectCommentOne(commentNum);

        if(commentDTO.getUserId().equals(userId)) {
            int result = commentDAO.deleteComment(commentNum);
            PrintResult.out(out, result, "deleteComment");
        } else {
            System.out.println("아이디 불일치");
        }

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/feed.do");
        return forward;
    }
}
