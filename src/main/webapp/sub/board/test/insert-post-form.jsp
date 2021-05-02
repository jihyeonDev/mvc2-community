<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>add-post-test</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/add-post.do" method="post" enctype="multipart/form-data">
        <div>
            <label for="userId">아이디 : </label>
            <input type="text" name="userId" id="userId">
        </div>
        <div>
            <label for="boardType">게시글 타입 : </label>
            <input type="text" name="boardType" id="boardType">
        </div>
        <div>
            <label for="secretPost">비밀글 여부 : </label>
            <input type="text" name="secretPost" id="secretPost">
        </div>
        <div>
            <label for="content">내용 : </label>
            <textarea name="content" id="content"></textarea>
        </div>
        <div>
            <label for="file">첨부파일 : </label>
            <input type="file" name="file" id="file">
        </div>
        <div> // artfeed에서만 나타나게
            <label for="artfile">아트파일 : </label>
            <input type="file" name="artfile" id="artfile">
        </div>
        <div>
            <label for="tag">태그 : </label> <%-- 태그 동적 추가 되게 js function --%>
            <input type="text" name="tag" id="tag">
            <input type="text" name="tag">
            <input type="text" name="tag">
        </div>
        <div>
            <input type="submit" value="글쓰기">
        </div>
    </form>
</body>
</html>
