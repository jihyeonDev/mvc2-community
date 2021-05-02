package com.vivers.service.board;

import com.vivers.dao.BoardDAO;
import com.vivers.model.BoardDTO;
import com.vivers.service.Action;
import com.vivers.service.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SearchBoard implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("execute SearchBoard.java");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        String searchBy = request.getParameter("searchBy");
        System.out.println("searchBy : " + searchBy);

        int start = 0;
        int end = 15;

        BoardDAO boardDAO = BoardDAO.getInstance();

        String type = (request.getParameter("type") == null) ? "" : request.getParameter("type");
        String subSearchBy = searchBy.substring(1, searchBy.length());
        if(searchBy.substring(0, 1).equals("#") || type.equals("tag")) { // 태그 검색
            if(type.equals(""))
                searchBy = subSearchBy;
            type = "tag";

        } else if(searchBy.substring(0, 1).equals("@") || type.equals("aka")) { // 작성자 닉네임 검색
            if(type.equals(""))
                searchBy = subSearchBy;
            type = "aka";

        } else { // 일반 검색
            type = "general";
        }

        List<BoardDTO> boardDTOList = boardDAO.boardSearchByContent(searchBy, start, end, type);

        System.out.println("boardDTOList : " + boardDTOList);
        System.out.println("subSearchBy : " + subSearchBy);
        System.out.println("searchBy.substring(0, 1) : " + searchBy.substring(0, 1));

        request.setAttribute("boardList", boardDTOList);
        request.setAttribute("searchBy", searchBy);
        request.setAttribute("type", type);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("./sub/board/feed.jsp");

        return forward;
    }
}
