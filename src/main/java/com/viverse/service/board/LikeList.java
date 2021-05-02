package com.vivers.service.board;

import com.vivers.dao.BoardDAO;
import com.vivers.dao.CommentDAO;
import com.vivers.model.LikeListDTO;
import com.vivers.service.Action;
import com.vivers.service.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class LikeList implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String userId = (String) session.getAttribute("userId"); // 세션 없으면 로그인 페이지 이동
        int boardNum = (request.getParameter("boardNum") == null) ? 0 : Integer.parseInt(request.getParameter("boardNum"));
        int commentNum = (request.getParameter("commentNum") == null) ? 0 : Integer.parseInt(request.getParameter("commentNum"));

        PrintWriter out = response.getWriter();
        int result = 0;

        BoardDAO boardDAO = BoardDAO.getInstance();
        if(commentNum != 0) {
            CommentDAO commentDAO = CommentDAO.getInstance();
            result = commentDAO.updateLikeCount(commentNum);
            PrintResult.out(out, result, "updateLikeCount");
        }

        LikeListDTO likeListDTO = new LikeListDTO();
        likeListDTO.setUserId(userId);
        likeListDTO.setBoardNum(boardNum);
        likeListDTO.setCommentNum(commentNum);
        likeListDTO.setStatus("0");

        result = boardDAO.insertLikeList(likeListDTO);

        PrintResult.out(out, result, "insertLikeList ");

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/feed.do");
        return forward;
    }
}
