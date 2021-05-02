<%--
    feed에서 글 수정하기 ->
    updatePostAction.java로 boardNum과 userId로 수정할 글 정보 얻어옴 ->
    update-post.jsp (글 수정 폼) ->
    update-post-action.do -> UpdatePost.java ->  feed.do
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>update-post-test</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/update-post.do" method="post" enctype="multipart/form-data">
        <div>
            <input type="hidden" name="boardNum" id="boardNum" value="${param.boardNum}">
            글 번호 : ${param.boardNum}
        </div>
        <div>
            <input type="hidden" name="userId" id="userId" value="${param.userId}">
            아이디 : ${param.userId}
        </div>
        <div>
            현재 게시글 타입 : <input type="text" name="boardType" id="boardType" value="${boardDTO.boardType}">
        </div>
        <div>
            <label for="secretPost">비밀글 여부 : </label>
            <input type="text" name="secretPost" id="secretPost" value="${boardDTO.secretPost}">
        </div>
        <div>
            <label for="content">내용 : </label>
            <textarea name="content" id="content">${boardDTO.content}</textarea>
        </div>
        <div>
            <label for="file">첨부파일 : </label>
            <input type="file" name="file" id="file">
            <div>
                <c:if test="${fileDTO.file != null}"><img src="<%=request.getContextPath()%>/upload/${fileDTO.file}" alt="첨부파일"></c:if>
                <c:if test="${fileDTO.file == null}">첨부한 파일이 없습니다.</c:if>
            </div>
        </div>
        <div>
            <input type="submit" value="글수정">
        </div>
    </form>
</body>
</html>
