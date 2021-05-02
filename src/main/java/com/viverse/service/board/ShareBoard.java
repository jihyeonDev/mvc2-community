package com.vivers.service.board;

import com.vivers.dao.BoardDAO;
import com.vivers.model.BoardDTO;
import com.vivers.service.Action;
import com.vivers.service.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class ShareBoard implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("execute ShareBoard.java");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        int boardNum = Integer.parseInt(request.getParameter("boardNum"));

        System.out.println("boardNum" + boardNum);
        int result = 0;

        BoardDAO boardDAO = BoardDAO.getInstance();
        result = boardDAO.shareCountUp(boardNum);

        if(result == 1) {
            System.out.println("shareCountUp 성공");
        } else {
            System.out.println("shareCountUp 실패");
        }

        ActionForward forward = new ActionForward();
        forward.setRedirect(true);
        forward.setPath("/feed.do");
        return forward;
    }
}
