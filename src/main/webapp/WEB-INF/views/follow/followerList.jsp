<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팔로우 리스트</title>
<jsp:include page="../module/header.jsp" flush="false" />
<!-- Custom styles -->
<link href="/resources/css/modern-business.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="../module/top2.jsp" flush="false" />
	<div class="form-group">
		<div class="col-sm-12">
			<h4 align="center">&nbsp;</h4>
			<h1 align="center">Follower List</h1>
			<h4 align="center">${login.nickname }의 팔로워</h4>
		</div>
	</div>
	<div class="container">
		<table class="table table-striped nanum table-hover">
			<!-- <table border="1"> -->
			<thead align="center">
				<tr>
					<th>닉네임</th>
				</tr>
			</thead>
			<tbody align="center">
				<c:forEach items="${followerList}" var="followerList">
					<tr>
						<td style="cursor: pointer;"
							onclick="location.href='/videoboard/videoBoardList?searchType=nickname&keyword=${followerList.nickname }'">
							${followerList.nickname}
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<jsp:include page="../module/bottom.jsp" flush="false" />
	</div>

	<h2 align="center">&nbsp;</h2>
</body>
</html>
