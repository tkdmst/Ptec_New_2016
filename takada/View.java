package socDB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Viewクラスは表示と入力補助をしています。
 */
public class View {
	private BufferedReader keybo;

	/**
	 * initializeではこのクラスの初期処理をしています。
	 */
	public void initialize() {
		keybo = new BufferedReader(new InputStreamReader(System.in));
	}

	/**
	 * displayメソッドではモデルクラスに格納されたデータを表示します。<br>
	 *
	 * @param model
	 *            モデルクラスのデータ
	 */
	public void display(Model model) {
		System.out.println(model.toString());
	}

<<<<<<< HEAD
	// keyboから読み込み
=======
	/**
	 * inputメソッドは標準入力された文字を読み込み、モデルクラスへ格納しています。<br>
	 * 尚、文字数が255を超えると終了します。
	 *
	 * @return Model(str) 読み込んだ文字をモデル型に格納
	 * @throws AccessException
	 *             エラー時にメッセージをつけて処理
	 */
>>>>>>> origin/master
	public Model input() throws AccessException {
		String str = null;
		try {
			if (keybo != null)
				str = keybo.readLine();
<<<<<<< HEAD
		} catch (IOException e) {
			e.printStackTrace();
			throw new AccessException();
=======
				if (str.length() > 255)
					throw new AccessException("文字列が長すぎます");
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new AccessException("致命的エラーです");
>>>>>>> origin/master
		}
		return new Model(str);
	}
}
