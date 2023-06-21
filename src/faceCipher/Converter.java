package faceCipher;

public class Converter {
	
	/**
	 * Unicode文字列に変換する("あ" -> "\u3042")
	 * @param original
	 * @return
	 */
	public String convertToUnicodeAndEmoji(String original)
	{
	    //文字列をunicodeに変換
		StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < original.length(); i++) {
	        sb.append(String.format("\\u%04X", Character.codePointAt(original, i)));
	    }
	    String unicode = sb.toString();
	    
	    //nullだったらエラー
	    if(unicode == null) {
	    	return null;
	    }
	    
	    //unicodeで使う文字を顔文字に変換
	    String unicodeslash = unicode.replaceAll("\\\\", "🤐");
	    String unicodeu = unicodeslash.replaceAll("u", "🌛");
	    String unicode0 = unicodeu.replaceAll("0", "😀");
	    String unicode1 = unicode0.replaceAll("1", "😁");
	    String unicode2 = unicode1.replaceAll("2", "😇");
	    String unicode3 = unicode2.replaceAll("3", "😈");
	    String unicode4 = unicode3.replaceAll("4", "😋");
	    String unicode5 = unicode4.replaceAll("5", "😌");
	    String unicode6 = unicode5.replaceAll("6", "😍");
	    String unicode7 = unicode6.replaceAll("7", "😒");
	    String unicode8 = unicode7.replaceAll("8", "😎");
	    String unicode9 = unicode8.replaceAll("9", "😤");
	    String unicodeA = unicode9.replaceAll("A", "😭");
	    String unicodeB = unicodeA.replaceAll("B", "😲");
	    String unicodeC = unicodeB.replaceAll("C", "🙃");
	    String unicodeD = unicodeC.replaceAll("D", "🤓");
	    String unicodeE = unicodeD.replaceAll("E", "🤪");
	    String unicodeF = unicodeE.replaceAll("F", "😰");
	    
	    //検査文字を追加
	    String retString = "😣";
	    
	    retString = retString.concat(unicodeF);

	    return retString;
	}

	/**
	 * Unicode文字列から元の文字列に変換する ("\u3042" -> "あ")
	 * @param unicode
	 * @return
	 */
	public String convertToOiginalAndEmoji(String unicode) {
		
		//顔文字からunicode仕様文字に変換
		String unicodeslash = unicode.replaceAll("🤐", "\\\\");
		String unicodeu = unicodeslash.replaceAll("🌛", "u");
	    String unicode0 = unicodeu.replaceAll("😀", "0");
	    String unicode1 = unicode0.replaceAll("😁", "1");
	    String unicode2 = unicode1.replaceAll("😇", "2");
	    String unicode3 = unicode2.replaceAll("😈", "3");
	    String unicode4 = unicode3.replaceAll("😋", "4");
	    String unicode5 = unicode4.replaceAll("😌", "5");
	    String unicode6 = unicode5.replaceAll("😍", "6");
	    String unicode7 = unicode6.replaceAll("😒", "7");
	    String unicode8 = unicode7.replaceAll("😎", "8");
	    String unicode9 = unicode8.replaceAll("😤", "9");
	    String unicodeA = unicode9.replaceAll("😭", "A");
	    String unicodeB = unicodeA.replaceAll("😲", "B");
	    String unicodeC = unicodeB.replaceAll("🙃", "C");
	    String unicodeD = unicodeC.replaceAll("🤓", "D");
	    String unicodeE = unicodeD.replaceAll("🤪", "E");
	    String unicodeF = unicodeE.replaceAll("😰", "F");
	    String unicodeFace = unicodeF.replace("😣","");
		
		//unicodeから日本語に変換
	    String[] codeStrs = unicodeFace.split("\\\\u");
	    int[] codePoints = new int[codeStrs.length - 1]; // 最初が空文字なのでそれを抜かす
	    for (int i = 0; i < codePoints.length; i++) {
	        codePoints[i] = Integer.parseInt(codeStrs[i + 1], 16);
	    }
	    String encodedText = new String(codePoints, 0, codePoints.length);
	    return encodedText;
	}
	
	
	/**
	 * RSA暗号技術で暗号化
	 * @param original
	 * @return
	 */
	public String convertToUnicodeRSA(String original)
	{
		//文字列をunicodeに変換
		StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < original.length(); i++) {
	        sb.append(String.format("\\u%04X", Character.codePointAt(original, i)));
	    }
	    String unicode = sb.toString();
	    
	    //unicode変換された文字列を一文字ずつ切り分け
	    String unicodeNumberString = unicode.replaceAll("\\\\u", ",");
	    unicodeNumberString = unicodeNumberString.replaceFirst(",", "");
	    
	    //秘密鍵と割る数を取得
	    String unicodeNumberArray[] = unicodeNumberString.split(",");
	    int hiddenKey = ConstantsField.hiddenKey;
	    int divisionKey = ConstantsField.divisionKey;
	    
	    //一文字ずつRSA暗号化
	    Long unicodeNumArray[] = new Long[unicodeNumberArray.length];
	    for(int i = 0; i < unicodeNumArray.length; i++) {
	    	
	    	//受け取った値（16進数）から10進数に変換し、Long型に代入
	    	Long unicodeNum = Long.parseLong(unicodeNumberArray[i],16);
	    	
	    	//余りを取得
	    	unicodeNumArray[i] = (unicodeNum * hiddenKey) % divisionKey;
	    	//割り算の答えを取得
	    	Long divisionNum = (unicodeNum * hiddenKey) / divisionKey;
	    	
	    	//切り分けのマーカーを付けて文字列生成
	    	String unicodeRSAString = String.valueOf(unicodeNumArray[i]).concat("G");
	    	String unicodeDivString = String.valueOf(divisionNum);
	    	String headString = "\\\\u";
	    	
	    	String changeString = headString.concat(unicodeRSAString);
	    	String changeStringInput = unicodeDivString.concat(changeString);
	    	unicodeNumberArray[i] = changeStringInput;
	    }
	    
	    //一文字ずつRSA暗号化した文字列をまとめる。
	    unicode = String.join("", unicodeNumberArray);
	    
	    //顔文字に変換
	    String unicodeslash = unicode.replaceAll("\\\\", "🤐");
	    String unicodeu = unicodeslash.replaceAll("u", "🌛");
	    String unicode0 = unicodeu.replaceAll("0", "😀");
	    String unicode1 = unicode0.replaceAll("1", "😁");
	    String unicode2 = unicode1.replaceAll("2", "😇");
	    String unicode3 = unicode2.replaceAll("3", "😈");
	    String unicode4 = unicode3.replaceAll("4", "😋");
	    String unicode5 = unicode4.replaceAll("5", "😌");
	    String unicode6 = unicode5.replaceAll("6", "😍");
	    String unicode7 = unicode6.replaceAll("7", "😒");
	    String unicode8 = unicode7.replaceAll("8", "😎");
	    String unicode9 = unicode8.replaceAll("9", "😤");
	    String unicodeA = unicode9.replaceAll("A", "😭");
	    String unicodeB = unicodeA.replaceAll("B", "😲");
	    String unicodeC = unicodeB.replaceAll("C", "🙃");
	    String unicodeD = unicodeC.replaceAll("D", "🤓");
	    String unicodeE = unicodeD.replaceAll("E", "🤪");
	    String unicodeF = unicodeE.replaceAll("F", "😰");
	    String unicodeG = unicodeF.replaceAll("G", "😵");

	    //検査文字を追加
	    String retString = "🤗";
	    
	    retString = retString.concat(unicodeG);

	    return retString;
	}
	
	
	
	/**
	 * 秘密鍵を使ってRSA暗号を復号
	 * @param unicode
	 * @return
	 */
	public String convertToOiginalRSA(String rsaString) {
		
		//顔文字から英数に変換
		String unicodeslash = rsaString.replaceAll("🤐", "\\\\");
		String unicodeu = unicodeslash.replaceAll("🌛", "u");
	    String unicode0 = unicodeu.replaceAll("😀", "0");
	    String unicode1 = unicode0.replaceAll("😁", "1");
	    String unicode2 = unicode1.replaceAll("😇", "2");
	    String unicode3 = unicode2.replaceAll("😈", "3");
	    String unicode4 = unicode3.replaceAll("😋", "4");
	    String unicode5 = unicode4.replaceAll("😌", "5");
	    String unicode6 = unicode5.replaceAll("😍", "6");
	    String unicode7 = unicode6.replaceAll("😒", "7");
	    String unicode8 = unicode7.replaceAll("😎", "8");
	    String unicode9 = unicode8.replaceAll("😤", "9");
	    String unicodeA = unicode9.replaceAll("😭", "A");
	    String unicodeB = unicodeA.replaceAll("😲", "B");
	    String unicodeC = unicodeB.replaceAll("🙃", "C");
	    String unicodeD = unicodeC.replaceAll("🤓", "D");
	    String unicodeE = unicodeD.replaceAll("🤪", "E");
	    String unicodeF = unicodeE.replaceAll("😰", "F");
	    String unicodeG = unicodeF.replaceAll("😵", "G");
	    String unicodeFace = unicodeG.replace("🤗","");
	    
	    //\\uをダブルゼータガンダムに変換
	    String unicodeNumberString = unicodeFace.replaceAll("\\\\\\\\u", "ZZ");
	    
	    //切り分けのマーカーで文字列を切り分け、配列に格納
	    String unicodeNumberArray[] = unicodeNumberString.split("G");
	    //秘密鍵と割る数を取得
	    int hiddenKey = ConstantsField.hiddenKey;
	    int divisionKey = ConstantsField.divisionKey;
	    
	    //一文字ずつ復号
	    Long unicodeNumArray[] = new Long[unicodeNumberArray.length];
	    for(int i = 0; i < unicodeNumArray.length; i++) {
	    	
	    	//ダブルゼータガンダムの前後で割り算の答えと余りに切り分け
	    	String changeArray[] = unicodeNumberArray[i].split("ZZ");
	    	
	    	//答えを取得
	    	Long divAns = Long.parseLong(changeArray[0]);
	    	//余りを取得
	    	Long unicodeNum = Long.parseLong(changeArray[1]);
	    	
	    	//元の数字に変換
	    	unicodeNumArray[i] = (divisionKey * divAns + unicodeNum) / hiddenKey;
	    	
	    	//unicodeに戻す
	    	String headString = "0x";
	    	String unicodeRSAString = Long.toHexString(unicodeNumArray[i]);
	    	String changeString = headString.concat(unicodeRSAString);
 	    	unicodeNumberArray[i] = changeString.replaceAll("0x", "\\\\u");
	    }
	    //一文字ずつ変換した文字をまとめる。
	    String unicode = String.join("", unicodeNumberArray);
		
		//unicodeを通常の文字列に変換
	    String[] codeStrs = unicode.split("\\\\u");
	    int[] codePoints = new int[codeStrs.length - 1]; // 最初が空文字なのでそれを抜かす
	    for (int i = 0; i < codePoints.length; i++) {
	        codePoints[i] = Integer.parseInt(codeStrs[i + 1], 16);
	    }
	    String encodedText = new String(codePoints, 0, codePoints.length);
	    return encodedText;
	}

}
