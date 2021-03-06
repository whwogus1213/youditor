<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구인구직 - YouditoR</title>
<style type="text/css">
button[type="button"] {
	font-family: FontAwesome;
}
</style>
<jsp:include page="../module/header.jsp" flush="false" />
<script src="https://kit.fontawesome.com/e83fabbb47.js"></script>
<link href="/resources/css/pagination.css" rel="stylesheet">
<style type="text/css">
.fa-search:before {
    content: "\f002";
    cursor: pointer;
}

.p1 a.is-active {
	background-color: PaleVioletRed;
	color: white;
}

 @media (min-width:476px){.container{max-width: 240px;}}
 @media (min-width:576px){.container{max-width: 300px;}}
 @media (min-width:768px){.container{max-width: 440px;}}
 @media (min-width:992px){.container{max-width: 640px;}}
 @media (min-width:1080px){.container{max-width: 800;}}
 @media (min-width:1200px){.container{max-width: 925px;}}
 @media (min-width:1400px){.container{max-width: 1100px;}}
 .container {
    float: left;
}
footer{
    margin-top: 45%;
}
.p1 a.is-active {
	background-color: PaleVioletRed ;
	border-radius: 100%;
	color: #fff;
}
</style>
<script>
	// 이전 버튼
	/*function fn_prev(page, range, rangeSize, searchType, keyword) {
		var page = ((range - 2) * rangeSize) + 1;
		var range = range - 1;
		var url = "${pageContext.request.contextPath}/recruitboard/recruitBoardList";

		url = url + "?page=" + page;
		url = url + "&range=" + range;
		url = url + "&searchType=" + searchType;
		url = url + "&keyword=" + keyword;
		location.href = url;
	}*/
	// 이전 버튼
	function fn_prev(categoryId, page, rangeSize, searchType, keyword) {
		var page = parseInt((page - 1) / rangeSize) * rangeSize;
		var url = "${pageContext.request.contextPath}/recruitboard/recruitBoardList?";

		if(categoryId != 0) {
			url = url + "categoryId=" + categoryId + "&";
		}
		url = url + "page=" + page;
		if(keyword != null && keyword != "") {
			url = url + "&searchType=" + searchType;
			url = url + "&keyword=" + keyword;
		}
		location.href = url;
	}

	//페이지 번호 클릭
	/*function fn_pagination(page, range, rangeSize, searchType, keyword) {
		var url = "${pageContext.request.contextPath}/recruitboard/recruitBoardList";

		url = url + "?page=" + page;
		url = url + "&range=" + range;
		url = url + "&searchType=" + searchType;
		url = url + "&keyword=" + keyword;
		
		location.href = url;
	}*/
	//페이지 번호 클릭
	function fn_pagination(categoryId, page, searchType, keyword) {
		var url = "${pageContext.request.contextPath}/recruitboard/recruitBoardList?";

		if(categoryId != 0) {
			url = url + "categoryId=" + categoryId + "&";
		}
		url = url + "page=" + page;
		if(keyword != null && keyword != "") {
			url = url + "&searchType=" + searchType;
			url = url + "&keyword=" + keyword;
		}
		location.href = url;
	}

	//다음 버튼 이벤트
	/*function fn_next(page, range, rangeSize, searchType, keyword) {
		var page = parseInt((range * rangeSize)) + 1;
		var range = parseInt(range) + 1;
		var url = "${pageContext.request.contextPath}/recruitboard/recruitBoardList";

		url = url + "?page=" + page;
		url = url + "&range=" + range;
		url = url + "&searchType=" + searchType;
		url = url + "&keyword=" + keyword;
		location.href = url;
	}*/
	//다음 버튼 이벤트
	function fn_next(categoryId, page, rangeSize, searchType, keyword) {
		var page = ((parseInt((page - 1) / rangeSize) + 1) * rangeSize) + 1;
		var url = "${pageContext.request.contextPath}/recruitboard/recruitBoardList?";

		if(categoryId != 0) {
			url = url + "categoryId=" + categoryId + "&";
		}
		url = url + "page=" + page;
		if(keyword != null && keyword != "") {
			url = url + "&searchType=" + searchType;
			url = url + "&keyword=" + keyword;
		}
		location.href = url;
	}

	// 검색버튼 이벤트
	$(function(){
		$('#keyword').keypress(function(e) {

			var keycode = event.keyCode;
			// enter를 쳤을 때 keycode가 13이다
			if (keycode == '13') {
				var url = "${pageContext.request.contextPath}/recruitboard/recruitBoardList?";
				var categoryId = "${categoryInfo.categoryId}";
				if(categoryId != 0) {
					url = url + "categoryId=" + categoryId + "&";
				}
				url = url + "searchType=" + $('#searchType').val();
				url = url + "&keyword=" + $('#keyword').val();

				location.href = url;
				console.log(url);
			}
			e.stopPropagation();
		});
	});

	// 검색
	/*$(document).on('click', '#btnSearch', function(e) {
		e.preventDefault();
		var url = "${pageContext.request.contextPath}/recruitboard/recruitBoardList";
		url = url + "?searchType=" + $('#searchType').val();
		url = url + "&keyword=" + $('#keyword').val();

		location.href = url;
		console.log(url);
	});*/
	// 검색
	function searchBtn(categoryId, searchType, keyword) {
		var url = "${pageContext.request.contextPath}/recruitboard/recruitBoardList?";
		var categoryId = "${categoryInfo.categoryId}";
		if(categoryId != 0) {
			url = url + "categoryId=" + categoryId + "&";
		}
		url = url + "searchType=" + $('#searchType').val();
		url = url + "&keyword=" + $('#keyword').val();
		
		location.href = url;
	}
	
	//게시글을 삭제 했을시 삭제했다고 경고창이 떳다가 사라지는 기능
	var result = '${result}';
	$(function(){
		if(result === 'deleteOK'){
			$('#deleteOK').removeClass('hidden');
			$('#deleteOK').removeAttr("style");
			$('#deleteOK').fadeOut(2000);
		}
	})
</script>
</head>
<body>
	<jsp:include page="../module/top2.jsp" flush="false"/>
	
	<!-- 배너 -->
	<div class="form-group">
		<div class="col-sm-12" style="background-image:url('/resources/images/recruit/recruit.jpg'); background-position:50% 60%; background-size:100%; font-family: 'Song Myung', sans-serif; color:white; text-shadow: -1px 0 LightPink, 0 1px LightPink, 1px 0 LightPink, 0 -1px LightPink; padding-top:130px; padding-bottom:5%">
			<h1 align="center" style="font-size:50px; letter-spacing:10px"><strong>구인/구직</strong></h1>
			<h4 align="center"><br>당신의 파트너가 될 YouditoR가 있습니다.</h4>
		</div>
	</div>
	
	<div id="deleteOK" class="alert alert-danger hidden" role="alert" style="visibility:hidden">글이 삭제되었습니다.</div>
	
	<jsp:include page="../module/aside.jsp" flush="false" />
	<div class="container">
		<table class="table nanum table-hover" style="height:auto; table-layout: fixed;">
			<!-- <table border="1"> -->
			<thead align="center">
				<tr style="color:PaleVioletRed;">
					<th style="width: 80px;">번호</th>
					<th style="width: 100px;">분류</th>
					<th>제목</th>
					<th style="width: 100px;">작성자</th>
					<th style="width: 130px;">작성일</th>
					<th style="width: 80px;">조회수</th>
				</tr>
			</thead>
			<tbody align="center">
				<c:forEach items="${RecruitBoardList}" var="RecruitBoardList">
					<tr>
						<td style="vertical-align: middle;">${RecruitBoardList.boardId}</td>
						<td>
							<c:if test="${RecruitBoardList.categoryId eq 1}">
								<button type="button" class="btn btn-link btn-sm" onclick="location.href='/recruitboard/recruitBoardList?categoryId=1'" style="background-color: Plum; color: white;">구인</button>
							</c:if>
							<c:if test="${RecruitBoardList.categoryId eq 2}">
								<button type="button" class="btn btn-link btn-sm" onclick="location.href='/recruitboard/recruitBoardList?categoryId=2'" style="background-color:LavenderBlush ; color: PaleVioletRed ;">구직</button>
							</c:if>
						</td>
						<td align="left" style="padding-left: 30px; cursor: pointer; vertical-align: middle;"
						onclick="location.href='/recruitboard/recruitBoardView?boardId=${RecruitBoardList.boardId}'">
								${RecruitBoardList.subject}
						</td>
						<td  style="vertical-align: middle; text-overflow: ellipsis; overflow: hidden; white-space: nowrap; padding-left: 0px;">
							<!-- 내 아이디에 마우스 오버 -->						
							<c:if test="${RecruitBoardList.accountId eq login.accountId }">
								<img src="<spring:url value='/image/${RecruitBoardList.picture}'/>" class=" mx-auto rounded-circle" 
								width="20px" height="20px"/>&nbsp;${RecruitBoardList.nickname}<br>
							</c:if>
							<!-- 다른 사람 아이디에 마우스 오버 -->
							<c:if test="${RecruitBoardList.accountId ne login.accountId }">
								<a id="dropdownBtn${RecruitBoardList.boardId}" onclick="checkFollow('${RecruitBoardList.accountId}'); return false;"
								data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="cursor:pointer">
									<img src="<spring:url value='/image/${RecruitBoardList.picture}'/>" class=" mx-auto rounded-circle" width="20px" height="20px"/>
									${RecruitBoardList.nickname}
								</a>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="/recruitboard/recruitBoardList?searchType=nickname&keyword=${RecruitBoardList.nickname}">
										<i class="far fa-file-alt" style="width: 20; height: 20"></i>&nbsp;&nbsp;글 더보기
									</a>
									<c:if test="${login.accountId ne null }">
										<a class="dropdown-item" href="#" onclick="messagePopup(); return false;">
											<i class="far fa-envelope"></i>&nbsp;&nbsp;쪽지 보내기
										</a>
										<script type="text/javascript">
										function messagePopup() {
											var nickname = "${RecruitBoardList.nickname }";
											var win = window.open("/message/writePopup.do?nickname=" + nickname, "_blank", 
													"width=650, height=470, left=200, top=200, location=no, menubar=no, resizble=no, scrollbars=no, status=no, titlebar=no, toolbar=no");
										}
										</script>
										<a class="dropdown-item dropdownBtnFollow${RecruitBoardList.accountId}"
										onclick="fn_follow(${RecruitBoardList.accountId}); return false;" style="cursor: pointer;">
											<i class="far fa-heart"></i>&nbsp;&nbsp;팔로우하기
										</a>
									</c:if>
								</div>
							</c:if>
						</td>
						<td style="vertical-align: middle;">${RecruitBoardList.reg_date}</td>
						<td style="vertical-align: middle;">${RecruitBoardList.viewCount}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


		<!-- 페이징 검색 시작 -->
		<div class="col-12" style="padding-top:1%" align="center">
			<!-- 페이징 -->
			<div class="p1 pagination col-12" style="display: block; text-align: center; margin-bottom: 10px;">
			<ul class="pagination" style="display:table; margin-left:auto; margin-right: auto;">
					<c:if test="${pagination.prev}">
						<a href="#" onclick="fn_prev('${categoryInfo.categoryId }', '${pagination.page}', '${pagination.rangeSize}',
						'${pagination.searchType}', '${pagination.keyword}'); return false;"><li>◀</li></a>
					</c:if>
					<c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" var="idx">
						<a class="<c:out value="${pagination.page == idx ? 'is-active' : ''}"/>" href="#"
						onclick="fn_pagination('${categoryInfo.categoryId }', '${idx}', '${pagination.searchType}', 
						'${pagination.keyword}'); return false;">
							<li>${idx}</li></a>
					</c:forEach>
					<c:if test="${pagination.next}">
						<a href="#" onclick="fn_next('${categoryInfo.categoryId }', '${pagination.page}', '${pagination.rangeSize}',
						'${pagination.searchType}', '${pagination.keyword}'); return false;"><li>▶</li></a>
					</c:if>
				</ul>
			</div>
			<!-- 페이징 -->

			<!-- 검색 -->
			<div class="input-group col-12" style="padding-top: 6px;" align="right">
				<div class="col-3" align="right" style="padding-right: 0px;">
					<select class="form-control form-control-sm" name="searchType"
						id="searchType" style="width: 66.6%">
						<option value="subject">제목</option>
						<option value="object">본문</option>
						<option value="nickname">닉네임</option>
					</select>
				</div>
				<div class="col-6" align="right" style="padding-right: 0px; padding-left: 5px;">
					<input type="text" class="form-control form-control-sm" name="keyword" id="keyword" style="float: left;">
				</div>

				<div class="col-1" style="padding-left: 0px;text-align: center;padding-right: 0px;padding-top: 5px; color: PaleVioletRed ;">
					<i class="fas fa-search" id="btnSearch" onclick="searchBtn('${categoryInfo.categoryId }',
					'${pagination.searchType}', '${pagination.keyword}'); return false;"></i>
				</div>

				<div class="col-2" align="right" style="padding-left: 0px; padding-right: 5px;">
					<c:if test="${login ne null }">
						<c:if test="${login.authority >= categoryInfo.viewAuthority }">
							<button type="button" class="btn btn-sm"
								onclick="location.href='/recruitboard/write.do' " style="background-color: PaleVioletRed ; color: white;">글쓰기</button>
						</c:if>
					</c:if>
				</div>
			</div>
			<!-- 검색 -->
		</div>
		<!-- 페이징, 검색 끝 -->

	</div>
	<br>
<script type="text/javascript">
	 //팔로우 추가
	 function fn_follow(accountId) {
	 	var tr = $(".dropdownBtnFollow"+accountId);
	 	var json = {
	 		"followAccountId" : accountId
	 	}
	 				
	 	event.stopPropagation();
	 	
	 	$.ajax({
	 		type : "POST",
	 		url : "/follow/insert",
	 		data : json,
	 		success : function(data) {
	 			if (data == "success") {
	 				tr.attr("style","");
	 				tr.css("color", "red");
	 				tr.find("i").css("color","red");
	 				tr.attr("onclick","");
	 				tr.css("cursor","default");
	 				tr.html("<i class='far fa-heart'></i>&nbsp;&nbsp;팔로우중");
	 				/* 웹소켓으로 팔로우 알람 */
	 				var sendText = {};
	 				sendText.from = "followAlarm";
	 				sendText.text = String(accountId);
	 				sock.send(JSON.stringify(sendText));
	 				console.log(tempcheck);
	 			}
	 			
	 		},
	 		error : function(data) {
	 			alert("에러");
	 		}
	 	});
	 	
	 }

	 //팔로우 체크
	 function checkFollow(accountId){
	 	console.log(accountId+"bbbbbbbbbbbbbbbbbbbbbb");
	 	var tr = $(".dropdownBtnFollow"+accountId);
	 	var json = {
	 			"followAccountId" : accountId,
	 			"followerAccountId" : ${login.accountId}
	 			};
	 	$.ajax({
	 		type : "POST",
	 		url : "/follow/check",
	 		data : json,
	 		success : function(data) {
	 			if (data == "1") {
	 				console.log("팔로우 되어있습니다.");
	 				tr.attr("style","");
	 				tr.css("color", "red");
	 				tr.find("i").css("color","red");
	 				tr.attr("onclick","");
	 				tr.css("cursor","default");
	 				tr.html("<i class='far fa-heart'></i>&nbsp;&nbsp;팔로우중");
	 				
	 			} else {
	 				console.log("팔로우 아님");
	 			}
	 		},
	 		error : function(data) {
	 			alert("에러");
	 		}
		});
	}
</script>
</body>
<jsp:include page="./../module/bottom.jsp" flush="false" />
</html>
