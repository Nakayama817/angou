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
	
	<h2 class="error">
		<%= request.getAttribute("errorMsg") %>
	</h2>
	
	<h3>このページは結構レアだよ。ctrlキーを押してね。</h3>
	
	<canvas id="canvas" width="600" height="500"></canvas>
	
	<h3 id="gamejudge"></h3>
	
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
			


<script>

document.addEventListener('keydown', (event) => {

    if (event.ctrlKey) {

        var demo2 = document.getElementById("gamejudge");
        demo2.innerHTML = "";
            

    	let canvas = document.getElementById("canvas");
    	let ctx = canvas.getContext("2d");

    	/********************
    	 ボールの設定
    	 ********************/
    	const BALL_COLOR = 'skyblue'; //ボールの色
    	const BALL_RADIUS = 10; //ボールの大きさ（半径）
    	var randomX = Math.floor( Math.random() * 300 ) + 50;
    	var randomY = Math.floor( Math.random() * 100 ) + 230;
    	let ballX = canvas.width - randomX; //ゲーム開始時のX軸（←→）の位置
    	let ballY = canvas.height - randomY; //ゲーム開始時のY軸（↑↓）の位置
    	let xMove = 3;  //X方向への移動量
    	let yMove = 3;  //Y方向への移動量

    	/********************
    	 操作する板の設定
    	 ********************/
    	const BAR_COLOR = 'blue'; //板の色
    	const BAR_HEIGHT = 10;  //板の高さ
    	const BAR_WIDTH = 75; //板の幅
    	let barX = (canvas.width - BAR_WIDTH) / 2;
    	let rigthKeyFlag = false;
    	let leftKeyFlag = false;

    	/********************
    	 ブロックの設定
    	 ********************/
    	const BLOCK_COLOR = 'orange'
    	const BLOCK_ROW_COUNT = 4; //ブロックの縦の数
    	const BLOCK_COLUMN_COUNT = 6; //ブロックの横の数
    	const BLOCK_WIDTH = 75; //ブロックの幅
    	const BLOCK_HEGHT = 20; //ブロックの高さ
    	const BLOCK_MARGIN = 10; //ブロックとブロックの隙間
    	const BLOCK_AREA_MARGIN = 30; //ブロックを並べる領域と画面の隙間
    	let blockArray = []; //配置したブロック位置の配列
    	for(let i = 0; i < BLOCK_ROW_COUNT * BLOCK_COLUMN_COUNT; i++){
    	  let row = Math.floor(i/BLOCK_COLUMN_COUNT);
    	  let column = i - (BLOCK_COLUMN_COUNT * row); 
    	  blockArray.push( {
    	    x : BLOCK_AREA_MARGIN + BLOCK_MARGIN * column + BLOCK_WIDTH * column,
    	    y : BLOCK_AREA_MARGIN + BLOCK_MARGIN * row +  BLOCK_HEGHT * row
    	    } );
    	}

    	function drawBall() {
    	  ballX += xMove;
    	  ballY += yMove;
    	  ctx.beginPath();
    	  ctx.arc(ballX, ballY, BALL_RADIUS, 0, Math.PI * 2);
    	  ctx.fillStyle = BALL_COLOR;
    	  ctx.fill();
    	  ctx.closePath();
    	  if (ballX + xMove > canvas.width - BALL_RADIUS || ballX + xMove < BALL_RADIUS) {
    	    xMove = -xMove;
    	  }
    	  if (ballY + yMove < BALL_RADIUS) {
    	    yMove = -yMove;
    	  } else if (ballY + yMove > canvas.height - BALL_RADIUS) {
    	    if (ballX > barX && ballX < barX + BAR_WIDTH) {
    	      yMove = -yMove;
    	    } else {
    	      var demo2 = document.getElementById("gamejudge");
    	      demo2.innerHTML = "ゲームオーバー";
    	      clearInterval(interval)
    	    }
    	  }
    	}

    	function drawBlock() {
    	  blockArray = blockArray.filter( (block, index) => {
    	    if (ballX > block.x && ballX < block.x + BLOCK_WIDTH && ballY > block.y && ballY < block.y + BLOCK_HEGHT) {
    	      yMove = -yMove;
    	    }else{
    	      return block;
    	    }
    	  });
    	  if(blockArray.length === 0){
    	    var demo2 = document.getElementById("gamejudge");
    	    demo2.innerHTML = "ゲームクリア！すごい！";
    	    clearInterval(interval);
    	    return;
    	  }
    	  blockArray.forEach((block) => {
    	    ctx.beginPath();
    	    ctx.rect(block.x, block.y, BLOCK_WIDTH, BLOCK_HEGHT);
    	    ctx.fillStyle = BLOCK_COLOR;
    	    ctx.fill();
    	    ctx.closePath();
    	  });
    	}

    	function drawBar() {
    	  if (rigthKeyFlag && barX < canvas.width - BAR_WIDTH) {
    	    barX += 7;
    	  } else if (leftKeyFlag && barX > 0) {
    	    barX -= 7;
    	  }
    	  ctx.beginPath();
    	  ctx.rect(barX, canvas.height - BAR_HEIGHT, BAR_WIDTH, BAR_HEIGHT);
    	  ctx.fillStyle = BAR_COLOR;
    	  ctx.fill();
    	  ctx.closePath();
    	}

    	function keyDownHandler(e) {
    	  if (e.key == "Right" || e.key == "ArrowRight") {
    	    rigthKeyFlag = true;
    	  } else if (e.key == "Left" || e.key == "ArrowLeft") {
    	    leftKeyFlag = true;
    	  }
    	}

    	function keyUpHandler(e) {
    	  if (e.key == "Right" || e.key == "ArrowRight") {
    	    rigthKeyFlag = false;
    	  } else if (e.key == "Left" || e.key == "ArrowLeft") {
    	    leftKeyFlag = false;
    	  }
    	}

    	function draw() {
    	  ctx.clearRect(0, 0, canvas.width, canvas.height);
    	  drawBall();
    	  drawBlock();
    	  drawBar();
    	}

    	document.addEventListener("keydown", keyDownHandler);
    	document.addEventListener("keyup", keyUpHandler);
    	let interval = setInterval(draw, 10);
    		
    } 
  });

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
  border-radius: 20px;
  transition: background-color 1s, border-radius 2s;
}
.error{
  font-size:  40px;
}

canvas {
  background: black; 
  
}
</style>
</body>
</html>