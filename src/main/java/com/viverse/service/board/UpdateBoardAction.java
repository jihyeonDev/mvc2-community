package com.vivers.service.board;

import com.vivers.dao.BoardDAO;
import com.vivers.model.BoardDTO;
import com.vivers.model.FileDTO;
import com.vivers.service.Action;
import com.vivers.service.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateBoardAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("execute UpdatePostAction.java");

        int boardNum = Integer.parseInt(request.getParameter("boardNum"));
        String userId = request.getParameter("userId");

        BoardDAO dao = BoardDAO.getInstance();
        BoardDTO boardDTO = dao.getPost(boardNum);
        FileDTO fileDTO = dao.getFile(boardNum);

        request.setAttribute("boardDTO", boardDTO);
        request.setAttribute("userId", userId);
        request.setAttribute("fileDTO", fileDTO);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("./sub/board/test/update-post.jsp");
        return forward;
    }
}
