package com.vivers.service.board;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.vivers.dao.BoardDAO;
import com.vivers.model.BoardDTO;
import com.vivers.model.FileDTO;
import com.vivers.service.Action;
import com.vivers.service.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class UpdateBoard implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("execute UpdatePost.java");

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String path = request.getRealPath("upload");
        int size = 2048 * 2048; // 4MB

        MultipartRequest multipartRequest = new MultipartRequest(request, path, size, "UTF-8", new DefaultFileRenamePolicy());

        int boardNum = Integer.parseInt(multipartRequest.getParameter("boardNum"));
        String userId = multipartRequest.getParameter("userId");

        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardNum(boardNum);
        boardDTO.setBoardType(multipartRequest.getParameter("boardType"));
        boardDTO.setSecretPost(multipartRequest.getParameter("secretPost"));
        boardDTO.setContent(multipartRequest.getParameter("content"));

        FileDTO fileDTO = new FileDTO();
        fileDTO.setBoardNum(boardNum);
        fileDTO.setFile(multipartRequest.getFilesystemName("file"));

        BoardDAO dao = BoardDAO.getInstance();
        BoardDTO oldDto = dao.getPost(boardNum);

        if (oldDto.getUserId().equals(userId)) {
            int result = dao.updatePost(boardDTO, fileDTO);
            if (result == 1) {
                System.out.println("수정 성공");
            }
        } else {
            out.println("<script>");
            out.println("alert('글 수정에 실패했습니다.');");
            out.println("history.back();");
            out.println("</script>");

            return null;
        }

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/feed.do");
        return forward;
    }
}
