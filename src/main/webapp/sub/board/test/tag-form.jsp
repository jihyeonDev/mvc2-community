<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>tag-form</title>
</head>
<body>
  <div>
    <form action="/get-tag.do" method="post">
      <div>[ tag get ]</div>
      <div>
        글 번호 : <input type="text" name="boardNum">
      </div>

      <div>
        <input type="submit">
      </div>
    </form>
    <div>
      <div>태그 조회</div>
      <div>글 번호 : ${boardNum}</div>
      <c:forEach var="tag" items="${tags}">
        <h6>${tag}</h6>
      </c:forEach>
    </div>
  </div>

</body>
</html>
