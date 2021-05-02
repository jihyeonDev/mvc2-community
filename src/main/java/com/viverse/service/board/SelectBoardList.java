package com.vivers.service.board;

import com.vivers.dao.BoardDAO;
import com.vivers.dao.CommentDAO;
import com.vivers.model.*;
import com.vivers.service.Action;
import com.vivers.service.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class SelectBoardList implements Action {

    static int start = 0;

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("execute GetPostList.java"); // PRINT TEST MESSAGE
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = requestURI.substring(contextPath.length());

        int parentCommentNum = (request.getParameter("parentCommentNum") == null) ? 0 : Integer.parseInt(request.getParameter("parentCommentNum"));

        BoardDAO boardDAO = BoardDAO.getInstance();

        boolean scrolled = (request.getParameter("scrolled") == null) ? false : true;
        String userId = (String) session.getAttribute("userId");
        String boardType = (request.getParameter("boardType") == null) ? "feed" : request.getParameter("boardType");

        if(scrolled) {
            start += 15;
        } else {
            start = 0;
        }

        System.out.println("start => " +start);
        int end = 15;

        List<BoardDTO> boardDTOList = new ArrayList<>();
        if(command.equals("/artfeed.do")) {
            request.setAttribute("pageTitle", "artfeed");
            boardDTOList = boardDAO.getartfeedList(start, end);
            boardType = "artfeed";

        } else if(command.equals("/myfeed.do")) {
            request.setAttribute("pageTitle", "myfeed");
            boardDTOList = boardDAO.getmyfeedList(start, end, userId);
            boardType = "myfeed";

        } else {
            request.setAttribute("pageTitle", "feed");
            boardDTOList = boardDAO.getPostList(start, end);
            boardType = "feed";
        }

        List<FileDTO> fileDTOList = boardDAO.selectFileList();
        List<TagDTO> tagDTOList = boardDAO.selectTagList();
        List<MyLikeBoardDTO> myLikeBoardDTOList = boardDAO.selectMyLikeBoardList();
        List<MyBookmarkDTO> myBookmarkDTOList = boardDAO.selectMyBookmarkList();
        List<TagDTO> popularTagList = boardDAO.popularTagList();

        // 댓글
        CommentDAO commentDAO = CommentDAO.getInstance();
        List<CommentDTO> commentDTOList = new ArrayList<>();
        if(parentCommentNum == 0) {
            commentDTOList = commentDAO.selectCommentList(0);
        } else {
            commentDTOList = commentDAO.selectCommentList(parentCommentNum);
        }

        request.setAttribute("boardList", boardDTOList);
        request.setAttribute("fileList", fileDTOList);
        request.setAttribute("tagList", tagDTOList);
        request.setAttribute("myLikeBoardList", myLikeBoardDTOList);
        request.setAttribute("myBookmarkList", myBookmarkDTOList);
        request.setAttribute("commentList", commentDTOList);
        request.setAttribute("popularTagList", popularTagList);
        request.setAttribute("start", start);
        request.setAttribute("boardType", boardType);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("./sub/board/feed.jsp");

        return forward;
    }
}
