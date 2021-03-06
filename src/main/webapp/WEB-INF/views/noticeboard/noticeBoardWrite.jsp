<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지게시판 - 작성 - YouditoR</title>
<jsp:include page="./../module/header.jsp" flush="false"/>
</head>
<body>
<jsp:include page="../module/top2.jsp" flush="false"/>
<c:choose>
<c:when test="${login.authority >= 4 }">
	<!-- 배너 -->
	<div class="form-group">
		<div class="col-sm-12"  style="background-image:url('/resources/images/notice/notice.jpg'); background-position:50% 60%; font-family: 'Jua', sans-serif; color:white; padding-top:130px; padding-bottom:5%">
			<h1 align="center" style="font-size:50px; letter-spacing:10px"><strong>공지사항</strong></h1>
			<h5 align="center"><br>YouditoR의 최신 소식과 이벤트를 알려드립니다.</h5>
		</div>
	</div>
	<div class="container">
		<div align="right" >
			<br>
	        <c:choose>
	        	<c:when test="${row ne null }">
					<h1 style="color:DarkGreen; font-family: 'Jua'">공지사항 수정</h1>
	        	</c:when>
	        	<c:otherwise>
					<h1 style="color:DarkGreen; font-family: 'Jua'">공지사항 작성</h1>
	        	</c:otherwise>
	        </c:choose>
		</div>
		<hr>
		<br>
	    <form class="form-horizontal" method="post" name="noticeWriteForm">
	    	<div class="form-inline">
	        	<label class="control-label col-sm-2"><strong style="color:DarkGreen">제목</strong></label>
	        	<div class="col-sm-8">
	        		<c:choose>
	        			<c:when test="${row ne null }">
	        				<input type="text" class="form-control" name="subject" id="subject" maxlength="50" style="width:100%" value="${row.subject }" placeholder="Enter Title">
	        				<input type="hidden" name="boardId" id="boardId" value="${row.boardId }">
	        			</c:when>
	        			<c:otherwise>
	        				<input type="text" class="form-control" name="subject" id="subject" maxlength="50" placeholder="Enter Title">
	        			</c:otherwise>
	        		</c:choose>
	        		<input type="hidden" class="form-control" name="accountId" id="accountId" value="${login.accountId}" readonly>
	        	</div>
			</div>
			<br>
			<div class="form-inline">
				<label class="control-label col-sm-2"><strong style="color:DarkGreen">카테고리</strong></label>
				<div class="col-sm-3">
	        		<select class="browser-default custom-select" name="categoryId" id="categoryId" style="width:190px">
		           		<c:forEach items="${nCatList}" var="nCatList">
		           			<c:choose>
		           				<c:when test="${nCatList.categoryId == 99 }">
		           					<c:if test="${login.authority >= nCatList.editAuthority }">
		           						<c:choose>
						        			<c:when test="${row.categoryId == nCatList.categoryId }">
						        				<option value="${nCatList.categoryId }" selected>${nCatList.categoryName }</option>
						        			</c:when>
						        			<c:otherwise>
						        				<option value="${nCatList.categoryId }">${nCatList.categoryName }</option>
						        			</c:otherwise>
						        		</c:choose>
		           					</c:if>
		           				</c:when>
		           				<c:otherwise>
		           						<c:choose>
						        			<c:when test="${row.categoryId == nCatList.categoryId }">
						        				<option value="${nCatList.categoryId }" selected>${nCatList.categoryName }</option>
						        			</c:when>
						        			<c:otherwise>
						        				<option value="${nCatList.categoryId }">${nCatList.categoryName }</option>
						        			</c:otherwise>
						        		</c:choose>
		           				</c:otherwise>
		           			</c:choose>
		           		</c:forEach>
		           </select>
				</div>
			</div>
			<br>
			<div class="form-inline">
		        <label class="control-label col-2"><strong style="color:DarkGreen">작성자</strong></label>
		        <div class="col-sm-3">
					<input type="text" class="form-control" maxlength="50" value="${login.nickname}" readonly>
					<input type="text" class="form-control" name="accountId" id="accountId" maxlength="50" value="${login.accountId}" style="display:none" readonly>
		        </div>
			</div>
			<br>
			<div class="form-inline">
				<label class="control-label col-sm-2"><strong style="color:DarkGreen">내용</strong></label>
				<div class="col-sm-3">
	        		<c:choose>
	        			<c:when test="${row ne null }">
	        				<textarea rows="10" cols="100" name="object" id="object"><c:out value="${row.object }"/></textarea>
	        			</c:when>
	        			<c:otherwise>
	        				<textarea rows="10" cols="100" name="object" id="object"></textarea>
	        			</c:otherwise>
	        		</c:choose>
				</div>
			</div>
			<br>
			<div class="col-sm-11" align="right">
        		<c:choose>
        			<c:when test="${row ne null }">
        				<button type="button" class="btn btn-md" onclick="updateBtn();"><strong style="color:DarkGreen;"><i class="fas fa-eraser"></i>&nbsp;&nbsp;수정</strong></button>|
        			</c:when>
        			<c:otherwise>
        				<button type="button" class="btn btn-md" onclick="insertBtn();"><strong style="color:DarkGreen;"><i class="fas fa-file-upload"></i>&nbsp;&nbsp;올리기</strong></button>|
        			</c:otherwise>
        		</c:choose>
				<script type="text/javascript">
				function insertBtn() {
		  			var subject = $("#subject").val();
		  			var object = $("#object").val();
		  			if(subject.length == 0) { alert("제목을 입력해 주세요."); $("#subject").focus(); return; }
		  			if(object.length == 0) { alert("내용을 입력해 주세요."); $("#object").focus(); return; }

		  			document.noticeWriteForm.action = "/noticeboard/insertNoticeBoardPro";
		  			document.noticeWriteForm.method = "POST";
		  			document.noticeWriteForm.submit();
				}
				
				function updateBtn() {
		  			var subject = $("#subject").val();
		  			var object = $("#object").val();
		  			if(subject.length == 0) { alert("제목을 입력해 주세요."); $("#subject").focus(); return; }
		  			if(object.length == 0) { alert("내용을 입력해 주세요."); $("#object").focus(); return; }

		  			document.noticeWriteForm.action = "/noticeboard/updateNoticeBoardPro";
		  			document.noticeWriteForm.method = "POST";
		  			document.noticeWriteForm.submit();
				}
				</script>
				<button type="reset" class="btn btn-md"><strong style="color:DarkGreen;"><i class="fas fa-undo-alt"></i>&nbsp;&nbsp;초기화&nbsp;</strong></button>|
				<button type="button" class="btn btn-md hBack"><strong style="color:DarkGreen;"><i class="fas fa-backspace"></i>&nbsp;&nbsp;뒤로 가기</strong></button>
					<script>
						$(".hBack").on("click", function(e){
						    e.preventDefault();
						    window.history.back();
						});
					</script>
			</div>
			<br>
		</form>
	</div>
</c:when>
<c:otherwise>
	<c:redirect url="/"/>
</c:otherwise>
</c:choose>
<jsp:include page="../module/bottom.jsp" flush="false" />
</body>
</html>
