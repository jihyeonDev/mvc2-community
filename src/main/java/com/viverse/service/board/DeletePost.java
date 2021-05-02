package com.vivers.service.board;

import com.vivers.dao.BoardDAO;
import com.vivers.model.BoardDTO;
import com.vivers.model.TagDTO;
import com.vivers.service.Action;
import com.vivers.service.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;

public class DeletePost implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("execute DeletePost.java");

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        int boardNum = Integer.parseInt(request.getParameter("boardNum"));
        String userId = request.getParameter("userId");
        String path = request.getRealPath("upload");

        BoardDAO dao = BoardDAO.getInstance();
        BoardDTO dto = dao.getPost(boardNum);
        TagDTO tagDTO = dao.selectTag(boardNum);

        if(dto.getUserId().equals(userId)) {
            int result = dao.deletePost(boardNum);
            if(result == 1) {
                System.out.println("글 삭제 성공");
            }

            if(dto.getArtFile() != "") {
                File upload = new File(path);
                upload.mkdir();

                File[] files = upload.listFiles();
                for(File file : files) {
                    if(file.getName().equals(dto.getArtFile())) {
                        file.delete();
                    }
                }
            }

            if(tagDTO.getTagName() != null) {
                result = dao.deleteTag(boardNum);
                if(result == 1) {
                    System.out.println("태그 삭제 성공");
                } else {
                    System.out.println("태그 삭제 실패");
                }
            }

        } else {
            out.println("<script>");
            out.println("alert('글 삭제 실패');");
            out.println("history.back();");
            out.println("</script>");
            out.close();

            return null;
        }

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/feed.do");
        return forward;
    }
}
