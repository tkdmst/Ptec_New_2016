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
		return key.hashCode();
	}

	// キー値を判定 重複しているならfalse いないならtrue
	public boolean doubleCheck(ArrayList<String> keyList, String key) {
		return (keyList.contains(key)) ? false : true;
	}

	public void listPlus(ArrayList<String> keyList, String key) {
		keyList.add(key);
	}

	public void listDel(ArrayList<String> keyList, String key) {
		keyList.remove(key);
	}

}
