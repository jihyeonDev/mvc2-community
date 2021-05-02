package com.vivers.service.board;

import com.vivers.dao.BoardDAO;
import com.vivers.model.BoardDTO;
import com.vivers.service.Action;
import com.vivers.service.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class ReportBoard implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("execute ReportPost.java"); // PRINT TEST MESSAGE
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        int boardNum = Integer.parseInt(request.getParameter("boardNum"));
        int result = 0;
        BoardDAO boardDAO = BoardDAO.getInstance();
        BoardDTO boardDTO = boardDAO.getPost(boardNum);

        if(boardDTO.getReportCount() < 2) {
            result = boardDAO.reportPost(boardNum);

            if(result == 1) {
                System.out.println("신고 완료");
            } else {
                out.println("<script>");
                out.println("alert('신고 실패')");
                out.println("history.back();");
                out.println("</script>");

                out.close();
                return null;
            }
        } else {
            result = boardDAO.deletePost(boardNum);

            if(result == 1) {
                System.out.println("신고 3회 이상으로 글이 삭제");
            } else {
                out.println("<script>");
                out.println("alert('신고 실패')");
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
