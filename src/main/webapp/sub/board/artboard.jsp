<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Viverse | Feed</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/import.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/feed.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css"
        integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
</head>

<body>
    <div class="wrap feed artboard">
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
		<section class="canvas-wrap">
			<canvas id="canvas" width="800" height="580"></canvas>
		</section>
        <section class="ab-tools">
            <section class="ab-colors">
                <div class="color red">#db3838</div>
                <div class="color green">#b2c225</div>
                <div class="color blue">#40a4d8</div>
                <div class="color yellow">#fecc2f</div>
                <div class="color purple">#a363d9</div>
                <div class="color pink">#ee657a</div>
                <div class="color black">#223</div>
                <div class="color white">#fefefe</div>
                <div id="pickerBtn" class="color picker-btn"></div> <!-- onclick open 컬러피커 -->
        	    <div id="picker" class="picker"></div>
            </section>
            <section>
                <input type="range" id="brushSize" class="brush-size-input" min="0" max="10" step="1">
            </section>
            <section class="ab-btns">
                <a href="" download="myart.png" id="saveMyArtBtn">그림저장</a>
                <button type="button" id="artboardFormBtn">글쓰기</button>
                <div id="formBack" style="display: none; position: fixed; top: 0; left: 0; right: 0; bottom: 0; background: rgba(100, 100, 100, 0.3);"></div>
                <form id="artboardWriteForm" style="z-index: 3; position: fixed; top: 50%; left: 50%; width: 500px; height: 400px; padding: 30px; background: #fff; transform: translate(-50%, -50%);">
                    <button type="button">닫기</button>
                    <h4>글쓰기</h4>
                    <textarea type="text" name="content"></textarea>
                    <label for="secretPost">비밀글</label>
                    <input type="checkbox" name="secretPost" id="secretPost">
                    <input type="submit" name="" value="글쓰기">
                </form>
            </section>
        </section>
    </div>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/base.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@jaames/iro@5"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/canvas.js"></script>
<script>
    $(function () {
        $('#formBack').stop().hide();
        $('#artboardWriteForm').stop().hide();
        $('#artboardFormBtn').click(function () {
            $('#formBack').stop().show();
            $('#artboardWriteForm').stop().show();
        });
        $('#formBack').click(function () {
            $('#formBack').stop().hide();
            $('#artboardWriteForm').stop().hide();
        });
        $('#artboardWriteForm > button').click(function () {
            $('#formBack').stop().hide();
            $('#artboardWriteForm').stop().hide();
        });
    });
</script>
</body>

</html>