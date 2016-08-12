package hash;

import java.util.ArrayList;

/* データ格納クラス */
public class Model {
	private String key;
	private String value;

	Model(String key) {
		this.key = key;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	// String, Builder格納用
	public String getLine() {
		return key + "//" + value + "//";
	}

	// Object表示用
	public String getString() {
		return "名前：" + key + ", 性別：" + value;
	}

	// ハッシュコードを求める
	public int getHash() {
		return  key.hashCode();
	}

	// キー値を判定 重複していたらerr していなかったらリストに登録する
	public void doubleCheck(ArrayList<String> keyList, String key) throws OnemoreException {
		if (!keyList.contains(key))
			keyList.add(key);
		else
			throw new OnemoreException("エラー>キーが重複しています");
	}

}
