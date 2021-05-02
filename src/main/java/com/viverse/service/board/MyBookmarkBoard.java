package com.vivers.service.board;

import com.vivers.dao.BoardDAO;
import com.vivers.model.BoardDTO;
import com.vivers.model.MyBookmarkDTO;
import com.vivers.service.Action;
import com.vivers.service.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class MyBookmarkBoard implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("execute MyBookmark.java");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        String userId = (String) session.getAttribute("userId");
        int boardNum = (request.getParameter("boardNum") == null) ? 0 : Integer.parseInt(request.getParameter("boardNum"));


        System.out.println("userId : " + userId);
        System.out.println("boardNum" + boardNum);
        int result = 0;

        BoardDAO boardDAO = BoardDAO.getInstance();
        BoardDTO boardDTO = boardDAO.getPost(boardNum);
        System.out.println(boardDTO);

        MyBookmarkDTO myBookmarkDTO = boardDAO.getMyBookmarkBoard(userId, boardNum);
        System.out.println("myBookmarkDTO : " + myBookmarkDTO);


        if(myBookmarkDTO.getUserId() == null || (!myBookmarkDTO.getUserId().equals(userId) && myBookmarkDTO.getBoardNum() != boardNum)) {
            result = boardDAO.insertMyBookmarkBoard(userId, boardNum);

            if(result == 1) {
                System.out.println("북마크 insert 성공");
            } else {
                out.println("<script>");
                out.println("alert('북마크 추가 실패');");
                out.println("history.back();");
                out.println("</script>");

                out.close();
                return null;
            }

        } else {
            result = boardDAO.deleteMyBookmarkBoard(userId, boardNum);

            if(result == 1) {
                System.out.println("북마크 delete 성공");
            } else {
                out.println("<script>");
                out.println("alert('북마크 삭제 실패');");
                out.println("history.back();");
                out.println("</script>");

                out.close();
                return null;
            }
        }

        ActionForward forward = new ActionForward();
        forward.setRedirect(true);
        forward.setPath("/feed.do");
        return forward;
    }
}
