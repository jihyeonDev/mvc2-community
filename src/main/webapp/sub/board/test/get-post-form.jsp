<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <div>글 상세페이지</div>
    <form method="post" action="/get-post.do">
        <div>
            <label for="boardNum">글 번호 : </label>
            <input type="text" id="boardNum" name="boardNum">
        </div>
        <div>
            <input type="submit" value="글 상세 보기">
        </div>
    </form>
</div>
</body>
</html>
