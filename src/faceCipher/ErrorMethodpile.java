package faceCipher;

import java.util.Random;

import jakarta.servlet.http.HttpServletRequest;

public class ErrorMethodpile {
	
	public static String NullCheckRoll(HttpServletRequest request) {
			
		Random random01 = new Random();
		int randomValue01 = random01.nextInt((ErrorStrConstValue.ERROR_STR.length - 1));
		
		Random random02 = new Random();
		int randomValue02 = random02.nextInt(100);
		
		//あたりのエラーメッセージのチェック
		if(randomValue02 == 1) {
			request.setAttribute("errorMsg", ErrorStrConstValue.ERROR_STR[0][1]);
			// エラーページへの遷移設定
			return "/jsp/rareError.jsp";
		}
		else {
			request.setAttribute("errorMsg", ErrorStrConstValue.ERROR_STR[randomValue01 + 1][1]);
			// エラーページへの遷移設定
			return "/jsp/error.jsp";
		}
	}

}
