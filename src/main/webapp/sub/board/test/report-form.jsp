<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>신고하기</title>
</head>
<body>
    <form action="/report-post.do" method="post" onsubmit="return confirm('신고하시겠습니까?')">
        <div>
            <label for="boardNum">신고할 글 번호 : </label>
            <input type="text" id="boardNum" name="boardNum">
        </div>
        <div>
            <input type="submit" value="신고하기"/>
        </div>
    </form>
</body>
</html>
