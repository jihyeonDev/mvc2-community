<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>react-form</title>
</head>
<body>
  <div>
    ${userId}
  </div>
  <div>
    <form method="post" action="/like.do">
      <div>
<%--        <input type="hidden" name="userId" value="${userId}">--%>
        <div>
          userId : <input type="text" name="userId">
        </div>
        <label for="boardNum">좋아요할 글 번호</label>
        <input type="text" name="boardNum" id="boardNum">
      </div>
      <div>
        <input type="submit">
      </div>
    </form>
  </div>
  <div>
    <form method="post" action="/bookmark.do">
      <div>
          <div>
              userId : <input type="text" name="userId">
          </div>
        <input type="hidden" name="userId" value="${userId}">
        <label for="boardNum">북마크할 글 번호</label>
        <input type="text" name="boardNum" id="boardNum">
      </div>
      <div>
        <input type="submit">
      </div>
    </form>
  </div>
  <div>
    <form method="post" action="/share.do">
      <div>
          <div>
              userId : <input type="text" name="userId">
          </div>
        <input type="hidden" name="userId" value="${userId}">
        <label for="boardNum">공유할 글 번호</label>
        <input type="text" name="boardNum" id="boardNum">
      </div>
      <div>
        <input type="submit">
      </div>
    </form>
  </div>
</body>
</html>
