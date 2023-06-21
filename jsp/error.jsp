<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="NewFile.css">
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
			
		<h2 class="error">
			 <%= request.getAttribute("errorMsg") %>
		</h2>

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
  border-radius: 20px;
  transition: background-color 1s, border-radius 2s;
}
.error{
  font-size:  100px;
}
</style>
</body>
</html>