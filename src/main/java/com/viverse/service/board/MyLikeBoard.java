package com.vivers.service.board;

import com.vivers.dao.BoardDAO;
import com.vivers.model.BoardDTO;
import com.vivers.model.LikeListDTO;
import com.vivers.model.MyLikeBoardDTO;
import com.vivers.service.Action;
import com.vivers.service.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class MyLikeBoard implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("execute MyLike.java");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        int commentNum = (request.getParameter("commentNum") == null) ? 0 : Integer.parseInt(request.getParameter("commentNum"));

        String userId = (String) session.getAttribute("userId");
        int boardNum = (request.getParameter("boardNum") == null) ? 0 : Integer.parseInt(request.getParameter("boardNum"));

        int result = 0;

        BoardDAO boardDAO = BoardDAO.getInstance();
        BoardDTO boardDTO = boardDAO.getPost(boardNum);
        System.out.println(boardDTO);

        MyLikeBoardDTO myLikeBoardDTO = boardDAO.getMyLikeBoard(userId, boardNum);
        System.out.println("myLikeBoardDTO : " + myLikeBoardDTO);

        LikeListDTO likeListDTO = new LikeListDTO();
        likeListDTO.setUserId(userId);
        likeListDTO.setBoardNum(boardNum);
        likeListDTO.setCommentNum(commentNum);
        likeListDTO.setStatus("0");

        if(boardNum != 0) {
            result = boardDAO.insertMyLikeBoard(userId, boardNum);
            PrintResult.out(out, result, "insertMyLikeBoard");
            result = boardDAO.insertLikeList(likeListDTO);
            PrintResult.out(out, result, "insertLikeList");

        } else {
            result = boardDAO.deleteMyLikeBoard(userId, boardNum);
            PrintResult.out(out, result, "deleteMyLikeBoard");
            // delete Like List
        }

        ActionForward forward = new ActionForward();
        forward.setRedirect(true);
        forward.setPath("/feed.do");
        return forward;
    }
}
