<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vivers | ArtFeed</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/import.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/feed.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css"
        integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
</head>

<body>
    <div class="wrap feed artfeed">
    	<div class="background"></div>
    	<%@ include file="/header.jsp" %>
        <section class="top">
            <nav>
                <ul>
                    <li><a href="/feed.do">피드</a></li>
                    <li><a href="/artboard.do">아트보드</a></li>
                </ul>
            </nav>
        </section>
        <div class="container feed">
            <jsp:include page="partial/left-aside.jsp"/>
            <main class="main">
                <jsp:include page="partial/search-box.jsp"/>
                <c:if test="${searchBy eq null}">
                    <jsp:include page="partial/insert-art-form.jsp"/>
                </c:if>
                <c:if test="${searchBy ne null}">
                    <h4>검색어(${type} 검색) : ${searchBy}</h4>
                </c:if>
                <div id="postArea">
                    <jsp:include page="partial/post-box.jsp"/>
                </div>
                <div class="end"></div>
            </main>
            <jsp:include page="partial/right-aside.jsp"/>
        </div>
        <jsp:include page="partial/modal-form.jsp"/>
    </div>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/base.js"></script>
<script src="<%=request.getContextPath()%>/js/feed.js"></script>
</body>

</html>