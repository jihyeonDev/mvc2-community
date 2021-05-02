package com.vivers.controller;

import com.vivers.service.Action;
import com.vivers.service.ActionForward;
import com.vivers.service.board.*;
import com.vivers.service.comment.*;
import com.vivers.service.main.*;
import com.vivers.service.member.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("*.do")
public class MainController extends HttpServlet {

    private void doProcess(HttpServletRequest request, HttpServletResponse response) {
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = requestURI.substring(contextPath.length());

        System.out.println("requestURI : " + requestURI); // PRINT TEST MESSAGE
        System.out.println("contextPath : " + contextPath); // PRINT TEST MESSAGE
        System.out.println("command : " + command); // PRINT TEST MESSAGE

        Action action = null;
        ActionForward forward = null;

        if (command.equals("/add-post.do")) {
            try {
                action = new InsertBoard();
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (command.equals("/delete-post.do")) {
            try {
                action = new DeletePost();
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (command.equals("/modify-post.do")) {
            try {
                action = new UpdateBoardAction();
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (command.equals("/update-post.do")) {
            try {
                action = new UpdateBoard();
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (command.equals("/get-post.do")) {
            try {
                action = new SelectBoardOne();
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/report-post.do")) {
            try {
                action = new ReportBoard();
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/like.do")) {
            try {
                action = new MyLikeBoard();
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/bookmark.do")) {
            try {
                action = new MyBookmarkBoard();
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/share.do")) {
            try {
                action = new ShareBoard();
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/get-tag.do")) {
            try {
                action = new SelectTag();
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        // Comment
        } else if(command.equals("/insert-comment.do")) {
            try {
                action = new InsertComment();
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/nested-comments.do")) {
            try {
                action = new InsertComment();
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if(command.equals("/search.do")) {
            try {
                action = new SearchBoard();
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (command.equals("/like-list.do")) {
            try {
                action = new LikeList();
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (command.equals("/feed.do") || command.equals("/artfeed.do") || command.equals("/myfeed.do")) {
            try {
                action = new SelectBoardList();
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else { }


        if (forward != null) {
            if (forward.isRedirect()) {
                try {
                    response.sendRedirect(forward.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
                try {
                    dispatcher.forward(request, response);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }
}
