<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<section class="addpost-area ct-box">
    <form action="<%=request.getContextPath()%>/add-post.do" method="POST" enctype="multipart/form-data" id="postForm" class="addpost">
        <div class="hidden-inputs">
            <input type="hidden" name="userId" value="${sessionScope.userId}">
            <input type="hidden" name="boardType" value="0"> <%-- 게시글 타입 -> 0: 피드 / 1: 아트피드--%>
            <input type="file" name="file" id="file" style="display: none;">
            <input type="hidden" name="secretPost" id="secretValue" value="0"> <%-- 비밀글 체크 -> 0 : 전체공개 / 1 : 비밀 --%>
        </div>

        <h3 class="addpost-title">포스트 쓰기</h3>
        <article class="content-header">
            <div class="writer-profile">
                <img src="https://via.placeholder.com/200" alt="프로필 이미지">
            </div>
            <div id="textareaWrap" class="content-area">
                <div class="post-input-area">
                    <textarea name="content" id="content" class="textarea placeholder" onkeydown="resize(this)" onkeyup="resize(this)">Vivers에 남겨보세요...</textarea>
                </div>
            </div>
        </article>
        <div id="tagArea" style="display: flex; margin-top: 20px;">
            <input type="text" name="tag" placeholder="태그" style="padding: 5px 10px; border: 1px solid #eee;"/>
            <input type="text" name="tag" placeholder="태그" style="padding: 5px 10px; border: 1px solid #eee;"/>
            <input type="text" name="tag" placeholder="태그" style="padding: 5px 10px; border: 1px solid #eee;"/>
        </div>
        <article class="addpost-footer">
            <div class="input-images">
                <a href="#" id="addFileBtn" class="add-file">
                    <i class="fas fa-palette mbgcolr"></i>
                </a>
                <div class="images">
                    <div class="image-preview"></div>
                </div>
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