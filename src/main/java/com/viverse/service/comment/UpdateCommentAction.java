package com.vivers.service.comment;

import com.vivers.dao.CommentDAO;
import com.vivers.model.CommentDTO;
import com.vivers.service.Action;
import com.vivers.service.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateCommentAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("execute InsertComment.java");
        request.setCharacterEncoding("UTF-8");

        int commentNum = Integer.parseInt(request.getParameter("commentNum"));
        String userId = request.getParameter("userId");

        CommentDAO commentDAO = CommentDAO.getInstance();
        CommentDTO commentDTO = commentDAO.selectCommentOne(commentNum);

        request.setAttribute("commentDTO", commentDTO);
        request.setAttribute("userId", userId);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/feed.do");
        return forward;
    }
}
