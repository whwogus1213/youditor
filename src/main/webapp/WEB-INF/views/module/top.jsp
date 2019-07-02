<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      <a class="navbar-brand" href="/">YouDitor</a>
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a class="nav-link" href="/">홈</a>
          </li>
          <li class="nav-item">
			<a class="nav-link" href="/noticeboard/noticeBoardList">공지</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownPortfolio" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
             	 카테고리
            </a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownPortfolio">
              <a class="dropdown-item" href="/videoboard/videoBoardList?category=0">전체</a>
              <a class="dropdown-item" href="/videoboard/videoBoardList?category=1">게임</a>
              <a class="dropdown-item" href="/videoboard/videoBoardList?category=2">먹방</a>
              <a class="dropdown-item" href="/videoboard/videoBoardList?category=3">일상</a>
              <a class="dropdown-item" href="/videoboard/videoBoardList?category=4">모터</a>
              <a class="dropdown-item" href="/videoboard/videoBoardList?category=5">스포츠</a>
              <a class="dropdown-item" href="/videoboard/videoBoardList?category=6">예능</a>
            </div>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">구인구직</a>
          </li>
				<li class="nav-item"><a class="nav-link" href="#">영상편집팁</a></li>

				<c:if test="${login.email ne null }">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdownPortfolio" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> 팔로우 </a>
						<div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownPortfolio">
							<a class="dropdown-item" href="/follow/followingList">팔로잉</a>
							<a class="dropdown-item" href="/follow/followerList">팔로워</a>
						</div>
					</li>
					<li class="nav-item"><a class="nav-link" href="/accounts/logout">로그아웃</a></li>
				</c:if>
				<c:if test="${login.email eq null }">
					<li class="nav-item"><a class="nav-link"
						href="/accounts/login.do">로그인</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/accounts/join.do">회원가입</a></li>
				</c:if>
			</ul>
      </div>
    </div>
  </nav>