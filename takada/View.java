package socDB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class View {
	private BufferedReader keybo;

	// keyboの処理関数 初期処理
	public void initialize() {
		keybo = new BufferedReader(new InputStreamReader(System.in));
	}

	// 表示 Modelクラスを利用可能に
	public void display(Model model) {
		System.out.println(model.toString());
	}

	// keyboから読み込み
	public Model input() throws AccessException {
		String str = null;
		try {
			if (keybo != null)
				str = keybo.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			throw new AccessException();
		}
		return new Model(str);
	}
}
