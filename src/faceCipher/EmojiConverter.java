package faceCipher;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class EmojiConverter {
	
	protected String encryptionConverter(HttpServletRequest request) {
		
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
		
		//絵文字に変換
		String retString = converter.convertToUnicodeAndEmoji(faceCipher);
		
		//変換後の文字列をリクエストにセット
		request.setAttribute("resultMsg", retString);
		
		// 遷移先URLを返す
		return "/jsp/StringConverterResult.jsp";
	}

}
