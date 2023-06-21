package faceCipher;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = { "/servlet" })
public class ConverterServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 文字コードを設定
		request.setCharacterEncoding("UTF-8");

		// 遷移のためのリクエスト・ディスパッチャー
		RequestDispatcher rd = null;

		try {
			// 遷移元で指定されたアクションを取得する
			String strActionName = request.getParameter("action_name");

			//絵文字に暗号化
			if (strActionName.equals("faceencryption")) {
				//絵文字用のコンバータークラスをインスタンス化
				EmojiConverter emojiConverter = new EmojiConverter();
				//アクションを実行し、遷移先URLを取得
				String forwardPath = emojiConverter.encryptionConverter(request);
				
				//リクエストディスパッチャーに代入
				rd = request.getRequestDispatcher(forwardPath);
				
			} 
			
			//RSA暗号化
			else if (strActionName.equals("RSAencryption")) {
				//RSA用のコンバータークラスをインスタンス化
				RSAConverter rsaConverter = new RSAConverter();
				//アクションを実行し、遷移先URLを取得
				String forwardPath = rsaConverter.encryptionConverter(request);
				
				//リクエストディスパッチャーに代入
				rd = request.getRequestDispatcher(forwardPath);
			}
			
			//復号化
			else if (strActionName.equals("decryption")) {
				//RSA用のコンバータークラスをインスタンス化
				DecryptionConverter decryptionConverter = new DecryptionConverter();
				//アクションを実行し、遷移先URLを取得
				String forwardPath = decryptionConverter.decryptionConverter(request);
				
				//リクエストディスパッチャーに代入
				rd = request.getRequestDispatcher(forwardPath);
				
			}
			
			else {
				//エラー
			}
		} catch (Exception e) {
			//遷移先取得
			String urlString = ErrorMethodpile.NullCheckRoll(request);
			// エラーページへの遷移設定
			rd = request.getRequestDispatcher( urlString);
		}
		// 遷移先のページに遷移
		rd.forward(request, response);
	}

}
