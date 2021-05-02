<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<div>
    <div>전달 받은 값</div>
    <span>boardNum : ${boardNum} / </span>
    <span>userId : ${userId}</span>
</div>--%>
<article class="comment-area">
    <span class="commenter-image">
        <img src="https://via.placeholder.com/32" alt="프로필 이미지">
    </span>
    <form action="/insert-comment.do" method="post" style="width: 100%;">
        <div>
            <input type="hidden" name="boardNum" value="${boardNum}">
            <input type="hidden" name="userId" value="${userId}">
        </div>
        <div class="comment-input">
            <div>
                <input type="text" name="commentContent" placeholder="댓글을 입력하세요...">
            </div>
            <button type="submit">게시</button>
        </div>
    </form>
</article>
<article class="comments-box">
<%--    <div>댓글 출력</div>--%>
    <c:forEach var="comment" items="${commentList}">
        <c:if test="${boardNum == comment.boardNum}">
            <div>
                <%--<div>프로필 이미지 : ${member.profile}</div>
                <div>닉네임 : ${member.aka}</div>--%>
            <c:if test="${comment.seq eq 0}">
                <div class="comment-member-info">
                    <span class="comment-member-aka">${comment.userId}</span>
                    <span><i class="fas fa-heart"></i></span>
                    <span>${comment.commentContent}</span>
                </div>
                <div>
                    <a href="/like-list.do?commentNum=${comment.commentNum}"><i class="far fa-thumbs-up"></i>&nbsp;${comment.likeCount}</a>
                    <span> • <fmt:formatDate value="${comment.cmRegDate}" pattern="yyyy.MM.dd HH:mm"/></span>
                    <div>
                        <div id="modalDim" style="display: none; position: fixed; z-index: 1; left: 0; top: 0; width: 100%; height: 100%; overflow: auto; background-color: rgba(0,0,0,0.4);"></div>
                        <div id="modal" class="modal-back" style="display: none; position: fixed; z-index: 2; left: 0; top: 0; width: 100%; height: 100%; overflow: auto;">
                            <div id="modalContent" class="modal-content" style="background-color: #fefefe; margin: 15% auto; padding: 20px; border: 1px solid #888; width: 30%; border-radius: 15px;">
                                <form action="/nested-comments.do" method="post" style="width: 100%">
                                    <input type="hidden" name="boardNum" value="${boardNum}">
                                    <input type="hidden" name="userId" value="${userId}">
                                    <input type="hidden" name="seq" value="${seq}">
                                    <input type="hidden" name="parentCommentNum" value="${parentCommentNum}">
                                    <h4>답글쓰기</h4>
                                    <input type="text" name="commentContent" style="width: 100%; height: 50px; border: 1px solid; padding: 10px 20px; margin: 20px 0; border: 1px solid #9b52c7;">
                                    <div class="btns" style="text-align: center;">
                                        <input type="submit" value="답글쓰기" style="padding: 10px; border: 1px solid #9b52c7; background-color: #9979c2; color: #fff;font-weight: bold;">
                                        <button type="button" id="closeBtn" style="padding: 10px; border: 1px solid #9b52c7; background-color: #9979c2; color: #fff; cursor: pointer;">창닫기</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <button type="button" id="commentBtn">답글쓰기</button>
                </div>
            </c:if>
                <%--                <a href="/nested-comments.do?seq=1" style="color: #b2c225;">답글 쓰기</a> &lt;%&ndash; 댓글 팝업창 &ndash;%&gt;--%>
            <c:if test="${comment.seq ne 0}">
                <div class="nested-comment">
                    <div class="comment-member-info">
                        <span class="comment-member-aka">${comment.userId}</span>
                        <span><i class="fas fa-heart"></i></span>
                        <span>${comment.commentContent}</span>
                    </div>
                    <div>
                        <a href="/like-list.do?commentNum=${comment.commentNum}"><i class="far fa-thumbs-up"></i>&nbsp;${comment.likeCount}</a>
                        <span> • <fmt:formatDate value="${comment.cmRegDate}" pattern="yyyy.MM.dd HH:mm"/></span>
                    </div>
                </div>
            </c:if>
            </div>
        </c:if>
    </c:forEach>
</article>