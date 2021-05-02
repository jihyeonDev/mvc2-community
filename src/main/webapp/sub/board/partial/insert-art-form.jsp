<%@ page pageEncoding="utf-8" %>
<%
    request.setCharacterEncoding("UTF-8");
    session.setAttribute("userId", "sjh");
%>
<section class="addpost-area ct-box">
    <form action="<%=request.getContextPath()%>/add-post.do" method="POST" enctype="multipart/form-data" id="postForm" class="addpost">
        <div class="hidden-inputs">
            <input type="hidden" name="userId" value="${sessionScope.userId}">
            <input type="hidden" name="boardType" value="1"> <%-- 게시글 타입 -> 0: 피드 / 1: 아트피드--%>
            <input type="file" name="file" id="file" style="display: none;">
            <input type="hidden" name="secretPost" id="secretValue" value="0"> <%-- 비밀글 체크 -> 0 : 전체공개 / 1 : 비밀 --%>
        </div>

        <h3 class="addpost-title">아트보드</h3>
        <div class="post-art">
            <p>한 번에 10장 레이어로 그리고 그림을 공유해보세요</p>
            <button>그림 올리기</button>
        </div>
        <article class="addpost-footer">
            <div class="input-images">
            </div>
            <div class="addpost-btns">
                <div id="secretPost" class="secret-post">
                    <span id="secretBtn" class="">
                        <i class="fas fa-check-circle"></i>
                    </span>
                    <span id="secretBtnText" class="secret-btn-text">Hide from</span>
                </div>
                <input type="submit" value="완료" class="addpost-btn mbgcolr">
            </div>
        </article>
    </form>
</section>