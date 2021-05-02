<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:forEach var="post" items="${boardList}">
<c:set var="boardNum" scope="session" value="${post.boardNum}"/>
    <section class="post-box ct-box">
        <article class="">
            <article class="content-header">
                <div class="writer">
                    <div class="writer-profile">
                        <img src="https://via.placeholder.com/200" alt="프로필 이미지"> <%-- member.profile --%>
                    </div>
                    <div class="writer-info" style="font-size: 13px;">
                        <h5>${post.userId}</h5> <%-- member.aka로 추후 수정 --%>
                        <p class="post-regdate">
                            <fmt:formatDate value="${post.regDate}" pattern="yyyy.MM.dd HH:mm"/>
                        </p>
                        <span>글 번호 : ${post.boardNum}</span>
                        <c:if test="${post.secretPost eq 1}">
                            <span style="color: #9b52c7;">[ 비밀글 ]</span>
                        </c:if>
                        <c:if test="${post.boardType eq 1}">
                            <span style="color: #9b52c7;"> [ 그림글 ]</span>
                        </c:if>
                    </div>
                </div>
                <div class="post-menu">
                    <a href="" onclick=""> <%-- click Fn - dropdown menu --%>
                        <span></span> <%-- 포스트 신고 팝업 / click function / reportCount 3이상이면 delete Post --%>
                        <span></span> <%-- 카카오로 공유하기 --%>
                        <span></span> <%-- 트위터로 공유하기 --%>
                    </a>
                </div>
            </article>
            <article class="content-main">
                <p>${post.content}</p>
                <c:forEach var="file" items="${fileList}">
                    <c:if test="${file.boardNum == post.boardNum}">
                        <div class="upload-file">
                            <img src="<%=request.getContextPath()%>/upload/${file.file}" alt="첨부파일" loading="lazy">
                        </div>
                    </c:if>
                </c:forEach>
                <div class="tags">
                <c:forEach var="tag" items="${tagList}">
                        <c:if test="${tag.boardNum == post.boardNum}">
                            <a href="/search.do?searchBy=${tag.tagName}&type=tag">${tag.tagName} </a>
                        </c:if>
                </c:forEach>
                </div>
            </article>
            <article class="content-reactions">
                <div>
                    <%--if 내좋아요 테이블에 추가돼 있음 => active css--%>
                    <%--click toggle => insert myLikeBoard / Delete myLikeBoard--%>
                        <a href="/like.do?boardNum=${post.boardNum}" style="
                    <c:forEach var="myLikeBoard" items="${myLikeBoardList}">
                        <c:if test="${myLikeBoard.userId == sessionScope.userId}">
                            <c:if test="${myLikeBoard.boardNum == post.boardNum}">color: #fcd2d3; font-weight: bold;</c:if>
                        </c:if>
                    </c:forEach>
                        ">
                        <span class="react-icon">
                            <i class="far fa-thumbs-up"></i>
                        </span>
                        <span class="react-text">좋아요</span>
                        <c:if test="${post.likeCount > 0}">
                            <span class="react-count">${post.likeCount}</span>
                        </c:if>
                    </a>
                </div>
                <div>
                    <a href="/bookmark.do?boardNum=${post.boardNum}" style="
                    <c:forEach var="myBookmark" items="${myBookmarkList}">
                        <c:if test="${myBookmark.userId == sessionScope.userId}">
                            <c:if test="${myBookmark.boardNum == post.boardNum}">color: #9979c2; font-weight: bold;</c:if>
                        </c:if>
                    </c:forEach>
                    ">
                        <span class="react-icon">
                            <i class="far fa-star"></i>
                        </span>
                        <span class="react-text">북마크</span>
                    </a>
                </div>
                <div>
                    <a href="/share.do?boardNum=${post.boardNum}">
                            <span class="react-icon">
                                <i class="fas fa-share-alt"></i>
                            </span>
                        <span class="react-text">공유하기</span>
                        <c:if test="${post.shareCount > 0}">
                            <span class="react-count">${post.shareCount}</span>
                        </c:if>
                    </a>
                    <%--
                    드롭다운메뉴
                    - 링크복사
                    - 카카오로 공유
                    - 라인으로 공유
                    --%>
                </div>
            </article>
            <jsp:include page="comment-list.jsp"/>
        </article>
    </section>
</c:forEach>
