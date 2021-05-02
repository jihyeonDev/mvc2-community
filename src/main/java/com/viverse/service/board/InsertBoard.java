package com.vivers.service.board;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.vivers.dao.BoardDAO;
import com.vivers.model.BoardDTO;
import com.vivers.service.Action;
import com.vivers.service.ActionForward;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class InsertBoard implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("execute InsertPost.java"); // PRINT TEST MESSAGE
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String path = request.getRealPath("upload");
		System.out.println("path : " + path); // PRINT TEST MESSAGE

		int size = 2048 * 2048; // 4MB

		MultipartRequest multipartRequest = new MultipartRequest(request, path, size, "UTF-8", new DefaultFileRenamePolicy());

		BoardDTO boardDTO = new BoardDTO();

		boardDTO.setUserId(multipartRequest.getParameter("userId"));
		boardDTO.setBoardType(multipartRequest.getParameter("boardType"));
		boardDTO.setSecretPost(multipartRequest.getParameter("secretPost"));
		boardDTO.setReportCount(0);
		boardDTO.setContent(multipartRequest.getParameter("content").replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
		boardDTO.setReadCount(0);
		boardDTO.setLikeCount(0);
		boardDTO.setShareCount(0);
		boardDTO.setArtFile(multipartRequest.getFilesystemName("artfile"));

		BoardDAO boardDAO = BoardDAO.getInstance();
		int result = boardDAO.insert(boardDTO);
		if (result == 1) {
			System.out.println("글 작성 성공"); // PRINT TEST MESSAGE
		} else {
			out.println("<script>");
			out.println("alert('글 작성에 실패했습니다.');");
			out.println("history.back();");
			out.println("</script>");

			out.close();
			return null;
		}

		// 첨부파일 insert
		String file = multipartRequest.getFilesystemName("file");

		if (file != null) {
			System.out.println(file); // PRINT TEST MESSAGE

			result = boardDAO.insertFile(file);

			if (result == 1) {
				System.out.println("파일 첨부 성공"); // PRINT TEST MESSAGE
			} else {
				out.println("<script>");
				out.println("alert('파일 첨부에 실패했습니다.');");
				out.println("history.back();");
				out.println("</script>");

				out.close();
				return null;
			}
		} else { }

		// 태그 insert
		String[] tags = multipartRequest.getParameterValues("tag");

		if(tags != null || tags.length != 0 || !tags.equals("")) {
			System.out.println(tags); // PRINT TEST MESSAGE

			for(String tag : tags) {
				String tagName = tag.replaceAll(" ", "");
				if(!tagName.equals("")) {
					result = boardDAO.insertTag(tagName);
				} else { }
			}

			if(result == 1) {
				System.out.println("태그 insert 성공");
			} else {
				System.out.println("태그 insert 실패");
			}
		} else { }

		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./feed.do");

		return forward;
	}
}
