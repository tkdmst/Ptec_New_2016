package hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class View {
	private BufferedReader keyInput;

	// input用の初期処理
	public void initialize() {
		keyInput = new BufferedReader(new InputStreamReader(System.in));
	}

	// 標準入力
	public String input() throws OnemoreException {
		String str = null;
		try {
			if (keyInput != null) {
				str = keyInput.readLine();
				if (str.indexOf(",") == 0 || str.indexOf("/") == 0
						|| str.indexOf("\\") == 0 || str.indexOf("\"") == 0)
					throw new OnemoreException("エラー>記号は使えません");
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new OnemoreException("エラー>予期しないエラーが発生しました");
		}
		return str;
	}
}
