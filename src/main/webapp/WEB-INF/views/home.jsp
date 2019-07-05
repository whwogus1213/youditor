<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/module/header.jsp" %>

<!DOCTYPE html>
<html>

<head>

  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>YouditoR</title>
  <!-- Custom styles -->
  <link href="/resources/css/modern-business.css" rel="stylesheet">

</head>

<body>

  <jsp:include page="./module/top2.jsp" flush="false"/>
	<jsp:include page="./module/sideChat.jsp" flush="false"/>
  <div class="container">
  
	  <!-- 페이지 내용 시작 -->
	  <header>
	    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
	      <ol class="carousel-indicators">
	        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
	        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
	        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
	      </ol>
	      <div class="carousel-inner" role="listbox">
	
	        <div class="carousel-item active" style="background-image: url('../resources/images/main_img1.jpg')">
	          <div class="carousel-caption d-none d-md-block">
	            <h3 style="color:black">YouDitor은 무슨 뜻인가요?</h3>
	            <p style="color:black">YouDitor 는 Youtube와 Editor의 합성어입니다.</p>
	          </div>
	        </div>
	
	        <div class="carousel-item" style="background-image: url('../resources/images/main_img2.jpg')">
	          <div class="carousel-caption d-none d-md-block">
	            <h3 style="color:gray">YouDitor은 어떤 곳인가요?</h3>
	            <p style="color:gray">YouDitor은 Youtube 편집자들을 위한 커뮤니티입니다.</p>
	          </div>
	        </div>
	
	        <div class="carousel-item" style="background-image: url('../resources/images/main_img.jpg')">
	          <div class="carousel-caption d-none d-md-block">
	            <h3 style="color:black">YouDitor에서는 무엇을 할 수 있나요?</h3>
	            <p style="color:black">다양한 YouDitor들과 당신의 결과물에 대해 토론하고 Creator와 협업할 수 있습니다.</p>
	          </div>
	        </div>
	      </div>
	      <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
	        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
	        <span class="sr-only">Previous</span>
	      </a>
	      <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
	        <span class="carousel-control-next-icon" aria-hidden="true"></span>
	        <span class="sr-only">Next</span>
	      </a>
	    </div>
	  </header>

    <h1 class="my-4"><span class="oi oi-video"></span>핫한 편집 영상</h1>

    <!-- Marketing Icons Section -->
    <div class="row">
      <div class="col-lg-4 mb-4">
        <div class="card h-100">
          <h4 class="card-header">영상 제목</h4>
          <div class="card-body">
<!--           <a href="#"><img class="card-img-top" src="" alt=""></a> -->
			<img width="300" height="200" src="https://img.youtube.com/vi/xZKVYhHInok/mqdefault.jpg"></img>
          </div>
          <div class="card-footer">
            <a href="#" class="btn btn-primary">여기는 좋아요, 작성자 프로필 만들곳</a>
          </div>
        </div>
      </div>
      <div class="col-lg-4 mb-4">
        <div class="card h-100">
          <h4 class="card-header">영상 제목</h4>
          <div class="card-body">
<!--           <a href="#"><img class="card-img-top" src="" alt=""></a> -->
			<img width="300" height="200" src="https://img.youtube.com/vi/xZKVYhHInok/mqdefault.jpg"></img>
          </div>
          <div class="card-footer">
            <a href="#" class="btn btn-primary">여기는 좋아요, 작성자 프로필 만들곳</a>
          </div>
        </div>
      </div>
      <div class="col-lg-4 mb-4">
        <div class="card h-100">
          <h4 class="card-header">영상 제목</h4>
          <div class="card-body">
<!--           <a href="#"><img class="card-img-top" src="" alt=""></a> -->
			<img width="300" height="200" src="https://img.youtube.com/vi/xZKVYhHInok/mqdefault.jpg"></img>
          </div>
          <div class="card-footer">
            <a href="#" class="btn btn-primary">여기는 좋아요, 작성자 프로필 만들곳</a>
          </div>
        </div>
      </div>
    </div>
    <!-- /.row -->

    <!-- 편집자 프로필 -->
    <h2>이번주 편집자 탑3</h2>

    <div class="row">
      <div class="col-lg-4 col-sm-6 portfolio-item">
        <div class="card h-100">
          <a href="#"><img class="card-img-top" src="/resources/images/member1.jpg" alt="" width="80" height="200"></a>
          <div class="card-body">
            <h4 class="card-title">
              <a href="#">편집자 아이디</a>
            </h4>
            <p class="card-text">편집자 설명</p>
          </div>
        </div>
      </div>
      <div class="col-lg-4 col-sm-6 portfolio-item">
        <div class="card h-100">
          <a href="#"><img class="card-img-top" src="/resources/images/member2.jpg" alt="" width="80" height="200"></a>
          <div class="card-body">
            <h4 class="card-title">
              <a href="#">편집자 아이디</a>
            </h4>
            <p class="card-text">편집자 설명</p>
          </div>
        </div>
      </div>
      <div class="col-lg-4 col-sm-6 portfolio-item">
        <div class="card h-100">
          <a href="#"><img class="card-img-top" src="/resources/images/member3.png" alt="" width="80" height="200"></a>
          <div class="card-body">
            <h4 class="card-title">
              <a href="#">편집자 아이디</a>
            </h4>
            <p class="card-text">편집자 설명</p>
          </div>
        </div>
      </div>

    <!-- /.row -->
  	</div>

    <hr>
  	
  </div>
  <!-- /.container -->

<!-- <div class="clear"></div> div 초기화 -->

  <!-- Footer -->
  <!--

  <footer class="py-5 bg-dark container">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright &copy; Your Website 2019 All contents Copyright 2013 SolWeb Inc. all rights reserved<br>
			Contact mail : solweb@lalala.com Tel: +82 02 966 1234 Fax: +82 02 123 4567</p>
    </div>
   -->
    <!-- /.container -->
  <!-- </footer>  -->
  <jsp:include page="./module/bottom.jsp" flush="false"/>


  <script src="/resources/vendor/jquery/jquery.min.js"></script>
  <script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
