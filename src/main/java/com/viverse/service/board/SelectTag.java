package com.vivers.service.board;

import com.vivers.dao.BoardDAO;
import com.vivers.model.TagDTO;
import com.vivers.service.Action;
import com.vivers.service.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SelectTag implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("execute GetTag.java"); // PRINT TEST MESSAGE

        int boardNum = Integer.parseInt(request.getParameter("boardNum"));

        BoardDAO boardDAO = BoardDAO.getInstance();
        TagDTO tagDTO = boardDAO.selectTag(boardNum);

        String[] tags = tagDTO.getTagName().split("#");

        request.setAttribute("tags", tags);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("./sub/board/test/tag-form.jsp");

        return forward;
    }
}
