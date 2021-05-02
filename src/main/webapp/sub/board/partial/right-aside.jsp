<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<aside class="right-aside">
	<section class="user-info">
		<div class="user-profile">
			<a href="/my.jsp"> <img src="https://via.placeholder.com/40"
				alt="프로필 이미지">
			</a>
		</div>
		<div class="user-text">
			<a href="/my.jsp" class="user-name">
				<c:if test="${userId ne null}">
					${userId}
				</c:if>
				<c:if test="${userId eq null}">
					로그인하세요
				</c:if>
			</a>
			<span class="reg-date">
				<c:if test="${userId ne null}">
					2021.04.06 가입함 <%--			<fmt:formatDate value="${member.regDate}"/>--%>
				</c:if>
			</span>
		</div>
		<div class="user-link">
			<a href="" class="">more</a>
		</div>
	</section>
	<c:if test="${popularTagList ne null}">
		<section class="popular-tags ct-box">
			<h5>인기태그</h5>
			<article class="tags-group">
				<article class="tag-group">
					<c:forEach var="popularTag" items="${popularTagList}" end="4">
						<a href="/search.do?searchBy=${popularTag.tagName}&type=tag">${popularTag.tagName}</a>
					</c:forEach>
				</article><article class="tag-group">
					<c:forEach var="popularTag" items="${popularTagList}" begin="5">
						<a href="/search.do?searchBy=${popularTag.tagName}&type=tag">${popularTag.tagName}</a>
					</c:forEach>
				</article>
			</article>
		</section>
	</c:if>
	<section class="site-info ct-box">
		<address>Lorem ipsum dolor sit amet consectetur adipisicing
			elit. Impedit, autem.</address>
		<nav class="footer-nav">
			<ul>
				<li><a href="">개인정보처리방침</a></li>
				<li><a href="">이용약관</a></li>
				<li><a href="">서비스운영정책</a></li>
				<li><a href="">유료서비스 이용약관</a></li>
				<li><a href="">청소년 보호 정책</a></li>
				<li><a href="">쿠키정책</a></li>
				<li><a href="">Oekaki 이용약관</a></li>
				<li><a href="">Oekaki 소개</a></li>
			</ul>
		</nav>
		<p class="copyright">2021 &copy;JEROPI TEAM.</p>
	</section>
</aside>