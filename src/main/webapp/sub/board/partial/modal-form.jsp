<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
