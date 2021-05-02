<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<section class="post-box ct-box">
    <article class="">
        <article class="content-header">
            <div class="writer">
                <div class="writer-profile">
                    <img src="https://via.placeholder.com/200" alt="프로필 이미지"> <%-- src="<%=request.getContextPath()%>/profile/${member.profile}%>" --%>
                </div>
                <div class="writer-info">
                    <h5>${board.userId}</h5> <%-- member.aka로 추후 수정 --%>
                    <p class="post-regdate">
                        <fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd HH:mm"/>
                    </p>
                </div>
            </div>
            <div class="post-menu">
                <a href="" onclick=""> <%-- click Fn - dropdown menu --%>
                    <span></span> <%-- 포스트 신고 --%>
                    <span></span> <%-- 카카오로 공유하기 --%>
                    <span></span> <%-- 트위터로 공유하기 --%>
                </a>
            </div>
        </article>
        <article class="content-main">
            <p>${board.content}</p>
            <c:if test="${file.file != null}">
                <div class="upload-file">
                    <img src="<%=request.getContextPath()%>/upload/${file.file}" alt="첨부파일">
                </div>
            </c:if>
        </article>
        <article class="content-reactions">
            <div>
                <a href="#">
                    <span class="react-icon">
                        <i class="far fa-thumbs-up"></i>
                    </span>
                    <span class="react-text">좋아요</span>
                </a>
            </div>
            <div>
                <a href="#">
                    <span class="react-icon">
                        <i class="far fa-star"></i>
                    </span>
                    <span class="react-text">찜하기</span>
                </a>
            </div>
            <div>
                <a href="#">
                    <span class="react-icon">
                        <i class="fas fa-share-alt"></i>
                    </span>
                    <span class="react-text">공유하기</span>
                </a>
                <!-- 드롭다운메뉴
                - 링크복사
                - 카카오로 공유
                - 라인으로 공유
                 -->
            </div>
        </article>
        <article class="comment-area">
            <span class="commenter-image">
                <img src="https://via.placeholder.com/32" alt="프로필 이미지"> <%-- src="<%=request.getContextPath()%>/profile/${member.profile}%>" --%>
            </span>
            <div class="comment-input">
                <div>
                    <input type="text" placeholder="댓글을 입력하세요...">
                </div>
                <button type="button">게시</button>
            </div>
        </article>
    </article>
</section>
</body>
</html>
