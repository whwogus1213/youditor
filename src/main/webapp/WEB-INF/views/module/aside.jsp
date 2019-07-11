<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
ul {
	padding-inline-start: 10px;
}

li {
	list-style: none;
}

.aside {
	margin: 20px;
	width: 200px;
	height: 279.2px;
	float: left;
	background-color: #eee;
}


</style>
<script type="text/javascript">
	$(function() {
		$.ajax({
			type : "POST",
			url : "/videostar/StarList",
			success : function(data) {

				var html = "";

				if(data.length > 0) {
					for(var i = 0; i < data.length; i++){
						html += "<tr onclick=\"location.href='/videoboard/videoBoardView?boardId="+data[i].boardId+"'\" style='cursor:pointer;'>";
						html += "<th scope='row'>"+(i+1)+"</th>";
						html += "<td>"+data[i].subject+"</td>";
						html += "<td>"+data[i].star+"</td>";
						html += "</tr>";
					}
				}

				$("#boardRank").html(html);

			},
			error : function(data) {
				alert('불러오기 실패');
			}
		});
		var $win = $(window);
		var top = $(window).scrollTop();

		/*사용자 설정 값 시작*/
		var speed = 400; //따라다닐 속도 : "slow", "normal", or "fast" or numeric(단위:msec)
		var easing = 'linear';
		var $layer = $('.float_sidebar'); //레이어 셀렉팅
		var layerTopOffset = 0; //레이어 높이 상한선, 단위:px
		$layer.css('position', 'relative').css('z-index', '1');
		/* 사용자 설정 값 끝*/

		//스크롤 바를 내린 상태에서 리프레시 했을 경우를 위해
		if (top > 0)
			$win.scrollTop(layerTopOffset + top);
		else
			$win.scrollTop(0);

		//스크롤 이벤트가 발생하면
		$(window).scroll(function() {
			yPosition = $win.scrollTop() - 500; //이부분을 조정해서 화면에 보이도록 맞추세요
			if (yPosition < 0) {
				yPosition = 0;
			}
			$layer.animate({
				"top" : yPosition
			}, {
				duration : speed,
				easing : easing,
				queue : false
			})
		});
	});
</script>

<div class="container-fluid">

	<div class="float_sidebar">
		<div class="aside">
			<table class="table table-hover">
				<thead class="thead-dark">
					<tr>
						<th scope="col">#</th>
						<th colspan="3">편집동영상 랭킹</th>
					</tr>
				</thead>
				<tbody id="boardRank">
					

				</tbody>
			</table>


		</div>
	</div>
</div>