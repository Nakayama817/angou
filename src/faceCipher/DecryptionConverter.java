package faceCipher;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class DecryptionConverter {
	
	protected String decryptionConverter(HttpServletRequest request) {
		
		// セッションを有効にする
		HttpSession session = request.getSession(false);
		
		//入力された文字列を取得
		String faceCipher = request.getParameter("faceCipher");
		
		//入力文字列のnullチェック
		if(StringChecker.NullCheck(faceCipher)) {
			
			String urlString = ErrorMethodpile.NullCheckRoll(request);
			// エラーページへの遷移設定
			return urlString;
		}
		
		//コンバータクラスをインスタンス化
		Converter converter = new Converter();
		
		//先頭の文字を取得
		String headCheckString = faceCipher.substring(0, 2);
		
		//絵文字とRSAで条件分岐
		if(headCheckString.equals("🤗")) {
			
			//RSA暗号文から通常の文字列に変換
			String retString = converter.convertToOiginalRSA(faceCipher);
			
			//変換後の文字列をリクエストにセット
			request.setAttribute("resultMsg", retString);
			
		}
		else if(headCheckString.equals("😣")) {
			//絵文字から通常の文字列に変換
			String retString = converter.convertToOiginalAndEmoji(faceCipher);
			
			//変換後の文字列をリクエストにセット
			request.setAttribute("resultMsg", retString);
		}
		
		
		// 遷移先URLを返す
		return "/jsp/StringConverterResult.jsp";
	}

}
