<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글 삭제 테스트</title>
</head>
<body>
    <form action="/delete-post.do" method="post" onsubmit="return confirm('글을 삭제하시겠습니까?')">
        <div>
            <label for="userId">아이디 : </label>
            <input type="text" name="userId" id="userId">
        </div>
        <div>
            <label for="boardNum">글 번호 : </label>
            <input type="text" name="boardNum" id="boardNum">
        </div>
        <div>
            <button type="submit">글 삭제</button>
        </div>
    </form>
</body>
</html>
