package com.vivers.service.board;

import com.vivers.dao.BoardDAO;
import com.vivers.model.*;
import com.vivers.service.Action;
import com.vivers.service.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SelectBoardOne implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("execute GetPost.java"); // PRINT TEST MESSAGE
        HttpSession session = request.getSession();

        String userId = request.getParameter("userId"); // 로그인한 회원 아이디
        int boardNum = Integer.parseInt(request.getParameter("boardNum"));

        BoardDAO boardDAO = BoardDAO.getInstance();
        boardDAO.readCountUp(boardNum);
        BoardDTO boardDTO = boardDAO.getPost(boardNum);
        FileDTO fileDTO = boardDAO.getFile(boardNum);
        TagDTO tagDTO = boardDAO.selectTag(boardNum);
        MyLikeBoardDTO myLikeBoardDTO = boardDAO.getMyLikeBoard(userId, boardNum);
        MyBookmarkDTO myBookmarkDTO = boardDAO.getMyBookmarkBoard(userId, boardNum);

        String content = boardDTO.getContent().replaceAll("\n", "<br>");
        String[] tags = tagDTO.getTagName().split("#");

        session.setAttribute("board", boardDTO);
        session.setAttribute("file", fileDTO);
        session.setAttribute("content", content);
        session.setAttribute("tags", tags);
        session.setAttribute("myLikeBoardDTO", myLikeBoardDTO);
        session.setAttribute("myBookmarkDTO", myBookmarkDTO);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("./sub/board/test/get-post.jsp");

        return forward;
    }
}
