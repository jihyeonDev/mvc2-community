<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<aside class="left-aside">
	<section class="profile-image">
		<h2 class="profile-name">
			<c:if test="${userId ne null}">
				${userId}
			</c:if>
			<c:if test="${userId eq null}">
				<span>비회원</span>
			</c:if>
		</h2>
		<a href="/feed.do"> <span></span>
		</a>
	</section>
	<nav class="side-nav mcolr">
		<ul>
			<li><a href="feed.do"
			<c:if test="${pageTitle eq 'feed'}">
				class="on"
			</c:if>
			>Feed</a></li>
			<li><a href="artfeed.do"
			<c:if test="${pageTitle eq 'artfeed'}">
				class="on"
			</c:if>
			>Art Feed</a></li>
			<li><a href="myfeed.do"
			<c:if test="${pageTitle eq 'myfeed'}">
				class="on"
			</c:if>
			>My Feed</a></li>
		</ul>
	</nav>
</aside>