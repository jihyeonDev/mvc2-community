<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>
        <div>글 수정하기</div>
        <form method="post" action="/modify-post.do">
            <div>
                <label for="userId">아이디 : </label>
                <input type="text" id="userId" name="userId">
            </div>
            <div>
                <label for="boardNum">글 번호 : </label>
                <input type="text" id="boardNum" name="boardNum">
            </div>
            <div>
                <input type="submit" value="이 글을 수정합니다">
            </div>
        </form>
    </div>
</body>
</html>
