<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section class="search ct-box">
    <form action="/search.do" method="post" class="search-form">
        <input name="searchBy" class="search-input" type="text" placeholder="글 내용, #태그, @작성자 검색">
        <div class="search-btn">
            <input class="search-submit" type="submit" value="검색">
            <i class="fas fa-search"></i>
        </div>
    </form>
</section>