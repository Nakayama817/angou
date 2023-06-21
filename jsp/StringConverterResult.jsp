<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
.aa {
	border-radius: 8px;
	text-align: center;
	padding: 20px 50;
}

.bb {
	background-color: #c79852;
	color: white;
	border: solid 1px #927141;
}

.movingbg {
	height: 100px;
	margin: 30px 0;
	background-image:
		url(https://i.pinimg.com/originals/cb/67/b0/cb67b093779035c8f403f48a17a28bfa.jpg);
	background-size: cover;
	background-repeat: no-repeat;
	background-position: 50% 0%;
}

.vertical {
	animation: vertical 5s linear infinite;
}

@
keyframes vertical { 0% {
	background-size: 1%;
}
100
%
{
background-size
:
300%;
}
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>あたしゃ暗号化しないと許さないよ</title>
</head>
<body>

	<div align="center" class="content">
		<h2>暗号化</h2>
	</div>
	<form align="center" method="POST" action="/fakeWebApp/servlet">
		<label for="faceCipher"></label>
		<textarea align="center" id="faceCipher" type="text" name="faceCipher"
			size="50" class="stringInput" placeholder="文字を入れろ！死にたいのか！"></textarea>
		<br> <br> <input type="radio" name="action_name"
			value="faceencryption">絵文字暗号化
		
		<tr>
			<input type="radio" name="action_name" value="RSAencryption">RSA暗号化
		
		<tr>
			<input type="radio" name="action_name" value="decryption">復号化

		
		<tr>
			<br>
			<br>
			<input class="aa bb button" type="submit" value="実行">
</tr></form>
	<textarea id="copyTarget" class="stringInput" readonly><%= request.getAttribute("resultMsg") %></textarea>
	<br>
	<br>
	<button onclick="copy()">I copy!</button>

<script>
function copy() {
    var copyTarget = document.getElementById("copyTarget");
    copyTarget.select();
    document.execCommand("Copy");
}
    </script>

			<style>
.stringInput {
	display: inline-block;
	width: 100%;
	padding: 10px;
	border: 1px solid #999;
	box-sizing: border-box;
	background: #f2f2f2;
	margin: 0.5em 0;
	line-height: 1.5;
	height: 6em;
}

.button {
	width: 200px;
	padding: 10px;
	border: 0;
	color: #fff;
	background-color: #f00;
	transition: background-color 0.5s, border-radius 1s;
}

.button:hover {
	background-color: #333;
	border-radius: 200px;
	transition: background-color 1s, border-radius 2s;
}
</style>
</body>
</html>