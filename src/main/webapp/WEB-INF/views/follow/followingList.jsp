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
<title>팔로우 리스트</title>
<jsp:include page="../module/header.jsp" flush="false" />

<style type="text/css">
*{
	margin: 0px;
	padding: 0px;
	box-sizing: border-box;
}

.cardbody{
/* 	height: 100vh; */
/* 	width: 100vw; */
/* 	display: flex; */
/* 	justify-content: center; */
/* 	align-items: center; */
	background: #fff;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
	text-align: center;
} 

/*- - - - - - C O N T A C T - C A R D S - - - - - */

.card{
   height: auto;
	max-height:299px;
	width: 200px; /* ⬅ Size - Small (width: 200px), Medium (width: 225px), Large (width: 250px) */
	overflow: hidden;
	background-color: #FFFFFF;
	border-radius: 10px;
	box-shadow: 0 5px 10px rgba(0,0,0,0.2);
	text-align: center;
	-webkit-transition: .1s ease-in-out;
	transition: .1s ease-in-out;
	display: inline-block;
/* 	margin-right: 25px; */
}
.cardremove{
   height: auto;
	max-height:299px;
	width: 200px; /* ⬅ Size - Small (width: 200px), Medium (width: 225px), Large (width: 250px) */
	overflow: hidden;
	background-color: #FFFFFF;
	border-radius: 10px;
	box-shadow: 0 5px 10px rgba(0,0,0,0.2);
	text-align: center;
	-webkit-transition: .1s ease-in-out;
	transition: .1s ease-in-out;
	display: inline-block;
/* 	margin-right: 25px; */
}


	.card:hover{
		margin-top: -25px;
		box-shadow: 0 15px 20px rgba(0,0,0,0.2);
	}

.banner{
	height: 100px;
	width: 100%;
	padding-top: 35px;
	background-color: #FAFAFA;
	background-size: cover;
	background-position: center;
}

	.card .banner{
		/* ⬇ Photo, geordannatheartist.com */
/*  		background-image: url("https://images.unsplash.com/photo-1533158326339-7f3cf2404354?ixlib=rb-1.2.1&auto=format&fit=crop&w=1004&q=80"); */
 		background-image: url("/resources/images/follow_namecard.jpg");
 		
/* 		background-image: url("/resources/images/main_logo.png"); */
/* 		background-size: contain; */
/* 		background-repeat: no-repeat; */
/* 		background-position: top; */
		
	}
	

.avatar{
	height: 100px;
	width: 100px;
	margin: auto;
	background-size: cover;
	background-position: center;
	background-color: #F1F1F1;
	border: 3px solid #FFFFFF;
	border-radius: 100%;
	box-shadow: 0 2px 2px rgba(0,0,0,0.1);
}
	.card .avatar{
/* 		background-image: url("https://pbs.twimg.com/profile_images/1152340777382612992/A8GyrnBu_400x400.jpg"); */
	}

.h3, .a, .i{
	font-family: 'Roboto', sans-serif;
	font-weight: lighter;
	-webkit-transition: .1s ease-in-out;
	transition: .1s ease-in-out;
}

	.h3{
		margin-top: 45px;
		margin-bottom: 5px;
		font-size: 18px;
		color: #212121;
	}

	.a{
		display: block;
		padding: 5px 0px;
		font-size: 14px;
		color: #9E9E9E;
		text-decoration: none;
	}

		.a:hover{
			background-color: #FAFAFA;
			color: inherit;
			text-decoration: none;
		}
		
.unfollowbtn:hover:before{
	color: #ff2e94;

}


.ul{
	margin-top: 10px;
	padding: 15px 0px;
	background-color: #FAFAFA;
}

.ul .a{
	display: inline;
	margin-right: 10px;
}

.ul .a:last-of-type{
	margin-right: 0px;
}

.ul .a .i:hover{
	transform: scale(1.5);
}
</style>
</head>
	
<body>
	<jsp:include page="../module/top2.jsp" flush="false" />
	
	<!-- 배너 -->
	<div class="form-group" style="margin-top:5%">
		<div class="col-sm-12" style="background-image:url('/resources/images/follow/follow.jpg'); background-position:100% 40%; background-size:160%; color:white; padding-top: 5%; padding-bottom:4%">
			<h1 align="center" style="font-family: 'Lobster', 'Poor story', sans-serif; font-size:50px; letter-spacing:10px; text-shadow: 3px 3px LightCoral;"><strong>Following List</strong></h1>
			<h3 align="center" style="font-family:'Gamja Flower';text-shadow: 2px 2px LightCoral;"><br><strong>${login.nickname}님이 팔로우하는 편집자들</strong></h3>
		</div>
	</div>
	
<div class="container cardbody" style="padding-top: 100px;">

<div class="row">

<c:forEach items="${followingList}" var="followingList">
<!-- card start -->
<div class="col-3">
<div class="card" style="margin-bottom: 35px;">
   <div class="banner">
   	  <a class="unfollowbtn" id = "unfollowbtn${followingList.followAccountId}" onclick="fn_unfollow('${followingList.followAccountId}'); return false;"><i class="fas fa-user-times unfollowbtn" style="position: absolute; top: 2%; left: 85%; color: snow; font-size: 1.2em; cursor: pointer;"></i></a>
   	  <spring:url value='/image/${followingList.picture}' var="pictureURL"/>
      <div class="avatar" style="background-image: url(${pictureURL}); cursor: pointer;" onclick="location.href='/videoboard/videoBoardList?searchType=nickname&keyword=${followingList.nickname}'"> </div>
   </div>
	<h3 class="h3" style="font-family: 'Jua'; font-size: 1.3em;  text-overflow: ellipsis; overflow: hidden; white-space: nowrap;" onclick="location.href='/videoboard/videoBoardList?searchType=nickname&keyword=${followingList.nickname}'">${followingList.nickname }</h3>
   
   <span class="a" data-trigger="hover" data-container="body" data-toggle="popover" data-placement="bottom" data-content="최근 로그인" style="text-align: left; padding-left: 13px; cursor: pointer; text-overflow: ellipsis; overflow: hidden; white-space: nowrap;"><i class="fas fa-rss"></i>&nbsp;<fmt:timeZone value="GMT"><fmt:formatDate value="${followingList.newLogin_date}" pattern="yyyy-MM-dd kk:mm:ss" /></fmt:timeZone></span>
   
   <span class="a" data-trigger="hover" data-container="body" data-toggle="popover" data-placement="bottom" data-content="${followingList.footer}" style="text-align: left; padding-left: 13px; cursor: pointer;  text-overflow: ellipsis; overflow: hidden; white-space: nowrap;"><i class="fas fa-portrait"></i>&nbsp;${followingList.footer} </span>
   
   	<c:if test="${followingList.check}">
   		<span  class="a" style="text-align: left; padding-left: 13px; cursor: pointer;" data-trigger="hover" data-container="body" data-toggle="popover" data-placement="bottom" data-content="서로 팔로우하고 있습니다."><i class="far fa-handshake"></i>&nbsp;
   			맞팔중
   		</span>
   	</c:if>
   	<c:if test="${!followingList.check}">
   		<span  class="a" style="text-align: left; padding-left: 13px; cursor: pointer;" data-trigger="hover" data-container="body" data-toggle="popover" data-placement="bottom" data-content="당신만 팔로우하고 있습니다."><i class="far fa-handshake"></i>&nbsp;
   			팔로우중
   		</span>
   	</c:if>
   	
   	<!-- 나중에 여기다 팔로우사람 평점, 순위 등등 추가하고 싶으면 사용 -->
	<ul class="ul" style="padding-top: 0px; margin-top: 2px;" data-trigger="hover" data-container="body" data-toggle="popover" data-placement="bottom" data-content="팔로우   받은별수   랭킹" >
	<li>
		<i class="fas fa-heart" style="font-size:14px; color: #0096d6;"></i> ${followingList.followCnt}&emsp;
		<i class="far fa-star" style="font-size:14px; color: #0096d6;"></i> ${followingList.starCnt}&emsp;
		<i class="fas fa-medal" style="font-size:14px; color: #0096d6;"></i> ${followingList.starRank}
	</li>
	</ul>
</div>
</div>
<!-- card end -->
</c:forEach>


</div><!-- row div  -->
</div><!-- container div -->


<script type="text/javascript">
// 	// 초기 팔로우 버튼
// 	$(function() {
// 		fn_followbtn();
// 	});

$(function(){
	$('[data-toggle="popover"]').popover({container: "body"});
});

	//팔로우 삭제
	function fn_unfollow(followAccountId) {
		var tr = $("#unfollowbtn" + followAccountId).parent().parent();
		var clonetr = JSON.stringify($("#unfollowbtn" + followAccountId).parent().parent().parent().clone().html());
		console.log(tr);
		console.log(clonetr);
		var json = {
			"followAccountId" : followAccountId
		}
		
		event.stopPropagation();
		
		$.ajax({
			type : "POST",
			url : "/follow/delete",
			data : json,
			success : function(data) {
				if (data == "success1") {
// 					tr.attr("style","");
// 					tr.css("border","1px solid red");
// 					tr.attr("onclick","");
// 					tr.css("opacity","0.4");
// 					tr.attr("class","cardremove");

					tr.css("box-shadow","0 5px 10px #ff2e94");
					tr.find(".unfollowbtn i").attr("class","fas fa-user-plus unfollowbtn");
					$("#unfollowbtn"+followAccountId).attr("onclick","fn_follow('"+followAccountId+"', "+clonetr+"); return false;");
				}
				
			},
			error : function(data) {
				alert("삭제에러");
			}
		});
	}

	//팔로우 추가
	function fn_follow(followerAccountId, clonetr) {
		console.log(followerAccountId);
		console.log(clonetr);
		var tr = $("#unfollowbtn" + followerAccountId).parent().parent().parent();

		var json = {
			"followAccountId" : followerAccountId
		};
					
		event.stopPropagation();
		
		$.ajax({
			type : "POST",
			url : "/follow/insert",
			data : json,
			success : function(data) {
				if (data == "success") {
					tr.html(clonetr);
				}
				
			},
			error : function(data) {
				alert("추가에러");
			}
		});

	}
</script>

</body>
<jsp:include page="../module/bottom.jsp" flush="false"/>
</html>
